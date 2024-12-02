package com.leetcode.adevent.two;

import com.leetcode.adevent.ResourceUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RedNosedReportSingleLevelTolerate {
    public int countSafeReports(List<List<Integer>> reports) {
        int counter = reports.size();
        for (List<Integer> report : reports) {
            if (report.get(0) < 0 || report.get(1) < 0) {
                return -1;
            }
            boolean increasing = report.get(0) < report.get(1);
            int tolerated = 0;
            for (int j = 1; j < report.size(); j++) {
                if (tolerated > 1) {
                    counter--;
                    break;
                }
                if (increasing) {
                    int diff = report.get(j) - report.get(j - 1);
                    if (diff < 1 || diff > 3) {
                        if (Math.abs(diff) > 4) {
                            counter--;
                            break;
                        }
                        tolerated++;
                    }

                } else {
                    if (report.get(j - 1) - report.get(j) < 1 || report.get(j - 1) - report.get(j) > 3) {
                        tolerated++;
                    }
                }
            }
        }
        return counter;
    }

    public static void main(String[] args) {
        try {
//            String file = ResourceUtils.readFile("red-nosed-report.txt");
//            List<List<Integer>> reposts = new ArrayList<>();
//            for (String s : file.split("\n")) {
//                List<Integer> report = new ArrayList<>();
//                for (String s1 : s.split(" ")) {
//                    report.add(Integer.parseInt(s1));
//                }
//                reposts.add(report);
//            }

            List<List<Integer>> reposts = new ArrayList<>();
            reposts.add(List.of(, 2, 3, 4, 5));
            RedNosedReportSingleLevelTolerate redNosedReport = new RedNosedReportSingleLevelTolerate();
            System.out.println(redNosedReport.countSafeReports(reposts));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
