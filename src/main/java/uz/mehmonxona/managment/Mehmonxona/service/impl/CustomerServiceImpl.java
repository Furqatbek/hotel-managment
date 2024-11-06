package uz.mehmonxona.managment.Mehmonxona.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.mehmonxona.managment.Mehmonxona.domain.dto.request.CustomerRequestDTO;
import uz.mehmonxona.managment.Mehmonxona.domain.dto.response.CustomerResponseDTO;
import uz.mehmonxona.managment.Mehmonxona.domain.entity.Customer;
import uz.mehmonxona.managment.Mehmonxona.repositories.CustomerRepo;
import uz.mehmonxona.managment.Mehmonxona.service.CustomerService;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public CustomerResponseDTO createCustomer(CustomerRequestDTO customer) {
        Customer newCustomer = mapToCustomer(customer);
        Customer savedCustomer = customerRepo.save(newCustomer);
        return mapToDTO(savedCustomer);
    }

    @Override
    public CustomerResponseDTO updateCustomerByPassNum(String passNum) {
        Customer customer = customerRepo.findCustomerByPassNumber(passNum);
        Customer updatedCustomer = customerRepo.save(customer);
        return mapToDTO(updatedCustomer);
    }

    @Override
    public CustomerResponseDTO getCustomerByPassNum(String passNum) {
        Customer customer = customerRepo.findCustomerByPassNumber(passNum);
        return mapToDTO(customer);
    }

    @Override
    public List<CustomerResponseDTO> getAllCustomers() {
        return customerRepo.findAll()
                .stream().map(this::mapToDTO)
                .toList();

    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepo.deleteById(id);
    }

    @Override
    public CustomerResponseDTO getCustomerByPhone(String phone) {
        return mapToDTO(customerRepo.findCustomerByPhone(phone));
    }

    @Override
    public CustomerResponseDTO getCustomerById(Long id) {
        return mapToDTO(customerRepo.getById(id));
    }

    public CustomerResponseDTO mapToDTO(Customer customer) {
        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        customerResponseDTO.setId(customer.getId());
        customerResponseDTO.setFirstName(customer.getFirstName());
        customerResponseDTO.setLastName(customer.getLastName());
        customerResponseDTO.setPhone(customer.getPhone());
        customerResponseDTO.setPassNumber(String.valueOf(customer.getPassNumber()));
        return customerResponseDTO;
    }

    public Customer mapToCustomer(CustomerRequestDTO customerResponseDTO) {
        Customer customer = new Customer();
        customer.setId(customerResponseDTO.getId());
        customer.setFirstName(customerResponseDTO.getFirstName());
        customer.setLastName(customerResponseDTO.getLastName());
        customer.setPhone(customerResponseDTO.getPhone());
        customer.setPassNumber(customerResponseDTO.getPassNumber());
        return customer;
    }
}
