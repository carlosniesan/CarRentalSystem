package carlosniesan.carrentalsystem.dto;

import carlosniesan.carrentalsystem.model.Rental;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalDTO {
    private Long id;
    private Long customerId;
    private Long carId;
    private LocalDate startDate;
    private LocalDate plannedEndDate;
    private LocalDate actualEndDate;
    private int plannedDays;
    private double initialPrice;
    private double extraCharges;
    private Rental.RentalStatus status;
    private int loyaltyPointsEarned;
}