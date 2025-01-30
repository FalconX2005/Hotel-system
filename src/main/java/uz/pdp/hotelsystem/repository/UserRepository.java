package uz.pdp.hotelsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.hotelsystem.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> getUserByUsername(String username);
}

