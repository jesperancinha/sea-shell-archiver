package org.jesperancinha.shell.client.shells;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlRootElement(name = "Shell")
@XmlType(name = "", propOrder = {
        "name", "scientificName", "slogan", "persons", "costumes"
})
public class Shell {

    private String name;
    private String scientificName;
    private String slogan;
    private List<Long> persons;
    private List<Long> costumes;

    public String getName() {
        return name;
    }

    @XmlElement(name = "Name")
    public void setName(String name) {
        this.name = name;
    }

    public String getScientificName() {
        return scientificName;
    }

    @XmlElement(name = "ScientificName")
    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getSlogan() {
        return slogan;
    }

    @XmlElement(name = "Slogan")
    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public List<Long> getPersons() {
        return persons;
    }

    @XmlElementWrapper(name = "Persons")
    @XmlElement(name = "PersonId")
    public void setPersons(List<Long> persons) {
        this.persons = persons;
    }

    public List<Long> getCostumes() {
        return costumes;
    }

    @XmlElementWrapper(name = "Costumes")
    @XmlElement(name = "CostumeId")
    public void setCostumes(List<Long> costumes) {
        this.costumes = costumes;
    }
}

