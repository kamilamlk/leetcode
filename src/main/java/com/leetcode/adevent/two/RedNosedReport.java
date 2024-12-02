package com.leetcode.adevent.two;

import com.leetcode.adevent.ResourceUtils;

import java.util.ArrayList;
import java.util.List;

public class RedNosedReport {
    public int countSafeReports(List<List<Integer>> reports) {
        int counter = reports.size();
        for (List<Integer> report : reports) {
            if (report.get(0) < 0 || report.get(1) < 0) {
                return -1;
            }
            boolean increasing = report.get(0) < report.get(1);
            for (int j = 1; j < report.size(); j++) {
                if (increasing) {
                    if (report.get(j) - report.get(j -1) < 1 || report.get(j) - report.get(j -1) > 3) {
                        counter--;
                        break;
                    }
                } else {
                    if (report.get(j - 1) - report.get(j) < 1 || report.get(j - 1) - report.get(j) > 3) {
                        counter--;
                        break;
                    }
                }
            }
        }
        return counter;
    }

    public static void main(String[] args) {
        try {
            String file = ResourceUtils.readFile("red-nosed-report.txt");
            List<List<Integer>> reposts = new ArrayList<>();
            for (String s : file.split("\n")) {
                List<Integer> report = new ArrayList<>();
                for (String s1 : s.split(" ")) {
                    report.add(Integer.parseInt(s1));
                }
                reposts.add(report);
            }

            RedNosedReport redNosedReport = new RedNosedReport();
            System.out.println(redNosedReport.countSafeReports(reposts));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
