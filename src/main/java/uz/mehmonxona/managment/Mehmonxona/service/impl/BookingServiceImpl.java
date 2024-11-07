package uz.mehmonxona.managment.Mehmonxona.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.mehmonxona.managment.Mehmonxona.domain.dto.request.BookingRequestDTO;
import uz.mehmonxona.managment.Mehmonxona.domain.dto.response.BookingResponseDTO;
import uz.mehmonxona.managment.Mehmonxona.domain.entity.Booking;
import uz.mehmonxona.managment.Mehmonxona.domain.entity.Customer;
import uz.mehmonxona.managment.Mehmonxona.domain.enumeration.PaymentType;
import uz.mehmonxona.managment.Mehmonxona.domain.enumeration.RoomTypes;
import uz.mehmonxona.managment.Mehmonxona.repositories.BookingRepo;
import uz.mehmonxona.managment.Mehmonxona.repositories.CustomerRepo;
import uz.mehmonxona.managment.Mehmonxona.service.BookingService;

import java.awt.print.Book;
import java.util.List;


@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepo bookingRepo;
    private CustomerServiceImpl customerService;
    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public BookingResponseDTO createBooking(BookingRequestDTO booking) {
        if (customerRepo.getById(booking.getCustomer().getId()) == null) {
            Customer newCustomer = new Customer();
            newCustomer.setFirstName(booking.getCustomer().getFirstName());
            newCustomer.setLastName(booking.getCustomer().getLastName());
            newCustomer.setPhone(booking.getCustomer().getPhone());
            newCustomer.setPassNumber(booking.getCustomer().getPassNumber());
            customerRepo.save(newCustomer);
        }
        Booking order = mappingToEntityBooking(booking);
        Booking newOrder = bookingRepo.save(order);
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
        bookingEntity.setId(booking.getId());
        bookingEntity.setCheckin(booking.getCheckin());
        bookingEntity.setCheckout(booking.getCheckout());
        bookingEntity.setCustomer(booking.getCustomer());
        bookingEntity.setRoom(booking.getRoom());
        bookingEntity.setPaid(booking.getBooked());
        bookingEntity.setAmount(booking.getPrice());
        bookingEntity.setPaymentType(PaymentType.fromString(booking.getPaymentMethod()));
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
