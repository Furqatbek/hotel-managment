package uz.mehmonxona.managment.Mehmonxona.domain.dto.request;

import uz.mehmonxona.managment.Mehmonxona.domain.enumeration.RoomTypes;

public class RoomRequestDTO {
    private String roomNumber;
    private Boolean available;
    private RoomTypes roomType;
    private Boolean cleaning;

    public RoomRequestDTO(String roomNumber, Boolean available, RoomTypes roomType, Boolean cleaning) {
        this.roomNumber = roomNumber;
        this.available = available;
        this.roomType = roomType;
        this.cleaning = cleaning;
    }

    public Boolean getCleaning() {
        return cleaning;
    }

    public void setCleaning(Boolean cleaning) {
        this.cleaning = cleaning;
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

    public RoomTypes getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomTypes roomType) {
        this.roomType = roomType;
    }
}
