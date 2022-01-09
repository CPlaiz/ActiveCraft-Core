package de.silencio.activecraftcore.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;

public class JsonReader {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static Map getMap(String url) throws IOException, JSONException {
        try (InputStream is = new URL(url).openStream()) {
            return new ObjectMapper().readValue(new URL(url), Map.class);
        }
    }
}