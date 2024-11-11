package uz.mehmonxona.managment.Mehmonxona.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mehmonxona.managment.Mehmonxona.domain.dto.response.BookingResponseDTO;
import uz.mehmonxona.managment.Mehmonxona.domain.entity.Booking;
import uz.mehmonxona.managment.Mehmonxona.domain.entity.Customer;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, Long> {
    public Booking findBookingByRoomId(Long id);

    List<Booking> findByCheckoutAfter(LocalDateTime after);
    // Booking findAllByCustomer(Customer customer);

    //List<BookingResponseDTO> findByPaid(Boolean paid);
}
