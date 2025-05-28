package carlosniesan.carrentalsystem.service;

import carlosniesan.carrentalsystem.dto.CarDTO;
import carlosniesan.carrentalsystem.model.Car;
import carlosniesan.carrentalsystem.repository.CarRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    
    private final CarRepository carRepository;
    
    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
    
    public List<CarDTO> getAllCars() {
        return carRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<CarDTO> getAvailableCars() {
        return carRepository.findByAvailableTrue().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public CarDTO getCarById(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car not found with id: " + id));
        return convertToDTO(car);
    }
    
    public Car getCarEntityById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car not found with id: " + id));
    }
    
    public List<Car> getCarEntitiesByIds(List<Long> ids) {
        List<Car> cars = carRepository.findAllById(ids);
        if (cars.size() != ids.size()) {
            throw new EntityNotFoundException("One or more cars not found");
        }
        return cars;
    }
    
    public CarDTO addCar(CarDTO carDTO) {
        Car car = convertToEntity(carDTO);
        car = carRepository.save(car);
        return convertToDTO(car);
    }
    
    public void updateCarAvailability(Long id, boolean available) {
        Car car = getCarEntityById(id);
        car.setAvailable(available);
        carRepository.save(car);
    }
    
    private CarDTO convertToDTO(Car car) {
        return new CarDTO(
                car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getLicensePlate(),
                car.getType(),
                car.isAvailable()
        );
    }
    
    private Car convertToEntity(CarDTO carDTO) {
        return new Car(
                carDTO.getId(),
                carDTO.getBrand(),
                carDTO.getModel(),
                carDTO.getLicensePlate(),
                carDTO.getType(),
                carDTO.isAvailable()
        );
    }
}