package uz.mehmonxona.managment.Mehmonxona.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.mehmonxona.managment.Mehmonxona.domain.dto.request.BookingRequestDTO;
import uz.mehmonxona.managment.Mehmonxona.domain.dto.request.CustomerRequestDTO;
import uz.mehmonxona.managment.Mehmonxona.domain.dto.response.BookingResponseDTO;
import uz.mehmonxona.managment.Mehmonxona.domain.entity.Booking;
import uz.mehmonxona.managment.Mehmonxona.domain.entity.Customer;
import uz.mehmonxona.managment.Mehmonxona.domain.entity.Room;
import uz.mehmonxona.managment.Mehmonxona.domain.enumeration.PaymentType;
import uz.mehmonxona.managment.Mehmonxona.domain.enumeration.RoomTypes;
import uz.mehmonxona.managment.Mehmonxona.repositories.BookingRepo;
import uz.mehmonxona.managment.Mehmonxona.repositories.CustomerRepo;
import uz.mehmonxona.managment.Mehmonxona.repositories.RoomRepo;
import uz.mehmonxona.managment.Mehmonxona.service.BookingService;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepo bookingRepo;
    @Autowired
    private CustomerServiceImpl customerService;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private RoomRepo roomRepo;

    @Transactional
    @Override
    public BookingResponseDTO createBooking(BookingRequestDTO booking) {
        // Retrieve the room by ID from the database
        Optional<Room> existingRoomOpt = roomRepo.findById(booking.getRoom().getId());

        // Check if the room exists, otherwise handle the case where the room doesn't exist
        if (existingRoomOpt.isPresent()) {
            Room existingRoom = existingRoomOpt.get();
            existingRoom.setAvailable(false);
            existingRoom.setCleaning(false);
            roomRepo.save(existingRoom); // Save the updated room
        } else {
            // Handle the case when room is not found (you could throw an exception or handle accordingly)
            throw new RuntimeException("Room not found");
        }

        // Check if the customer already exists
        Optional<Customer> existingCustomer = Optional.empty();
        if (booking.getCustomer().getId() != null) {
            existingCustomer = customerRepo.findById(booking.getCustomer().getId());
        }

        // If the customer exists, set it, otherwise save the new customer
        existingCustomer.ifPresentOrElse(
                booking::setCustomer,
                () -> customerRepo.save(booking.getCustomer()) // Save new customer if not found
        );

        // Map the booking and save it
        Booking order = mappingToEntityBooking(booking);
        Booking newOrder = bookingRepo.save(order);

        // Return the mapped DTO of the new booking
        return mappingToDtoBooking(newOrder);
    }


    @Override
    public BookingResponseDTO getBookingById(Long id) {
        return mappingToDtoBooking(bookingRepo.findById(id).get());
    }

    @Override
    public List<BookingResponseDTO> getAllBookings() {
        return bookingRepo.findAll()
                .stream().map(this::mappingToDtoBooking)
                .toList();
    }

    @Override
    public BookingResponseDTO updateBooking(BookingRequestDTO booking) {
        Booking order = mappingToEntityBooking(booking);
        Booking newOrder = bookingRepo.save(order);
        return mappingToDtoBooking(newOrder);
    }

    @Override
    public void deleteBooking(BookingRequestDTO booking) {
        Booking order = mappingToEntityBooking(booking);
        bookingRepo.delete(order);
    }

    @Scheduled(cron = "0 * * * * ?")
    @Override
    public void updateExpiredBooking() {
        System.out.println("Starting handler check expired bookings");
        LocalDateTime currentTime = LocalDateTime.now();
        List<Booking> bookings = bookingRepo.findByCheckoutAfter(currentTime);
        if (bookings.isEmpty()) {
            System.out.println("Bookings not found");
        }
        List<Room> rooms = new ArrayList<>();
        for (Booking b : bookings) {
            rooms.add(b.getRoom());
            b.setActive(false);
            bookingRepo.save(b);
        }

        for (Room r : rooms) {
            r.setAvailable(true);
            r.setCleaning(true);
            roomRepo.save(r);
        }
    }

    @Override
    public BookingResponseDTO getBookingByRoomId(Long id) {
        return mappingToDtoBooking(bookingRepo.findBookingByRoomId(id));
    }
//
//    @Override
//    public List<BookingResponseDTO> getAllBookingsByUserId(Long userId) {
//        return bookingRepo.findAllByCustomer(customerService.mapToCustomer(customerService.mapToDTO(customerService.getCustomerById(userId))));
//    }
//
//    @Override
//    public List<BookingResponseDTO> getAllUnpaidBookings() {
//        return bookingRepo.findByPaid(true).stream().map(this::mappingToDtoBooking).toList();
//    }

    public Booking mappingToEntityBooking(BookingRequestDTO booking) {
        Booking bookingEntity = new Booking();
        bookingEntity.setCheckin(booking.getCheckin());
        bookingEntity.setCheckout(booking.getCheckout());
        bookingEntity.setCustomer(booking.getCustomer());
        bookingEntity.setRoom(booking.getRoom());
        bookingEntity.setPaid(booking.getBooked());
        bookingEntity.setAmount(booking.getPrice());
        bookingEntity.setPaymentType(PaymentType.fromString(booking.getPaymentMethod()));
        bookingEntity.setActive(true);
        bookingEntity.setPaid(true);
        return bookingEntity;
    }

    public BookingResponseDTO mappingToDtoBooking(Booking booking) {
        BookingResponseDTO bookingResponseDTO = new BookingResponseDTO();
        bookingResponseDTO.setId(booking.getId());
        bookingResponseDTO.setCheckin(booking.getCheckin());
        bookingResponseDTO.setCheckout(booking.getCheckout());
        bookingResponseDTO.setCustomer(booking.getCustomer());
        bookingResponseDTO.setRoom(booking.getRoom());
        bookingResponseDTO.setAmount(booking.getAmount());
        bookingResponseDTO.setPayed(booking.getPaid());
        return bookingResponseDTO;
    }
}
