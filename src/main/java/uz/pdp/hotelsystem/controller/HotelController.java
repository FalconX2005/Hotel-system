package uz.pdp.hotelsystem.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.hotelsystem.entity.Hotel;
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
    public HotelDTO update(@RequestBody HotelDTO hotelDTO) {
        Hotel hotel = new Hotel();
        Optional<Hotel> byId = hotelRepository.findById(Long.valueOf(hotelDTO.getId()));
        if (byId.isPresent()) {
            hotel.setName(hotelDTO.getName());
            return hotelDTO;
        }
        else {
            throw new RuntimeException("hotel not found");
        }
    }

}
