package reservation.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import reservation.exception.*;
import reservation.mapper.AvailableRoomsTypeRowMapper;
import reservation.mapper.ReservationDetailRowMapper;
import reservation.model.AvailableRoomsType;
import reservation.model.Reservation;
import reservation.model.ReservationDetail;
import reservation.model.RoomType;

import java.util.*;

/**
 * Created by Adisorn on 1/3/2560.
 */
@Repository
public class ReservationRepository {

    @Autowired
    private JdbcTemplate jdbc;

    public ReservationRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Transactional(readOnly = true)
    public ReservationDetail getReservation(int reservation_id) {
        ReservationDetail reservation = null;

        String sql = "select r.reservation_id, r.reservation_date, r.reservation_checkout, r.reservation_adult, " +
                "r.reservation_children, s.status_description, p.payment_type_description, r.room_type, " +
                "r.customer_title_name, r.customer_full_name, r.customer_email, r.customer_tel, r.customer_country, " +
                "r.customer_nation " +
                "from reservation r " +
                "join reservation_status s ON r.reservation_status = s.status_id " +
                "join payment_type p ON r.reservation_payment_type = p.payment_type_id " +
                "where reservation_id=?;";
        try {
            reservation = jdbc.queryForObject(sql, new Object[]{reservation_id}, new ReservationDetailRowMapper());
        } catch(Exception ex) {
            ex.printStackTrace();
            throw new NotFoundException(reservation_id);
        }

        return reservation;
    }



    @Transactional
    public void saveReservation(Reservation reservation) {

        if(searchAvailable(reservation.getCheckIn(), reservation.getCheckOut(),
                reservation.getAdults(), reservation.getChildren()).size() > 0) {

            String sql = "insert into reservation values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            int result = jdbc.update(sql, reservation.getCheckIn(),
                    reservation.getCheckOut(),
                    reservation.getAdults(),
                    reservation.getChildren(),
                    reservation.getStatus(),
                    reservation.getPaymentType(),
                    reservation.getRoomType(),
                    reservation.getCustomer().getTitleName(),
                    reservation.getCustomer().getFullName(),
                    reservation.getCustomer().getEmail(),
                    reservation.getCustomer().getTel(),
                    reservation.getCustomer().getCountry(),
                    reservation.getCustomer().getNation(),
                    reservation.getCreditCard().getNumber(),
                    reservation.getCreditCard().getExpiredDate(),
                    reservation.getCreditCard().getCvv());
            if (result < 0) {
                throw new AddReservationFailedException();
            }
        }
        else {
            throw new RoomTypeNotAvailableException();
        }
    }

    @Transactional
    public void cancelReservation(int reservation_id) {
        ReservationDetail rs = getReservation(reservation_id);
        if(rs.getStatus().equals("completed")) {
            String sql = "update reservation set reservation_status=3 where reservation_id=?;";
            int result = this.jdbc.update(sql, reservation_id);
            if (result < 0) {
                throw new CancelFailedException(reservation_id);
            }
        }
        else {
            throw new CancelDeniedException(reservation_id);
        }

    }

    @Transactional
    public void confirmReservation(int reservation_id) {

        if(getReservation(reservation_id).getStatus().equals("waiting")) {

            String sql = "update reservation set reservation_status=2 where reservation_id=?;";
            int result = this.jdbc.update(sql, reservation_id);
            if(result < 0) {
                throw new ConfirmFailedException(reservation_id);
            }
        }
        else {
            throw new ConfirmDeniedException(reservation_id);
        }

    }

    @Transactional(readOnly = true)
    public List<AvailableRoomsType> searchAvailable(String checkin, String checkout, int adults, int children) {

        RestTemplate template = new RestTemplate();

        ResponseEntity<List<RoomType>> responseEntity = template.exchange("http://localhost:9001/rooms/types/" + adults + "/" + children,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<RoomType>>() { });

        List<RoomType> roomTypes = responseEntity.getBody();

        HashSet<Integer> tempId = new HashSet<>();
        for(RoomType roomType: roomTypes) {
            tempId.add(roomType.getTypeId());
        }
        String whereIn = tempId.toString().replace("[", "(").replace("]", ")");

        String sql = "select room_type, count(reservation_id) as total from reservation " +
                "where ((reservation_date >= " + checkin + " and reservation_date <= " + checkout + ") or " +
                "(reservation_checkout <= " + checkin + " and reservation_checkout >= " + checkout + ")) and " +
                "reservation_status = 2 and room_type in " + whereIn +
                " GROUP BY room_type;";

        List<AvailableRoomsType> available = this.jdbc.query(sql, new AvailableRoomsTypeRowMapper());
        List<AvailableRoomsType> tempRoom = new ArrayList<>();

        for(AvailableRoomsType availableRoomsType: available) {
            for(RoomType roomType: roomTypes) {
                if(availableRoomsType.getRoomType() == roomType.getTypeId()) {
                    if(availableRoomsType.getTotal() < roomType.getTypeTotalRooms()) {
                        availableRoomsType.setTotal(roomType.getTypeTotalRooms() - availableRoomsType.getTotal());
                    }
                    else {
                        tempRoom.add(availableRoomsType);
                    }
                    break;
                }
            }
        }

        for(RoomType roomType: roomTypes) {
            boolean noReservationInRoomType = true;
            for(AvailableRoomsType availableRoomsType: available) {
                if(roomType.getTypeId() == availableRoomsType.getRoomType()) {
                    noReservationInRoomType = false;
                    break;
                }
            }

            if(noReservationInRoomType) {
                AvailableRoomsType ar = new AvailableRoomsType();
                ar.setRoomType(roomType.getTypeId());
                ar.setTotal(roomType.getTypeTotalRooms());
                available.add(ar);
            }

        }

        for(AvailableRoomsType availableRoomsType: tempRoom) {
            available.remove(availableRoomsType);
        }

        Collections.sort(available, new Comparator<AvailableRoomsType>() {
            @Override
            public int compare(AvailableRoomsType ar1, AvailableRoomsType ar2) {
                return ar1.getRoomType() - ar2.getRoomType();
            }
        });

        return available;

    }

}
