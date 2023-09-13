package lv.yuri.pcd.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class CountryCode {

    @Id
    @GeneratedValue
    private Long id;

}
