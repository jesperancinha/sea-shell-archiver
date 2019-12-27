package org.jesperancinha.shell.client.lowers;

import org.jesperancinha.shell.client.accounts.SeaShellsWSDLAbstract;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public abstract class SeaShellsWSDLCostumesAbstract extends SeaShellsWSDLAbstract<Costume> {
    public SeaShellsWSDLCostumesAbstract(String[] args) throws MalformedURLException, URISyntaxException {
        super(args);
    }

    public SeaShellsWSDLCostumesAbstract(URL url) {
        super(url);
    }
}
