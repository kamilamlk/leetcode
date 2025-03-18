package com.leetcode.arrays;

import java.util.HashSet;
import java.util.Set;

public class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0;
        int totalCost = 0;
        int tank = 0;
        int startIdx = 0;
        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
            tank += gas[i] - cost[i];
            if (tank < 0) {
                startIdx = i + 1;
                tank = 0;
            }
        }

        return totalGas < totalCost ? -1 : startIdx;
    }

    public static void main(String[] args) {
        GasStation gasStation = new GasStation();
        System.out.println(gasStation.canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}));
        //System.out.println(gasStation.canCompleteCircuit(new int[]{2, 3, 4}, new int[]{3, 4, 3}));

        System.out.println(gasStation.canCompleteCircuit(new int[]{1,2,3,4,3,2,4,1,5,3,2,4}, new int[]{1,1,1,3,2,4,3,6,7,4,3,1}));
    }
}
