package pl.nitl.employeeManager.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import pl.nitl.employeeManager.models.Employee;
import pl.nitl.employeeManager.views.EmployeeDto;

@Mapper(uses ={AdressMapper.class})
public interface EmployeeMapper {

    @Mappings({
            @Mapping(target = "employeeId", source = "eid"),
            @Mapping(target = "firstName", source = "fname"),
            @Mapping(target = "lastName", source = "lname")

    })
    EmployeeDto employeeToEmployeeDto(Employee entity);

    @Mappings({
            @Mapping(target = "eid", source = "employeeId"),
            @Mapping(target = "fname", source = "firstName"),
            @Mapping(target = "lname", source = "lastName")

    })
    Employee employeeDtoToEmployee(EmployeeDto dto);

}
