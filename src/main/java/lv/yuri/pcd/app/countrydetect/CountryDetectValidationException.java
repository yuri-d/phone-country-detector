package lv.yuri.pcd.app.countrydetect;

public class CountryDetectValidationException extends Exception {

    //todo https://devwithus.com/exception-handling-spring-boot-rest-api/
    public CountryDetectValidationException(String errorMessage) {
        super(errorMessage);
    }
}
