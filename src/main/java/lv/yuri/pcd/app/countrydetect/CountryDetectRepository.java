package lv.yuri.pcd.app.countrydetect;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lv.yuri.pcd.domain.CountryCallingCode;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CountryDetectRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<CountryCallingCode> listByCodes(List<Integer> numberCodesList) {
        Query query = entityManager.createQuery("""
                        FROM CountryCallingCode ccc
                        WHERE ccc.code = (
                            SELECT MAX(code)
                            FROM CountryCallingCode
                            WHERE code in(:pNumberCodesList) )
                        """)
                .setParameter("pNumberCodesList", numberCodesList);
        return query.getResultList();
    }

}
