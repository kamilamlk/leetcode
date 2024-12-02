package com.leetcode.adevent.two;

import com.leetcode.adevent.LocationsDto;
import com.leetcode.adevent.ResourceUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SimilarityScore {
    public long similarityScore(int[] locations1, int[] locations2) {

        Map<Integer, Integer> scoreMap = new HashMap<>();
        for (int j : locations2) {
            scoreMap.putIfAbsent(j, 0);
            scoreMap.put(j, scoreMap.get(j) + 1);
        }
        long score = 0;
        for (int j : locations1) {
            score += ((long) scoreMap.getOrDefault(j, 0) * j);
        }
        return score;
    }

    public static void main(String[] args) {
        LocationsDto locationsDto = ResourceUtils.getLocations("locations.txt");
        SimilarityScore similarityScore = new SimilarityScore();

        System.out.println(similarityScore.similarityScore(locationsDto.getLocations1(), locationsDto.getLocations2()));
    }
}
