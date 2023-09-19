package lv.yuri.pcd.app.wikidataload.parsingstrategy;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Parse single code string and construct a list of one integer.<br>
 * Example:<br>
 * <li>Original string: [ 371 ]
 * <li>Result list: [371]
 * */
@Slf4j
public class WikiParse1Strategy implements WikiParseStrategy {

    public static final String MATCH_PATTERN = "[0-9]+";

    @Override
    public List<Integer> parseCodes(String rawCodes) {
        log.info("Start parsing rawCodes:[{}]", rawCodes);
        return new ArrayList<>(Arrays.asList(resolveMainCode(rawCodes)));
    }

    private static int resolveMainCode(String rawCode) {
        return Integer.parseInt(rawCode.trim());
    }


}
