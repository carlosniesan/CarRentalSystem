package carlosniesan.carrentalsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnResponseDTO {
    private RentalDTO rental;
    private int extraDays;
    private double extraCharge;
}