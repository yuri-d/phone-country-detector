package lv.yuri.pcd.app.countrydetect;

import java.util.ArrayList;
import java.util.List;

public class CountryDetectUtils {

    public static List<Integer> populateCountryCodeCandidatesListFromPhoneNumber(String phoneNumber, int maxCodeLength) {
        int maxCodeLengthNorm = normalizeMaxCodeLength(phoneNumber, maxCodeLength);
        List<Integer> numberCodesList = new ArrayList<>();
        int i=0;
        while (i < maxCodeLengthNorm) {
            String substr = phoneNumber.substring(0, maxCodeLengthNorm-i);
            numberCodesList.add(Integer.parseInt(substr));
            i++;
        }
        return numberCodesList;
    }

    private static int normalizeMaxCodeLength(String phoneNumber, int maxCodeLength) {
        int delta = phoneNumber.length() - maxCodeLength;
        if(delta < 0) {
            return maxCodeLength+delta;
        }
        return maxCodeLength;
    }

}
