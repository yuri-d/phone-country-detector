package lv.yuri.pcd.app.wikidataload.parsingstrategy;

import lombok.extern.slf4j.Slf4j;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class WikiParse4Strategy implements WikiParseStrategy {

    public static final String MATCH_PATTERN = "[0-9]+([(][,0-9]+[)])([,0-9 A-Za-z]+)";

    private static final String RAW_CODE_SPLIT_PATTERN = "([(])|([)][,])";

    private static final String RAW_CODE_REMOVE_CHARS_PATTERN = "[a-zA-Z]";

    @Override
    public List<Integer> parseCodes(String rawCodes) {
        log.info("Start parsing rawCodes rawCodes:[{}]", rawCodes);
        List<String> rawCodeList = Arrays.stream(
                        WikiParseStrategyUtils
                                .normalize(rawCodes)
                                .split(RAW_CODE_SPLIT_PATTERN))
                .toList();
        int mainCode = resolveMainCode(rawCodeList);
        String subCode = resolveSubCode(rawCodeList);
        int assignedCode = resolveAssignedCode(rawCodeList);

        return Arrays.asList(
                constructFullCode(mainCode, subCode),
                assignedCode);
    }

    private static int resolveMainCode(List<String> rawCodeList) {
        return Integer.parseInt(rawCodeList.get(0));
    }

    private static String resolveSubCode(List<String> rawCodeList) {
        return rawCodeList.get(1)
                .replace(")", "");
    }

    private static int resolveAssignedCode(List<String> rawCodeList) {
        return Integer.parseInt(
                rawCodeList.get(2)
                        .replaceAll(RAW_CODE_REMOVE_CHARS_PATTERN, ""));
    }

    private static int constructFullCode(int mainCode, String subCode) {
        return Integer.parseInt((mainCode+subCode));
    }
}
