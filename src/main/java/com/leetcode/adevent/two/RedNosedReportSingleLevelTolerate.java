package com.leetcode.adevent.two;

import com.leetcode.adevent.ResourceUtils;

import java.util.ArrayList;
import java.util.List;

public class RedNosedReportSingleLevelTolerate {
    public int countSafeReports(List<List<Integer>> reports) {
        int counter = reports.size();
        for (List<Integer> report : reports) {
            if (report.get(0) < 0 || report.get(1) < 0) {
                return -1;
            }
            boolean increasing = report.get(0) < report.get(1);
            int tolerated = 0;
            boolean safe = true;
            for (int j = 1; j < report.size(); j++) {
                if (tolerated > 1) {
                    safe = false;
                    break;
                }
                if (increasing) {
                    int diff = report.get(j) - report.get(j - 1);
                    if (diff < 1 || diff > 3) {
                        if (Math.abs(diff) > 4) {
                            safe = false;
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
            if (!safe) {
                safe = canBeMadeSafe(report, increasing);
            }
            if (!safe) {
                counter--;
            }
        }
        return counter;
    }

    private boolean canBeMadeSafe(List<Integer> report, boolean increasing) {
        for (int i = 0; i < report.size(); i++) {
            List<Integer> modifiedReport = new ArrayList<>(report);
            modifiedReport.remove(i);
            if (isSafe(modifiedReport, increasing)) {
                return true;
            }
        }
        return false;
    }

    private boolean isSafe(List<Integer> report, boolean increasing) {
        int tolerated = 0;
        for (int j = 1; j < report.size(); j++) {
            if (tolerated > 1) {
                return false;
            }
            if (increasing) {
                int diff = report.get(j) - report.get(j - 1);
                if (diff < 1 || diff > 3) {
                    if (Math.abs(diff) > 4) {
                        return false;
                    }
                    tolerated++;
                }
            } else {
                if (report.get(j - 1) - report.get(j) < 1 || report.get(j - 1) - report.get(j) > 3) {
                    tolerated++;
                }
            }
        }
        return tolerated <= 1;
    }

    public static void main(String[] args) {
        try {
//            List<List<Integer>> reposts = new ArrayList<>();
//            reposts.add(List.of(1, 2, 3, 4, 5));
//            reposts.add(List.of(7, 6, 4, 2, 1));
//            reposts.add(List.of(1, 2, 7, 8, 9));
//            reposts.add(List.of(9, 7, 6, 2, 1));
//            reposts.add(List.of(1, 3, 2, 4, 5));
//            reposts.add(List.of(8, 6, 4, 4, 1));
//            reposts.add(List.of(1, 3, 6, 7, 9));

            String file = ResourceUtils.readFile("red-nosed-report.txt");
            List<List<Integer>> reposts = new ArrayList<>();
            for (String s : file.split("\n")) {
                List<Integer> report = new ArrayList<>();
                for (String s1 : s.split(" ")) {
                    report.add(Integer.parseInt(s1));
                }
                reposts.add(report);
            }
            RedNosedReportSingleLevelTolerate redNosedReport = new RedNosedReportSingleLevelTolerate();
            System.out.println(redNosedReport.countSafeReports(reposts));


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}