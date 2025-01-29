package uz.pdp.hotelsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.hotelsystem.entity.Guest;


public interface GuestRepository extends JpaRepository<Guest, Long> {
}
