package carlosniesan.carrentalsystem.controller;

import carlosniesan.carrentalsystem.dto.CarDTO;
import carlosniesan.carrentalsystem.model.CarType;
import carlosniesan.carrentalsystem.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CarControllerTest {

    @Mock
    private CarService carService;

    @InjectMocks
    private CarController carController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCars() {
        List<CarDTO> cars = Arrays.asList(
            new CarDTO(1L, "BMW", "7 Series", "ABC123", CarType.PREMIUM, true),
            new CarDTO(2L, "Kia", "Sorento", "DEF456", CarType.SUV, true)
        );
        
        when(carService.getAllCars()).thenReturn(cars);
        
        ResponseEntity<List<CarDTO>> response = carController.getAllCars();
        
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(carService, times(1)).getAllCars();
    }

    @Test
    void getAvailableCars() {
        List<CarDTO> availableCars = Arrays.asList(
            new CarDTO(1L, "BMW", "7 Series", "ABC123", CarType.PREMIUM, true)
        );
        
        when(carService.getAvailableCars()).thenReturn(availableCars);
        
        ResponseEntity<List<CarDTO>> response = carController.getAvailableCars();
        
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(carService, times(1)).getAvailableCars();
    }
}