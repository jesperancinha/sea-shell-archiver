package org.jesperancinha.shell.client.tops;

import org.jesperancinha.shell.client.accounts.SeaShellsWSDLAbstract;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public abstract class SeaShellsWSDLTopAbstract extends SeaShellsWSDLAbstract<Long, Top> {
    public SeaShellsWSDLTopAbstract(String[] args) throws MalformedURLException, URISyntaxException {
        super(args);
    }

    public SeaShellsWSDLTopAbstract(URL url) {
        super(url);
    }
}
