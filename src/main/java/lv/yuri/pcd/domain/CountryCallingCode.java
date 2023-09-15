package lv.yuri.pcd.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class CountryCallingCode {
    @Id
    @GeneratedValue
    @Getter
    private Long id;

    @Setter
    @Getter
    @Column(length = 15)
    private int code;

    @Setter
    @Getter
    @Column(length = 100)
    private String country;
}
