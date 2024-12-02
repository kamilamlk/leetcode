package com.leetcode.adevent;

import com.leetcode.adevent.one.HistorianHysteria;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ResourceUtils {
    private ResourceUtils() {

    }

    public static String readFile(String file) throws IOException, URISyntaxException {
        var path = Paths.get(HistorianHysteria.class.getClassLoader().getResource(file).toURI());
        return Files.readString(path);

    }

    public static LocationsDto getLocations(String file) {
        try {
            String input = readFile(file);
            String[] lines = input.split("\n");
            int[] locations1 = new int[lines.length];
            int[] locations2 = new int[lines.length];
            for (int i = 0; i < lines.length; i++) {
                String[] line = lines[i].split(" {3}");
                locations1[i] = Integer.parseInt(line[0]);
                locations2[i] = Integer.parseInt(line[1]);
            }
            return new LocationsDto(locations1, locations2);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
