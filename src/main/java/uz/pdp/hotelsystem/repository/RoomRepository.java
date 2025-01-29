package uz.pdp.hotelsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.hotelsystem.entity.Room;


public interface RoomRepository extends JpaRepository<Room, Long> {

}
