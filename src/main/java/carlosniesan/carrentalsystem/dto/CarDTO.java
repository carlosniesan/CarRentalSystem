package carlosniesan.carrentalsystem.dto;

import carlosniesan.carrentalsystem.model.CarType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {
    private Long id;
    private String brand;
    private String model;
    private String licensePlate;
    private CarType type;
    private boolean available;
}