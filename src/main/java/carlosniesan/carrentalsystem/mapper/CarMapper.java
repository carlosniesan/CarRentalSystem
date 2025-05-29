package carlosniesan.carrentalsystem.mapper;

import carlosniesan.carrentalsystem.dto.CarDTO;
import carlosniesan.carrentalsystem.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    CarDTO toDTO(Car car);
    Car toEntity(CarDTO carDTO);
    List<CarDTO> toDTOList(List<Car> cars);
}