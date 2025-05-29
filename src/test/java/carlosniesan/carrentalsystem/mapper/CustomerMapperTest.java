package carlosniesan.carrentalsystem.mapper;

import carlosniesan.carrentalsystem.dto.CustomerDTO;
import carlosniesan.carrentalsystem.model.Customer;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CustomerMapperTest {

    private final CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);

    @Test
    void shouldMapCustomerToCustomerDTO() {
        Customer customer = new Customer(1L, "John Doe", "john@example.com", 5);
        
        CustomerDTO customerDTO = customerMapper.toDTO(customer);
        
        assertNotNull(customerDTO);
        assertEquals(customer.getId(), customerDTO.getId());
        assertEquals(customer.getName(), customerDTO.getName());
        assertEquals(customer.getEmail(), customerDTO.getEmail());
        assertEquals(customer.getLoyaltyPoints(), customerDTO.getLoyaltyPoints());
    }

    @Test
    void shouldMapCustomerDTOToCustomer() {
        CustomerDTO customerDTO = new CustomerDTO(1L, "John Doe", "john@example.com", 5);
        
        Customer customer = customerMapper.toEntity(customerDTO);
        
        assertNotNull(customer);
        assertEquals(customerDTO.getId(), customer.getId());
        assertEquals(customerDTO.getName(), customer.getName());
        assertEquals(customerDTO.getEmail(), customer.getEmail());
        assertEquals(customerDTO.getLoyaltyPoints(), customer.getLoyaltyPoints());
    }

    @Test
    void shouldMapCustomerListToCustomerDTOList() {
        List<Customer> customers = Arrays.asList(
            new Customer(1L, "John Doe", "john@example.com", 5),
            new Customer(2L, "Jane Smith", "jane@example.com", 10)
        );
        
        List<CustomerDTO> customerDTOs = customerMapper.toDTOList(customers);
        
        assertNotNull(customerDTOs);
        assertEquals(2, customerDTOs.size());
        assertEquals(customers.get(0).getId(), customerDTOs.get(0).getId());
        assertEquals(customers.get(1).getId(), customerDTOs.get(1).getId());
    }
}