package org.jesperancinha.shell.client.accounts;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;

public abstract class SeaShellsWSDLAbstract<T> {

    protected final URL url;

    public SeaShellsWSDLAbstract(String[] args) throws MalformedURLException, URISyntaxException {
        this.url = getUrlFromCommandLine(args);
    }

    public SeaShellsWSDLAbstract(URL url) {
        this.url = url;
    }

    public abstract T getItem(long itemId);

    public abstract URL getLocalWsdlLocation() throws MalformedURLException, URISyntaxException;

    protected URL getUrl(String commandLineUrl) {
        if (commandLineUrl != null && !"".equals(commandLineUrl)) {
            File wsdlFile = new File(commandLineUrl);
            try {
                if (wsdlFile.exists()) {
                    return wsdlFile.toURI().toURL();
                } else {
                    return new URL(commandLineUrl);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    protected URL getUrlFromCommandLine(String[] args) throws MalformedURLException, URISyntaxException {
        if (args.length > 0) {
            return Optional.ofNullable(getUrl(args[0])).orElse(getLocalWsdlLocation());
        }
        return getLocalWsdlLocation();
    }

}
