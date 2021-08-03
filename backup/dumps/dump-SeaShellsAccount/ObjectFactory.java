package org.jesperancinha.shell.client.accounts;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the org.jesperancinha.shell.client.accounts package.
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.jesperancinha.shell.client.accounts
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AccountRequest }
     */
    public AccountRequest createAccounts() {
        return new AccountRequest();
    }

    /**
     * Create an instance of {@link AccountResponse }
     */
    public AccountResponse createAccountsResponse() {
        return new AccountResponse();
    }

}
