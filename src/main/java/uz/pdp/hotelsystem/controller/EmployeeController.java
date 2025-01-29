package uz.pdp.hotelsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import uz.pdp.hotelsystem.entity.Employee;
import uz.pdp.hotelsystem.repository.EmployeeRepository;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public EmployeeDTO findById(@PathVariable Integer id) {
        Optional<Employee> employee = employeeRepository.findById(Long.valueOf(id));
        if (employee.isPresent()) {
            Employee employee1 = employee.get();
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setId(Math.toIntExact(employee1.getId()));
            employeeDTO.setFirstName(employee1.getFirstName());
            employeeDTO.setLastName(employee1.getLastName());
            employeeDTO.setAge(employee1.getAge());
            employeeDTO.setWorkTime(employee1.getWorkTime());
            employeeDTO.setRole(employee1.getRole());
            return employeeDTO;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        if (Objects.isNull(employeeDTO)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setAge(employeeDTO.getAge());
        employee.setWorkTime(employeeDTO.getWorkTime());
        employee.setRole(employeeDTO.getRole());
        employeeRepository.save(employee);

        return employeeDTO;

    }

    @PutMapping
    public EmployeeDTO updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Optional<Employee> employee = employeeRepository.findById(Long.valueOf(employeeDTO.getId()));
        if (employee.isPresent()) {
            Employee employee1 = employee.get();
            employee1.setFirstName(employeeDTO.getFirstName());
            employee1.setLastName(employeeDTO.getLastName());
            employee1.setAge(employeeDTO.getAge());
            employee1.setWorkTime(employeeDTO.getWorkTime());
            employee1.setRole(employeeDTO.getRole());
            employeeRepository.save(employee1);
            return employeeDTO;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public void deleteEmployee(@PathVariable Integer id) {
        Optional<Employee> employee = employeeRepository.findById(Long.valueOf(id));
        employee.ifPresent(employeeRepository::delete);
    }

}
