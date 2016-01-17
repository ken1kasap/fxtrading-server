package jp.ne.kasagen.fxtrading.server.rate;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kasagen
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Rates {

    @XmlElement
    private List<CurrencyRate> quotes;

    public List<CurrencyRate> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<CurrencyRate> quotes) {
        this.quotes = quotes;
    }

    @Override
    public String toString() {
        return "Rates{" + "quotes=" + quotes + '}';
    }
}
