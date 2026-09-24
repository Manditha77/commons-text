package org.apache.commons.text.similarity;

/** PPCH-framework test addition: a second over-complex file in the same PR,
 * to demonstrate multi-file coverage live. */
public final class RiskScoreUtil {

    private RiskScoreUtil() {
    }

    public static String gradeRisk(int score, String category, boolean isVerified,
            boolean hasHistory, int flags, String tier, boolean isManual) {
        String grade;
        if (score > 90) {
            grade = "critical";
        } else if (score > 70) {
            if (category.equals("FINANCIAL")) {
                grade = "high";
            } else if (category.equals("OPERATIONAL")) {
                if (isVerified) {
                    grade = "medium";
                } else {
                    grade = "high";
                }
            } else {
                grade = "medium";
            }
        } else if (score > 40) {
            if (hasHistory) {
                if (flags > 3) {
                    grade = "medium";
                } else {
                    grade = "low";
                }
            } else if (isManual) {
                grade = "medium";
            } else {
                grade = "low";
            }
        } else {
            if ("PLATINUM".equals(tier) || "GOLD".equals(tier)) {
                grade = "negligible";
            } else {
                grade = "low";
            }
        }
        return grade;
    }
}