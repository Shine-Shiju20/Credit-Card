package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestDataStore {
    public static String currentEmail;
    public static String currentPassword;

    public static Map<String,String> createdAccounts =
            new HashMap<>();

    // Existing accounts before creation
    public static List<String> existingAccounts =
            new ArrayList<>();

    public static List<String> closedAccounts =
            new ArrayList<>();

    public static Map<String,String> updatedAccounts =
            new HashMap<>();

    public static Map<String,Double> accountBalances=
            new HashMap<>();

    //For API

    public static Map<String,String>
            apiCreatedAccounts =
            new HashMap<>();

    public static Map<String,String>
            apiUpdatedAccounts =
            new HashMap<>();

    public static List<String>
            apiClosedAccounts =
            new ArrayList<>();

    public static Map<String,Double>
            apiAccountBalances =
            new HashMap<>();


    public static void clear(){

        createdAccounts.clear();

        closedAccounts.clear();

        updatedAccounts.clear();

        accountBalances.clear();

        apiCreatedAccounts.clear();

        apiClosedAccounts.clear();

        apiUpdatedAccounts.clear();

        apiAccountBalances.clear();

        currentEmail = null;

        currentPassword = null;

        LoggerUtility.info(
                "TestDataStore Cleared");
    }
}