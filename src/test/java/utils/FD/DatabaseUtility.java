package utils.FD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import utils.LoggerUtility;

public class DatabaseUtility {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/banking_system";

    private static final String USER =
            "postgres";

    private static final String PASSWORD =
            "root@12";

    private static final String TEST_EMAIL =
            "sk.shreya651@gmail.com";

    public static void deleteTestFDs() {

        String query =
                "DELETE FROM fds WHERE user_id = " +
                        "(SELECT user_id FROM users WHERE email = ?)";

        try (
                Connection conn =
                        DriverManager.getConnection(URL, USER, PASSWORD);

                PreparedStatement stmt =
                        conn.prepareStatement(query)
        ) {

            stmt.setString(1, TEST_EMAIL);

            int deletedCount = stmt.executeUpdate();

            LoggerUtility.info(
                    "Deleted " + deletedCount + " FD records from database"
            );

        } catch (Exception e) {

            LoggerUtility.fail(
                    "Database cleanup failed: " + e.getMessage()
            );

            e.printStackTrace();
        }
    }
}