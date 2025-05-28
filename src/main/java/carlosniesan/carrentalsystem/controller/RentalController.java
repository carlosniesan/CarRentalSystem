package carlosniesan.carrentalsystem.controller;

import carlosniesan.carrentalsystem.dto.RentalDTO;
import carlosniesan.carrentalsystem.dto.RentalResponseDTO;
import carlosniesan.carrentalsystem.dto.RentCarRequestDTO;
import carlosniesan.carrentalsystem.dto.ReturnCarRequestDTO;
import carlosniesan.carrentalsystem.dto.ReturnResponseDTO;
import carlosniesan.carrentalsystem.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
@Tag(name = "Rental Management", description = "APIs for managing car rentals")
public class RentalController {
    
    private final RentalService rentalService;
    
    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }
    
    @GetMapping
    @Operation(summary = "Get all rentals", description = "Retrieve all rentals")
    public ResponseEntity<List<RentalDTO>> getAllRentals() {
        return ResponseEntity.ok(rentalService.getAllRentals());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get rental by ID", description = "Retrieve a rental by its ID")
    public ResponseEntity<RentalDTO> getRentalById(@PathVariable Long id) {
        return ResponseEntity.ok(rentalService.getRentalById(id));
    }
    
    @PostMapping("/rent")
    @Operation(
            summary = "Rent cars", 
            description = "Rent one or more cars for a specified number of days and calculate the price"
    )
    public ResponseEntity<RentalResponseDTO> rentCars(@Valid @RequestBody RentCarRequestDTO request) {
        return ResponseEntity.ok(rentalService.rentCars(request));
    }
    
    @PostMapping("/return")
    @Operation(
            summary = "Return a car", 
            description = "Return a rented car and calculate any surcharges for late returns"
    )
    public ResponseEntity<ReturnResponseDTO> returnCar(@Valid @RequestBody ReturnCarRequestDTO request) {
        return ResponseEntity.ok(rentalService.returnCar(request));
    }
}