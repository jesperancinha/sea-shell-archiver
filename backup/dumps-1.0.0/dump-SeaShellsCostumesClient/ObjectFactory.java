package org.jesperancinha.shell.client.costumes;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the org.jesperancinha.shell.client.costumes package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.jesperancinha.shell.client.costumes
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CostumeRequest }
     */
    public CostumeRequest createCostumes() {
        return new CostumeRequest();
    }

    /**
     * Create an instance of {@link CostumeResponse }
     */
    public CostumeResponse createCostumesResponse() {
        return new CostumeResponse();
    }

}
