package com.leetcode.queue.queue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class OpenLock {
    private static final int[] MOVES = {1, -1};

    public int openLock(String[] deadends, String target) {
        Set<String> deadendsSet = new HashSet<>();
        deadendsSet.addAll(Arrays.asList(deadends));

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add("0000");
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String node = queue.poll();
                if (node.equals(target)) {
                    return count;
                }
                if (visited.contains(node)) {
                    continue;
                }
                visited.add(node);
                if (deadendsSet.contains(node)) {
                    continue;
                }
                addNext(node, queue, deadendsSet);
            }
            count++;
        }
        return -1;
    }

    private void addNext(String node, Queue<String> queue, Set<String> deadendsSet) {
        char[] currCodeCh = node.toCharArray();
        for (int wheelNo = 0; wheelNo < 4; wheelNo++) {
            char originalCh = currCodeCh[wheelNo];

            for (int move : MOVES) {
                currCodeCh[wheelNo] = (char) ((originalCh - '0' + move + 10) % 10 + '0');
                String newCode = String.valueOf(currCodeCh);
                currCodeCh[wheelNo] = originalCh;
                if (!deadendsSet.contains(newCode)) {
                    queue.add(newCode);
                }
            }
        }
    }

    private char next(char c, boolean increment) {
        if (increment) {
            return c == '9' ? '0' : (char) (c + 1);
        } else {
            return c == '0' ? '9' : (char) (c - 1);
        }
    }
    
    public static void main(String[] args) {
        OpenLock openLock = new OpenLock();
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
