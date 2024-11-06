package uz.mehmonxona.managment.Mehmonxona.domain.dto.request;

import uz.mehmonxona.managment.Mehmonxona.domain.entity.Customer;
import uz.mehmonxona.managment.Mehmonxona.domain.entity.Room;

import java.time.LocalDateTime;

public class BookingRequestDTO {
    private Long id;
    private Customer customer;
    private Room room;
    private Double price;
    private Boolean isBooked;
    private LocalDateTime checkin;
    private LocalDateTime checkout;
    private String paymentMethod;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getBooked() {
        return isBooked;
    }

    public void setBooked(Boolean booked) {
        isBooked = booked;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
