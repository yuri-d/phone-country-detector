package lv.yuri.pcd.app.countrydetect

import lv.yuri.pcd.domain.CountryCallingCode
import lv.yuri.pcd.repository.CountryCallingCodeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest
class CountryDetectServiceIntegrationSpec extends Specification {

    @Autowired
    CountryDetectService subject
    @Autowired
    CountryCallingCodeRepository callingCodeRepository

    def setup() {
        populateCallingCodeRepositoryData();
    }

    def cleanup() {
        callingCodeRepository.deleteAll()
    }

    @Unroll
    def "test detectByPhoneNumber() - single and multiple countries found"() {

        when:
            CountryDetectByPhoneNumberRespDto result = subject.detectByPhoneNumber(givenPhoneNumber)

        then:
            result.getCountries().size() == expectedResultSize
            if(expectedResultSize == 1) {
                assert result.countries.contains(expectedCountry1)
            } else if(expectedResultSize == 2) {
                assert result.countries.contains(expectedCountry1)
                assert result.countries.contains(expectedCountry2)
            }

        where:
            givenPhoneNumber || expectedResultSize | expectedCountry1 | expectedCountry2
            "12423222931"    || 1                  | "Bahamas"        | _
            "71423423412"    || 1                  | "Russia"         | _
            "77112227231"    || 1                  | "Kazakhstan"     | _
            "11165384765"    || 2                  | "United States"  | "Canada"
    }

    @Unroll
    def "test detectByPhoneNumber() - validation warnings"() {

        when:
            CountryDetectByPhoneNumberRespDto result = subject.detectByPhoneNumber(givenPhoneNumber)

        then:
            result.getCountries().size() == 0
            result.hasWarning == true
            result.warningMsg == warningMsg

        where:
            givenPhoneNumber || warningMsg
            null             || "Phone Number not entered"
            ""               || "Phone Number not entered"
            "abc"            || "Only digits allowed"
            "99999999999"    || "Nothing was found"

    }

    private void populateCallingCodeRepositoryData() {
        callingCodeRepository.saveAndFlush(new CountryCallingCode(code: 25524, country: "Zanzibar"))
        callingCodeRepository.saveAndFlush(new CountryCallingCode(code: 1242, country: "Bahamas"))
        callingCodeRepository.saveAndFlush(new CountryCallingCode(code: 1, country: "Canada"))
        callingCodeRepository.saveAndFlush(new CountryCallingCode(code: 7, country: "Russia"))
        callingCodeRepository.saveAndFlush(new CountryCallingCode(code: 1, country: "United States"))
        callingCodeRepository.saveAndFlush(new CountryCallingCode(code: 76, country: "Kazakhstan"))
        callingCodeRepository.saveAndFlush(new CountryCallingCode(code: 77, country: "Kazakhstan"))
        callingCodeRepository.saveAndFlush(new CountryCallingCode(code: 7997, country: "Kazakhstan"))
        callingCodeRepository.saveAndFlush(new CountryCallingCode(code: 962, country: "Jordan"))
        /*
            JpaRepository Batch Save doesn't work here:
                <S extends T> List<S> saveAllAndFlush(Iterable<S> entities);
                --> InvalidDataAccessApiUsageException: java.util.ArrayList is not an entity
         */
    }

}
