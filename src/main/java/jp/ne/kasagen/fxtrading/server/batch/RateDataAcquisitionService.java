package jp.ne.kasagen.fxtrading.server.batch;

import java.time.LocalDateTime;
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
import jp.ne.kasagen.fxtrading.server.util.MarketTime;
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

    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(RateDataAcquisitionService.class);

    /**
     * Currency pairs to register.
     */
    private static final List<String> PAIRS = Arrays.asList("USDJPY", "EURUSD", "AUDUSD", "NZDUSD", "EURJPY", "AUDJPY", "NZDJPY");

    /**
     * JAX-RS Client.
     */
    private Client client;

    /**
     * JAX-RS client request invocation builder.
     */
    private Invocation.Builder invocationBuilder;

    /**
     * Rate registration service.
     */
    @Inject
    private RateService rateService;

    /**
     * Constructor.
     */
    public RateDataAcquisitionService() {
        LOG.debug("constructed.");
    }

    /**
     * Initialize JAX-RS client.
     */
    @PostConstruct
    public void initialize() {
        client = ClientBuilder.newClient().register(JacksonFeature.class).register(GaitameJacksonReader.class);
        WebTarget webTarget = client.target("http://www.gaitameonline.com/rateaj/");
        WebTarget resourceWebTarget = webTarget.path("getrate");
        invocationBuilder = resourceWebTarget.request(MediaType.TEXT_HTML_TYPE);
    }

    /**
     * Rate acquire service method.
     * 
     * Service method is invoked every minutes.
     * When the market is closed, service method is returned.
     */
    @Schedule(minute = "*", hour = "*")
    public void acquire() {
        LOG.debug("acquire() is invoked.");
        
        if (!isMarketOpened(LocalDateTime.now())) {
            LOG.info("Market is not opened.");
            return;
        }
        
        Response response = invocationBuilder.get();
        Date currentTimestamp = new Date();
        Rates rates = response.readEntity(Rates.class);
        LOG.info(rates.toString());
        response.close();
        List<CurrencyRate> currencyRatesList = filter(rates);
        rateService.saveRates(currencyRatesList, currentTimestamp);
    }

    /**
     * 取得した全レートの中から指定した通過ペアのみにフィルタリングします。
     * @param rates 全通貨レート
     * @return PAIRSで定義した通過ペアのCurrencyRateリストを返します。
     */
    private List<CurrencyRate> filter(Rates rates) {
        return rates.getQuotes().stream().filter(c -> PAIRS.contains(c.getCurrencyPairCode())).collect(Collectors.toList());
    }
    
    /**
     * 取引市場が開いているかを返却します。
     * 市場が開いている場合にtrueを返します。
     * 
     * @param datetime LocalDateTime
     * @return 市場が開いている場合にtrue
     */
    private boolean isMarketOpened(LocalDateTime datetime) {
        MarketTime market = new MarketTime();
        return market.isOpened(datetime);
    }
}
