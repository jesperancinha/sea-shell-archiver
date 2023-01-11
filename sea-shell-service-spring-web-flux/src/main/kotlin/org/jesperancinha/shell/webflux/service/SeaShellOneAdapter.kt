package org.jesperancinha.shell.webflux.service

import org.jesperancinha.shell.webflux.repo.*

open class SeaShellOneAdapter(
    protected val shellRepository: ShellRepositoryImpl,
    protected val shellPersonRepository: ShellPersonRepositoryImpl,
    protected val shellCostumeRepository: ShellCostumeRepositoryImpl,
    protected val shellAccountRepository: ShellAccountRepositoryImpl,
    protected val shellTopRepository: ShellTopRepositoryImpl,
    protected val shellLowerRepository: ShellLowerRepositoryImpl
)