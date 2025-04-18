package uz.pdp.hotelsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hotelsystem.entity.BookingRoom;
import uz.pdp.hotelsystem.entity.Guest;
import uz.pdp.hotelsystem.entity.Room;
import uz.pdp.hotelsystem.enums.StatusBooking;
import uz.pdp.hotelsystem.exception.RestException;
import uz.pdp.hotelsystem.payload.ApiResult;
import uz.pdp.hotelsystem.payload.BookingRoomDTO;
import uz.pdp.hotelsystem.repository.BookingRepository;
import uz.pdp.hotelsystem.repository.GuestRepository;
import uz.pdp.hotelsystem.repository.RoomRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;




    @Secured("ROLE_ADMIN")
    @GetMapping
    public ApiResult<List<BookingRoomDTO>> readBookingRooms(){
        List<BookingRoom> all = bookingRepository.findAll();
        List<BookingRoomDTO> bookingDto = new ArrayList<>();
        for (BookingRoom bookingRoom : all) {
            if (!bookingRoom.getRoom().getIs_available()) {
                BookingRoomDTO bookingRoomDTO = new BookingRoomDTO();
                bookingRoomDTO.setRoomId(bookingRoom.getRoom().getId());
                bookingRoomDTO.setId(bookingRoom.getId());
                bookingRoomDTO.setStatus(bookingRoom.getStatus());
                bookingRoomDTO.setGuestId(bookingRoom.getGuest().getId());
                bookingRoomDTO.setCheck_in_date(bookingRoom.getCheck_in_date());
                bookingRoomDTO.setCheck_out_date(bookingRoom.getCheck_out_date());
                bookingDto.add(bookingRoomDTO);
                return ApiResult.success(bookingDto);
            }

        }
        return ApiResult.error("Booking rooms not found");
    }
    @Secured("ROLE_ADMIN,ROLE_MANAGER,ROLE_REGISTER")
    @PostMapping
    public ApiResult<BookingRoomDTO> createBookingRoom(@RequestBody BookingRoomDTO bookingRoomDTO){
        BookingRoom bookingRoom = new BookingRoom();
        if (Objects.isNull(bookingRoomDTO)){
            return ApiResult.error("Booking room DTO is null");
        }
        Guest guest = guestRepository.findById(Long.valueOf(bookingRoomDTO.
                getGuestId())).
                orElseThrow(() -> RestException.error("Guest not found"));
        Room room = roomRepository.findById(Long.valueOf(bookingRoomDTO.
                getRoomId())).
                orElseThrow(() -> RestException.error("Room not found"));
        if(!room.getIs_available()) {
            throw RestException.error("Room is not available");
        }


        bookingRoom.setGuest(guest);
        bookingRoom.setRoom(room);
        bookingRoom.setCheck_in_date(bookingRoomDTO.getCheck_in_date());
        bookingRoom.setCheck_out_date(bookingRoomDTO.getCheck_out_date());
        bookingRoom.setStatus(StatusBooking.BOOKED);
        bookingRepository.save(bookingRoom);

        return ApiResult.success(bookingRoomDTO);
    }

}
