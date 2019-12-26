package org.jesperancinha.shell.client.shells;

import org.jesperancinha.shell.client.accounts.SeaShellsWSDLAbstract;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public abstract class SeaShellsWSDLShellsAbstract extends SeaShellsWSDLAbstract<Shell> {

    public SeaShellsWSDLShellsAbstract(String[] args) throws MalformedURLException, URISyntaxException {
        super(args);
    }

    public SeaShellsWSDLShellsAbstract(URL url) {
        super(url);
    }

    public abstract List<Long> getAllShellIds();
}
