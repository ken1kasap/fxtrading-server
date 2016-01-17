package jp.ne.kasagen.fxtrading.server.batch;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import jp.ne.kasagen.fxtrading.server.rate.CurrencyRate;
import jp.ne.kasagen.fxtrading.server.rate.GaitameJacksonReader;
import jp.ne.kasagen.fxtrading.server.rate.Rates;
import jp.ne.kasagen.fxtrading.server.service.RateService;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author kasagen
 */
@Startup
@Singleton
public class RateDataAcquisitionService {

    private static final Logger LOG = LoggerFactory.getLogger(RateDataAcquisitionService.class);

    private static final List<String> PAIRS = Arrays.asList("USDJPY", "EURUSD", "AUDUSD", "NZDUSD", "EURJPY", "AUDJPY", "NZDJPY");

    private Client client;

    private Invocation.Builder invocationBuilder;

    @Inject
    private RateService rateService;

    public RateDataAcquisitionService() {
        LOG.debug("constructed.");
    }

    @PostConstruct
    public void initialize() {
        client = ClientBuilder.newClient().register(JacksonFeature.class).register(GaitameJacksonReader.class);
        WebTarget webTarget = client.target("http://www.gaitameonline.com/rateaj/");
        WebTarget resourceWebTarget = webTarget.path("getrate");
        invocationBuilder = resourceWebTarget.request(MediaType.TEXT_HTML_TYPE);
    }

    @Schedule(minute = "*", hour = "*")
    public void acquire() {
        LOG.debug("invoked");
        System.out.println("invoked");
        
        if (isMarketClosed()) {
            LOG.info("Market is closed. return this batch.");
            return;
        }
        
        Response response = invocationBuilder.get();
        Date currentTimestamp = new Date();
        Rates rates = response.readEntity(Rates.class);
        System.out.println(rates);
        response.close();
        List<CurrencyRate> currencyRatesList = filter(rates);
        rateService.saveRates(currencyRatesList, currentTimestamp);
    }

    private List<CurrencyRate> filter(Rates rates) {
        return rates.getQuotes().stream().filter(c -> PAIRS.contains(c.getCurrencyPairCode())).collect(Collectors.toList());
    }
    
    /**
     * 
     * @return 
     */
    private boolean isMarketClosed() {
        return false;
    }
}
