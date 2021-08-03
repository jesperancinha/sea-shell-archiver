package org.jesperancinha.shell.client.shells;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlRootElement(name = "Shell")
@XmlType(name = "", propOrder = {"shellIds"})
public class AllShell {

    private List<Long> shellIds;

    public List<Long> getShellIds() {
        return shellIds;
    }

    @XmlElementWrapper(name = "Shells")
    @XmlElement(name = "ShellId")
    public void setShellIds(List<Long> shellIds) {
        this.shellIds = shellIds;
    }
}

