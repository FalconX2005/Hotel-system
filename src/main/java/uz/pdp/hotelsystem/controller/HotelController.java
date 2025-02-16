package uz.pdp.hotelsystem.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping
    public String hotel() {
        return "Hotel System";
    }

}
