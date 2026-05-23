package Hooks.Loan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DbCleanup {

    public static void deleteLoansByEmail(String email) {

        String dbUrl =
                "jdbc:postgresql://localhost:5432/banking_system";

        String dbUsername =
                "postgres";

        String dbPassword =
                "postgres";

        String query =
                "DELETE FROM loans " +
                        "WHERE user_id = (" +
                        "SELECT user_id FROM users WHERE email = ?" +
                        ")";

        try (
                Connection connection =
                        DriverManager.getConnection(
                                dbUrl,
                                dbUsername,
                                dbPassword
                        );

                PreparedStatement statement =
                        connection.prepareStatement(query)
        ) {

            statement.setString(1, email);

            int deletedRows =
                    statement.executeUpdate();

            System.out.println(
                    "Deleted loans for " + email + " : " + deletedRows
            );

        } catch (Exception e) {

            System.out.println(
                    "Loan cleanup failed : " + e.getMessage()
            );

            e.printStackTrace();
        }
    }
}