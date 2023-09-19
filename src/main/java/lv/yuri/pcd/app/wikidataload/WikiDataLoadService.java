package lv.yuri.pcd.app.wikidataload;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class WikiDataLoadService {

    @Autowired
    WikiDataLoadRepository wikiDataLoadRepository;

    public void load() {
        WikiCountryCallingCodesPage page = new WikiCountryCallingCodesPage();
        Map<String, String> tableRawData = page.getAlphabeticalOrderTableAsRawMap();
        List<WikiCountryCallingCodesDto> dtoList = WikiCountryCallingCodesDtoMapper.construct(tableRawData);
        wikiDataLoadRepository.saveCountryCodeList(dtoList);
    }

}
