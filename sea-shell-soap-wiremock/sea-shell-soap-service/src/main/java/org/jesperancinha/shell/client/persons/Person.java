package org.jesperancinha.shell.client.persons;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Person")
@XmlType(name = "", propOrder = {
        "name","activity", "costumeId", "accountId","shellId"
})
public class Person {

    private String name;
    private String activity;
    private Integer costumeId;
    private String accountId;
    private Integer shellId;

    public String getName() {
        return name;
    }

    @XmlElement(name = "Name")
    public void setName(String name) {
        this.name = name;
    }

    public String getActivity() {
        return activity;
    }

    @XmlElement(name = "Activity")
    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Integer getCostumeId() {
        return costumeId;
    }

    @XmlElement(name = "CostumeId")
    public void setCostumeId(Integer costumeId) {
        this.costumeId = costumeId;
    }

    public String getAccountId() {
        return accountId;
    }

    @XmlElement(name = "AccountId")
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Integer getShellId() {
        return shellId;
    }

    @XmlElement(name = "ShellId")
    public void setShellId(Integer shellId) {
        this.shellId = shellId;
    }
}
