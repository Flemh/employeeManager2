package pl.nitl.employeeManager.mappers;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import pl.nitl.employeeManager.models.Employee;
import pl.nitl.employeeManager.views.EmployeeDto;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeMapperImplTest {

    private EmployeeMapper mapper
            = Mappers.getMapper(EmployeeMapper.class);

    @Test
    void employeeToEmployeeDto() {
        Employee entity = Employee.builder()
                .eid(2)
                .fname("John")
                .lname("Wick")
                .pesel(7923799)
                .build();

        EmployeeDto dto = mapper.employeeToEmployeeDto(entity);

        assertEquals(entity.getEid(), dto.getEmployeeId());
        assertEquals(entity.getFname(), dto.getFirstName());
        assertEquals(entity.getLname(), dto.getLastName());
        assertEquals(entity.getPesel(), dto.getPesel());
    }

    @Test
    void employeeDtoToEmployee() {
        EmployeeDto dto = EmployeeDto.builder()
                .employeeId(2)
                .firstName("John")
                .lastName("Wick")
                .pesel(7923799)
                .build();

        Employee entity = mapper.employeeDtoToEmployee(dto);

        assertEquals(dto.getEmployeeId(), entity.getEid());
        assertEquals(dto.getFirstName(), entity.getFname());
        assertEquals(dto.getLastName(), entity.getLname());
        assertEquals(dto.getPesel(), entity.getPesel());
    }
}
