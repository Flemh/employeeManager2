package pl.nitl.employeeManager.mappers;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import pl.nitl.employeeManager.models.Adress;
import pl.nitl.employeeManager.models.AdressType;
import pl.nitl.employeeManager.models.Employee;
import pl.nitl.employeeManager.views.AdressDto;
import pl.nitl.employeeManager.views.EmployeeDto;

import static java.lang.Boolean.TRUE;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
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
                .active(TRUE)
                .adress(Adress.builder().adressId(1).adressType(AdressType.ZAMELDOWANIA).street("boczna").number(7).build())
                .adress(Adress.builder().adressId(2).adressType(AdressType.ZAMIESZKANIA).street("mila").number(5).build())
                .build();
       entity.getAdresses().forEach(adress -> adress.setEmployeeRef(entity));

        EmployeeDto dto = mapper.employeeToEmployeeDto(entity);

        assertEquals(entity.getEid(), dto.getEmployeeId());
        assertEquals(entity.getFname(), dto.getFirstName());
        assertEquals(entity.getLname(), dto.getLastName());
        assertEquals(entity.getPesel(), dto.getPesel());
        assertEquals(entity.isActive(), dto.isActive());
        assertEquals(entity.getAdresses().size(), dto.getAdresses().size());
    }

   @Test
    void employeeDtoToEmployee() {
        EmployeeDto dto = EmployeeDto.builder()
                .employeeId(2)
                .firstName("John")
                .lastName("Wick")
                .pesel(7923799)
                .active(TRUE)
                .adress(AdressDto.builder().adressId(1).adressType(AdressType.ZAMELDOWANIA).street("boczna").number(7).build())
                .adress(AdressDto.builder().adressId(2).adressType(AdressType.ZAMIESZKANIA).street("mila").number(5).build())
                .build();

        Employee entity = mapper.employeeDtoToEmployee(dto);

        assertEquals(dto.getEmployeeId(), entity.getEid());
        assertEquals(dto.getFirstName(), entity.getFname());
        assertEquals(dto.getLastName(), entity.getLname());
        assertEquals(dto.getPesel(), entity.getPesel());
        assertEquals(dto.isActive(), entity.isActive());
        assertEquals(dto.getAdresses().size(), entity.getAdresses().size());
    }
}
