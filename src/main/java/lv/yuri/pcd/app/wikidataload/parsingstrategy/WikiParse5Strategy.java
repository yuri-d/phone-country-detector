package lv.yuri.pcd.app.wikidataload.parsingstrategy;

import lombok.extern.slf4j.Slf4j;
import java.util.Collections;
import java.util.List;

@Slf4j
public class WikiParse5Strategy implements WikiParseStrategy {

    public static final String MATCH_PATTERN = "[0-9]+([(][,0-9]+[)])([(][,0-9 A-Za-z]+[)])";

    @Override
    public List<Integer> parseCodes(String rawCodes) {
        log.info("Parsing strategy not implemented for rawCodes:[{}]. Empty list will be returned.", rawCodes);
        return Collections.emptyList();
    }

}
