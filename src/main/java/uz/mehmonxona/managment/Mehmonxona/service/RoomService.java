package uz.mehmonxona.managment.Mehmonxona.service;

import uz.mehmonxona.managment.Mehmonxona.domain.dto.request.RoomRequestDTO;
import uz.mehmonxona.managment.Mehmonxona.domain.dto.response.RoomResponseDTO;
import uz.mehmonxona.managment.Mehmonxona.domain.entity.Room;
import uz.mehmonxona.managment.Mehmonxona.domain.enumeration.RoomTypes;

import java.util.List;

public interface RoomService {
    public List<RoomResponseDTO> getAllRooms();
    public RoomResponseDTO getRoomById(Long id);
    public RoomResponseDTO createRoom(RoomRequestDTO room);
    public RoomResponseDTO getRoomByNumber(String number);
    public RoomResponseDTO updateRoomByRoomNumber(String roomNumber);
    public void deleteRoomByRoomNumber(String roomNumber);
    public List<RoomResponseDTO> getAllAvailableRooms(Boolean available);
    public List<RoomResponseDTO> getAllRoomsByLevel(int level);
    public List<RoomResponseDTO> getRoomsByType(RoomTypes type);
}
