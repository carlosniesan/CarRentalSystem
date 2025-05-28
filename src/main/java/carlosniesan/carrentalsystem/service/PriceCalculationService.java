package carlosniesan.carrentalsystem.service;

import carlosniesan.carrentalsystem.model.Car;
import carlosniesan.carrentalsystem.model.CarType;
import org.springframework.stereotype.Service;

@Service
public class PriceCalculationService {
    
    public double calculateRentalPrice(Car car, int days) {
        CarType carType = car.getType();
        
        switch (carType) {
            case PREMIUM:
                return calculatePremiumPrice(days);
            case SUV:
                return calculateSUVPrice(days);
            case SMALL:
                return calculateSmallPrice(days);
            default:
                throw new IllegalArgumentException("Unknown car type");
        }
    }
    
    public double calculateExtraDayCharge(Car car, int extraDays) {
        CarType carType = car.getType();
        double basePrice = carType.getBasePrice();
        double extraDayRate = carType.getExtraDaySurchargeRate();
        
        switch (carType) {
            case PREMIUM:
                return extraDays * (basePrice + (basePrice * extraDayRate));
            case SUV:
                // SUV extra day: SUV price + 60% of small price per day
                return extraDays * (basePrice + (CarType.SMALL.getBasePrice() * extraDayRate));
            case SMALL:
                // Small extra day: small price + 30% of small price
                return extraDays * (basePrice + (basePrice * extraDayRate));
            default:
                throw new IllegalArgumentException("Unknown car type");
        }
    }
    
    private double calculatePremiumPrice(int days) {
        // Premium cars - Price is premium price times number of days rented
        return days * CarType.PREMIUM.getBasePrice();
    }
    
    private double calculateSUVPrice(int days) {
        double basePrice = CarType.SUV.getBasePrice();
        double totalPrice = 0;
        
        // For the first 7 days: SUV price per day
        if (days <= 7) {
            totalPrice = days * basePrice;
        } else if (days <= 30) {
            // Between 7 and 30 days: first 7 at full price, rest at 80%
            totalPrice = 7 * basePrice + (days - 7) * (basePrice * 0.8);
        } else {
            // More than 30 days: first 7 at full price, next 23 at 80%, rest at 50%
            totalPrice = 7 * basePrice + 23 * (basePrice * 0.8) + (days - 30) * (basePrice * 0.5);
        }
        
        return totalPrice;
    }
    
    private double calculateSmallPrice(int days) {
        double basePrice = CarType.SMALL.getBasePrice();
        double totalPrice = 0;
        
        // For the first 7 days: small price per day
        if (days <= 7) {
            totalPrice = days * basePrice;
        } else {
            // More than 7 days: first 7 at full price, rest at 60%
            totalPrice = 7 * basePrice + (days - 7) * (basePrice * 0.6);
        }
        
        return totalPrice;
    }
}