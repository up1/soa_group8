package reservation.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import reservation.exception.*;
import reservation.mapper.AvailableRoomsTypeRowMapper;
import reservation.mapper.ReservationDetailRowMapper;
import reservation.mapper.ReservationRowMapper;
import reservation.model.*;

import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

        String sql = "select r.reservation_id, r.reservation_date, r.reservation_checkout, r.reservation_adults, " +
                "r.reservation_children, s.status_description, r.reservation_partial, r.reservation_timestamp,r.room_type, " +
                "r.customer_title_name, r.customer_full_name, r.customer_email, r.customer_tel, r.customer_country, " +
                "r.customer_nation " +
                "from reservation r " +
                "join reservation_status s ON r.reservation_status = s.status_id " +
                "where reservation_id=?;";
        try {
            reservation = jdbc.queryForObject(sql, new Object[]{reservation_id}, new ReservationDetailRowMapper());
        } catch(Exception ex) {
            ex.printStackTrace();
            throw new NotFoundException(reservation_id);
        }

        return reservation;
    }

    private Reservation getFullReservation(int reservation_id) {
        Reservation reservation = new Reservation();
        String sql = "select reservation_id, reservation_date, reservation_checkout, reservation_adults, " +
                "reservation_children, reservation_status, reservation_partial, reservation_timestamp, room_type, " +
                "customer_title_name, customer_full_name, customer_email, customer_tel, customer_country, customer_nation, " +
                "credit_card_id, credit_card_type, credit_card_expired_date, credit_card_cvv " +
                "from reservation " +
                "where reservation_id=?";
        try {
            reservation = jdbc.queryForObject(sql, new Object[]{reservation_id}, new ReservationRowMapper());
        } catch(Exception ex) {
            ex.printStackTrace();
            throw new NotFoundException(reservation_id);
        }
        return reservation;
    }

    @Transactional
    public void saveReservation(Reservation reservation) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        String sql = "INSERT INTO reservation (reservation_date, reservation_checkout, " +
                "reservation_adults, reservation_children, reservation_status, " +
                "room_type, customer_title_name, customer_full_name, customer_email, " +
                "customer_tel, customer_country, customer_nation, " +
                "credit_card_id, credit_card_type, credit_card_expired_date, credit_card_cvv) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        int result = jdbc.update(sql,
                reservation.getCheckIn(),
                reservation.getCheckOut(),
                reservation.getAdults(),
                reservation.getChildren(),
                reservation.getStatus(),
                reservation.getRoomType(),
                reservation.getCustomer().getTitleName(),
                reservation.getCustomer().getFullName(),
                reservation.getCustomer().getEmail(),
                reservation.getCustomer().getTel(),
                reservation.getCustomer().getCountry(),
                reservation.getCustomer().getNation(),
                reservation.getCreditCard().getNumber(),
                reservation.getCreditCard().getType(),
                reservation.getCreditCard().getExpiredDate(),
                reservation.getCreditCard().getCvv());
        if (result < 0) {
            throw new AddReservationFailedException();
        }
        else {

            String[] txtStart = reservation.getCheckIn().split("-");
            String[] txtEnd = reservation.getCheckOut().split("-");

            DateTime start = new DateTime(Integer.valueOf(txtStart[0]),
                    Integer.valueOf(txtStart[1]),
                    Integer.valueOf(txtStart[2]),
                    12, 0, 0, 0);
            DateTime end = new DateTime(Integer.valueOf(txtEnd[0]),
                    Integer.valueOf(txtEnd[1]),
                    Integer.valueOf(txtEnd[2]),
                    12, 0, 0, 0);
            Interval interval = new Interval(start, end);
            int days = daysBetween(interval.getStartMillis(), interval.getEndMillis());

            RestTemplate template = new RestTemplate();
            RoomType roomType = template.getForObject("http://localhost:9001/rooms/type/" + reservation.getRoomType(), RoomType.class);

            Email email = new Email();
            email.setFullName(reservation.getCustomer().getTitleName() + reservation.getCustomer().getFullName());
            email.setDestination(reservation.getCustomer().getEmail());
            email.setEmailType(1);

            Reservation rs = getFullReservation(getLastInsertId());

            Content content = new Content();
            content.setRoomType(reservation.getRoomType());
            content.setReservationId(rs.getId());
            content.setTotal((int)(days * roomType.getTypePrice()));
            content.setConfirmationLink("http://localhost:9000/reservation/" + rs.getId() + "/confirm?id=" + getConfirmationId(rs));

            email.setContent(content);
            sendEmail(email);

        }
    }

    @Transactional
    public void cancelReservation(int reservationId) {
        ReservationDetail rs = getReservation(reservationId);
        if(rs.getStatus().equals("completed")) {
            String sql = "update reservation set reservation_status=3 where reservation_id=?;";
            int result = this.jdbc.update(sql, reservationId);
            if (result < 0) {
                throw new CancelFailedException(reservationId);
            }
            else {
                Reservation reservation = getFullReservation(reservationId);

                Email email = new Email();
                email.setFullName(reservation.getCustomer().getTitleName() + reservation.getCustomer().getFullName());
                email.setDestination(reservation.getCustomer().getEmail());
                email.setEmailType(3);

                Content content = new Content();
                content.setRoomType(reservation.getRoomType());
                content.setReservationId(reservation.getId());
                content.setTotal((int)(0));
                content.setConfirmationLink(getConfirmationId(reservation));

                email.setContent(content);
                sendEmail(email);
            }
        }
        else {
            throw new CancelDeniedException(reservationId);
        }

    }

    @Transactional
    public void confirmReservation(int reservationId, String confirmId) {
        Reservation rs = getFullReservation(reservationId);
        if(confirmId.equals(getConfirmationId(rs))) {

            List<AvailableRoomsType> roomsTypes = searchAvailable(rs.getCheckIn(), rs.getCheckOut(), rs.getAdults(), rs.getChildren());
            boolean checkRoomType = false;
            for(AvailableRoomsType roomsType: roomsTypes) {
                if(roomsType.getRoomType() == rs.getRoomType()) {
                    checkRoomType = true;
                    break;
                }
            }

            if (roomsTypes.size() > 0 && checkRoomType) {

                if (rs.getStatus() == 1) {

                    String sql = "update reservation set reservation_status=2 where reservation_id=?;";
                    int result = this.jdbc.update(sql, reservationId);
                    if (result < 0) {
                        throw new ConfirmFailedException(reservationId);
                    } else {
                        ReservationDetail reservation = getReservation(reservationId);

                        Email email = new Email();
                        email.setFullName(reservation.getCustomer().getTitleName() + reservation.getCustomer().getFullName());
                        email.setDestination(reservation.getCustomer().getEmail());
                        email.setEmailType(2);

                        Content content = new Content();
                        content.setRoomType(reservation.getRoomType());
                        content.setReservationId(reservation.getId());

                        email.setContent(content);
                        sendEmail(email);
                    }
                } else {
                    throw new ConfirmDeniedException(reservationId);
                }
            } else {
                throw new RoomTypeNotAvailableException();
            }
        }
        else {
            System.out.println(confirmId);
            throw new ConfirmIDNotMatchException(reservationId);
        }

    }

    @Transactional
    public void updatePartialCheckout(int reservationId) {
        ReservationDetail rs = getReservation(reservationId);
        if(rs.getStatus().equals("completed")) {
            String sql = "update reservation set reservation_partial = 1 where reservation_id = ?";
            int result = this.jdbc.update(sql, reservationId);
            if(result < 0) {
                throw new PartialCheckoutFailedException(reservationId);
            }
        }
        else {
            throw new PartialCheckoutDeniedException(reservationId);
        }

    }

    @Transactional(readOnly = true)
    public List<AvailableRoomsType> searchAvailable(String checkin, String checkout, int adults, int children) {

        RestTemplate template = new RestTemplate();

        ResponseEntity<List<RoomType>> responseEntity = template.exchange(
                "http://localhost:9001/rooms/types?adults=" + adults + "&children=" + children,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<RoomType>>() { });

        List<RoomType> roomTypes = responseEntity.getBody();

        HashSet<Integer> tempId = new HashSet<>();
        for(RoomType roomType: roomTypes) {
            tempId.add(roomType.getTypeId());
        }
        String whereIn = tempId.toString().replace("[", "(").replace("]", ")");

        String sql = "select room_type, count(reservation_id) as total from reservation " +
                "where ((reservation_date >= '" + checkin + "' and reservation_date <= '" + checkout + "') or " +
                "(reservation_checkout <= '" + checkin + "' and reservation_checkout >= '" + checkout + "')) and " +
                "reservation_status = 2 and room_type in " + whereIn + " and reservation_partial = 0" +
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

    private String getConfirmationId(Reservation reservation) {
        String id = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String textToHash = String.valueOf(reservation.getId())+reservation.getCustomer().getEmail() + reservation.getTimestamp(); // ex. reservation_id = 24 -> "24kmitlresidence@gmail.com"
            md.update(textToHash.getBytes("UTF-8"));
            byte[] digest = md.digest();
            String confirmation_id = String.format("%064x", new java.math.BigInteger(1, digest)).substring(0, 30); // slice string from 64 to 32 chars (reduce)
            id = confirmation_id;
            System.out.println(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    private int getLastInsertId() {
        String sql = "select reservation_id from reservation order by reservation_id desc limit 1;";
        return jdbc.queryForObject(sql, Integer.class);
    }

    private int daysBetween(long t1, long t2) {
        return (int) ((t2 - t1) / (1000 * 60 * 60 * 24));
    }

    private void sendEmail(Email email) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            RestTemplate template = new RestTemplate();

            String json = mapper.writeValueAsString(email);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<String>(json, headers);
            template.exchange("http://localhost:9003/sendmail", HttpMethod.POST, entity, String.class);

        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
    }

}
