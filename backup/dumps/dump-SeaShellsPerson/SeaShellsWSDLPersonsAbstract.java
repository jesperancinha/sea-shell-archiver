package org.jesperancinha.shell.client.persons;

import org.jesperancinha.shell.client.accounts.SeaShellsWSDLAbstract;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public abstract class SeaShellsWSDLPersonsAbstract extends SeaShellsWSDLAbstract<Long, Person> {
    public SeaShellsWSDLPersonsAbstract(String[] args) throws MalformedURLException, URISyntaxException {
        super(args);
    }

    public SeaShellsWSDLPersonsAbstract(URL url) {
        super(url);
    }
}
