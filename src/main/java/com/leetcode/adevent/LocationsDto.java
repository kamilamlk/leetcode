package com.leetcode.adevent;

public class LocationsDto {
    private final int[] locations1;
    private final int[] locations2;

    public LocationsDto(int[] locations1, int[] locations2) {
        this.locations1 = locations1;
        this.locations2 = locations2;
    }

    public int[] getLocations1() {
        return locations1;
    }

    public int[] getLocations2() {
        return locations2;
    }
}
