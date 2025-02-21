package uz.pdp.hotelsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hotelsystem.entity.Hotel;
import uz.pdp.hotelsystem.exception.RestException;
import uz.pdp.hotelsystem.payload.HotelDTO;
import uz.pdp.hotelsystem.repository.HotelRepository;


import java.util.List;
import java.util.Optional;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotel")
@RequiredArgsConstructor
public class HotelController {

    private final HotelRepository hotelRepository;

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping
    public List<Hotel> read() {
        return hotelRepository.findAll();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ApiResult<HotelDTO> update(@RequestBody HotelDTO hotelDTO) {
        Hotel hotel = hotelRepository.findById(Long.valueOf(hotelDTO.getId()))
                .orElseThrow(() -> RestException.notFound("Hotel not found", hotelDTO.getId()));

        hotel.setName(hotelDTO.getName());
        hotelRepository.save(hotel);

        return ApiResult.success(hotelDTO);
    }


}
