package carlosniesan.carrentalsystem.service;

import carlosniesan.carrentalsystem.dto.CustomerDTO;
import carlosniesan.carrentalsystem.mapper.CustomerMapper;
import carlosniesan.carrentalsystem.model.Customer;
import carlosniesan.carrentalsystem.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerService customerService;

    private Customer customer;
    private CustomerDTO customerDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        customer = new Customer(1L, "John Doe", "john@example.com", 0);
        customerDTO = new CustomerDTO(1L, "John Doe", "john@example.com", 0);
    }

    @Test
    void addCustomer() {
        when(customerMapper.toEntity(any(CustomerDTO.class))).thenReturn(customer);
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        when(customerMapper.toDTO(any(Customer.class))).thenReturn(customerDTO);
        
        CustomerDTO result = customerService.addCustomer(customerDTO);
        
        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getName());
        assertEquals("john@example.com", result.getEmail());
        assertEquals(0, result.getLoyaltyPoints());
        
        verify(customerMapper, times(1)).toEntity(any(CustomerDTO.class));
        verify(customerRepository, times(1)).save(any(Customer.class));
        verify(customerMapper, times(1)).toDTO(any(Customer.class));
    }

    @Test
    void addLoyaltyPoints() {
        Customer updatedCustomer = new Customer(1L, "John Doe", "john@example.com", 8);
        
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(updatedCustomer);
        
        customerService.addLoyaltyPoints(1L, 3);
        
        verify(customerRepository, times(1)).findById(1L);
        verify(customerRepository, times(1)).save(any(Customer.class));
    }
}