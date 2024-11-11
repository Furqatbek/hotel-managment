package uz.mehmonxona.managment.Mehmonxona.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.mehmonxona.managment.Mehmonxona.domain.dto.request.RoomRequestDTO;
import uz.mehmonxona.managment.Mehmonxona.domain.dto.response.RoomResponseDTO;
import uz.mehmonxona.managment.Mehmonxona.domain.entity.Room;
import uz.mehmonxona.managment.Mehmonxona.domain.enumeration.RoomTypes;
import uz.mehmonxona.managment.Mehmonxona.repositories.RoomRepo;
import uz.mehmonxona.managment.Mehmonxona.service.RoomService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepo roomRepo;

    @Override
    public List<RoomResponseDTO> getAllRooms() {
        List<Room> rooms = roomRepo.findAll();
        return rooms.stream()
                .map(this::mapToRoomResponseDTO)
                .toList();
    }

    @Override
    public RoomResponseDTO getRoomById(Long id) {
        Room room = roomRepo.findById(id).orElse(null);
        return mapToRoomResponseDTO(room);
    }

    @Override
    public RoomResponseDTO createRoom(RoomRequestDTO room) {
        Room newRoom = mapToRoom(room);
        Room savedRoom = roomRepo.save(newRoom);
        return mapToRoomResponseDTO(savedRoom);
    }

    @Override
    public void saveRoom(RoomRequestDTO roomRequestDTO) {
        Room savedRoom = roomRepo.save(mapToRoom(roomRequestDTO));
    }

    @Override
    public RoomResponseDTO getRoomByNumber(String number) {
        Room room = roomRepo.findByRoomNumber(number);
        return mapToRoomResponseDTO(room);
    }

    @Override
    public RoomResponseDTO updateRoomByRoomNumber(String roomNumber, RoomRequestDTO room) {
        Room currentRoom = roomRepo.findByRoomNumber(roomNumber);
        currentRoom.setAvailable(room.getAvailable());
        currentRoom.setCleaning(room.getCleaning());
        Room savedRoom = roomRepo.save(currentRoom);
        return mapToRoomResponseDTO(savedRoom);
    }

    @Override
    public void deleteRoomByRoomNumber(String roomNumber) {
        Room room = roomRepo.findByRoomNumber(roomNumber);
        roomRepo.delete(room);
    }
    @Override
    public List<RoomResponseDTO> getRoomsByType(RoomTypes roomTypes) {
        return roomRepo.getAllByType(roomTypes)
                .stream()
                .map(this::mapToRoomResponseDTO)
                .toList();
    }

    private Room mapToRoom(RoomRequestDTO roomRequestDTO) {
        Room room = new Room();
        room.setRoomNumber(roomRequestDTO.getRoomNumber());
        room.setAvailable(true);
        room.setCleaning(false);
        room.setType(roomRequestDTO.getRoomType());
        return room;
    }

    private RoomResponseDTO mapToRoomResponseDTO(Room room) {
        RoomResponseDTO dto = new RoomResponseDTO();
        dto.setId(room.getId());
        dto.setRoomNumber(room.getRoomNumber());
        dto.setAvailable(room.getAvailable());
        dto.setCleaning(room.getCleaning());
        dto.setRoomType(room.getType());
        return dto;
    }
}
