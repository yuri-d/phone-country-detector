package lv.yuri.pcd.app.wikidataload.parsingstrategy;

import lombok.extern.slf4j.Slf4j;
import java.util.Collections;
import java.util.List;

@Slf4j
public class WikiParseUnsupportedStrategy implements WikiParseStrategy {
    @Override
    public List<Integer> parseCodes(String rawCodes) {
        log.info("Parsing strategy not supported for rawCodes:[{}]. Empty list will be returned.", rawCodes);
        return Collections.emptyList();
    }
}
