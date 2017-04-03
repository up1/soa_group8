package reservation.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reservation.repository.ReservationRepository;

/**
 * Created by Adisorn on 3/4/2560.
 */
@Component
public class CancelReservationTask {

    @Autowired
    private ReservationRepository reservationRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public void cancelReservations() {
        reservationRepository.cancelReservations();
        System.out.println("Cancel");
    }

}
