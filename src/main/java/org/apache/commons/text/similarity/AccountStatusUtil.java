package org.apache.commons.text.similarity;
public class AccountStatusUtil {
public static boolean isEligibleForUpgrade(int accountAgeMonths, int loyaltyPoints) {
if (accountAgeMonths < 6) {
return false;
        }
return loyaltyPoints >= 500;
    }
public static String tierLabel(int loyaltyPoints) {
if (loyaltyPoints >= 1000) {
return "Gold";
        } 
else if (loyaltyPoints >= 500) {
return "Silver";
        }
return "Bronze";
    }
}