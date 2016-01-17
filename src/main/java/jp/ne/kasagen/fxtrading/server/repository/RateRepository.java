package jp.ne.kasagen.fxtrading.server.repository;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jp.ne.kasagen.fxtrading.server.entity.Rate;

/**
 *
 * @author kasagen
 */
@Singleton
public class RateRepository {
 
    @PersistenceContext
    private EntityManager em;
    
    public void save(Rate entity) {
        em.persist(entity);
    }
}
