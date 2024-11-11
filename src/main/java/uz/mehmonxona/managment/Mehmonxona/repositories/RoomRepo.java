package uz.mehmonxona.managment.Mehmonxona.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mehmonxona.managment.Mehmonxona.domain.dto.response.RoomResponseDTO;
import uz.mehmonxona.managment.Mehmonxona.domain.entity.Room;
import uz.mehmonxona.managment.Mehmonxona.domain.enumeration.RoomTypes;

import java.util.List;

public interface RoomRepo extends JpaRepository<Room, Long> {
    Room findByRoomNumber(String number);
    List<Room> findByAvailable(Boolean available);

    List<Room> getAllByType(RoomTypes type);
}
