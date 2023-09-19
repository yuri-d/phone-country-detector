package lv.yuri.pcd.app.countrydetect;

import lombok.extern.slf4j.Slf4j;
import lv.yuri.pcd.domain.CountryCallingCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Slf4j
@Component
public class CountryDetectService {

    final static int MAX_CODE_LENGTH = 6;

    @Autowired
    CountryDetectRepository countryDetectRepository;

    public CountryDetectByPhoneNumberRespDto detectByPhoneNumber(String phoneNumber) {
        try {
            CountryDetectValidator.checkPhoneNumber(phoneNumber);
            List<Integer> countryCodeCandidatesList = CountryDetectUtils.populateCountryCodeCandidatesListFromPhoneNumber(phoneNumber, MAX_CODE_LENGTH);
            List<CountryCallingCode> countryCallingCodeList = countryDetectRepository.listByCodes(countryCodeCandidatesList);
            CountryDetectValidator.checkFoundResult(countryCallingCodeList);
            return CountryDetectByPhoneNumberRespDtoMapper.map(countryCallingCodeList);
        } catch (CountryDetectValidationException e) {
            log.warn(e.getMessage());
            CountryDetectByPhoneNumberRespDto responseDto = new CountryDetectByPhoneNumberRespDto();
            responseDto.setWarningMsg(e.getMessage());
            responseDto.setHasWarning(true);
            return responseDto;
        }
    }

}
