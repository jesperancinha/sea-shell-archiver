package org.jesperancinha.shell;


import wiremock.org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStreamReader;

public class CharStreams {
    public static String toString(InputStreamReader stringFromResource) throws IOException {
        return String.join("\n", IOUtils.readLines(stringFromResource));
    }
}
