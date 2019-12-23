package org.jesperancinha.shell.client.lowers;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;

@XmlRootElement(name = "Lower")
@XmlType(name = "", propOrder = {
        "type", "color", "size"
})
public class Lower {

    private String type;
    private String color;
    private String size;

    public String getType() {
        return type;
    }

    @XmlElement(name = "Type")
    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    @XmlElement(name = "Color")
    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    @XmlElement(name = "Size")
    public void setSize(String size) {
        this.size = size;
    }



}
