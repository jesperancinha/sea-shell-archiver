package org.jesperancinha.shell.client.accounts;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.3.4
 * 2019-12-20T11:26:09.214+01:00
 * Generated source version: 3.3.4
 *
 */
@WebService(targetNamespace = "http://accountor/SeaShellsWSDLAccounts/", name = "SeaShellsWSDLAccounts")
@XmlSeeAlso({ObjectFactory.class})
public interface SeaShellsWSDLAccounts {

    @WebMethod(action = "http://org.jesperancinha.shells/SeaShellsWSDLAccounts/accounts")
    @RequestWrapper(localName = "accounts", targetNamespace = "http://org.jesperancinha.shells/SeaShellsWSDLAccounts/", className = "org.jesperancinha.shell.client.accounts.Accounts")
    @ResponseWrapper(localName = "accountsResponse", targetNamespace = "http://org.jesperancinha.shells/SeaShellsWSDLAccounts/", className = "org.jesperancinha.shell.client.accounts.AccountsResponse")
    @WebResult(name = "Account", targetNamespace = "")
    public Account accounts(

            @WebParam(name = "accountId", targetNamespace = "")
                    long accountId
    );
}
