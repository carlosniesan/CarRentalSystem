package carlosniesan.carrentalsystem.service;

import carlosniesan.carrentalsystem.dto.CustomerDTO;
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

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addCustomer() {
        CustomerDTO customerDTO = new CustomerDTO(null, "John Doe", "john@example.com", 0);
        Customer customer = new Customer(1L, "John Doe", "john@example.com", 0);
        
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        
        CustomerDTO result = customerService.addCustomer(customerDTO);
        
        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getName());
        assertEquals("john@example.com", result.getEmail());
        assertEquals(0, result.getLoyaltyPoints());
        
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void addLoyaltyPoints() {
        Customer customer = new Customer(1L, "John Doe", "john@example.com", 5);
        Customer updatedCustomer = new Customer(1L, "John Doe", "john@example.com", 8);
        
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(updatedCustomer);
        
        customerService.addLoyaltyPoints(1L, 3);
        
        verify(customerRepository, times(1)).findById(1L);
        verify(customerRepository, times(1)).save(any(Customer.class));
    }
}