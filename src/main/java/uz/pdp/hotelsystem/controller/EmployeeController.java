package uz.pdp.hotelsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import uz.pdp.hotelsystem.entity.Employee;
import uz.pdp.hotelsystem.entity.User;
import uz.pdp.hotelsystem.payload.EmployeeDTO;
import uz.pdp.hotelsystem.repository.EmployeeRepository;
import uz.pdp.hotelsystem.repository.UserRepository;



import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;


    private final UserRepository userRepository;

    public EmployeeController(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public EmployeeDTO findById(@PathVariable Integer id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(Long.valueOf(id));
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setId(Math.toIntExact(employee.getId()));
            employeeDTO.setLastName(employee.getLastName());
            employeeDTO.setFirstName(employee.getFirstName());
            employeeDTO.setAge(employee.getAge());
            employeeDTO.setWorkTime(employee.getWorkTime());
            return employeeDTO;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        if (Objects.isNull(employeeDTO)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setAge(employeeDTO.getAge());
        employee.setWorkTime(employeeDTO.getWorkTime());
        Integer userId = employeeDTO.getUserId();
        Optional<User> byId = userRepository.findById(userId);
        if (byId.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setPassword(passwordEncoder.encode(employeeDTO.getUser().getPassword()));
        user.setUsername(employeeDTO.getUser().getUsername());
        user.setRole(employeeDTO.getUser().getRole());
        employee.setUser(user);
        employeeRepository.save(employee);


        return employeeDTO;

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public EmployeeDTO updateEmployee(@PathVariable Integer id ,
                                      @RequestBody EmployeeDTO employeeDTO) {
        Optional<Employee> employee = employeeRepository.findById(id.longValue());
        if (employee.isPresent()) {
            Employee employee1 = employee.get();
            employee1.setFirstName(employeeDTO.getFirstName());
            employee1.setLastName(employeeDTO.getLastName());
            employee1.setAge(employeeDTO.getAge());
            employee1.setWorkTime(employeeDTO.getWorkTime());
            employeeRepository.save(employee1);
            return employeeDTO;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Integer id) {
        Optional<Employee> employee = employeeRepository.findById(Long.valueOf(id));
        employee.ifPresent(employeeRepository::delete);
    }

}
