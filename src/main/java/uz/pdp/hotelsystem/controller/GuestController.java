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
    public List<GuestDTO> readAllGuests() {
        List<Guest> allGuests = guestRepository.findAll();

        List<GuestDTO> guestDTOList = allGuests.stream()
                .map(guest -> new GuestDTO(
                        guest.getId(),
                        guest.getFirstName(),
                        guest.getLastName(),
                        guest.getPhoneNumber()
                ))
                .toList();

        return guestDTOList;
    }

    @GetMapping("/{id}")
    public GuestDTO readGuest(@PathVariable Integer id) {
        GuestDTO guestDTO = new GuestDTO();
        Optional<Guest> byId = guestRepository.findById(id.longValue());
        if (!byId.isPresent()) {
            throw new RuntimeException("Guest not found");
        }
        Guest guest = byId.get();
        guestDTO.setId(guest.getId());
        guestDTO.setFirstName(guest.getFirstName());
        guestDTO.setLastName(guest.getLastName());
        guestDTO.setPhoneNumber(guest.getPhoneNumber());
        return guestDTO;
    }
    @PostMapping
    public GuestDTO createGuest(@RequestBody GuestDTO guestDTO) {
        Guest guest = new Guest();
        if (Objects.isNull(guestDTO)){
            throw new RuntimeException("GuestDTO is null");
        }
        guest.setFirstName(guestDTO.getFirstName());
        guest.setLastName(guestDTO.getLastName());
        guest.setPhoneNumber(guestDTO.getPhoneNumber());
        guestRepository.save(guest);
        return guestDTO;
    }
    @DeleteMapping
    public GuestDTO deleteGuest(@RequestBody GuestDTO guestDTO) {
        Guest guest = new Guest();
        if (Objects.isNull(guestDTO)){
            throw new RuntimeException("GuestDTO is null");
        }
        guestRepository.findById(guestDTO.getId().longValue()).ifPresent(guestRepository::delete);
        return guestDTO;
    }
    @PutMapping
    public GuestDTO updateGuest(@RequestBody GuestDTO guestDTO) {
        GuestDTO guestDTO1 = readGuest(guestDTO.getId());
        Guest guest = new Guest();
              guest.setFirstName(guestDTO.getFirstName());
        guest.setLastName(guestDTO.getLastName());
        guest.setPhoneNumber(guestDTO.getPhoneNumber());
        guestRepository.save(guest);
      return   guestDTO;
    }
}
