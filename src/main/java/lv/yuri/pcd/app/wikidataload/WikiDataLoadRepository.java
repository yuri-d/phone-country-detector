package lv.yuri.pcd.app.wikidataload;

import lombok.extern.slf4j.Slf4j;
import lv.yuri.pcd.domain.CountryCallingCode;
import lv.yuri.pcd.repository.CountryCallingCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Slf4j
@Repository
public class WikiDataLoadRepository {

    @Autowired
    CountryCallingCodeRepository countryCallingCodeRepository;

    public int saveCountryCodeList(List<WikiCountryCallingCodesDto> dtoList) {
        List<CountryCallingCode> entityList = dtoList
                .stream()
                .map(dto -> {
                    CountryCallingCode ccc = new CountryCallingCode();
                    ccc.setCountry(dto.countryName());
                    ccc.setCode(dto.countryCode());
                    return ccc;
                })
                .toList();
        int savedEntitiesSize = countryCallingCodeRepository.saveAll(entityList).size();
        log.info("Items saved to DB:[{}]", savedEntitiesSize);
        return savedEntitiesSize;
    }

}
