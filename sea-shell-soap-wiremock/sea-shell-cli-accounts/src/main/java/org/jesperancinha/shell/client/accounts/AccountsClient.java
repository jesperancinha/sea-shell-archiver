package org.jesperancinha.shell.client.accounts;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import javax.xml.bind.JAXBElement;

/**
 * Created by jofisaes on 02/08/2021
 */

public class AccountsClient extends WebServiceGatewaySupport {
    @Value("${sea.shell.cli.soap.accounts}")
    private String seaShellsWSDLAccountsClientLocation;

    public Account getAccount(final String accountId) {

        final AccountRequest request = new AccountRequest();
        request.setAccountId(accountId);

        return ((JAXBElement<Account>) (getWebServiceTemplate()
                .marshalSendAndReceive(seaShellsWSDLAccountsClientLocation, request,
                        new SoapActionCallback(
                                "http://org.jesperancinha.shells/SeaShellsWSDLShells/accounts")))).getValue();
    }

}