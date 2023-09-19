package lv.yuri.pcd.app.countrydetect;

import lombok.Getter;
import lombok.Setter;
import java.util.Collections;
import java.util.List;

public class CountryDetectByPhoneNumberRespDto {

    @Setter
    @Getter
    private String warningMsg = "";

    @Setter
    @Getter
    private boolean hasWarning;

    @Setter
    @Getter
    private List<String> countries = Collections.emptyList();

}
