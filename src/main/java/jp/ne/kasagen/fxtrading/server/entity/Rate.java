package jp.ne.kasagen.fxtrading.server.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kasagen
 */
@Entity
@Table(name = "rate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rate.findAll", query = "SELECT r FROM Rate r"),
    @NamedQuery(name = "Rate.findByTransactionId", query = "SELECT r FROM Rate r WHERE r.transactionId = :transactionId"),
    @NamedQuery(name = "Rate.findByCurrencyPair", query = "SELECT r FROM Rate r WHERE r.currencyPair = :currencyPair"),
    @NamedQuery(name = "Rate.findByAcquisitionTime", query = "SELECT r FROM Rate r WHERE r.acquisitionTime = :acquisitionTime")
})
@SequenceGenerator(name = "transactionSeq",sequenceName = "transaction_seq", allocationSize = 1)
public class Rate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactionSeq")
    private Long transactionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "currency_pair")
    private String currencyPair;
    @Basic(optional = false)
    @NotNull
    @Column(name = "acquisition_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date acquisitionTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "high_rate")
    private BigDecimal high;
    @Basic(optional = false)
    @NotNull
    @Column(name = "low_rate")
    private BigDecimal low;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ask_rate")
    private BigDecimal ask;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bid_rate")
    private BigDecimal bid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "open_rate")
    private BigDecimal open;

    public Rate() {
    }

    public Rate(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Rate(Long transactionId, String currencyPair, Date acquisitionTime, BigDecimal high, BigDecimal low, BigDecimal ask, BigDecimal bid, BigDecimal open) {
        this.transactionId = transactionId;
        this.currencyPair = currencyPair;
        this.acquisitionTime = acquisitionTime;
        this.high = high;
        this.low = low;
        this.ask = ask;
        this.bid = bid;
        this.open = open;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }

    public void setCurrencyPair(String currencyPair) {
        this.currencyPair = currencyPair;
    }

    public Date getAcquisitionTime() {
        return acquisitionTime;
    }

    public void setAcquisitionTime(Date acquisitionTime) {
        this.acquisitionTime = acquisitionTime;
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
        int hash = 0;
        hash += (transactionId != null ? transactionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rate)) {
            return false;
        }
        Rate other = (Rate) object;
        if ((this.transactionId == null && other.transactionId != null) || (this.transactionId != null && !this.transactionId.equals(other.transactionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Rate{" + "transactionId=" + transactionId + ", currencyPair=" + currencyPair + ", acquisitionTime=" + acquisitionTime + ", high=" + high + ", low=" + low + ", ask=" + ask + ", bid=" + bid + ", open=" + open + '}';
    }

}
