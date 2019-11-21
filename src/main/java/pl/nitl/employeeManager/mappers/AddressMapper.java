package pl.nitl.employeeManager.mappers;

import org.mapstruct.Mapper;
import pl.nitl.employeeManager.models.Address;
import pl.nitl.employeeManager.views.AddressDto;
import java.util.List;

@Mapper(uses = {EmployeeMapper.class})
public interface AddressMapper {

    List<AddressDto> addressToAddressDto(List<Address> addresses);
    List<Address> addressDtoToAddress(List<AddressDto> addresses);
}
