package uz.mehmonxona.managment.Mehmonxona.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.mehmonxona.managment.Mehmonxona.domain.dto.request.RoomRequestDTO;
import uz.mehmonxona.managment.Mehmonxona.domain.dto.response.RoomResponseDTO;
import uz.mehmonxona.managment.Mehmonxona.domain.entity.Room;
import uz.mehmonxona.managment.Mehmonxona.domain.enumeration.RoomTypes;
import uz.mehmonxona.managment.Mehmonxona.repositories.RoomRepo;
import uz.mehmonxona.managment.Mehmonxona.service.RoomService;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepo roomRepo;

    @Override
    public List<RoomResponseDTO> getAllAvailableRooms(Boolean available) {
        List<Room> rooms = roomRepo.findByAvailable(available);
        return rooms.stream()
                .map(this::mapToRoomResponseDTO)
                .toList();
    }

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
    public RoomResponseDTO getRoomByNumber(String number) {
        Room room = roomRepo.findByRoomNumber(number);
        return mapToRoomResponseDTO(room);
    }

    @Override
    public RoomResponseDTO updateRoomByRoomNumber(String roomNumber, RoomRequestDTO room) {
        Room currentRoom = roomRepo.findByRoomNumber(roomNumber);
        currentRoom.setRoomNumber(roomNumber);
        currentRoom.setType(room.getRoomType());
        currentRoom.setAvailable(room.getAvailable());
        currentRoom.setPrice(room.getPrice());
        currentRoom.setCleaning(room.getCleaning());
        currentRoom.setLevel(room.getLevel());
        currentRoom.setCapacity(room.getCapacity());
        Room savedRoom = roomRepo.save(currentRoom);
        return mapToRoomResponseDTO(savedRoom);
    }

    @Override
    public void deleteRoomByRoomNumber(String roomNumber) {
        Room room = roomRepo.findByRoomNumber(roomNumber);
        roomRepo.delete(room);
    }

    @Override
    public List<RoomResponseDTO> getAllRoomsByLevel(int level) {
        return roomRepo.findAllByLevel(level).stream()
                .map(this::mapToRoomResponseDTO)
                .toList();
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
        room.setId(roomRequestDTO.getId());
        room.setRoomNumber(roomRequestDTO.getRoomNumber());
        room.setAvailable(true);
        room.setLevel(roomRequestDTO.getLevel());
        room.setCapacity(roomRequestDTO.getCapacity());
        room.setCleaning(false);
        room.setPrice(roomRequestDTO.getPrice());
        room.setType(roomRequestDTO.getRoomType());
        return room;
    }

    private RoomResponseDTO mapToRoomResponseDTO(Room room) {
        RoomResponseDTO dto = new RoomResponseDTO();
        dto.setId(room.getId());
        dto.setRoomNumber(room.getRoomNumber());
        dto.setAvailable(room.getAvailable());
        dto.setLevel(room.getLevel());
        dto.setCapacity(room.getCapacity());
        dto.setCleaning(room.getCleaning());
        dto.setPrice(room.getPrice());
        dto.setRoomType(room.getType());
        return dto;
    }
}
