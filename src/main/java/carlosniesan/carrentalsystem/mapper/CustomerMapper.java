package carlosniesan.carrentalsystem.mapper;

import carlosniesan.carrentalsystem.dto.CustomerDTO;
import carlosniesan.carrentalsystem.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO toDTO(Customer customer);
    Customer toEntity(CustomerDTO customerDTO);
    List<CustomerDTO> toDTOList(List<Customer> customers);
}