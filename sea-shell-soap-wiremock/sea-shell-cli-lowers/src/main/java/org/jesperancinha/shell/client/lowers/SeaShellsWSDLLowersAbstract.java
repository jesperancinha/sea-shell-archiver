package org.jesperancinha.shell.client.lowers;

import org.jesperancinha.shell.client.accounts.SeaShellsWSDLAbstract;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public abstract class SeaShellsWSDLLowersAbstract extends SeaShellsWSDLAbstract<Long, Lower> {
    public SeaShellsWSDLLowersAbstract(final String[] args) throws MalformedURLException, URISyntaxException {
        super(args);
    }

    public SeaShellsWSDLLowersAbstract(final URL url) {
        super(url);
    }
}
