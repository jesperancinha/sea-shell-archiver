package org.jesperancinha.shell.webflux.service;

import org.jesperancinha.shell.webflux.repo.*;

public class SeaShellOneAdapter {

    protected final ShellRepositoryImpl shellRepository;

    protected final ShellPersonRepositoryImpl shellPersonRepository;

    protected final ShellCostumeRepositoryImpl shellCostumeRepository;

    protected final ShellAccountRepositoryImpl shellAccountRepository;

    protected final ShellTopRepositoryImpl shellTopRepository;

    protected final ShellLowerRepositoryImpl shellLowerRepository;


    public SeaShellOneAdapter(ShellRepositoryImpl shellRepository,
                              ShellPersonRepositoryImpl shellPersonRepository,
                              ShellCostumeRepositoryImpl shellCostumeRepository,
                              ShellAccountRepositoryImpl shellAccountRepository,
                              ShellTopRepositoryImpl shellTopRepository,
                              ShellLowerRepositoryImpl shellLowerRepository) {
        this.shellRepository = shellRepository;
        this.shellPersonRepository = shellPersonRepository;
        this.shellCostumeRepository = shellCostumeRepository;
        this.shellAccountRepository = shellAccountRepository;
        this.shellTopRepository = shellTopRepository;
        this.shellLowerRepository = shellLowerRepository;
    }

}
