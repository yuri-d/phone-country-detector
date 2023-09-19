package lv.yuri.pcd.app.wikidataload.parsingstrategy;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Parse comma-separated codes string and construct a list of integers.<br>
 * Example:<br>
 * <li>Original string: [ 371, 372 ]
 * <li>Result list: [371,372]
 * */
@Slf4j
public class WikiParse2Strategy implements WikiParseStrategy {

    public static final String MATCH_PATTERN = "[0-9]+(,[0-9]+)+";
    private static final String MAIN_CODES_SPLIT_PATTERN = ",";

    @Override
    public List<Integer> parseCodes(String rawCodes) {
        log.info("Start parsing rawCodes rawCodes:[{}]", rawCodes);
        return Stream.of(WikiParseStrategyUtils
                        .normalize(rawCodes)
                        .split(MAIN_CODES_SPLIT_PATTERN))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }


}
