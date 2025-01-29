package uz.pdp.hotelsystem.entity;

import jakarta.persistence.*;
import uz.pdp.hotelsystem.enums.RoomType;


@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    private RoomType type;

    private Long price;

    private Boolean is_available;

    @ManyToOne
    private Hotel hotel;

    public Room() {
    }

    public Room(String name, RoomType type, Long price, Boolean is_available, Hotel hotel) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.is_available = is_available;
        this.hotel = hotel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Boolean getIs_available() {
        return is_available;
    }

    public void setIs_available(Boolean is_available) {
        this.is_available = is_available;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }


}
