package lv.yuri.pcd.repository;

import lv.yuri.pcd.domain.CountryCallingCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryCallingCodeRepository extends JpaRepository<CountryCallingCode, Long> {

}
