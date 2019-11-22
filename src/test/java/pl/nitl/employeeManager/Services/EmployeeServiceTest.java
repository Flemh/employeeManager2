package pl.nitl.employeeManager.Services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.nitl.employeeManager.exceptions.*;
import pl.nitl.employeeManager.models.Address;
import pl.nitl.employeeManager.models.AddressType;
import pl.nitl.employeeManager.models.Employee;
import pl.nitl.employeeManager.respositories.EmployeeRespository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    private static final int ANY_INT = 27;
    private static final String ANY_STRING = "AnyString";
    private static final String MORE_THAN_FIVE= "more than five";
    private static final String FIVE_LONG_STRING= "five5";

    @Mock
    private EmployeeRespository mockRepo;

    @InjectMocks
    EmployeeService employeeService = new EmployeeService();

    @Test
    void checkAndSaveShouldBeOk() {
        //given
        Employee employee = Employee.builder()
                .eid(1)
                .fname("anyString")
                .lname("anyString")
                .pesel(ANY_INT)
                .address(Address.builder().addressId(1).addressType(AddressType.CURRENT).street("anyString").number(ANY_INT).build())
                .address(Address.builder().addressId(2).addressType(AddressType.INVOICING).street("anyString").number(ANY_INT).build())
                .build();
        //when
       employeeService.checkAndSave(employee);
       //than
        verify(mockRepo,times(1)).save(any(Employee.class));
    }

    @Test
    void checkAndSaveShouldThrowIncorrectAddressesListSizeException(){
        //given
        Employee employee = Employee.builder()
                .eid(1)
                .fname(ANY_STRING)
                .lname(ANY_STRING)
                .pesel(ANY_INT)
                .address(Address.builder().addressId(1).addressType(AddressType.CURRENT).street(ANY_STRING).number(ANY_INT).build())
                .address(Address.builder().addressId(2).addressType(AddressType.INVOICING).street(ANY_STRING).number(ANY_INT).build())
                .address(Address.builder().addressId(3).addressType(AddressType.POSTAL).street(ANY_STRING).number(ANY_INT).build())
                .build();
        //when
        //than
        Assertions.assertThrows(BadRequestException.class, () ->  employeeService.checkAndSave(employee) );
    }
    @Test
    void checkAndSaveShouldThrowIncorrectInvoicingNumberException(){
        //given
        Employee employee = Employee.builder()
                .eid(1)
                .fname(ANY_STRING)
                .lname(ANY_STRING)
                .pesel(ANY_INT)
                .address(Address.builder().addressId(1).addressType(AddressType.INVOICING).street(ANY_STRING).number(ANY_INT).build())
                .address(Address.builder().addressId(2).addressType(AddressType.INVOICING).street(ANY_STRING).number(ANY_INT).build())
                .build();
        //when
        //than
        Assertions.assertThrows(BadRequestException.class, () -> employeeService.checkAndSave(employee));
    }

    @Test
    void checkAndUpdateShouldThrowEmployeeNotFoundException() {
        //given
        Employee employee = Employee.builder()
                .eid(1)
                .fname(ANY_STRING)
                .lname(ANY_STRING)
                .pesel(ANY_INT)
                .address(Address.builder().addressId(1).addressType(AddressType.CURRENT).street(ANY_STRING).number(ANY_INT).build())
                .address(Address.builder().addressId(2).addressType(AddressType.INVOICING).street(ANY_STRING).number(ANY_INT).build())
                .build();

        when(mockRepo.findById(anyInt())).thenReturn(Optional.empty());
        //when
        //than
        Assertions.assertThrows(MyNotFoundException.class, () -> employeeService.checkAndUpdate(employee,1));
    }

    @Test
    void checkAndUpdateShouldBeOk() {
        //given
        Employee employee = Employee.builder()
                .eid(1)
                .fname(ANY_STRING)
                .lname(ANY_STRING)
                .pesel(ANY_INT)
                .address(Address.builder().addressId(1).addressType(AddressType.CURRENT).street(ANY_STRING).number(ANY_INT).build())
                .address(Address.builder().addressId(2).addressType(AddressType.INVOICING).street(ANY_STRING).number(ANY_INT).build())
                .build();

        when(mockRepo.save(any(Employee.class))).thenReturn(employee);
        when(mockRepo.findById(anyInt())).thenReturn(Optional.of(employee));
        //when
        employeeService.checkAndUpdate(employee,1);
        //than
        verify(mockRepo,times(1)).save(any(Employee.class));
    }

    @Test
    void deleteShouldThrowEmployeeNotFoundException() {
        //given
        when(mockRepo.findById(anyInt())).thenReturn(Optional.empty());
        //when
        //than
        Assertions.assertThrows(MyNotFoundException.class, () -> employeeService.delete(1));
    }

    @Test
    void deleteShouldBeOk() {
        //given
        Employee employee = Employee.builder()
                .eid(1)
                .fname(ANY_STRING)
                .lname(ANY_STRING)
                .pesel(ANY_INT)
                .address(Address.builder().addressId(1).addressType(AddressType.CURRENT).street(ANY_STRING).number(ANY_INT).build())
                .address(Address.builder().addressId(2).addressType(AddressType.INVOICING).street(ANY_STRING).number(ANY_INT).build())
                .build();

        doNothing().when(mockRepo).delete(any(Employee.class));
        when(mockRepo.findById(anyInt())).thenReturn(Optional.of(employee));
        //when
        employeeService.delete(1);
        //than
        verify(mockRepo,times(1)).delete(any(Employee.class));
    }

    @Test
    void deleteWith5ShouldThrowEmptyListException() {
        //given
        when(mockRepo.findAll()).thenReturn(null);
        //when
        //than
        Assertions.assertThrows(MyNotFoundException.class, () -> employeeService.deleteWith5());
    }

    @Test
    void deleteWith5ShouldThereIsNoOneWith5Exception() {
        //given
        Employee employee1 = Employee.builder()
                .eid(1)
                .fname(ANY_STRING)
                .lname(MORE_THAN_FIVE)
                .pesel(ANY_INT)
                .address(Address.builder().addressId(1).addressType(AddressType.CURRENT).street(ANY_STRING).number(ANY_INT).build())
                .address(Address.builder().addressId(2).addressType(AddressType.INVOICING).street(ANY_STRING).number(ANY_INT).build())
                .build();
        Employee employee2 = Employee.builder()
                .eid(2)
                .fname(ANY_STRING)
                .lname(MORE_THAN_FIVE)
                .pesel(ANY_INT)
                .address(Address.builder().addressId(1).addressType(AddressType.CURRENT).street(ANY_STRING).number(ANY_INT).build())
                .address(Address.builder().addressId(2).addressType(AddressType.INVOICING).street(ANY_STRING).number(ANY_INT).build())
                .build();
        List<Employee> testList = Arrays.asList(employee1,employee2);

        when(mockRepo.findAll()).thenReturn(testList);
        //when
        //than
        Assertions.assertThrows(BadRequestException.class, () -> employeeService.deleteWith5());
    }

    @Test
    void deleteWith5ShouldBeOk() {
        //given
        Employee employee1 = Employee.builder()
                .eid(1)
                .fname(ANY_STRING)
                .lname(FIVE_LONG_STRING)
                .pesel(ANY_INT)
                .address(Address.builder().addressId(1).addressType(AddressType.CURRENT).street(ANY_STRING).number(ANY_INT).build())
                .address(Address.builder().addressId(2).addressType(AddressType.INVOICING).street(ANY_STRING).number(ANY_INT).build())
                .build();
        Employee employee2 = Employee.builder()
                .eid(2)
                .fname(ANY_STRING)
                .lname(FIVE_LONG_STRING)
                .pesel(ANY_INT)
                .address(Address.builder().addressId(1).addressType(AddressType.CURRENT).street(ANY_STRING).number(ANY_INT).build())
                .address(Address.builder().addressId(2).addressType(AddressType.INVOICING).street(ANY_STRING).number(ANY_INT).build())
                .build();
        Employee employee3 = Employee.builder()
                .eid(3)
                .fname(ANY_STRING)
                .lname(MORE_THAN_FIVE)
                .pesel(ANY_INT)
                .address(Address.builder().addressId(1).addressType(AddressType.CURRENT).street(ANY_STRING).number(ANY_INT).build())
                .address(Address.builder().addressId(2).addressType(AddressType.INVOICING).street(ANY_STRING).number(ANY_INT).build())
                .build();
        Employee employee4 = Employee.builder()
                .eid(4)
                .fname(ANY_STRING)
                .lname(FIVE_LONG_STRING)
                .pesel(ANY_INT)
                .address(Address.builder().addressId(1).addressType(AddressType.CURRENT).street(ANY_STRING).number(ANY_INT).build())
                .address(Address.builder().addressId(2).addressType(AddressType.INVOICING).street(ANY_STRING).number(ANY_INT).build())
                .build();
        Employee employee5 = Employee.builder()
                .eid(5)
                .fname(ANY_STRING)
                .lname(FIVE_LONG_STRING)
                .pesel(ANY_INT)
                .address(Address.builder().addressId(1).addressType(AddressType.CURRENT).street(ANY_STRING).number(ANY_INT).build())
                .address(Address.builder().addressId(2).addressType(AddressType.INVOICING).street(ANY_STRING).number(ANY_INT).build())
                .build();
        List<Employee> testList = Arrays.asList(employee1,employee2,employee3,employee4,employee5);

        when(mockRepo.findAll()).thenReturn(testList);
        doNothing().when(mockRepo).delete(any(Employee.class));
        //when
        employeeService.deleteWith5();
        //than
        verify(mockRepo,times(4)).delete(any(Employee.class));
    }

    @Test
    void showAllShouldBeOk() {
        //given
        Employee employee1 = Employee.builder()
                .eid(1)
                .fname(ANY_STRING)
                .lname(MORE_THAN_FIVE)
                .pesel(ANY_INT)
                .address(Address.builder().addressId(1).addressType(AddressType.CURRENT).street(ANY_STRING).number(ANY_INT).build())
                .address(Address.builder().addressId(2).addressType(AddressType.INVOICING).street(ANY_STRING).number(ANY_INT).build())
                .build();
        Employee employee2 = Employee.builder()
                .eid(2)
                .fname(ANY_STRING)
                .lname(MORE_THAN_FIVE)
                .pesel(ANY_INT)
                .address(Address.builder().addressId(1).addressType(AddressType.CURRENT).street(ANY_STRING).number(ANY_INT).build())
                .address(Address.builder().addressId(2).addressType(AddressType.INVOICING).street(ANY_STRING).number(ANY_INT).build())
                .build();
        List<Employee> testList = Arrays.asList(employee1,employee2);

        when(mockRepo.findAll()).thenReturn(testList);
        //when
        employeeService.showAll();
        //than
        verify(mockRepo,times(1)).findAll();
    }

    @Test
    void showOneShouldThrowEmployeeNotFoundException() {
        //given
        when(mockRepo.findById(anyInt())).thenReturn(Optional.empty());
        //when
        //than
        Assertions.assertThrows(MyNotFoundException.class, () -> employeeService.showOne(1));

    }
    @Test
    void showOneShouldBeOk() {
        //given
        Employee employee = Employee.builder()
                .eid(1)
                .fname(ANY_STRING)
                .lname(MORE_THAN_FIVE)
                .pesel(ANY_INT)
                .address(Address.builder().addressId(1).addressType(AddressType.CURRENT).street(ANY_STRING).number(ANY_INT).build())
                .address(Address.builder().addressId(2).addressType(AddressType.INVOICING).street(ANY_STRING).number(ANY_INT).build())
                .build();

        when(mockRepo.findById(anyInt())).thenReturn(Optional.of(employee));
        //when
        employeeService.showOne(1);
        //than
        verify(mockRepo,times(1)).findById(1);
    }
}