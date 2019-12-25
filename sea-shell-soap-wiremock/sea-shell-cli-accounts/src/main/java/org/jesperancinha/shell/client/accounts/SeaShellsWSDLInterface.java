package org.jesperancinha.shell.client.accounts;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public interface SeaShellsWSDLInterface<T> {

    Account getItem(int _accounts_accountId);

    URL getLocalWsdlLocation() throws MalformedURLException, URISyntaxException;
}
