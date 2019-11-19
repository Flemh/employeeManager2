package pl.nitl.employeeManager.mappers;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import pl.nitl.employeeManager.models.Adress;
import pl.nitl.employeeManager.models.AdressType;
import pl.nitl.employeeManager.views.AdressDto;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.collection.IsEmptyCollection;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AdressMapperTest {
    private AdressMapper mapper
            = Mappers.getMapper(AdressMapper.class);
    @Test
    void adressToAdressDto() {
        Adress adress1 = Adress.builder()
        .adressId(1)
                .adressType(AdressType.ZAMELDOWANIA)
                .street("Boczna")
                .number(3)
                .build();
        Adress adress2 = Adress.builder()
                .adressId(2)
                .adressType(AdressType.KORESPONDENCYJNY)
                .street("Lipna")
                .number(8)
                .build();
        List<Adress> adressesEntity = new ArrayList<>();
        adressesEntity.add(adress1);
        adressesEntity.add(adress2);

        List<AdressDto> adressesDto = mapper.AdressToAdressDto(adressesEntity);
        assertThat(adressesDto, hasSize(adressesEntity.size()));
    }

    @Test
    void adressDtoToAdress() {
        AdressDto adress1 = AdressDto.builder()
                .adressId(1)
                .adressType(AdressType.ZAMELDOWANIA)
                .street("Boczna")
                .number(3)
                .build();
        AdressDto adress2 = AdressDto.builder()
                .adressId(2)
                .adressType(AdressType.KORESPONDENCYJNY)
                .street("Lipna")
                .number(8)
                .build();
        List<AdressDto> adressesDto = new ArrayList<>();
        adressesDto.add(adress1);
        adressesDto.add(adress2);

        List<Adress> adressesEntity = mapper.AdressDtoToAdress(adressesDto);
        assertThat(adressesEntity, hasSize(adressesDto.size()));
    }
}