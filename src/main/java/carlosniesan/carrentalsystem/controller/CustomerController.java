package carlosniesan.carrentalsystem.controller;

import carlosniesan.carrentalsystem.dto.CustomerDTO;
import carlosniesan.carrentalsystem.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "Customer Management", description = "APIs for managing customers")
public class CustomerController {
    
    private final CustomerService customerService;
    
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    
    @GetMapping
    @Operation(summary = "Get all customers", description = "Retrieve all customers")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get customer by ID", description = "Retrieve a customer by their ID")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }
    
    @PostMapping
    @Operation(summary = "Add a new customer", description = "Add a new customer")
    public ResponseEntity<CustomerDTO> addCustomer(@RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerService.addCustomer(customerDTO));
    }
}