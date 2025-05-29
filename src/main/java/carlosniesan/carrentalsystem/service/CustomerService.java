package carlosniesan.carrentalsystem.service;

import carlosniesan.carrentalsystem.dto.CustomerDTO;
import carlosniesan.carrentalsystem.mapper.CustomerMapper;
import carlosniesan.carrentalsystem.model.Customer;
import carlosniesan.carrentalsystem.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    
    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }
    
    public List<CustomerDTO> getAllCustomers() {
        return customerMapper.toDTOList(customerRepository.findAll());
    }
    
    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));
        return customerMapper.toDTO(customer);
    }
    
    public Customer getCustomerEntityById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));
    }
    
    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        customer = customerRepository.save(customer);
        return customerMapper.toDTO(customer);
    }
    
    public void addLoyaltyPoints(Long customerId, int points) {
        Customer customer = getCustomerEntityById(customerId);
        customer.setLoyaltyPoints(customer.getLoyaltyPoints() + points);
        customerRepository.save(customer);
    }
}