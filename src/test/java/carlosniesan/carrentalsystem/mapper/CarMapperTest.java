package carlosniesan.carrentalsystem.mapper;

import carlosniesan.carrentalsystem.dto.CarDTO;
import carlosniesan.carrentalsystem.model.Car;
import carlosniesan.carrentalsystem.model.CarType;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CarMapperTest {

    private final CarMapper carMapper = Mappers.getMapper(CarMapper.class);

    @Test
    void shouldMapCarToCarDTO() {
        Car car = new Car(1L, "BMW", "7 Series", "ABC123", CarType.PREMIUM, true);
        
        CarDTO carDTO = carMapper.toDTO(car);
        
        assertNotNull(carDTO);
        assertEquals(car.getId(), carDTO.getId());
        assertEquals(car.getBrand(), carDTO.getBrand());
        assertEquals(car.getModel(), carDTO.getModel());
        assertEquals(car.getLicensePlate(), carDTO.getLicensePlate());
        assertEquals(car.getType(), carDTO.getType());
        assertEquals(car.isAvailable(), carDTO.isAvailable());
    }

    @Test
    void shouldMapCarDTOToCar() {
        CarDTO carDTO = new CarDTO(1L, "BMW", "7 Series", "ABC123", CarType.PREMIUM, true);
        
        Car car = carMapper.toEntity(carDTO);
        
        assertNotNull(car);
        assertEquals(carDTO.getId(), car.getId());
        assertEquals(carDTO.getBrand(), car.getBrand());
        assertEquals(carDTO.getModel(), car.getModel());
        assertEquals(carDTO.getLicensePlate(), car.getLicensePlate());
        assertEquals(carDTO.getType(), car.getType());
        assertEquals(carDTO.isAvailable(), car.isAvailable());
    }

    @Test
    void shouldMapCarListToCarDTOList() {
        List<Car> cars = Arrays.asList(
            new Car(1L, "BMW", "7 Series", "ABC123", CarType.PREMIUM, true),
            new Car(2L, "Kia", "Sorento", "DEF456", CarType.SUV, true)
        );
        
        List<CarDTO> carDTOs = carMapper.toDTOList(cars);
        
        assertNotNull(carDTOs);
        assertEquals(2, carDTOs.size());
        assertEquals(cars.get(0).getId(), carDTOs.get(0).getId());
        assertEquals(cars.get(1).getId(), carDTOs.get(1).getId());
    }
}