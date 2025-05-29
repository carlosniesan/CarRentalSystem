package carlosniesan.carrentalsystem.mapper;

import carlosniesan.carrentalsystem.dto.RentalDTO;
import carlosniesan.carrentalsystem.model.Rental;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RentalMapper {
    RentalMapper INSTANCE = Mappers.getMapper(RentalMapper.class);

    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "car.id", target = "carId")
    RentalDTO toDTO(Rental rental);

    List<RentalDTO> toDTOList(List<Rental> rentals);
}