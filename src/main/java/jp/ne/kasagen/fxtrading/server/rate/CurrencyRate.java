package jp.ne.kasagen.fxtrading.server.rate;

import java.math.BigDecimal;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kasagen
 */
@XmlRootElement(name = "quotes")
@XmlAccessorType(XmlAccessType.FIELD)
public class CurrencyRate {

    @XmlElement
    private String currencyPairCode;

    @XmlElement
    private BigDecimal high;

    @XmlElement
    private BigDecimal low;

    @XmlElement
    private BigDecimal ask;

    @XmlElement
    private BigDecimal bid;

    @XmlElement
    private BigDecimal open;

    public String getCurrencyPairCode() {
        return currencyPairCode;
    }

    public void setCurrencyPairCode(String currencyPairCode) {
        this.currencyPairCode = currencyPairCode;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getAsk() {
        return ask;
    }

    public void setAsk(BigDecimal ask) {
        this.ask = ask;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.currencyPairCode);
        hash = 71 * hash + Objects.hashCode(this.high);
        hash = 71 * hash + Objects.hashCode(this.low);
        hash = 71 * hash + Objects.hashCode(this.ask);
        hash = 71 * hash + Objects.hashCode(this.bid);
        hash = 71 * hash + Objects.hashCode(this.open);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CurrencyRate other = (CurrencyRate) obj;
        if (!Objects.equals(this.currencyPairCode, other.currencyPairCode)) {
            return false;
        }
        if (!Objects.equals(this.high, other.high)) {
            return false;
        }
        if (!Objects.equals(this.low, other.low)) {
            return false;
        }
        if (!Objects.equals(this.ask, other.ask)) {
            return false;
        }
        if (!Objects.equals(this.bid, other.bid)) {
            return false;
        }
        if (!Objects.equals(this.open, other.open)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CurrencyRate{" + "currencyPairCode=" + currencyPairCode + ", high=" + high + ", low=" + low + ", ask=" + ask + ", bid=" + bid + ", open=" + open + '}';
    }

}
