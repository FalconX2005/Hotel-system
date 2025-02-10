package uz.pdp.hotelsystem.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.pdp.hotelsystem.entity.Employee;
import uz.pdp.hotelsystem.payload.EmployeeDTO;
import uz.pdp.hotelsystem.payload.EmployeeFilterDTO;
import uz.pdp.hotelsystem.repository.EmployeeService;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EntityManager entityManager;
    @Override
    public List<EmployeeDTO> filter(EmployeeFilterDTO filter) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);

        Root<Employee> root= criteriaQuery.from(Employee.class);

        List<Predicate> predicates = new ArrayList<>();

        if(Objects.nonNull(filter.getFirstName())){
            Path<String> firstName1 = root.get("firstName");
            Predicate firstName = criteriaBuilder.like(firstName1,
                    "%" +
                            filter.getFirstName()
                            + "%");
            predicates.add(firstName);

        }
        if(Objects.nonNull(filter.getLastName())){
            Predicate lastName = criteriaBuilder.like(root.get("lastName"),
                    "%" +
                            filter.getLastName()
                            + "%");
            predicates.add(lastName);

        }
        if (filter.getAge()!=null) {
            Predicate age = criteriaBuilder.greaterThan(root.get("age"), 18);
            predicates.add(age);
        }

        if (Objects.nonNull(filter.getWorkTime())) {
            Predicate workTime = criteriaBuilder.between(root.get("workTime"),
                     LocalTime.of(9,0),
                    LocalTime.of(18,0));
            predicates.add(workTime);
        }


        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        List<Employee> employees = entityManager.createQuery(criteriaQuery).getResultList();

        return List.of();
    }
}
