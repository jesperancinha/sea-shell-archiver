package org.jesperancinha.shell;


import java.nio.charset.StandardCharsets;

public class CharStreams {
    public static String toString(byte[] bytesFromSource) {
        return new String(bytesFromSource, StandardCharsets.UTF_8);
    }
}
