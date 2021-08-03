package org.jesperancinha.shell.client.accounts;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public abstract class SeaShellsWSDLAccountsAbstract extends SeaShellsWSDLAbstract<String, Account> {

    public SeaShellsWSDLAccountsAbstract(String[] args) throws MalformedURLException, URISyntaxException {
        super(args);
    }

    public SeaShellsWSDLAccountsAbstract(URL url) {
        super(url);
    }
}
