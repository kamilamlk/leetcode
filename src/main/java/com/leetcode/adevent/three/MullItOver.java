package com.leetcode.adevent.three;

import com.leetcode.adevent.ResourceUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.regex.Pattern;

public class MullItOver {
    private final Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)|do\\(\\)|don't\\(\\)");

    public int mullItOver(String input) {
        var matcher = pattern.matcher(input);
        var sum = 0;
        boolean isEnabled = true;
        while (matcher.find()) {
            if (matcher.group().equals("do()")) {
                isEnabled = true;
            } else if (matcher.group().equals("don't()")) {
                isEnabled = false;
            } else if (isEnabled && matcher.group().startsWith("mul")) {
                int x = Integer.parseInt(matcher.group(1));
                int y = Integer.parseInt(matcher.group(2));
                sum += x * y;
            }
        }
        return sum;
    }


    public static void main(String[] args) throws IOException, URISyntaxException {
        MullItOver mullItOver = new MullItOver();
        System.out.println(mullItOver.mullItOver("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"));
        String file = ResourceUtils.readFile("mull-it-over.txt");
        System.out.println(mullItOver.mullItOver(file));
    }
}
