package lv.yuri.pcd.app.countrydetect;

import lv.yuri.pcd.domain.CountryCallingCode;

import java.util.List;

public class CountryDetectValidator {

    private static final String DIGITS_PATTERN = "[0-9]+";

    static void checkPhoneNumber(String phoneNumber) throws CountryDetectValidationException {
        if(phoneNumber == null || phoneNumber.isEmpty()) {
            throw new CountryDetectValidationException("Phone Number not entered");
        }
        if(!phoneNumber.matches(DIGITS_PATTERN)) {
            throw new CountryDetectValidationException("Only digits allowed");
        }
    }

    static void checkFoundResult(List<CountryCallingCode> countryCallingCodeList) throws CountryDetectValidationException {
        if(countryCallingCodeList.isEmpty()) {
            throw new CountryDetectValidationException("Nothing was found");
        }
    }

}
