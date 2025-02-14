package uz.pdp.hotelsystem.repository;

import uz.pdp.hotelsystem.entity.Employee;
import uz.pdp.hotelsystem.payload.EmployeeFilterDTO;

import java.util.List;

public interface EmployeeService {
    List<Employee> filter(EmployeeFilterDTO employeeFilterDTO);
}
