package uz.mehmonxona.managment.Mehmonxona.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.mehmonxona.managment.Mehmonxona.domain.dto.request.RoomRequestDTO;
import uz.mehmonxona.managment.Mehmonxona.domain.entity.Room;
import uz.mehmonxona.managment.Mehmonxona.domain.enumeration.RoomTypes;
import uz.mehmonxona.managment.Mehmonxona.repositories.RoomRepo;
import uz.mehmonxona.managment.Mehmonxona.service.RoomService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DatatInitializer implements CommandLineRunner {

    @Autowired
    private RoomRepo roomRepo;
    @Autowired
    private RoomService roomService;

    @Override
    public void run(String... args) throws Exception {
        List<RoomRequestDTO> rooms = new ArrayList<>();
        rooms.add(new RoomRequestDTO("101", true, RoomTypes.TWIN, false));
        rooms.add(new RoomRequestDTO("102", true, RoomTypes.ONE, false));
        rooms.add(new RoomRequestDTO("103", true, RoomTypes.DOUBLE, false));
        rooms.add(new RoomRequestDTO("104", true, RoomTypes.ONE, false));
        rooms.add(new RoomRequestDTO("105", true, RoomTypes.TWIN, false));
        rooms.add(new RoomRequestDTO("106", true, RoomTypes.DOUBLE, false));
        rooms.add(new RoomRequestDTO("107", true, RoomTypes.ONE, false));
        rooms.add(new RoomRequestDTO("108", true, RoomTypes.ONE, false));
        rooms.add(new RoomRequestDTO("109", true, RoomTypes.DOUBLE, false));
        rooms.add(new RoomRequestDTO("201", true, RoomTypes.TRIPLE_LUXE, false));
        rooms.add(new RoomRequestDTO("202", true, RoomTypes.TWIN_LUXE, false));
        rooms.add(new RoomRequestDTO("203", true, RoomTypes.ONE, false));
        rooms.add(new RoomRequestDTO("204", true, RoomTypes.DOUBLE, false));
        rooms.add(new RoomRequestDTO("205", true, RoomTypes.DOUBLE_LUXE, false));
        rooms.add(new RoomRequestDTO("206", true, RoomTypes.DOUBLE, false));
        rooms.add(new RoomRequestDTO("207", true, RoomTypes.DOUBLE, false));
        rooms.add(new RoomRequestDTO("208", true, RoomTypes.DOUBLE, false));
        rooms.add(new RoomRequestDTO("209", true, RoomTypes.DOUBLE, false));
        rooms.add(new RoomRequestDTO("210", true, RoomTypes.ONE, false));
        rooms.add(new RoomRequestDTO("211", true, RoomTypes.DOUBLE, false));
        rooms.add(new RoomRequestDTO("212", true, RoomTypes.DOUBLE, false));

        for(RoomRequestDTO room : rooms) {
            roomService.saveRoom(room);
        }
    }
}
