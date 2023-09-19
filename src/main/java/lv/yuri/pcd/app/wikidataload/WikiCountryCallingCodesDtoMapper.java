package lv.yuri.pcd.app.wikidataload;

import lombok.extern.slf4j.Slf4j;
import lv.yuri.pcd.app.wikidataload.parsingstrategy.WikiParseStrategy;
import lv.yuri.pcd.app.wikidataload.parsingstrategy.WikiParseStrategyResolver;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class WikiCountryCallingCodesDtoMapper {

    static List<WikiCountryCallingCodesDto> construct(Map<String, String> tableRawData) {
        List<WikiCountryCallingCodesDto> dtoList = new ArrayList<>();

        for (Map.Entry<String, String> entrySet : tableRawData.entrySet()) {
            WikiParseStrategy strategy = WikiParseStrategyResolver.resolve(entrySet.getValue());
            dtoList.addAll(
                    WikiCountryCallingCodesDtoMapper
                            .parseOneCountryRawData(
                                    entrySet.getKey(),
                                    entrySet.getValue(),
                                    strategy));
        }

        log.info("Wiki HTML Raw data parsing done. DTO items created:[{}]", dtoList.size());
        return dtoList;
    }

    private static List<WikiCountryCallingCodesDto> parseOneCountryRawData(
            String rawCountry,
            String rawCode,
            WikiParseStrategy strategy) {
        List<Integer> codeList = strategy.parseCodes(rawCode);
        return codeList.stream()
                .map(code -> new WikiCountryCallingCodesDto(rawCountry.trim(), code))
                .collect(Collectors.toList());
    }

}
