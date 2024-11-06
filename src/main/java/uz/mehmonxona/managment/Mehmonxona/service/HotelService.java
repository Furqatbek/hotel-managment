package uz.mehmonxona.managment.Mehmonxona.service;

import uz.mehmonxona.managment.Mehmonxona.domain.dto.response.HotelResponseDTO;
import uz.mehmonxona.managment.Mehmonxona.domain.entity.Hotel;

import java.util.List;

public interface HotelService {
    public Hotel createHotel(Hotel hotel);
    public Hotel updateHotel(Hotel hotel);
    public Hotel deleteHotel(Hotel hotel);
    public List<Hotel> getAllHotels();
    public Hotel getHotelById(Long id);
    public Hotel getHotelByName(String hotelName);
    public HotelResponseDTO mapHotelToHotelResponseDTO(Hotel hotel);
    public Hotel mapHotelDTOToHotel(HotelResponseDTO hotelResponseDTO);
}
