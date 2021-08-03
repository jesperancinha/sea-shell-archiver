package org.jesperancinha.shell.client.accounts;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import javax.xml.bind.JAXBElement;

/**
 * Created by jofisaes on 02/08/2021
 */

@Component
public class AccountsClient extends WebServiceGatewaySupport {
    @Value("${sea.shell.cli.soap.accounts}")
    private String seaShellsWSDLAccountsClientLocation;

    public AccountsClient() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("org.jesperancinha.shell.client.accounts");
        this.setDefaultUri(seaShellsWSDLAccountsClientLocation);
        this.setMarshaller(marshaller);
        this.setUnmarshaller(marshaller);
    }

    public Account getAccount(final String accountId) {

        final AccountRequest request = new AccountRequest();
        request.setAccountId(accountId);

        return (Account) (getWebServiceTemplate()
                .marshalSendAndReceive(seaShellsWSDLAccountsClientLocation, request,
                        new SoapActionCallback(
                                "http://org.jesperancinha.shells/SeaShellsWSDLAccounts/accounts")));
    }

}