package uz.mehmonxona.managment.Mehmonxona.service;

import uz.mehmonxona.managment.Mehmonxona.domain.dto.request.CustomerRequestDTO;
import uz.mehmonxona.managment.Mehmonxona.domain.dto.response.CustomerResponseDTO;
import uz.mehmonxona.managment.Mehmonxona.domain.entity.Customer;

import java.util.List;

public interface CustomerService {
    public CustomerResponseDTO createCustomer(CustomerRequestDTO customer);
    public CustomerResponseDTO updateCustomerByPassNum(String passNum);
    public CustomerResponseDTO getCustomerByPassNum(String passNum);
    public List<CustomerResponseDTO> getAllCustomers();
    public void deleteCustomer(Long id);
    public CustomerResponseDTO getCustomerByPhone(String phone);
    public CustomerResponseDTO getCustomerById(Long id);
}
