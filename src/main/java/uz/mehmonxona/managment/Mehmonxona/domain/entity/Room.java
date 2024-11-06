package uz.mehmonxona.managment.Mehmonxona.domain.entity;

import jakarta.persistence.*;
import uz.mehmonxona.managment.Mehmonxona.domain.enumeration.RoomTypes;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoomTypes type;

    private String roomNumber;
    private Double price;
    private Boolean available;
    private int capacity;
    private Boolean cleaning;
    private int level;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public Room(Long id, String roomNumber, Double price, Boolean available, int capacity, Hotel hotel, Boolean cleaning, int level, RoomTypes type) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.price = price;
        this.available = available;
        this.capacity = capacity;
        this.hotel = hotel;
        this.cleaning = cleaning;
        this.level = level;
        this.type = type;
    }

    public Room() {

    }

    public Boolean getCleaning() {
        return cleaning;
    }

    public void setCleaning(Boolean cleaning) {
        this.cleaning = cleaning;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public RoomTypes getType() {
        return type;
    }

    public void setType(RoomTypes type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
