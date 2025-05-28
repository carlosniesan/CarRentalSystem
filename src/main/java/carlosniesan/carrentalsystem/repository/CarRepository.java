package carlosniesan.carrentalsystem.repository;

import carlosniesan.carrentalsystem.model.Car;
import carlosniesan.carrentalsystem.model.CarType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByAvailableTrue();
    List<Car> findByType(CarType type);
    List<Car> findByTypeAndAvailableTrue(CarType type);
}