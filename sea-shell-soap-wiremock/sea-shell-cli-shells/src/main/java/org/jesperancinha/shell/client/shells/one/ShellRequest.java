package org.jesperancinha.shell.client.shells.one;

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
 *         &lt;element name="shellId" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "shellRequest", propOrder = {"shellId"})
@XmlRootElement(name = "shells")
public class ShellRequest {

    protected int shellId;

    /**
     * Gets the value of the shellId property.
     */
    public int getShellId() {
        return shellId;
    }

    /**
     * Sets the value of the shellId property.
     */
    public void setShellId(int value) {
        this.shellId = value;
    }

}
