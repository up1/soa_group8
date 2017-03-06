package reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Adisorn on 1/3/2560.
 */
@RestController
@CrossOrigin(origins = "*")
public class ReservationController {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @RequestMapping("/reservation/{reservation_id}")
    public ReservationDetail getReservation(@PathVariable String reservation_id) {
        return this.reservationRepository.getReservation(Integer.valueOf(reservation_id));
    }

    @RequestMapping(value = "/reservation/add",method = RequestMethod.POST)
    public ResponseEntity saveReservation(@RequestBody Reservation reservation) {
        reservationRepository.saveReservation(reservation);
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
