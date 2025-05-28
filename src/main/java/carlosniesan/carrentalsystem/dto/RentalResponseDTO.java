package carlosniesan.carrentalsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalResponseDTO {
    private List<RentalDTO> rentals;
    private double totalPrice;
    private int totalLoyaltyPointsEarned;
}