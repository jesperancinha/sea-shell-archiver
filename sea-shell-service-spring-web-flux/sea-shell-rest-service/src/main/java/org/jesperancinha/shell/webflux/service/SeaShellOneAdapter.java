package org.jesperancinha.shell.webflux.service;

import org.jesperancinha.shell.webflux.repo.ShellAccountRepository;
import org.jesperancinha.shell.webflux.repo.ShellCostumeRepository;
import org.jesperancinha.shell.webflux.repo.ShellLowerRepository;
import org.jesperancinha.shell.webflux.repo.ShellPersonRepository;
import org.jesperancinha.shell.webflux.repo.ShellRepository;
import org.jesperancinha.shell.webflux.repo.ShellTopRepository;

public class SeaShellOneAdapter {

    protected final ShellRepository shellRepository;

    protected final ShellPersonRepository shellPersonRepository;

    protected final ShellCostumeRepository shellCostumeRepository;

    protected final ShellAccountRepository shellAccountRepository;

    protected final ShellTopRepository shellTopRepository;

    protected final ShellLowerRepository shellLowerRepository;


    public SeaShellOneAdapter(ShellRepository shellRepository,
                              ShellPersonRepository shellPersonRepository,
                              ShellCostumeRepository shellCostumeRepository,
                              ShellAccountRepository shellAccountRepository,
                              ShellTopRepository shellTopRepository,
                              ShellLowerRepository shellLowerRepository) {
        this.shellRepository = shellRepository;
        this.shellPersonRepository = shellPersonRepository;
        this.shellCostumeRepository = shellCostumeRepository;
        this.shellAccountRepository = shellAccountRepository;
        this.shellTopRepository = shellTopRepository;
        this.shellLowerRepository = shellLowerRepository;
    }

}
