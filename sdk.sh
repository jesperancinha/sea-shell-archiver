#!/usr/bin/env sh

echo -e '\033[92mChanging to the latest available SDK MAN Java SDK 25 version.\033[0m'
echo -e '\033[91mRemember to run this command like this: . ./sdk.sh\033[0m'
sdk install java 25-open
sdk use java $(sdk list java | grep installed | grep "| 25" |  cut -d'|' -f6- | cut -d' ' -f2-)
