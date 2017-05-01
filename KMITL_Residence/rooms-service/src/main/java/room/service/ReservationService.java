package room.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import room.exception.ReservationNotFoundException;
import room.model.Reservation;
import room.model.ReservationMessage;

/**
 * Created by Adisorn on 1/5/2560.
 */
@Service
public class ReservationService {

    @Autowired
    private RestTemplate template;

    @Value("${url.reservation.service}")
    String reservationURL;

    @Value("${authentication.token.header}")
    String authTokenHeader;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public Reservation getReservation(int reservationId, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(authTokenHeader, token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        Reservation reservation;
        try {
            ResponseEntity<Reservation> reservationResponseEntity =
                    template.exchange(reservationURL + reservationId + "/", HttpMethod.GET, entity, Reservation.class);
            reservation = reservationResponseEntity.getBody();
        }catch(Exception e) {
            throw new ReservationNotFoundException(reservationId);
        }
        return reservation;
    }

    public void partialCheckout(int reservationId, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(authTokenHeader, token);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        template.exchange(reservationURL + reservationId + "/partialCheckout", HttpMethod.PUT, entity, ReservationMessage.class);

    }
}
