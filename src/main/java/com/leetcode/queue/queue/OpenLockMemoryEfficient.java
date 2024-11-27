package com.leetcode.queue.queue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class OpenLockMemoryEfficient {

    private static final int[] MOVES = {1, -1};
    private static final String START = "0000";

    public int openLock(String[] deadends, String target) {
        Set<String> deadCodes = new HashSet<>(Arrays.asList(deadends));

        Set<String> startCodes = new HashSet<>();
        startCodes.add(START);
        Set<String> endCodes = new HashSet<>();
        endCodes.add(target);

        int distance = 0;
        Set<String> temp;
        while (!startCodes.isEmpty() && !endCodes.isEmpty()) {
            if (startCodes.size() > endCodes.size()) {
                temp = startCodes;
                startCodes = endCodes;
                endCodes = temp;
            }
            System.out.println("startCodes: " + startCodes + " endCodes: " + endCodes);

            temp = new HashSet<>();
            for (String currCode : startCodes) {
                if (endCodes.contains(currCode)) {
                    return distance;
                }

                if (deadCodes.contains(currCode)) continue;
                deadCodes.add(currCode);

                char[] currCodeCh = currCode.toCharArray();
                for (int wheelNo = 0; wheelNo < 4; wheelNo++) {
                    char originalCh = currCodeCh[wheelNo];

                    for (int move : MOVES) {
                        currCodeCh[wheelNo] = (char) ((originalCh - '0' + move + 10) % 10 + '0');
                        String newCode = String.valueOf(currCodeCh);
                        currCodeCh[wheelNo] = originalCh;
                        if (!deadCodes.contains(newCode)) {
                            temp.add(newCode);
                        }
                    }
                }
            }
            startCodes = temp;
            distance++;
        }
        return -1;
    }
    
    public static void main(String[] args) {
        OpenLockMemoryEfficient openLock = new OpenLockMemoryEfficient();
        String[] deadends = new String[] {"0201", "0101", "0102", "1212", "2002"};
        String target = "0202";
        System.out.println(openLock.openLock(deadends, target));

        deadends = new String[] {"8888"};
        target = "0009";
        System.out.println(openLock.openLock(deadends, target));

        deadends = new String[] {"2111","2202","2210","0201","2210"};
        target = "2201";
        System.out.println(openLock.openLock(deadends, target));

        deadends = new String[] {"8887","8889","8878","8898","8788","8988","7888","9888"};
        target = "8888";
        System.out.println(openLock.openLock(deadends, target));
    }
}
