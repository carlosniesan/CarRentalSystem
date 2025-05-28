package carlosniesan.carrentalsystem.controller;

import carlosniesan.carrentalsystem.dto.CarDTO;
import carlosniesan.carrentalsystem.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@Tag(name = "Car Management", description = "APIs for managing car inventory")
public class CarController {
    
    private final CarService carService;
    
    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }
    
    @GetMapping
    @Operation(summary = "Get all cars", description = "Retrieve all cars in the inventory")
    public ResponseEntity<List<CarDTO>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }
    
    @GetMapping("/available")
    @Operation(summary = "Get available cars", description = "Retrieve all cars that are available for rent")
    public ResponseEntity<List<CarDTO>> getAvailableCars() {
        return ResponseEntity.ok(carService.getAvailableCars());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get car by ID", description = "Retrieve a car by its ID")
    public ResponseEntity<CarDTO> getCarById(@PathVariable Long id) {
        return ResponseEntity.ok(carService.getCarById(id));
    }
    
    @PostMapping
    @Operation(summary = "Add a new car", description = "Add a new car to the inventory")
    public ResponseEntity<CarDTO> addCar(@RequestBody CarDTO carDTO) {
        return ResponseEntity.ok(carService.addCar(carDTO));
    }
}