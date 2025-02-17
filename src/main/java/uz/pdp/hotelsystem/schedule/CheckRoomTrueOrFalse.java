package uz.pdp.hotelsystem.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import uz.pdp.hotelsystem.controller.ApiResult;
import uz.pdp.hotelsystem.entity.BookingRoom;
import uz.pdp.hotelsystem.entity.Room;
import uz.pdp.hotelsystem.repository.BookingRepository;
import uz.pdp.hotelsystem.repository.RoomRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@EnableScheduling
@Component
@RequiredArgsConstructor
public class CheckRoomTrueOrFalse {

    private final BookingRepository bookingRepository;

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.HOURS)
    public LocalDateTime checkRoomTrueOrFalse() {

        List<BookingRoom> allBookingRooms = bookingRepository.findAll();
        for (BookingRoom bookingRoom : allBookingRooms) {

            if (Objects.equals(bookingRoom.getCheck_out_date(), LocalDateTime.now())) {

                bookingRoom.getRoom().setIs_available(true);

            }

        }
        return LocalDateTime.now();
    }

}
