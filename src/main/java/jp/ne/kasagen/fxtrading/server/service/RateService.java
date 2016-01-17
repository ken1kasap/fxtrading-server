
package jp.ne.kasagen.fxtrading.server.service;

import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import jp.ne.kasagen.fxtrading.server.entity.Rate;
import jp.ne.kasagen.fxtrading.server.rate.CurrencyRate;
import jp.ne.kasagen.fxtrading.server.repository.RateRepository;

/**
 *
 * @author kasagen
 */
@Singleton
public class RateService {
   
    @Inject
    private RateRepository rateRepository;
    
    @Transactional
    public void saveRates(List<CurrencyRate> rates, Date acquisitionTime) {
        rates.stream().forEach(c -> {
            Rate rate = toRate(c, acquisitionTime);
            rateRepository.save(rate);
        });
    }
    

    private Rate toRate(CurrencyRate c, Date t) {
        Rate r = new Rate();
        r.setCurrencyPair(c.getCurrencyPairCode());
        r.setAcquisitionTime(t);
        r.setHigh(c.getHigh());
        r.setLow(c.getLow());
        r.setAsk(c.getAsk());
        r.setBid(c.getBid());
        r.setOpen(c.getOpen());
        return r;
    }
}
