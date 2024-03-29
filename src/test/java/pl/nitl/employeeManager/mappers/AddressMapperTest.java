package pl.nitl.employeeManager.mappers;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import pl.nitl.employeeManager.models.Address;
import pl.nitl.employeeManager.models.AddressType;
import pl.nitl.employeeManager.views.AddressDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.util.AssertionErrors.assertNull;

class AddressMapperTest {
    private AddressMapper mapper
            = Mappers.getMapper(AddressMapper.class);

    @Test
    void addressToAddressDto() {
        //given
        Address address1 = Address.builder()
                .addressId(1)
                .addressType(AddressType.CURRENT)
                .street("Boczna")
                .number(3)
                .build();
        Address address2 = Address.builder()
                .addressId(2)
                .addressType(AddressType.POSTAL)
                .street("Lipna")
                .number(8)
                .build();
        List<Address> addressesEntity = Arrays.asList(address1,address2);

        //when
        List<AddressDto> addressesDto = mapper.addressToAddressDto(addressesEntity);
        //then
        assertThat(addressesDto, hasSize(addressesEntity.size()));
        assertNull(null, mapper.addressToAddressDto(null));
    }

    @Test
    void addressDtoToAddress() {
        //given
        AddressDto address1 = AddressDto.builder()
                .addressId(1)
                .addressType(AddressType.CURRENT)
                .street("Boczna")
                .number(3)
                .build();
        AddressDto address2 = AddressDto.builder()
                .addressId(2)
                .addressType(AddressType.POSTAL)
                .street("Lipna")
                .number(8)
                .build();
        List<AddressDto> addressesDto = Arrays.asList(address1,address2);
        //when
        List<Address> addressesEntity = mapper.addressDtoToAddress(addressesDto);
        //than
        assertThat(addressesEntity, hasSize(addressesDto.size()));
        assertNull(null, mapper.addressDtoToAddress(null));
    }
}