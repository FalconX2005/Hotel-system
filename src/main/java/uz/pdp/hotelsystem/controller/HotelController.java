package uz.pdp.hotelsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hotelsystem.entity.Hotel;
import uz.pdp.hotelsystem.payload.HotelDTO;
import uz.pdp.hotelsystem.repository.HotelRepository;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
@RequiredArgsConstructor
public class HotelController {
    private final HotelRepository hotelRepository;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Hotel> read() {
        return hotelRepository.findAll();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ApiResult<HotelDTO> update(@RequestBody HotelDTO hotelDTO) {
        Hotel hotel = new Hotel();
        Optional<Hotel> byId = hotelRepository.findById(Long.valueOf(hotelDTO.getId()));
        if (byId.isPresent()) {
            hotel.setName(hotelDTO.getName());
            return ApiResult.success(hotelDTO);
        }
        else {
            return ApiResult.error("Hotel not found");
        }
    }

}
