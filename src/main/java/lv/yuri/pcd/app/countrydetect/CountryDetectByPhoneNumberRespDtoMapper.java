package lv.yuri.pcd.app.countrydetect;

import lv.yuri.pcd.domain.CountryCallingCode;

import java.util.List;
import java.util.stream.Collectors;

public class CountryDetectByPhoneNumberRespDtoMapper {

    public static CountryDetectByPhoneNumberRespDto map(List<CountryCallingCode> countryCallingCodeList) {
        CountryDetectByPhoneNumberRespDto responseDto = new CountryDetectByPhoneNumberRespDto();
        List<String> countries = countryCallingCodeList.stream()
                .map(CountryCallingCode::getCountry)
                .collect(Collectors.toList());
        responseDto.setCountries(countries);
        return responseDto;
    }

}
