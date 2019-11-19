package pl.nitl.employeeManager.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.nitl.employeeManager.models.Adress;
import pl.nitl.employeeManager.views.AdressDto;

import java.util.List;

@Mapper(uses = {EmployeeMapper.class})
public interface AdressMapper {

    List<AdressDto> AdressToAdressDto(List<Adress> adresses);
    List<Adress> AdressDtoToAdress(List<AdressDto> adresses);
}
