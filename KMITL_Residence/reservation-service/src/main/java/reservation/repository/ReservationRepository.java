package reservation.repository;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import java.security.MessageDigest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import reservation.exception.*;
import reservation.mapper.AvailableRoomsTypeRowMapper;
import reservation.mapper.ReservationDetailRowMapper;
import reservation.mapper.ReservationRowMapper;
import reservation.model.*;

@Repository
public class ReservationRepository {

    /*
        --------------------------------- Utilities ---------------------------------
    */
    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private Environment env;

    public ReservationRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /*
        --------------------------------- 1. Get ReservationDetail by reservation_id ---------------------------------
    */
    @Transactional(readOnly = true)
    public ReservationDetail getReservation(int reservation_id) {
        ReservationDetail reservation = null;
        final String SELECT_SQL = "select r.reservation_id, r.reservation_date, r.reservation_checkout, r.reservation_adults, " +
                "r.reservation_children, s.status_description, r.reservation_partial, r.reservation_timestamp,r.room_type, " +
                "r.customer_title_name, r.customer_full_name, r.customer_email, r.customer_tel, r.customer_country, " +
                "r.customer_nation " +
                "from reservation r " +
                "join reservation_status s ON r.reservation_status = s.status_id " +
                "where reservation_id=?;";
        try {
            reservation = jdbc.queryForObject(SELECT_SQL, new Object[]{reservation_id}, new ReservationDetailRowMapper());
        } catch(Exception ex) {
            throw new NotFoundException(reservation_id);
        }
        return reservation;
    }

    /*
        --------------------------------- 2. Get full Reservation object by reservation_id ---------------------------------
    */
    @Transactional(readOnly = true)
    public Reservation getFullReservation(int reservation_id) {
        Reservation reservation = null;
        final String SELECT_SQL = "select reservation_id, reservation_date, reservation_checkout, reservation_adults, " +
                "reservation_children, reservation_status, reservation_partial, reservation_timestamp, room_type, " +
                "customer_title_name, customer_full_name, customer_email, customer_tel, customer_country, customer_nation, " +
                "credit_card_id, credit_card_holder_name, credit_card_type, credit_card_expired_date, credit_card_cvc " +
                "from reservation " +
                "where reservation_id=?";
        try {
            reservation = jdbc.queryForObject(SELECT_SQL, new Object[]{reservation_id}, new ReservationRowMapper());
        } catch(Exception ex) {
            throw new NotFoundException(reservation_id);
        }
        return reservation;
    }

    /*
        --------------------------------- 3. Save Reservation object ---------------------------------
    */
    @Transactional
    public int saveReservation(Reservation reservation) {
        int id;
        String[] txtStart = reservation.getCheckIn().split("-");
        String[] txtEnd = reservation.getCheckOut().split("-");
        //set date in joda datetime
        DateTime start = new DateTime(Integer.valueOf(txtStart[0]),
                Integer.valueOf(txtStart[1]),
                Integer.valueOf(txtStart[2]),
                0, 0, 0, 0);
        DateTime end = new DateTime(Integer.valueOf(txtEnd[0]),
                Integer.valueOf(txtEnd[1]),
                Integer.valueOf(txtEnd[2]),
                0, 0, 0, 0);
        DateTime current = new DateTime();

        List<AvailableRoomsType> availableRoomsTypes = searchAvailable(reservation.getCheckIn(), reservation.getCheckOut(),
                reservation.getAdults(), reservation.getChildren());

        if(start.getMillis() > end.getMillis() || start.getMillis() < current.getMillis()) {
            //throw exception when check-in > checkout or check-in < current date
            throw new DateException(reservation.getCheckIn(), reservation.getCheckOut());
        }
        else if(!isAvailable(reservation, availableRoomsTypes)) {
            throw new RoomTypeNotAvailableException();
        }
        else {
            final String INSERT_SQL = "INSERT INTO reservation (reservation_date, reservation_checkout, " +
                    "reservation_adults, reservation_children, " +
                    "room_type, customer_title_name, customer_full_name, customer_email, " +
                    "customer_tel, customer_country, customer_nation, " +
                    "credit_card_id, credit_card_holder_name, credit_card_type, credit_card_expired_date, credit_card_cvc) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            int result = jdbc.update(INSERT_SQL,
                    reservation.getCheckIn(),
                    reservation.getCheckOut(),
                    reservation.getAdults(),
                    reservation.getChildren(),
                    reservation.getRoomType(),
                    reservation.getCustomer().getTitleName(),
                    reservation.getCustomer().getFullName(),
                    reservation.getCustomer().getEmail(),
                    reservation.getCustomer().getTel(),
                    reservation.getCustomer().getCountry(),
                    reservation.getCustomer().getNation(),
                    reservation.getCreditCard().getNumber(),
                    reservation.getCreditCard().getHolderName(),
                    reservation.getCreditCard().getType(),
                    reservation.getCreditCard().getExpiredDate(),
                    reservation.getCreditCard().getCvc());
            if (result < 0) {
                throw new AddReservationFailedException();
            } else {
                Interval interval = new Interval(start, end);
                int days = daysBetween(interval.getStartMillis(), interval.getEndMillis());

                RestTemplate template = new RestTemplate();
                String url = env.getProperty("url.rooms.type") + reservation.getRoomType();
                RoomType roomType = template.getForObject(url, RoomType.class);

                Email email = new Email();
                email.setTitleName(reservation.getCustomer().getTitleName());
                email.setFullName(reservation.getCustomer().getFullName());
                email.setDestination(reservation.getCustomer().getEmail());
                email.setEmailType(1);

                id = getLastInsertId();
                Reservation rs = getFullReservation(id);

                String confirmLink = env.getProperty("url.link") + rs.getId() + "/confirm?id=" + getGenerateId(rs);
                String cancelLink = env.getProperty("url.link") + rs.getId() + "/cancel?id=" + getGenerateId(rs);

                Content content = new Content();
                content.setRoomType(reservation.getRoomType());
                content.setReservationId(rs.getId());
                content.setTotalPrice((int) (days * roomType.getTypePrice()));
                content.setTotalNight(days);
                content.setConfirmationLink(confirmLink);
                content.setCancelLink(cancelLink);

                email.setContent(content);
                sendEmail(email);
            }
        }
        return id;
    }

    /*
        --------------------------------- 4. Cancel reservation by reservation_id and cancel_id ---------------------------------
    */
    @Transactional
    public void cancelReservation(int reservation_id, String cancel_id) {
        Reservation rs = getFullReservation(reservation_id);

        if(cancel_id.equals(getGenerateId(rs))) {
            if (rs.getStatus() != 3) {
                final String UPDATE_SQL = "update reservation set reservation_status=3 where reservation_id=?;";
                int result = this.jdbc.update(UPDATE_SQL, reservation_id);
                if (result < 0) {
                    throw new CancelFailedException(reservation_id);
                } else {
                    Reservation reservation = getFullReservation(reservation_id);

                    Email email = new Email();
                    email.setTitleName(reservation.getCustomer().getTitleName());
                    email.setFullName(reservation.getCustomer().getFullName());
                    email.setDestination(reservation.getCustomer().getEmail());
                    email.setEmailType(3);

                    Content content = new Content();
                    content.setRoomType(reservation.getRoomType());
                    content.setReservationId(reservation.getId());
                    content.setConfirmationLink(getGenerateId(reservation));

                    email.setContent(content);
                    sendEmail(email);
                }
            } else {
                throw new CancelDeniedException(reservation_id);
            }
        }
        else {
            throw new CancelIdNotMatchException(reservation_id);
        }
    }

    /*
        --------------------------------- 5. Confirm reservation by reservation_id and confirm_id ---------------------------------
    */
    @Transactional
    public void confirmReservation(int reservation_id, String confirm_id) {
        Reservation rs = getFullReservation(reservation_id);
        if(confirm_id.equals(getGenerateId(rs))) {
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
                    int result = this.jdbc.update(sql, reservation_id);
                    if (result < 0) {
                        throw new ConfirmFailedException(reservation_id);
                    } else {
                        ReservationDetail reservation = getReservation(reservation_id);

                        Email email = new Email();
                        email.setTitleName(reservation.getCustomer().getTitleName());
                        email.setFullName(reservation.getCustomer().getFullName());
                        email.setDestination(reservation.getCustomer().getEmail());
                        email.setEmailType(2);

                        Content content = new Content();
                        content.setRoomType(reservation.getRoomType());
                        content.setReservationId(reservation.getId());

                        email.setContent(content);
                        sendEmail(email);
                    }
                } else {
                    throw new ConfirmDeniedException(reservation_id);
                }
            } else {
                throw new RoomTypeNotAvailableException();
            }
        }
        else {
            throw new ConfirmIDNotMatchException(reservation_id);
        }
    }

    /*
        --------------------------------- 6. Update partial check-out by reservation_id ---------------------------------
    */
    @Transactional
    public void updatePartialCheckout(int reservation_id) {
        ReservationDetail rs = getReservation(reservation_id);
        if(rs.getStatus().equals("completed")) {
            final String UPDATE_SQL = "update reservation set reservation_partial = 1 where reservation_id = ?";
            int result = this.jdbc.update(UPDATE_SQL, reservation_id);
            if(result < 0) {
                throw new PartialCheckoutFailedException(reservation_id);
            }
        }
        else {
            throw new PartialCheckoutDeniedException(reservation_id);
        }
    }

    /*
        --------------------------------- 7. Cancel reservations ---------------------------------
    */
    @Transactional
    public void cancelReservations() {
        final String UPDATE_SQL = "update reservation set reservation_status=3 where reservation_status=1;";
        jdbc.update(UPDATE_SQL);
    }

    /*
        --------------------------------- 8. Get List of available room by checkin, checkout, adults, and children ---------------------------------
    */
    @Transactional(readOnly = true)
    public List<AvailableRoomsType> searchAvailable(String checkin, String checkout, int adults, int children) {
        String checkIn = checkin.replace("'", "");
        String checkOut = checkout.replace("'", "");

        RestTemplate template = new RestTemplate();
        String url = env.getProperty("url.rooms.types") + "adults=" + adults + "&children=" + children;
        ResponseEntity<List<RoomType>> responseEntity = template.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RoomType>>() { });

        List<RoomType> roomTypes = responseEntity.getBody();

        HashSet<Integer> tempId = new HashSet<>();
        for(RoomType roomType: roomTypes) {
            tempId.add(roomType.getTypeId());
        }
        String whereIn = tempId.toString().replace("[", "(").replace("]", ")");

        String sql = "select room_type, count(reservation_id) as total from reservation " +
                "where ((reservation_date >= '" + checkIn + "' and reservation_date <= '" + checkOut + "') or " +
                "(reservation_checkout <= '" + checkIn + "' and reservation_checkout >= '" + checkOut + "')) and " +
                "reservation_status != 3 and room_type in " + whereIn + " and reservation_partial = 0" +
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

    /*
        --------------------------------- 9. Generate ID by Reservation object ---------------------------------
    */
    public String getGenerateId(Reservation reservation) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String textToHash = String.valueOf(reservation.getId())+reservation.getCustomer().getEmail() + reservation.getTimestamp(); // ex. reservation_id = 24 -> "24kmitlresidence@gmail.com"
            md.update(textToHash.getBytes("UTF-8"));
            byte[] digest = md.digest();
            String confirmation_id = String.format("%064x", new java.math.BigInteger(1, digest)).substring(0, 30); // slice string from 64 to 32 chars (reduce)
            return confirmation_id;
        } catch (Exception e) {
            System.err.println("exception in 'getGenerateId' method");
        }
        return "";
    }

    /*
        --------------------------------- 10. Get last insertion ID ---------------------------------
    */
    public int getLastInsertId() {
        final String SELECT_SQL = "select reservation_id from reservation order by reservation_id desc limit 1;";
        return jdbc.queryForObject(SELECT_SQL, Integer.class);
    }

    /*
        --------------------------------- 11. Get day between two arguments ---------------------------------
    */
    public int daysBetween(long t1, long t2) {
        return (int) ((t2 - t1) / (1000 * 60 * 60 * 24));
    }

    /*
        --------------------------------- 12. Send E-mail ---------------------------------
    */
    public void sendEmail(Email email) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ObjectMapper mapper = new ObjectMapper();
                try {
                    RestTemplate template = new RestTemplate();
                    String url = env.getProperty("url.sendmail");
                    String json = mapper.writeValueAsString(email);
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    HttpEntity<String> entity = new HttpEntity<>(json, headers);
                    template.exchange(url, HttpMethod.POST, entity, String.class);

                } catch (JsonProcessingException ex) {
                    throw new EmailSendingFailedException(email.getDestination());
                }
                return;
            }
        }).start();
    }

    /*
        --------------------------------- 13. Get available status of reservation ---------------------------------
    */
    public boolean isAvailable(Reservation reservation, List<AvailableRoomsType> availableRoomsTypes) {
        boolean result = false;
        for(AvailableRoomsType availableRoomsType: availableRoomsTypes) {
            if(reservation.getRoomType() == availableRoomsType.getRoomType()) {
                result = true;
                break;
            }
        }
        return result;
    }
}