package de.silencio.activecraftcore.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

public class WebReader {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static HashMap<String, Integer> getACVersionMap() throws IOException {
        HashMap<String, Integer> map = new HashMap<>();
        URL u = new URL("https://raw.githubusercontent.com/CPlaiz/ActiveCraft-Core/master/plugins.json");
        URLConnection conn = u.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        conn.getInputStream()));
        StringBuffer buffer = new StringBuffer();
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            buffer.append(inputLine);
        in.close();
        String jsonString = buffer.toString();
        jsonString = jsonString.replace(" ", "")
                .replace("\"", "")
                .replace("{", "")
                .replace("}", "");
        String[] jsonItems = jsonString.split(",");
        for (String item : jsonItems) map.put(item.split(":")[0], Integer.valueOf(item.split(":")[1]));
        return map;
    }
}