package uz.pdp.hotelsystem.repository;

import uz.pdp.hotelsystem.payload.EmployeeDTO;
import uz.pdp.hotelsystem.payload.EmployeeFilterDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> filter(EmployeeFilterDTO employeeFilterDTO);
}
