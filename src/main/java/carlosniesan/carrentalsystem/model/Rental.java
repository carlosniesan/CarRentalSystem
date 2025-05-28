package carlosniesan.carrentalsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rental {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Customer customer;
    
    @ManyToOne
    private Car car;
    
    private LocalDate startDate;
    private LocalDate plannedEndDate;
    private LocalDate actualEndDate;
    
    private int plannedDays;
    private double initialPrice;
    private double extraCharges = 0.0;
    
    @Enumerated(EnumType.STRING)
    private RentalStatus status = RentalStatus.ACTIVE;
    
    private int loyaltyPointsEarned;
    
    public enum RentalStatus {
        ACTIVE, COMPLETED, CANCELLED
    }
}