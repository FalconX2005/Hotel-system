package uz.pdp.hotelsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.hotelsystem.entity.BookingRoom;
import uz.pdp.hotelsystem.entity.Payment;

import java.util.Optional;


public interface BookingRepository extends JpaRepository<BookingRoom,Long> {
}
