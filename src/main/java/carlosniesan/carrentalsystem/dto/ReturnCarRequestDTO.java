package carlosniesan.carrentalsystem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnCarRequestDTO {
    @NotNull
    private Long rentalId;
    
    @NotNull
    private LocalDate returnDate;
}