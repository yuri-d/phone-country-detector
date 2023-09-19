package lv.yuri.pcd.app.wikidataload;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class WikiCountryCallingCodesPage {

    private static final String PAGE_URL = "https://en.wikipedia.org/wiki/List_of_country_calling_codes#Alphabetical_order";
    private static final String ELEM_TABLE_TITLE_ID = "Alphabetical_order";
    private static final String ELEM_TABLE_HEADING_TAG = "th";
    private static final String ELEM_TABLE_BODY_PATH = "tbody > tr";
    private static final String ELEM_TABLE_CELL_COUNTRY = "tr > td";
    private Document document;

    public WikiCountryCallingCodesPage() {
       init();
    }

    private void init() {
        try {
            this.document = Jsoup.connect(PAGE_URL).get();
            log.info("Connection created with URL: [{}]", PAGE_URL);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Elements getAlphabeticalOrderTableElements() {
        Element tableTitle = this.document.getElementById(ELEM_TABLE_TITLE_ID);
        Elements tableRows = tableTitle
                .parent()
                .nextElementSibling()
                .select(ELEM_TABLE_BODY_PATH);
        removeAlphabeticalOrderTableHeadingElem(tableRows);
        return tableRows;
    }

    private void removeAlphabeticalOrderTableHeadingElem(Elements tableRows) {
        Element tableHeadingElem = tableRows.get(0);
        if(tableHeadingElem
                .child(0)
                .tag()
                .getName()
                .equalsIgnoreCase(ELEM_TABLE_HEADING_TAG)  ) {
            tableRows.remove(0);
        }
    }

    public Map<String, String> getAlphabeticalOrderTableAsRawMap() {
        Elements tableRows = getAlphabeticalOrderTableElements();
        log.info("Wiki HTML [{}] - html elements loaded:[{}]", ELEM_TABLE_TITLE_ID, tableRows.size());
        Map<String, String> tableRawData = new HashMap<>();
        tableRows.forEach(element -> {
            Element cellCountry = element.select(ELEM_TABLE_CELL_COUNTRY).first();
            Element cellCode = cellCountry.nextElementSibling();
            tableRawData.put(cellCountry.text(), cellCode.text());
        });
        log.info("Wiki HTML [{}] - list items created:[{}]", ELEM_TABLE_TITLE_ID, tableRawData.size());
        return tableRawData;
    }

}
