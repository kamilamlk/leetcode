package com.leetcode.adevent.one;

import com.leetcode.adevent.LocationsDto;
import com.leetcode.adevent.ResourceUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

public class HistorianHysteria {
    public long distance(int[] locations1, int[] locations2) {
        Arrays.sort(locations1);
        Arrays.sort(locations2);
        long distance = 0;
        if (locations1.length != locations2.length) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < locations1.length; i++) {
            distance += Math.abs(locations1[i] - locations2[i]);
        }
        return distance;
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        LocationsDto locationsDto = ResourceUtils.getLocations("locations.txt");
        HistorianHysteria historianHysteria = new HistorianHysteria();

        System.out.println(historianHysteria.distance(
            locationsDto.getLocations1(),
            locationsDto.getLocations2())
        );
    }
}
