package org.jesperancinha.shell.client.accounts;

import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;

@XmlRootElement(name = "Account")
@XmlType(name = "", propOrder = {
        "value", "currency"
})
@ToString
public class Account {

    private BigDecimal value;
    private String currency;

    public BigDecimal getValue() {
        return value;
    }

   @XmlElement(name = "Value")
   public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    @XmlElement(name = "Currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
