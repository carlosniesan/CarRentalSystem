package carlosniesan.carrentalsystem.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentCarRequestDTO {
    @NotNull
    private Long customerId;
    
    @NotNull
    private List<Long> carIds;
    
    @NotNull
    @Min(1)
    private int days;
}