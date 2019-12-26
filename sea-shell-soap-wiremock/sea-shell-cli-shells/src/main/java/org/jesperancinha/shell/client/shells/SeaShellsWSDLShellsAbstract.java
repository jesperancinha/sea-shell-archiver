package org.jesperancinha.shell.client.shells;

import org.jesperancinha.shell.client.accounts.SeaShellsWSDLAbstract;

import java.util.List;

public abstract class SeaShellsWSDLShellsAbstract extends SeaShellsWSDLAbstract<Shell> {

    public abstract List<Long> getAllShellIds();
}
