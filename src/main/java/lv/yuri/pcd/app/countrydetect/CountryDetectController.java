package lv.yuri.pcd.app.countrydetect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryDetectController {

    @Autowired
    CountryDetectService countryDetectService;

    @GetMapping("/detect/country/{phoneNumber}")
    public ResponseEntity<CountryDetectByPhoneNumberRespDto> detectByPhoneNumber(@PathVariable String phoneNumber) {
        CountryDetectByPhoneNumberRespDto dto = countryDetectService.detectByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(dto);
    }

}
