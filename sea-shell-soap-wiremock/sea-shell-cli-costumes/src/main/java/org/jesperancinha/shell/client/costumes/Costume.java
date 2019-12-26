package org.jesperancinha.shell.client.costumes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Costume")
@XmlType(name = "", propOrder = {
        "topId", "lowerId"
})
public class Costume {

    private Long topId;
    private Long lowerId;

    public Long getTopId() {
        return topId;
    }

    @XmlElement(name = "TopId")
    public void setTopId(Long topId) {
        this.topId = topId;
    }

    public Long getLowerId() {
        return lowerId;
    }

    @XmlElement(name = "LowerId")
    public void setLowerId(Long lowerId) {
        this.lowerId = lowerId;
    }
}
