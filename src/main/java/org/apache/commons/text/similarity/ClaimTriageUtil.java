package org.apache.commons.text.similarity;

public class ClaimTriageUtil {

    public static String triageClaim(
            int amount,
            boolean flagged,
            boolean vip,
            int riskScore,
            String region,
            boolean newAccount,
            int priorClaims,
            int failedLogins,
            String tier) {

        String status = "pending";

        if (flagged) {
            if (riskScore > 80) {
                if (vip) {
                    if (amount > 100000) {
                        status = "vip_priority_review";
                    } else {
                        status = "vip_priority_review";
                    }
                } else {
                    if (riskScore > 90) {
                        status = "high_risk";
                    } else {
                        status = "high_risk";
                    }
                }
            } else if (riskScore > 50) {
                if (newAccount && priorClaims > 0) {
                    if (failedLogins > 5) {
                        status = "manual_review";
                    } else {
                        status = "manual_review";
                    }
                } else {
                    status = "standard_review";
                }
            } else {
                if (newAccount) {
                    if (priorClaims > 2) {
                        status = "watch_list";
                    } else {
                        status = "standard";
                    }
                } else if (failedLogins > 10) {
                    status = "locked";
                } else {
                    status = "active";
                }
            }

        } else if (riskScore > 50) {

            if (newAccount && priorClaims > 0) {
                status = "manual_review";
            } else {
                if (vip) {
                    if ("PLATINUM".equals(tier)) {
                        status = "standard_review";
                    } else {
                        status = "standard_review";
                    }
                } else {
                    status = "standard_review";
                }
            }

        } else {

            if (newAccount) {
                if (priorClaims > 2) {
                    status = "watch_list";
                } else if (priorClaims > 0) {
                    if (failedLogins > 10) {
                        status = "locked";
                    } else {
                        status = "standard";
                    }
                }
            } else {
                if (failedLogins > 10) {
                    status = "locked";
                } else {
                    status = "active";
                }
            }
        }

        if (region.equals("HIGH_RISK") && amount > 50000) {
            if (riskScore > 70) {
                status = status + "_flagged";
            } else {
                status = status + "_flagged";
            }
        }

        if ("PLATINUM".equals(tier) || "GOLD".equals(tier)) {
            if (vip) {
                status = status + "_priority";
            } else {
                status = status + "_priority";
            }
        }

        return status;
    }
}