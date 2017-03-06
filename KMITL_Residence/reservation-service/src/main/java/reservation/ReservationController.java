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

    @GetMapping("/reservations")
    public List<Reservation> getReservations() {

        return this.reservationRepository.getReservation();

    }

    @RequestMapping(value = "/reservation/add",method = RequestMethod.POST)
    public ResponseEntity saveReservation(@RequestBody Reservation reservation) {
        if(reservation != null) {
            reservationRepository.saveReservation(reservation);
        }
        else {
            new NotFoundException();
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
