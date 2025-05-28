package carlosniesan.carrentalsystem.service;

import carlosniesan.carrentalsystem.dto.RentalDTO;
import carlosniesan.carrentalsystem.dto.RentalResponseDTO;
import carlosniesan.carrentalsystem.dto.RentCarRequestDTO;
import carlosniesan.carrentalsystem.dto.ReturnCarRequestDTO;
import carlosniesan.carrentalsystem.dto.ReturnResponseDTO;
import carlosniesan.carrentalsystem.model.Car;
import carlosniesan.carrentalsystem.model.Customer;
import carlosniesan.carrentalsystem.model.Rental;
import carlosniesan.carrentalsystem.repository.RentalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalService {
    
    private final RentalRepository rentalRepository;
    private final CarService carService;
    private final CustomerService customerService;
    private final PriceCalculationService priceCalculationService;
    
    @Autowired
    public RentalService(
            RentalRepository rentalRepository,
            CarService carService,
            CustomerService customerService,
            PriceCalculationService priceCalculationService) {
        this.rentalRepository = rentalRepository;
        this.carService = carService;
        this.customerService = customerService;
        this.priceCalculationService = priceCalculationService;
    }
    
    public List<RentalDTO> getAllRentals() {
        return rentalRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public RentalDTO getRentalById(Long id) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rental not found with id: " + id));
        return convertToDTO(rental);
    }
    
    @Transactional
    public RentalResponseDTO rentCars(RentCarRequestDTO request) {
        Customer customer = customerService.getCustomerEntityById(request.getCustomerId());
        List<Car> cars = carService.getCarEntitiesByIds(request.getCarIds());
        
        // Check if all cars are available
        for (Car car : cars) {
            if (!car.isAvailable()) {
                throw new IllegalStateException("Car with id " + car.getId() + " is not available for rent");
            }
        }
        
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(request.getDays());
        
        List<Rental> rentals = new ArrayList<>();
        double totalPrice = 0;
        int totalLoyaltyPoints = 0;
        
        for (Car car : cars) {
            double price = priceCalculationService.calculateRentalPrice(car, request.getDays());
            int loyaltyPoints = car.getType().getLoyaltyPoints();
            
            Rental rental = new Rental();
            rental.setCustomer(customer);
            rental.setCar(car);
            rental.setStartDate(startDate);
            rental.setPlannedEndDate(endDate);
            rental.setPlannedDays(request.getDays());
            rental.setInitialPrice(price);
            rental.setLoyaltyPointsEarned(loyaltyPoints);
            
            Rental savedRental = rentalRepository.save(rental);
            rentals.add(savedRental);
            
            // Update car availability
            carService.updateCarAvailability(car.getId(), false);
            
            totalPrice += price;
            totalLoyaltyPoints += loyaltyPoints;
        }
        
        // Add loyalty points to customer
        customerService.addLoyaltyPoints(customer.getId(), totalLoyaltyPoints);
        
        List<RentalDTO> rentalDTOs = rentals.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        
        return new RentalResponseDTO(rentalDTOs, totalPrice, totalLoyaltyPoints);
    }
    
    @Transactional
    public ReturnResponseDTO returnCar(ReturnCarRequestDTO request) {
        Rental rental = rentalRepository.findById(request.getRentalId())
                .orElseThrow(() -> new EntityNotFoundException("Rental not found with id: " + request.getRentalId()));
        
        if (rental.getStatus() != Rental.RentalStatus.ACTIVE) {
            throw new IllegalStateException("Rental is not active");
        }
        
        LocalDate returnDate = request.getReturnDate();
        if (returnDate.isBefore(rental.getStartDate())) {
            throw new IllegalArgumentException("Return date cannot be before the rental start date");
        }
        
        rental.setActualEndDate(returnDate);
        rental.setStatus(Rental.RentalStatus.COMPLETED);
        
        // Make the car available again
        carService.updateCarAvailability(rental.getCar().getId(), true);
        
        // Calculate extra charges if returned late
        double extraCharge = 0;
        int extraDays = 0;
        
        if (returnDate.isAfter(rental.getPlannedEndDate())) {
            extraDays = (int) ChronoUnit.DAYS.between(rental.getPlannedEndDate(), returnDate);
            extraCharge = priceCalculationService.calculateExtraDayCharge(rental.getCar(), extraDays);
            rental.setExtraCharges(extraCharge);
        }
        
        Rental updatedRental = rentalRepository.save(rental);
        
        return new ReturnResponseDTO(convertToDTO(updatedRental), extraDays, extraCharge);
    }
    
    private RentalDTO convertToDTO(Rental rental) {
        return new RentalDTO(
                rental.getId(),
                rental.getCustomer().getId(),
                rental.getCar().getId(),
                rental.getStartDate(),
                rental.getPlannedEndDate(),
                rental.getActualEndDate(),
                rental.getPlannedDays(),
                rental.getInitialPrice(),
                rental.getExtraCharges(),
                rental.getStatus(),
                rental.getLoyaltyPointsEarned()
        );
    }
}