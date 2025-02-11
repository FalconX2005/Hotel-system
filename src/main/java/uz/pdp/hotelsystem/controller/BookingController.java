package uz.pdp.hotelsystem.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.hotelsystem.entity.BookingRoom;
import uz.pdp.hotelsystem.entity.Guest;
import uz.pdp.hotelsystem.entity.Room;
import uz.pdp.hotelsystem.exception.RestException;
import uz.pdp.hotelsystem.payload.BookingRoomDTO;
import uz.pdp.hotelsystem.repository.BookingRepository;
import uz.pdp.hotelsystem.repository.GuestRepository;
import uz.pdp.hotelsystem.repository.RoomRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;

    public BookingController(BookingRepository bookingRepository, RoomRepository roomRepository, GuestRepository guestRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
    }


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
    @PostMapping
    public ApiResult<BookingRoomDTO> createBookingRoom(@RequestBody BookingRoomDTO bookingRoomDTO){
        BookingRoom bookingRoom = new BookingRoom();
        if (Objects.isNull(bookingRoomDTO)){
            throw RestException.error("Booking room DTO is null");
        }
        Guest guest = guestRepository.findById(Long.valueOf(bookingRoomDTO.getGuestId())).get();
        Room room = roomRepository.findById(Long.valueOf(bookingRoomDTO.getRoomId())).get();
        if(room.getIs_available()) {
            bookingRoom.setRoom(room);
        }
        bookingRoom.setGuest(guest);
        bookingRoom.setCheck_in_date(bookingRoomDTO.getCheck_in_date());
        bookingRoom.setCheck_out_date(bookingRoomDTO.getCheck_out_date());
        bookingRoom.setStatus(bookingRoomDTO.getStatus());

        return ApiResult.success(bookingRoomDTO);
    }

}
