package uz.pdp.hotelsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.hotelsystem.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
