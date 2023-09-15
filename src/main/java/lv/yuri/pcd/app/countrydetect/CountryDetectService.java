package lv.yuri.pcd.app.countrydetect;

import lv.yuri.pcd.domain.CountryCallingCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CountryDetectService {

    final static int MAX_CODE_LENGTH = 6;

    @Autowired
    CountryDetectRepository countryDetectRepository;

    public CountryDetectByPhoneNumberRespDto detectByPhoneNumber(String phoneNumber) {
        try {
            validatePhoneNumber(phoneNumber);
            validateMaxCodeLength(phoneNumber, MAX_CODE_LENGTH);
            List<Integer> numberCodesList = CountryDetectUtils.phoneNumberToCountryCodesList(phoneNumber, MAX_CODE_LENGTH);
            List<CountryCallingCode> countryCallingCodeList = countryDetectRepository.listByCodes(numberCodesList);
            return CountryDetectByPhoneNumberRespDtoMapper.map(countryCallingCodeList);
        } catch (CountryDetectValidationException e) {
           CountryDetectByPhoneNumberRespDto responseDto = new CountryDetectByPhoneNumberRespDto();
           responseDto.setErrorMessage(e.getMessage());
           responseDto.setHasError(true);
           return responseDto;
        } catch (Exception e) {
            CountryDetectByPhoneNumberRespDto responseDto = new CountryDetectByPhoneNumberRespDto();
            responseDto.setErrorMessage("General server error");
            responseDto.setHasError(true);
            return responseDto;
        }
    }

    private void validatePhoneNumber(String phoneNumber) throws CountryDetectValidationException {
        if(phoneNumber.isEmpty()) {
            throw new CountryDetectValidationException("Number is empty");
        }
    }

    private void validateMaxCodeLength(String phoneNumber, int maxCodeLength) throws CountryDetectValidationException {
        if(maxCodeLength == 0) {
            throw new CountryDetectValidationException("Max Country code must not be null");
        }
    }


}
