package lv.yuri.pcd.app.wikidataload.parsingstrategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WikiParseStrategyResolver {
    public static WikiParseStrategy resolve(String rawCode) {
        String rawCodeNorm = WikiParseStrategyUtils.normalize(rawCode);
        if(rawCodeNorm.matches(WikiParse1Strategy.MATCH_PATTERN)) return new WikiParse1Strategy();
        if(rawCodeNorm.matches(WikiParse2Strategy.MATCH_PATTERN)) return new WikiParse2Strategy();
        if(rawCodeNorm.matches(WikiParse3Strategy.MATCH_PATTERN)) return new WikiParse3Strategy();
        if(rawCodeNorm.matches(WikiParse4Strategy.MATCH_PATTERN)) return new WikiParse4Strategy();
        if(rawCodeNorm.matches(WikiParse5Strategy.MATCH_PATTERN)) return new WikiParse5Strategy();
        return new WikiParseUnsupportedStrategy();
    }

}
