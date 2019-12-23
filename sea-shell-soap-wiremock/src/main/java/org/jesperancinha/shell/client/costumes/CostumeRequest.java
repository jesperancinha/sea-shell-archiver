
package org.jesperancinha.shell.client.costumes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="costumeId" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "costumeId"
})
@XmlRootElement(name = "costumes")
public class CostumeRequest {

    protected int costumeId;

    /**
     * Gets the value of the costumeId property.
     * 
     */
    public int getCostumeId() {
        return costumeId;
    }

    /**
     * Sets the value of the costumeId property.
     * 
     */
    public void setCostumeId(int value) {
        this.costumeId = value;
    }

}
