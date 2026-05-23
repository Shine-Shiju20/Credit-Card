package utils.Loan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DatabaseCleanup {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/banking_system";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "postgres";

    private static final String TEST_USER_EMAIL = "rohansafenet123@gmail.com";

    public static void deleteLoansForTestUser() {

        String query = """
                DELETE FROM loans
                WHERE user_id = (
                    SELECT user_id
                    FROM users
                    WHERE email = ?
                )
                """;

        try (
                Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setString(1, TEST_USER_EMAIL);

            int deletedRows = statement.executeUpdate();

            System.out.println("Deleted loans only for test user: " + deletedRows);

        } catch (Exception e) {
            throw new RuntimeException("Failed to delete loans for test user: " + e.getMessage(), e);
        }
    }
}