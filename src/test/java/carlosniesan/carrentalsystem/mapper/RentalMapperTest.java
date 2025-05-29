package carlosniesan.carrentalsystem.mapper;

import carlosniesan.carrentalsystem.dto.RentalDTO;
import carlosniesan.carrentalsystem.model.Car;
import carlosniesan.carrentalsystem.model.CarType;
import carlosniesan.carrentalsystem.model.Customer;
import carlosniesan.carrentalsystem.model.Rental;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RentalMapperTest {

    private final RentalMapper rentalMapper = Mappers.getMapper(RentalMapper.class);

    @Test
    void shouldMapRentalToRentalDTO() {
        Customer customer = new Customer(1L, "John Doe", "john@example.com", 5);
        Car car = new Car(1L, "BMW", "7 Series", "ABC123", CarType.PREMIUM, false);
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(5);
        
        Rental rental = new Rental();
        rental.setId(1L);
        rental.setCustomer(customer);
        rental.setCar(car);
        rental.setStartDate(startDate);
        rental.setPlannedEndDate(endDate);
        rental.setPlannedDays(5);
        rental.setInitialPrice(1500.0);
        rental.setStatus(Rental.RentalStatus.ACTIVE);
        rental.setLoyaltyPointsEarned(5);
        
        RentalDTO rentalDTO = rentalMapper.toDTO(rental);
        
        assertNotNull(rentalDTO);
        assertEquals(rental.getId(), rentalDTO.getId());
        assertEquals(customer.getId(), rentalDTO.getCustomerId());
        assertEquals(car.getId(), rentalDTO.getCarId());
        assertEquals(rental.getStartDate(), rentalDTO.getStartDate());
        assertEquals(rental.getPlannedEndDate(), rentalDTO.getPlannedEndDate());
        assertEquals(rental.getPlannedDays(), rentalDTO.getPlannedDays());
        assertEquals(rental.getInitialPrice(), rentalDTO.getInitialPrice());
        assertEquals(rental.getStatus(), rentalDTO.getStatus());
        assertEquals(rental.getLoyaltyPointsEarned(), rentalDTO.getLoyaltyPointsEarned());
    }

    @Test
    void shouldMapRentalListToRentalDTOList() {
        Customer customer = new Customer(1L, "John Doe", "john@example.com", 5);
        Car car = new Car(1L, "BMW", "7 Series", "ABC123", CarType.PREMIUM, false);
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(5);
        
        Rental rental1 = new Rental();
        rental1.setId(1L);
        rental1.setCustomer(customer);
        rental1.setCar(car);
        rental1.setStartDate(startDate);
        rental1.setPlannedEndDate(endDate);
        rental1.setPlannedDays(5);
        rental1.setInitialPrice(1500.0);
        rental1.setStatus(Rental.RentalStatus.ACTIVE);
        rental1.setLoyaltyPointsEarned(5);
        
        Rental rental2 = new Rental();
        rental2.setId(2L);
        rental2.setCustomer(customer);
        rental2.setCar(car);
        rental2.setStartDate(startDate);
        rental2.setPlannedEndDate(endDate);
        rental2.setPlannedDays(5);
        rental2.setInitialPrice(1500.0);
        rental2.setStatus(Rental.RentalStatus.ACTIVE);
        rental2.setLoyaltyPointsEarned(5);
        
        List<Rental> rentals = Arrays.asList(rental1, rental2);
        
        List<RentalDTO> rentalDTOs = rentalMapper.toDTOList(rentals);
        
        assertNotNull(rentalDTOs);
        assertEquals(2, rentalDTOs.size());
        assertEquals(rentals.get(0).getId(), rentalDTOs.get(0).getId());
        assertEquals(rentals.get(1).getId(), rentalDTOs.get(1).getId());
    }
}