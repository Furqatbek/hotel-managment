package uz.mehmonxona.managment.Mehmonxona.domain.dto.request;

import uz.mehmonxona.managment.Mehmonxona.domain.enumeration.RoomTypes;

public class RoomDTO {
    private Long id;
    private String roomNumber;
    private Double price;
    private Boolean available;
    private RoomTypes roomType;
    private int capacity;
}
