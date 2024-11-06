package uz.mehmonxona.managment.Mehmonxona.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.mehmonxona.managment.Mehmonxona.domain.dto.request.CustomerRequestDTO;
import uz.mehmonxona.managment.Mehmonxona.domain.dto.response.CustomerResponseDTO;
import uz.mehmonxona.managment.Mehmonxona.service.CustomerService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/all")
    public List<CustomerResponseDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/search/by-phone/{phone}")
    public CustomerResponseDTO getCustomerByPhone(@PathVariable("phone") String phone) {
        return customerService.getCustomerByPhone(phone);
    }

    @GetMapping("/search/by-pass-num/{passnum}")
    public CustomerResponseDTO getCustomerByPassNum(@PathVariable("passnum") String passnum) {
        return customerService.getCustomerByPassNum(passnum);
    }

    @PostMapping
    public CustomerResponseDTO createCustomer(@RequestBody CustomerRequestDTO customerRequestDTO) {
        return customerService.createCustomer(customerRequestDTO);
    }

    @PostMapping("/update/by-pass-num/{passNum}")
    public CustomerResponseDTO updateCustomer(@PathVariable String passNum) {
        return customerService.updateCustomerByPassNum(passNum);
    }

    @PostMapping("/delete/{id}")
    public void deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
    }
}
