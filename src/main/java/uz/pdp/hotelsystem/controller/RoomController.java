package uz.pdp.hotelsystem.controller;


import org.springframework.web.bind.annotation.*;
import uz.pdp.hotelsystem.entity.Hotel;
import uz.pdp.hotelsystem.entity.Room;
import uz.pdp.hotelsystem.payload.RoomDTO;
import uz.pdp.hotelsystem.repository.HotelRepository;
import uz.pdp.hotelsystem.repository.RoomRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {
    private   final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    public RoomController(RoomRepository roomRepository, HotelRepository hotelRepository) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
    }
    @GetMapping
    public List<RoomDTO> readAll() {
        List<RoomDTO> rooms = new ArrayList<>();
        List<Room> all = roomRepository.findAll();

        for (Room room : all) {
            if (room.getIs_available()){
                RoomDTO roomDTO = new RoomDTO();
                roomDTO.setHotelId(room.getHotel().getId());
                roomDTO.setIs_available(room.getIs_available());
                roomDTO.setPrice(room.getPrice());
                roomDTO.setType(room.getType());
                roomDTO.setName(room.getName());
                rooms.add(roomDTO);
            }
        }
        return rooms;
    }

    @GetMapping("/{id}")
    public RoomDTO read(@PathVariable Long id) {
        RoomDTO roomDto = new RoomDTO();
        Optional<Room> byId = roomRepository.findById(id.longValue());
        Room room = byId.get();
        if (room.getIs_available()) {
            roomDto.setHotelId(room.getHotel().getId());
            roomDto.setIs_available(room.getIs_available());
            roomDto.setPrice(room.getPrice());
            roomDto.setType(room.getType());
            roomDto.setName(room.getName());
            roomDto.setId(room.getId());
            return roomDto;
        }
        else {
            throw new RuntimeException("Room with id " + id + " is not available");
        }
        }



    @PostMapping("/create")
    public Room create(@RequestBody RoomDTO roomDto) {
        List<Room> allRooms = roomRepository.findAll();
        for (Room allRoom : allRooms) {

            if (roomDto.getId().equals(allRoom.getId())) {
                throw new RuntimeException("Room with id " + allRoom.getId() + " already exists");
            }
        }

        Room room = new Room();

        room.setName(roomDto.getName());
        room.setIs_available(roomDto.getIs_available());
        room.setPrice(roomDto.getPrice());
        room.setType(roomDto.getType());

        Optional<Hotel> hotel = hotelRepository.findById(Long.valueOf(roomDto.getHotelId()));
        room.setHotel(hotel.get());


        return roomRepository.save(room);

    }

    @DeleteMapping("delete/{id}")
    public Room delete(@PathVariable Integer id) {
        Optional<Room> byId = roomRepository.findById(id.longValue());
        if (byId.isPresent()) {
            roomRepository.delete(byId.get());
        }
        return byId.get();
    }


    @PutMapping("/update/{id}")
    public Room update(@RequestBody RoomDTO roomDto) {
        Optional<Room> byId = roomRepository.findById(Long.valueOf(roomDto.getId()));

        if (byId.isPresent()) {
            Room room = byId.get();
            if (room.getIs_available()) {
                room.setName(roomDto.getName());
                room.setPrice(roomDto.getPrice());
                room.setType(roomDto.getType());
                room.setIs_available(roomDto.getIs_available());
                Hotel hotel = hotelRepository.findById(roomDto.getHotelId().longValue()).get();
                room.setHotel(hotel);
                return roomRepository.save(room);
            }
            else {
                throw new RuntimeException("Room with id " + roomDto.getId() + " is not available");
            }
        }
        else {
            throw new RuntimeException("Room with id " + roomDto.getId() + "  room not found");
        }
    }
}
