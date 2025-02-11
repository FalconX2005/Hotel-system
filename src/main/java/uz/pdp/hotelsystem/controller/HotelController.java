package uz.pdp.hotelsystem.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.hotelsystem.entity.Hotel;
import uz.pdp.hotelsystem.exception.RestException;
import uz.pdp.hotelsystem.payload.HotelDTO;
import uz.pdp.hotelsystem.repository.HotelRepository;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    private final HotelRepository hotelRepository;

    public HotelController(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @GetMapping
    public List<Hotel> read() {
        return hotelRepository.findAll();
    }

    @PutMapping
    public ApiResult<HotelDTO> update(@RequestBody HotelDTO hotelDTO) {
        Hotel hotel = hotelRepository.findById(Long.valueOf(hotelDTO.getId()))
                .orElseThrow(() -> RestException.notFound("Hotel not found", hotelDTO.getId()));

        hotel.setName(hotelDTO.getName());
        hotelRepository.save(hotel);

        return ApiResult.success(hotelDTO);
    }


}
