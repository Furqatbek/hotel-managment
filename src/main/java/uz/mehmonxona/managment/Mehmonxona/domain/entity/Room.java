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
    private Boolean available;
    private Boolean cleaning;

    public Room(String roomNumber, Boolean available, Boolean cleaning, RoomTypes type) {
        this.roomNumber = roomNumber;
        this.available = available;
        this.cleaning = cleaning;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public Room() {

    }

    public Boolean getCleaning() {
        return cleaning;
    }

    public void setCleaning(Boolean cleaning) {
        this.cleaning = cleaning;
    }

    public RoomTypes getType() {
        return type;
    }

    public void setType(RoomTypes type) {
        this.type = type;
    }
    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

}
