package uz.mehmonxona.managment.Mehmonxona.domain.dto.response;

import uz.mehmonxona.managment.Mehmonxona.domain.entity.Customer;
import uz.mehmonxona.managment.Mehmonxona.domain.entity.Room;

import java.time.LocalDateTime;

public class BookingResponseDTO {
    private Long id;
    private Customer customer;
    private Room room;
    private LocalDateTime checkin;
    private LocalDateTime checkout;
    private Boolean payed;
    private Double amount;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Boolean getPayed() {
        return payed;
    }

    public void setPayed(Boolean payed) {
        this.payed = payed;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDateTime getCheckin() {
        return checkin;
    }

    public void setCheckin(LocalDateTime checkin) {
        this.checkin = checkin;
    }

    public LocalDateTime getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalDateTime checkout) {
        this.checkout = checkout;
    }
}
