package lv.yuri.pcd.app.countrydetect;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CountryDetectController {

    @Autowired
    CountryDetectService countryDetectService;

    @GetMapping(value = {
                    "/api/country-detect-by/phone-number/{phoneNumber}",
                    "/api/country-detect-by/phone-number/"})
    public ResponseEntity<Object> detectByPhoneNumber(@PathVariable(required = false) String phoneNumber) {
        log.info("Start for number: [{}]", phoneNumber);
        try {
            return ResponseEntity.ok(countryDetectService.detectByPhoneNumber(phoneNumber));
        } catch (Exception e) {
            log.error("Error processing phoneNumber:[{}]", phoneNumber, e);
            return ResponseEntity.unprocessableEntity().body("Something went wrong");
        }
    }

}
