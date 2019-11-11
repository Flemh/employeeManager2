package pl.nitl.employeeManager.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.nitl.employeeManager.models.Employee;
import pl.nitl.employeeManager.views.EmployeeDto;

@Mapper
public interface EmployeeMapper {
    @Mappings({
            @Mapping(target = "employeeId", source = "entity.eid"),
            @Mapping(target = "firstName", source = "entity.fname"),
            @Mapping(target = "lastName", source = "entity.lname")
    })
    EmployeeDto employeeToEmployeeDto(Employee entity);

    @Mappings({
            @Mapping(target = "eid", source = "dto.employeeId"),
            @Mapping(target = "fname", source = "dto.firstName"),
            @Mapping(target = "lname", source = "dto.lastName")
    })
    Employee employeeDtoToEmployee(EmployeeDto dto);

}
