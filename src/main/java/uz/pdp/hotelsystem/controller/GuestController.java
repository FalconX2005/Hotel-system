package uz.pdp.hotelsystem.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.hotelsystem.entity.Guest;
import uz.pdp.hotelsystem.payload.GuestDTO;
import uz.pdp.hotelsystem.repository.GuestRepository;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/guest")
public class GuestController {
    private final GuestRepository guestRepository;

    public GuestController(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @GetMapping
    public ApiResult<List<GuestDTO>> readAllGuests() {
        List<Guest> allGuests = guestRepository.findAll();

        List<GuestDTO> guestDTOList = allGuests.stream()
                .map(guest -> new GuestDTO(
                        guest.getId(),
                        guest.getFirstName(),
                        guest.getLastName(),
                        guest.getPhoneNumber()
                ))
                .toList();
        if(guestDTOList.isEmpty()) {
            return ApiResult.error("Guest not found");
        }
        return ApiResult.success(guestDTOList);
    }

    @GetMapping("/{id}")
    public ApiResult<GuestDTO> readGuest(@PathVariable Integer id) {
        GuestDTO guestDTO = new GuestDTO();
        Optional<Guest> byId = guestRepository.findById(id.longValue());
        if (!byId.isPresent()) {
            return ApiResult.error("Guest not found");
        }
        Guest guest = byId.get();
        guestDTO.setId(guest.getId());
        guestDTO.setFirstName(guest.getFirstName());
        guestDTO.setLastName(guest.getLastName());
        guestDTO.setPhoneNumber(guest.getPhoneNumber());
        return ApiResult.success(guestDTO);
    }
    @PostMapping
    public ApiResult<GuestDTO> createGuest(@RequestBody GuestDTO guestDTO) {
        Guest guest = new Guest();
        if (Objects.isNull(guestDTO)){
            return ApiResult.error("GuestDTO is null");
        }
        guest.setFirstName(guestDTO.getFirstName());
        guest.setLastName(guestDTO.getLastName());
        guest.setPhoneNumber(guestDTO.getPhoneNumber());
        guestRepository.save(guest);
        return ApiResult.success(guestDTO);
    }
    @DeleteMapping
    public ApiResult<GuestDTO> deleteGuest(@RequestBody GuestDTO guestDTO) {
        Guest guest = new Guest();
        if (Objects.isNull(guestDTO)){
            return ApiResult.error("GuestDTO is null");
        }
        Optional<Guest> byId = guestRepository.findById(guestDTO.getId().longValue());
//        byId.ifPresent(guestRepository::delete);
        if(byId.isPresent()) {
            guestRepository.delete(byId.get());
            return ApiResult.success(guestDTO);
        }
        return ApiResult.error("Guest not found");
    }
    @PutMapping
    public ApiResult<GuestDTO> updateGuest(@RequestBody GuestDTO guestDTO) {
//        GuestDTO guestDTO1 = readGuest(guestDTO.getId()).getData();
//        Guest guest = new Guest();
//        guestDTO1.setFirstName(guestDTO.getFirstName());
//        guestDTO1.setLastName(guestDTO.getLastName());
//        guestDTO1.setPhoneNumber(guestDTO.getPhoneNumber());
//        guestRepository.save(guestDTO1);
        Optional<Guest> byId = guestRepository.findById(guestDTO.getId().longValue());
        if (byId.isPresent()) {
            Guest guest = byId.get();
            guest.setFirstName(guestDTO.getFirstName());
            guest.setLastName(guestDTO.getLastName());
            guest.setPhoneNumber(guestDTO.getPhoneNumber());
            guestRepository.save(guest);
            return ApiResult.success(guestDTO);
        }

        return ApiResult.error("Guest not found");
    }
}
