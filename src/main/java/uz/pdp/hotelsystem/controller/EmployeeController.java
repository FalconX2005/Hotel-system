package uz.pdp.hotelsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import uz.pdp.hotelsystem.entity.Employee;
import uz.pdp.hotelsystem.entity.User;
import uz.pdp.hotelsystem.exception.RestException;
import uz.pdp.hotelsystem.payload.ApiResult;
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

    @Secured("ROLE_ADMIN,ROLE_MANAGER")
    @GetMapping
    public List<Employee> findAll() {

        return employeeRepository.findAll();
    }

    @Secured("ROLE_ADMIN,ROLE_MANAGER")
    @GetMapping("/{id}")
    public EmployeeDTO findById(@PathVariable Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setId(employee.getId());
            employeeDTO.setLastName(employee.getLastName());
            employeeDTO.setFirstName(employee.getFirstName());
            employeeDTO.setAge(employee.getAge());
            employeeDTO.setWorkTime(employee.getWorkTime());
            employeeDTO.setRole(employee.getUser().getRole());
            employeeDTO.setUsername(employee.getUser().getUsername());

            return employeeDTO;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @Secured("ROLE_ADMIN,ROLE_MANAGER")
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
        User user = new User();
        user.setRole(employeeDTO.getRole());
        user.setPassword(passwordEncoder.encode(employeeDTO.getPassword()));
        user.setUsername(employeeDTO.getUsername());
        userRepository.save(user);
        employee.setUser(user);
        employeeRepository.save(employee);
        return employeeDTO;

    }

    //@PreAuthorize("hasRole('ADMIN')")
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ApiResult<Object> updateEmployee(@PathVariable(name = "id") Integer id ,
                                            @RequestBody EmployeeDTO employeeDTO) {
        Optional<Employee> employee = employeeRepository.findById(id.longValue());
        if (employee.isPresent()) {
            Employee employee1 = employee.get();
            employee1.setFirstName(employeeDTO.getFirstName());
            employee1.setLastName(employeeDTO.getLastName());
            employee1.setAge(employeeDTO.getAge());
            employee1.setWorkTime(employeeDTO.getWorkTime());
            employee1.getUser().setRole(employeeDTO.getRole());
            employee1.getUser().setPassword(passwordEncoder.encode(employeeDTO.getPassword()));
            employee1.getUser().setUsername(employeeDTO.getUsername());

            employeeRepository.save(employee1);

            return ApiResult.success(employee1);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public ApiResult<Object> deleteEmployee(@PathVariable Integer id) {
        Optional<Employee> employee = employeeRepository.findById(Long.valueOf(id));
        if (employee.isPresent()) {
            Employee employee1 = employee.get();
            Long userId = employee1.getUser().getId();
            employeeRepository.delete(employee1);
            userRepository.deleteById(userId);
            return ApiResult.success(employee.get());
        }
        throw RestException.notFound("employee not found",id );

    }

}
