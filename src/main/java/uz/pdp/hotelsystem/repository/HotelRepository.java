package uz.pdp.hotelsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.hotelsystem.entity.Hotel;


public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
