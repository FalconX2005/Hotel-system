package uz.pdp.hotelsystem.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.hotelsystem.payload.EmployeeDTO;
import uz.pdp.hotelsystem.payload.EmployeeFilterDTO;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class CustomFilter {

    @GetMapping("/filter")
    public List<EmployeeDTO> filter(EmployeeFilterDTO filterDTO ){
        log.info(">>> filter {}", filterDTO);

    }
}
