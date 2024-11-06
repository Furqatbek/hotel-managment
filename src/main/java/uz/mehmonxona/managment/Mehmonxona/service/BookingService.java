package uz.mehmonxona.managment.Mehmonxona.service;

import uz.mehmonxona.managment.Mehmonxona.domain.dto.request.BookingRequestDTO;
import uz.mehmonxona.managment.Mehmonxona.domain.dto.response.BookingResponseDTO;
import uz.mehmonxona.managment.Mehmonxona.domain.entity.Booking;

import java.util.List;

public interface BookingService {
    public BookingResponseDTO createBooking(BookingRequestDTO booking);
    public BookingResponseDTO getBookingById(Long id);
    public List<BookingResponseDTO> getAllBookings();
    public BookingResponseDTO updateBooking(BookingRequestDTO booking);
    public void deleteBooking(BookingRequestDTO booking);
    //public List<BookingResponseDTO> getAllBookingsByUserId(Long userId);
    //public List<BookingResponseDTO> getAllUnpaidBookings();
 }
