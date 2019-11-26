package pl.nitl.employeeManager.mappers;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import pl.nitl.employeeManager.models.Address;
import pl.nitl.employeeManager.models.AddressType;
import pl.nitl.employeeManager.models.Employee;
import pl.nitl.employeeManager.views.AddressDto;
import pl.nitl.employeeManager.views.EmployeeDto;

import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class EmployeeMapperTest {

    private EmployeeMapper mapper
            = Mappers.getMapper(EmployeeMapper.class);

    @Test
    void employeeToEmployeeDto() {
        //given
        Employee entity = Employee.builder()
                .eid(2)
                .fname("John")
                .lname("Wick")
                .pesel(7923799)
                .active(TRUE)
                .address(Address.builder().addressId(1).addressType(AddressType.CURRENT).street("boczna").number(7).build())
                .address(Address.builder().addressId(2).addressType(AddressType.INVOICING).street("mila").number(5).build())
                .build();
        entity.getAddresses().forEach(address -> address.setEmployeeRef(entity));
        //when
        EmployeeDto dto = mapper.employeeToEmployeeDto(entity);
        //than
        assertEquals(entity.getEid(), dto.getEmployeeId());
        assertEquals(entity.getFname(), dto.getFirstName());
        assertEquals(entity.getLname(), dto.getLastName());
        assertEquals(entity.getPesel(), dto.getPesel());
        assertEquals(entity.isActive(), dto.isActive());
        assertEquals(entity.getAddresses().size(), dto.getAddresses().size());
        assertNull(mapper.employeeToEmployeeDto(null));
    }

    @Test
    void employeeDtoToEmployee() {
        //given
        EmployeeDto dto = EmployeeDto.builder()
                .employeeId(2)
                .firstName("John")
                .lastName("Wick")
                .pesel(7923799)
                .active(TRUE)
                .address(AddressDto.builder().addressId(1).addressType(AddressType.CURRENT).street("boczna").number(7).build())
                .address(AddressDto.builder().addressId(2).addressType(AddressType.INVOICING).street("mila").number(5).build())
                .build();
        //when
        Employee entity = mapper.employeeDtoToEmployee(dto);
        //than
        assertEquals(dto.getEmployeeId(), entity.getEid());
        assertEquals(dto.getFirstName(), entity.getFname());
        assertEquals(dto.getLastName(), entity.getLname());
        assertEquals(dto.getPesel(), entity.getPesel());
        assertEquals(dto.isActive(), entity.isActive());
        assertEquals(dto.getAddresses().size(), entity.getAddresses().size());
        assertNull(mapper.employeeDtoToEmployee(null));
    }
}
