package uz.pdp.hotelsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.hotelsystem.entity.BookingRoom;


public interface BookingRepository extends JpaRepository<BookingRoom,Integer> {
}
