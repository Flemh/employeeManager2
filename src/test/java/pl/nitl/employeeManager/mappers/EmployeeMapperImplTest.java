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
        Employee entity = new Employee();
        entity.setEid(2);
        entity.setFname("John");
        entity.setLname("Wick");
        entity.setPesel(7923799);

        EmployeeDto dto = mapper.employeeToEmployeeDto(entity);

        assertEquals(entity.getEid(), dto.getEmployeeId());
        assertEquals(entity.getFname(), dto.getFirstName());
        assertEquals(entity.getLname(), dto.getLastName());
        assertEquals(entity.getPesel(), dto.getPesel());
    }

    @Test
    void employeeDtoToEmployee() {
        EmployeeDto dto = new EmployeeDto();
        dto.setEmployeeId(1);
        dto.setFirstName("John");
        dto.setLastName("Wick");
        dto.setPesel(7923799);

        Employee entity = mapper.employeeDtoToEmployee(dto);

        assertEquals(dto.getEmployeeId(), entity.getEid());
        assertEquals(dto.getFirstName(), entity.getFname());
        assertEquals(dto.getLastName(), entity.getLname());
        assertEquals(dto.getPesel(), entity.getPesel());
    }
}
