package lv.yuri.pcd.repository;

import lv.yuri.pcd.domain.CountryCallingCode;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CountryCallingCodeRepository extends JpaRepository<CountryCallingCode, Long> {
    List<CountryCallingCode> findAllByCountry(String country);
    List<CountryCallingCode> findAllByCode(int code);
}
