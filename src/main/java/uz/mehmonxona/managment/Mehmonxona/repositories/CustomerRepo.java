package uz.mehmonxona.managment.Mehmonxona.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNullApi;
import uz.mehmonxona.managment.Mehmonxona.domain.entity.Customer;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Customer findCustomerByPassNumber(String passNumber);
    Customer findCustomerByPhone(String phone);
}
