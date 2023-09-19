package lv.yuri.pcd.app.wikidataload.parsingstrategy;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Parse comma-separated codes and sub-codes within parentheses and construct a list of integers.<br>
 * Example:<br>
 * <li>Original string: [ 599 (3, 4, 7) ]
 * <li>Result list: [3599,4599,7599]
 * */
@Slf4j
public class WikiParse3Strategy implements WikiParseStrategy {

    public static final String MATCH_PATTERN = "[0-9]+([(][,0-9]+[)])";
    private static final String RAW_CODE_SPLIT_PATTERN = "([(])|([)][,])";
    private static final String SUB_CODES_SPLIT_PATTERN = ",";

    @Override
    public List<Integer> parseCodes(String rawCodes) {
        log.info("Start parsing rawCodes rawCodes:[{}]", rawCodes);
        List<String> rawCodeList = Arrays.stream(
                        WikiParseStrategyUtils
                                .normalize(rawCodes)
                                .split(RAW_CODE_SPLIT_PATTERN))
                .toList();
        int mainCode = resolveMainCode(rawCodeList);
        String subCodeList = resolveSubCodes(rawCodeList);
        return Stream.of(subCodeList.split(SUB_CODES_SPLIT_PATTERN))
                .map(subCode -> constructFullCode(mainCode, subCode))
                .collect(Collectors.toList());
    }

    private static int resolveMainCode(List<String> rawCodeList) {
        return Integer.parseInt(rawCodeList.get(0));
    }

    private static String resolveSubCodes(List<String> rawCodeList) {
        return rawCodeList.get(1)
                .replace(")","");
    }

    private static int constructFullCode(int mainCode, String subCode) {
        return Integer.parseInt((mainCode+subCode));
    }

}
