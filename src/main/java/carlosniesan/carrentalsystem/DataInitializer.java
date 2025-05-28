package carlosniesan.carrentalsystem;

import carlosniesan.carrentalsystem.model.Car;
import carlosniesan.carrentalsystem.model.CarType;
import carlosniesan.carrentalsystem.model.Customer;
import carlosniesan.carrentalsystem.repository.CarRepository;
import carlosniesan.carrentalsystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    
    @Autowired
    public DataInitializer(CarRepository carRepository, CustomerRepository customerRepository) {
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }
    
    @Override
    public void run(String... args) {
        // Initialize demo data
        
        // Add some customers
        Customer customer1 = new Customer(null, "John Doe", "john.doe@example.com", 0);
        Customer customer2 = new Customer(null, "Jane Smith", "jane.smith@example.com", 0);
        
        customerRepository.save(customer1);
        customerRepository.save(customer2);
        
        // Add some cars
        Car car1 = new Car(null, "BMW", "7 Series", "ABC123", CarType.PREMIUM, true);
        Car car2 = new Car(null, "Kia", "Sorento", "DEF456", CarType.SUV, true);
        Car car3 = new Car(null, "Nissan", "Juke", "GHI789", CarType.SUV, true);
        Car car4 = new Car(null, "Seat", "Ibiza", "JKL012", CarType.SMALL, true);
        Car car5 = new Car(null, "Mercedes", "S-Class", "MNO345", CarType.PREMIUM, true);
        Car car6 = new Car(null, "Toyota", "RAV4", "PQR678", CarType.SUV, true);
        Car car7 = new Car(null, "Ford", "Fiesta", "STU901", CarType.SMALL, true);
        
        carRepository.save(car1);
        carRepository.save(car2);
        carRepository.save(car3);
        carRepository.save(car4);
        carRepository.save(car5);
        carRepository.save(car6);
        carRepository.save(car7);
    }
}