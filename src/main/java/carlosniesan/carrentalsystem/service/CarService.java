package carlosniesan.carrentalsystem.service;

import carlosniesan.carrentalsystem.dto.CarDTO;
import carlosniesan.carrentalsystem.mapper.CarMapper;
import carlosniesan.carrentalsystem.model.Car;
import carlosniesan.carrentalsystem.repository.CarRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    
    private final CarRepository carRepository;
    private final CarMapper carMapper;
    
    @Autowired
    public CarService(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }
    
    public List<CarDTO> getAllCars() {
        return carMapper.toDTOList(carRepository.findAll());
    }
    
    public List<CarDTO> getAvailableCars() {
        return carMapper.toDTOList(carRepository.findByAvailableTrue());
    }
    
    public CarDTO getCarById(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car not found with id: " + id));
        return carMapper.toDTO(car);
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
        Car car = carMapper.toEntity(carDTO);
        car = carRepository.save(car);
        return carMapper.toDTO(car);
    }
    
    public void updateCarAvailability(Long id, boolean available) {
        Car car = getCarEntityById(id);
        car.setAvailable(available);
        carRepository.save(car);
    }
}