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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
    @Mock
    EmployeeRespository mockRepo;

    @InjectMocks
    EmployeeService employeeService = new EmployeeService();

    @Test
    void checkAndSaveShouldBeOk() {
        //given
        Employee e1 = Employee.builder()
                .eid(1)
                .fname("Pzemek")
                .lname("abcde")
                .pesel(123)
                .address(Address.builder().addressId(1).addressType(AddressType.ZAMELDOWANIA).street("boczna").number(7).build())
                .address(Address.builder().addressId(2).addressType(AddressType.ZAMIESZKANIA).street("boczna").number(6).build())
                .build();
        //when
       employeeService.checkAndSave(e1);
       //than
        verify(mockRepo,times(1)).save(any(Employee.class));
    }

    @Test
    void checkAndSaveShouldThrowIncorrectAddressesListSizeException(){
        //given
        Employee e4 = Employee.builder()
                .eid(4)
                .fname("Przemek")
                .lname("aaaaa")
                .pesel(123)
                .address(Address.builder().addressId(1).addressType(AddressType.ZAMIESZKANIA).street("boczna").number(7).build())
                .address(Address.builder().addressId(2).addressType(AddressType.KORESPONDENCYJNY).street("boczna").number(6).build())
                .address(Address.builder().addressId(2).addressType(AddressType.ZAMELDOWANIA).street("boczna").number(6).build())
                .build();
        //when
        //than
        Assertions.assertThrows(IncorrectAddressesListSizeException.class, () -> {
            employeeService.checkAndSave(e4);
        });
    }
    @Test
    void checkAndSaveShouldThrowIncorrectZameldowaniaNumberException(){
        //given
        Employee e2 = Employee.builder()
                .eid(2)
                .fname("Przemek")
                .lname("aaaaa")
                .pesel(123)
                .address(Address.builder().addressId(1).addressType(AddressType.ZAMELDOWANIA).street("boczna").number(7).build())
                .address(Address.builder().addressId(2).addressType(AddressType.ZAMELDOWANIA).street("boczna").number(6).build())
                .build();
        //when
        //than
        Assertions.assertThrows(IncorrectZameldowaniaNumberException.class, () -> {
            employeeService.checkAndSave(e2);
        });
    }

    @Test
    void checkAndUpdateShouldThrowEmployeeNotFoundException() {
        //given
        Employee e1 = Employee.builder()
                .eid(1)
                .fname("Pzemek")
                .lname("abcde")
                .pesel(123)
                .address(Address.builder().addressId(1).addressType(AddressType.ZAMELDOWANIA).street("boczna").number(7).build())
                .address(Address.builder().addressId(2).addressType(AddressType.ZAMIESZKANIA).street("boczna").number(6).build())
                .build();
        //when
        when(mockRepo.findById(anyInt())).thenReturn(Optional.empty());
        //than
        Assertions.assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.checkAndUpdate(e1,1);
        });
    }

    @Test
    void checkAndUpdateShouldBeOk() {
        //given
        Employee e1 = Employee.builder()
                .eid(1)
                .fname("Pzemek")
                .lname("abcde")
                .pesel(123)
                .address(Address.builder().addressId(1).addressType(AddressType.ZAMELDOWANIA).street("boczna").number(7).build())
                .address(Address.builder().addressId(2).addressType(AddressType.ZAMIESZKANIA).street("boczna").number(6).build())
                .build();
        //when
        when(mockRepo.save(any(Employee.class))).thenReturn(e1);
        when(mockRepo.findById(anyInt())).thenReturn(Optional.of(e1));
        employeeService.checkAndUpdate(e1,1);
        //than
        verify(mockRepo,times(1)).save(any(Employee.class));
    }

    @Test
    void deleteShouldThrowEmployeeNotFoundException() {
        //given
        //when
        when(mockRepo.findById(anyInt())).thenReturn(Optional.empty());
        //than
        Assertions.assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.delete(1);
        });
    }

    @Test
    void deleteShouldBeOk() {
        //given
        Employee e1 = Employee.builder()
                .eid(1)
                .fname("Pzemek")
                .lname("abcde")
                .pesel(123)
                .address(Address.builder().addressId(1).addressType(AddressType.ZAMELDOWANIA).street("boczna").number(7).build())
                .address(Address.builder().addressId(2).addressType(AddressType.ZAMIESZKANIA).street("boczna").number(6).build())
                .build();
        //when
        doNothing().when(mockRepo).delete(any(Employee.class));
        when(mockRepo.findById(anyInt())).thenReturn(Optional.of(e1));
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
        Assertions.assertThrows(EmptyListException.class, () -> {
            employeeService.deleteWith5();
        });
    }

    @Test
    void deleteWith5ShouldThereIsNoOneWith5Exception() {
        //given
        Employee e1 = Employee.builder()
                .eid(1)
                .fname("Przemek")
                .lname("moreThan5")
                .pesel(123)
                .address(Address.builder().addressId(1).addressType(AddressType.ZAMIESZKANIA).street("boczna").number(7).build())
                .address(Address.builder().addressId(2).addressType(AddressType.KORESPONDENCYJNY).street("boczna").number(6).build())
                .build();
        Employee e2 = Employee.builder()
                .eid(2)
                .fname("Przemek")
                .lname("more")
                .pesel(123)
                .address(Address.builder().addressId(1).addressType(AddressType.ZAMIESZKANIA).street("boczna").number(7).build())
                .address(Address.builder().addressId(2).addressType(AddressType.KORESPONDENCYJNY).street("boczna").number(6).build())
                .build();
        List<Employee> testList = new ArrayList<>();
        testList.add(e1);
        testList.add(e2);
        //when
        when(mockRepo.findAll()).thenReturn(testList);
        //than
        Assertions.assertThrows(ThereIsNoOneWith5Exception.class, () -> {
            employeeService.deleteWith5();
        });
    }

    @Test
    void deleteWith5ShouldBeOk() {
        //given
        Employee e1 = Employee.builder()
                .eid(1)
                .fname("Pzemek")
                .lname("abcde")
                .pesel(123)
                .address(Address.builder().addressId(1).addressType(AddressType.ZAMELDOWANIA).street("boczna").number(7).build())
                .address(Address.builder().addressId(2).addressType(AddressType.ZAMIESZKANIA).street("boczna").number(6).build())
                .build();
        Employee e2 = Employee.builder()
                .eid(2)
                .fname("Przemek")
                .lname("aaaaa")
                .pesel(123)
                .address(Address.builder().addressId(1).addressType(AddressType.ZAMELDOWANIA).street("boczna").number(7).build())
                .address(Address.builder().addressId(2).addressType(AddressType.ZAMELDOWANIA).street("boczna").number(6).build())
                .build();
        Employee e3 = Employee.builder()
                .eid(3)
                .fname("Przemek")
                .lname("moreThan5")
                .pesel(123)
                .address(Address.builder().addressId(1).addressType(AddressType.ZAMIESZKANIA).street("boczna").number(7).build())
                .address(Address.builder().addressId(2).addressType(AddressType.KORESPONDENCYJNY).street("boczna").number(6).build())
                .build();
        Employee e4 = Employee.builder()
                .eid(4)
                .fname("Przemek")
                .lname("aaaaa")
                .pesel(123)
                .address(Address.builder().addressId(1).addressType(AddressType.ZAMIESZKANIA).street("boczna").number(7).build())
                .address(Address.builder().addressId(2).addressType(AddressType.KORESPONDENCYJNY).street("boczna").number(6).build())
                .address(Address.builder().addressId(2).addressType(AddressType.ZAMELDOWANIA).street("boczna").number(6).build())
                .build();
        Employee e5 = Employee.builder()
                .eid(5)
                .fname("Przemek")
                .lname("aaaaa")
                .pesel(123)
                .build();
        List<Employee> testList = new ArrayList<>();
        testList.add(e1);
        testList.add(e2);
        testList.add(e3);
        testList.add(e4);
        testList.add(e5);
        //when
        when(mockRepo.findAll()).thenReturn(testList);
        doNothing().when(mockRepo).delete(any(Employee.class));
        employeeService.deleteWith5();
        //than
        verify(mockRepo,times(4)).delete(any(Employee.class));
    }

    @Test
    void showAllShouldBeOk() {
        //given
        Employee e1 = Employee.builder()
                .eid(1)
                .fname("Przemek")
                .lname("moreThan5")
                .pesel(123)
                .address(Address.builder().addressId(1).addressType(AddressType.ZAMIESZKANIA).street("boczna").number(7).build())
                .address(Address.builder().addressId(2).addressType(AddressType.KORESPONDENCYJNY).street("boczna").number(6).build())
                .build();
        Employee e2 = Employee.builder()
                .eid(2)
                .fname("Przemek")
                .lname("more")
                .pesel(123)
                .address(Address.builder().addressId(1).addressType(AddressType.ZAMIESZKANIA).street("boczna").number(7).build())
                .address(Address.builder().addressId(2).addressType(AddressType.KORESPONDENCYJNY).street("boczna").number(6).build())
                .build();
        List<Employee> testList = new ArrayList<>();
        testList.add(e1);
        testList.add(e2);
        //when
        when(mockRepo.findAll()).thenReturn(testList);
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
        Assertions.assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.showOne(1);
        });

    }
    @Test
    void showOneShouldBeOk() {
        //given
        Employee e1 = Employee.builder()
                .eid(1)
                .fname("Przemek")
                .lname("moreThan5")
                .pesel(123)
                .address(Address.builder().addressId(1).addressType(AddressType.ZAMIESZKANIA).street("boczna").number(7).build())
                .address(Address.builder().addressId(2).addressType(AddressType.KORESPONDENCYJNY).street("boczna").number(6).build())
                .build();
        //when
        when(mockRepo.findById(anyInt())).thenReturn(Optional.of(e1));
        employeeService.showOne(1);
        //than
        verify(mockRepo,times(1)).findById(1);
    }
}