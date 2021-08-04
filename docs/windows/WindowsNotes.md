# Sea Shell Archiver Windows Installation and Setup notes

---

## Installation notes

This is just a document listing how the process went to make JDK16 work on windows without resourcing to a Java version manager and only using PowerShell.

If you are looking for a quick installation using a [PowerShell Script](https://github.com/jesperancinha/sea-shell-archiver/blob/master/docs/windows/setup.ps), please make sure to follow the first point in the following listing in points 1 and 2.

In regards to point 2, the most secure way to perform this install is probably just to install it manually on windows.

Disabling and bypassing security policies may pose a risk to your machine depending on your circumstances and this is only needed for a quick run of the script.

Since you can run this script manually, I advise you to run step by step each command and check the output on each case.

The [chocolatey](https://chocolatey.org/install) installation needs to force a security bypass in order to achieve this. It is a temporary one and the scope of it is the process you are running it in.

1. https://chocolatey.org/install
2. Installing with the script
   - Set-ExecutionPolicy -Scope Process -ExecutionPolicy Bypass
   - run [setup.ps1](./setup.ps1)
   - Set-ExecutionPolicy -Scope Process -ExecutionPolicy Default
3. https://plugins.jetbrains.com/plugin/10249-powershell/features
4. https://github.com/shyiko/jabba
5. https://www.azul.com/blog/eclipse-temurin-new-distribution-of-openjdk/
6. echo $env:JAVA_HOME


The script attempts to install [chocolatey](https://chocolatey.org/install), git, maven and finally [JDK16](https://adoptium.net/).

Be sure to check its contents:

```shell
Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://chocolatey.org/install.ps1'))

choco install maven -y
choco uninstall maven -y
choco install maven -y
choco install git -y
choco install docker -y
choco install docker-machine -y
choco install docker-desktop -y

curl https://github.com/adoptium/temurin16-binaries/releases/download/jdk-16.0.2%2B7/OpenJDK16U-jdk_x64_windows_hotspot_16.0.2_7.msi -o jdk16.msi
jdk16.msi
```

This script may not completely run on the first run and this is why there is a maven uninstall and then a new installation.

The first script line is taken literally from [chocolatey](https://chocolatey.org/install).

The final script lines download the JDK16 from [Adoptium](https://adoptium.net/) and finally run the freshly downloaded msi file.

If you experience issues starting Docker in regard to the WSL 2 Linux Kernel, please find more help on the [computerhope website](https://www.computerhope.com/issues/ch001879.htm) and the [omgubuntu website](https://www.omgubuntu.co.uk/how-to-install-wsl2-on-windows-10).
I followed their steps which are shortly these:

```powershell
dism.exe /online /enable-feature /featurename:Microsoft-Windows-Subsystem-Linux /all /norestart

dism.exe /online /enable-feature /featurename:VirtualMachinePlatform /all /norestart

Enable-WindowsOptionalFeature -Online -FeatureName Microsoft-Windows-Subsystem-Linux

wsl --set-default-version 2
```

According to the [microsoft website](https://docs.microsoft.com/nl-nl/windows/wsl/install-win10#step-4---download-the-linux-kernel-update-package), you may have to download a special WSL 2 package:

```
curl https://wslstorestorage.blob.core.windows.net/wslblob/wsl_update_x64.msi -o wsl_update_x64.msi
```

---