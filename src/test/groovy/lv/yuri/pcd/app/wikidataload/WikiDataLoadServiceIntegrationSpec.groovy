package lv.yuri.pcd.app.wikidataload

import lv.yuri.pcd.domain.CountryCallingCode
import lv.yuri.pcd.repository.CountryCallingCodeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class WikiDataLoadServiceIntegrationSpec extends Specification {

    @Autowired
    WikiDataLoadService subject
    @Autowired
    CountryCallingCodeRepository callingCodeRepository

    def cleanup() {
        callingCodeRepository.deleteAll()
    }

    def "test load() - data is loaded from Wiki page to App DB"() {

        given: "DB is empty"
            def itemsCountBefore = callingCodeRepository.count()

        when: "Load data from Wiki to App DB"
            subject.load()

        then: "Data loaded - DB is not empty"
            def itemsCountAfter = callingCodeRepository.count()
            assert itemsCountBefore == 0
            assert itemsCountAfter > 0
    }

    def "test load() - single and multiple countries loaded"() {

        when: "Load data from Wiki to App DB"
            subject.load()

        then: "Data loaded - DB is not empty"
            callingCodeRepository.count() > 0

        and: "Country Canada exists in DB with code 1"
            List<CountryCallingCode> resultList1 = callingCodeRepository.findAllByCountry("Canada")
            resultList1.size() == 1
            resultList1.get(0).code == 1

        and: "Country United States exists in DB with code 1"
            List<CountryCallingCode> resultList2 = callingCodeRepository.findAllByCountry("United States")
            resultList2.size() == 1
            resultList2.get(0).code == 1

        and: "Two countries: Canada and United States found by the same code 1"
            List<CountryCallingCode> resultList3 = callingCodeRepository.findAllByCode(1)
            resultList3.size() == 2
            resultList3.collect {it.country}.containsAll("Canada", "United States")
    }

}
