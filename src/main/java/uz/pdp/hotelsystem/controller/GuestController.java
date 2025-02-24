package uz.pdp.hotelsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hotelsystem.entity.Guest;
import uz.pdp.hotelsystem.exception.RestException;
import uz.pdp.hotelsystem.payload.ApiResult;
import uz.pdp.hotelsystem.payload.GuestDTO;
import uz.pdp.hotelsystem.repository.GuestRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/guest")
@RequiredArgsConstructor
public class GuestController {
    private final GuestRepository guestRepository;


    @Secured("ROLE_ADMIN,ROLE_MANAGER,ROLE_REGISTER")
    @GetMapping
    public ApiResult<List<GuestDTO>> readAllGuests() {
        List<Guest> allGuests = guestRepository.findAll();

        if (allGuests.isEmpty()) {
            throw RestException.error("Guest not found");
        }

        List<GuestDTO> guestDTOList = allGuests.stream()
                .map(guest -> new GuestDTO(
                        guest.getId(),
                        guest.getFirstName(),
                        guest.getLastName(),
                        guest.getPhoneNumber()
                ))
                .toList();
        if (guestDTOList.isEmpty()) {
            throw RestException.error("Guest not found");
        }
        return ApiResult.success(guestDTOList);
    }

    @Secured("ROLE_ADMIN,ROLE_MANAGER,ROLE_REGISTER")
    @GetMapping("/{id}")
    public ApiResult<GuestDTO> readGuest(@PathVariable Integer id) {
        GuestDTO guestDTO = new GuestDTO();
        Optional<Guest> byId = guestRepository.findById(id.longValue());
        if (!byId.isPresent()) {
            throw RestException.error("Guest not found");
        }
        Guest guest = byId.get();
        guestDTO.setId(guest.getId());
        guestDTO.setFirstName(guest.getFirstName());
        guestDTO.setLastName(guest.getLastName());
        guestDTO.setPhoneNumber(guest.getPhoneNumber());
        return ApiResult.success(guestDTO);
    }

    @Secured("ROLE_ADMIN,ROLE_MANAGER,ROLE_REGISTER")
    @PostMapping
    public ApiResult<GuestDTO> createGuest(@RequestBody GuestDTO guestDTO) {
        Guest guest = new Guest();
        if (Objects.isNull(guestDTO)) {
            throw RestException.error("GuestDTO is null");
        }
        guest.setFirstName(guestDTO.getFirstName());
        guest.setLastName(guestDTO.getLastName());
        guest.setPhoneNumber(guestDTO.getPhoneNumber());
        guestRepository.save(guest);
        return ApiResult.success(guestDTO);
    }

    @Secured("ROLE_ADMIN,ROLE_MANAGER,ROLE_REGISTER")
    @DeleteMapping("/{id}")
    public ApiResult<GuestDTO> deleteGuest(@PathVariable Integer id) {
        Guest guest = new Guest();
        if (Objects.isNull(id)) {
            throw RestException.error("id is null");
        }
        Optional<Guest> byId = guestRepository.findById(id.longValue());
        if (byId.isPresent()) {
            guestRepository.delete(byId.get());
            return ApiResult.success("Guest deleted successfully");
        }
        throw RestException.error("Guest not found");
    }

    @Secured("ROLE_ADMIN,ROLE_MANAGER,ROLE_REGISTER")
    @PutMapping("/{id}")
    public ApiResult<GuestDTO> updateGuest(@PathVariable Integer id, @RequestBody GuestDTO guestDTO) {
        Guest guest = guestRepository.findById(id.longValue())
                .orElseThrow(() -> RestException.notFound("Guest not found", id));

        guest.setFirstName(guestDTO.getFirstName());
        guest.setLastName(guestDTO.getLastName());
        guest.setPhoneNumber(guestDTO.getPhoneNumber());

        guestRepository.save(guest);

        return ApiResult.success(guestDTO);
    }

}
