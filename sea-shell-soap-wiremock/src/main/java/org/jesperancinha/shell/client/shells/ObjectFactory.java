
package org.jesperancinha.shell.client.shells;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.jesperancinha.shell.client.shells package.
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.jesperancinha.shell.client.shells
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ShellRequest }
     *
     */
    public ShellRequest createShells() {
        return new ShellRequest();
    }

    /**
     * Create an instance of {@link ShellResponse }
     * 
     */
    public ShellResponse createShellsResponse() {
        return new ShellResponse();
    }

}
