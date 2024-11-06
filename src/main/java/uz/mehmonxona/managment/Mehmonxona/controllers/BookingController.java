package uz.mehmonxona.managment.Mehmonxona.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.mehmonxona.managment.Mehmonxona.domain.dto.request.BookingRequestDTO;
import uz.mehmonxona.managment.Mehmonxona.domain.dto.response.BookingResponseDTO;
import uz.mehmonxona.managment.Mehmonxona.service.BookingService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/booking ")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/search/by-id/{id}")
    public BookingResponseDTO getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    @GetMapping("/all")
    public List<BookingResponseDTO> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @PostMapping("/new")
    public BookingResponseDTO createBooking(@RequestBody BookingRequestDTO bookingRequestDTO) {
        return bookingService.createBooking(bookingRequestDTO);
    }

    @PostMapping("/update")
    public BookingResponseDTO updateBooking(@RequestBody BookingRequestDTO bookingRequestDTO) {
        return bookingService.updateBooking(bookingRequestDTO);
    }

    @PostMapping("/delete")
    public void deleteBooking(@RequestBody BookingRequestDTO bookingRequestDTO) {
        bookingService.deleteBooking(bookingRequestDTO);
    }
}
