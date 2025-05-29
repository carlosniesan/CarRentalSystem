package carlosniesan.carrentalsystem.service;

import carlosniesan.carrentalsystem.model.Car;
import carlosniesan.carrentalsystem.model.CarType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PriceCalculationServiceTest {

    private PriceCalculationService priceCalculationService;
    private Car premiumCar;
    private Car suvCar;
    private Car smallCar;

    @BeforeEach
    void setUp() {
        priceCalculationService = new PriceCalculationService();
        
        premiumCar = new Car();
        premiumCar.setType(CarType.PREMIUM);
        
        suvCar = new Car();
        suvCar.setType(CarType.SUV);
        
        smallCar = new Car();
        smallCar.setType(CarType.SMALL);
    }

    @Test
    void calculatePremiumCarPrice() {
        // BMW 7 (Premium) 10 days -> 3000€
        double price = priceCalculationService.calculateRentalPrice(premiumCar, 10);
        assertEquals(3000.0, price);
    }

    @Test
    void calculateSUVCarPrice() {
        // Kia Sorento (SUV) 9 days -> 1290€
        double price = priceCalculationService.calculateRentalPrice(suvCar, 9);
        assertEquals(1290.0, price);
        
        // Nissan Juke (SUV) 2 days -> 300€
        price = priceCalculationService.calculateRentalPrice(suvCar, 2);
        assertEquals(300.0, price);
    }

    @Test
    void calculateSmallCarPrice() {
        // Seat Ibiza (small) 10 days -> 440€
        double price = priceCalculationService.calculateRentalPrice(smallCar, 10);
        assertEquals(440.0, price);
    }

    @Test
    void calculateExtraDayCharges() {
        // BMW 7 (Premium) 2 extra days -> 720€
        double extraCharge = priceCalculationService.calculateExtraDayCharge(premiumCar, 2);
        assertEquals(720.0, extraCharge);

        // Nissan Juke (SUV) 1 day extra -> 180€
        extraCharge = priceCalculationService.calculateExtraDayCharge(suvCar, 1);
        assertEquals(180.0, extraCharge);
    }
}