package lv.yuri.pcd.app;

import lombok.extern.slf4j.Slf4j;
import lv.yuri.pcd.app.wikidataload.WikiDataLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AppEvents {

    @Autowired
    private Environment environment;

    @Autowired
    WikiDataLoadService wikiDataLoadService;

    @EventListener
    public void onApplicationReadyEvent(ApplicationReadyEvent event) {
        boolean loadWikiData = Boolean.parseBoolean(environment.getProperty("app.onstartup.load.wikidata"));
        log.info("Switcher property: app.onstartup.load.wikidata: ["+loadWikiData+"]");
        if(loadWikiData) {
            wikiDataLoadService.load();
        }
    }

}
