package lv.yuri.pcd.app.wikidataload.parsingstrategy;

import java.util.List;

public interface WikiParseStrategy {

    List<Integer> parseCodes(String rawCodes);

}
