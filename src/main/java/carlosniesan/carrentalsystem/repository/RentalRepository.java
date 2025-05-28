package carlosniesan.carrentalsystem.repository;

import carlosniesan.carrentalsystem.model.Car;
import carlosniesan.carrentalsystem.model.Customer;
import carlosniesan.carrentalsystem.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByCustomer(Customer customer);
    List<Rental> findByCar(Car car);
    List<Rental> findByStatus(Rental.RentalStatus status);
}