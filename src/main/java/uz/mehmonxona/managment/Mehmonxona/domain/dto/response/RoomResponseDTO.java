package uz.mehmonxona.managment.Mehmonxona.domain.dto.response;

import uz.mehmonxona.managment.Mehmonxona.domain.enumeration.RoomTypes;

public class RoomResponseDTO {
    private String roomNumber;
    private Boolean available;
    private RoomTypes roomType;
    private Boolean cleaning;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
