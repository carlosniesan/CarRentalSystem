package carlosniesan.carrentalsystem.service;

import carlosniesan.carrentalsystem.dto.CustomerDTO;
import carlosniesan.carrentalsystem.model.Customer;
import carlosniesan.carrentalsystem.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    
    private final CustomerRepository customerRepository;
    
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));
        return convertToDTO(customer);
    }
    
    public Customer getCustomerEntityById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));
    }
    
    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        Customer customer = convertToEntity(customerDTO);
        customer = customerRepository.save(customer);
        return convertToDTO(customer);
    }
    
    public void addLoyaltyPoints(Long customerId, int points) {
        Customer customer = getCustomerEntityById(customerId);
        customer.setLoyaltyPoints(customer.getLoyaltyPoints() + points);
        customerRepository.save(customer);
    }
    
    private CustomerDTO convertToDTO(Customer customer) {
        return new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getLoyaltyPoints()
        );
    }
    
    private Customer convertToEntity(CustomerDTO customerDTO) {
        return new Customer(
                customerDTO.getId(),
                customerDTO.getName(),
                customerDTO.getEmail(),
                customerDTO.getLoyaltyPoints()
        );
    }
}