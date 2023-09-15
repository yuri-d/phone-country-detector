package lv.yuri.pcd.app.countrydetect;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

public class CountryDetectByPhoneNumberRespDto {

    @Setter
    @Getter
    private String errorMessage = "";

    @Setter
    @Getter
    private boolean hasError = false;

    @Setter
    @Getter
    private List<String> countries = Collections.emptyList();

}
