package uz.pdp.hotelsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.hotelsystem.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> getUserByUsername(String username);

    List<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    User findByResetToken(String token); // Token orqali foydalanuvchini topish
}

