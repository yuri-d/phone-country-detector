package lv.yuri.pcd.app.countrydetect;

import java.util.ArrayList;
import java.util.List;

public class CountryDetectUtils {

    public static List<Integer> phoneNumberToCountryCodesList(String phoneNumber, int maxCodeLength) {
        List<Integer> numberCodesList = new ArrayList<>();
        int i=0;
        while (i < maxCodeLength) {
            String substr = phoneNumber.substring(0, maxCodeLength-i);
            numberCodesList.add(Integer.parseInt(substr));
            i++;
        }
        return numberCodesList;
    }

}
