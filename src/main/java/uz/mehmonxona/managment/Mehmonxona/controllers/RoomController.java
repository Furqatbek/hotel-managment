package uz.mehmonxona.managment.Mehmonxona.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.mehmonxona.managment.Mehmonxona.domain.dto.request.RoomRequestDTO;
import uz.mehmonxona.managment.Mehmonxona.domain.dto.response.RoomResponseDTO;
import uz.mehmonxona.managment.Mehmonxona.domain.enumeration.RoomTypes;
import uz.mehmonxona.managment.Mehmonxona.service.impl.RoomServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {

    @Autowired
    private RoomServiceImpl roomService;

    @GetMapping("/all")
    public List<RoomResponseDTO> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/{id}")
    public RoomResponseDTO getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }

    @GetMapping("/{number}")
    public RoomResponseDTO getRoomByRoomNumber(@PathVariable String number) {
        return roomService.getRoomByNumber(number);
    }

    @GetMapping("/type/{roomType}")
    public List<RoomResponseDTO> getAllRoomsByRoomType(@PathVariable String roomType) {
        return roomService.getRoomsByType(RoomTypes.fromString(roomType));
    }

    @PostMapping
    public RoomResponseDTO createRoom(@RequestBody RoomRequestDTO room) {
        return roomService.createRoom(room);
    }

    @PostMapping("/delete/{number}")
    public void deleteRoom(@PathVariable String number) {
        roomService.deleteRoomByRoomNumber(number);
    }

    @PostMapping("/update/{number}")
    public RoomResponseDTO updateRoomByRoomNumber(@PathVariable String number, @RequestBody RoomRequestDTO room) {
        return roomService.updateRoomByRoomNumber(number, room);
    }
}
