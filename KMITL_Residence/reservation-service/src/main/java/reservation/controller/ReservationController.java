package reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import reservation.exception.UnauthorizedException;
import reservation.jwt.JwtService;
import reservation.model.*;
import reservation.repository.ReservationRepository;

@RestController
@CrossOrigin(origins = "*")
public class ReservationController {

    /*
        --------------------------------- Utilities ---------------------------------
    */
    private final ReservationRepository reservationRepository;

    @Autowired
    private JwtService service;

    @Autowired
    public ReservationController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    /*
        --------------------------------- [GET] View reservation by reservation_id ---------------------------------
    */
    @RequestMapping("/reservation/{reservation_id}")
    public ReservationDetail getReservation(@PathVariable String reservation_id,
                                            @RequestHeader(value = "authenticate-token") String token) {
        if(token == null) {
            throw new UnauthorizedException();
        }
        try {
            JwtUser user = service.getUser(token);
        } catch (Exception ex) {
            throw new UnauthorizedException();
        }
        return this.reservationRepository.getReservation(Integer.valueOf(reservation_id));
    }

    /*
        --------------------------------- [POST] Add reservation ---------------------------------
    */
    @RequestMapping(value = "/reservation/add",method = RequestMethod.POST)
    public ResponseEntity<ResultMessage> saveReservation(@RequestBody Reservation reservation) {
        ResultMessage resultMessage = null;

        if(reservation != null) {
            int id = reservationRepository.saveReservation(reservation);
            resultMessage = new ResultMessage(id, "Success");
        }
        return new ResponseEntity(resultMessage, HttpStatus.CREATED);
    }

    /*
        --------------------------------- [PUT] Confirm reservation ---------------------------------
    */
    @RequestMapping(value = "/reservation/{reservation_id}/confirm", method = RequestMethod.PUT)
    public ResponseEntity<ResultMessage> confirmReservation(@PathVariable String reservation_id,
                                                            @RequestParam(value = "id", required = true) String id) {
        ResultMessage resultMessage = new ResultMessage(Integer.valueOf(reservation_id), "Success");
        reservationRepository.confirmReservation(Integer.valueOf(reservation_id), id);
        return new ResponseEntity(resultMessage, HttpStatus.OK);
    }

    /*
        --------------------------------- [PUT] Cancel reservation ---------------------------------
    */
    @RequestMapping(value = "/reservation/{reservation_id}/cancel", method = RequestMethod.PUT)
    public ResponseEntity<ResultMessage> cancelReservation(@PathVariable String reservation_id,
                                                           @RequestParam(value = "id", required = true) String id) {
        ResultMessage resultMessage = new ResultMessage(Integer.valueOf(reservation_id), "Success");
        reservationRepository.cancelReservation(Integer.valueOf(reservation_id), id);
        return new ResponseEntity(resultMessage, HttpStatus.OK);
    }

    /*
        --------------------------------- [PUT] Save partial checkout ---------------------------------
    */
    @RequestMapping(value = "/reservation/{reservation_id}/partialCheckout", method = RequestMethod.PUT)
    public ResponseEntity<ResultMessage> updatePartialCheckout(@PathVariable String reservation_id) {
        ResultMessage resultMessage = new ResultMessage(Integer.valueOf(reservation_id), "Success");
        reservationRepository.updatePartialCheckout(Integer.valueOf(reservation_id));
        return new ResponseEntity(resultMessage, HttpStatus.OK);
    }

    /*
        --------------------------------- [PUT] Available Room(s) ---------------------------------
    */
    @RequestMapping(value = "/reservation/availableSearch", method = RequestMethod.GET)
    public List<AvailableRoomsType> availableSearch(@RequestParam(value = "checkin", required = true) String checkin,
                                                    @RequestParam(value = "checkout", required = true) String checkout,
                                                    @RequestParam(value = "adults", required = true) int adults,
                                                    @RequestParam(value = "children", required = true) int children) {
            List<AvailableRoomsType> availableRoomsTypes = reservationRepository.searchAvailable(checkin, checkout, adults, children);
            return availableRoomsTypes;
    }
}