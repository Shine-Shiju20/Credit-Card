Feature: Login Page

  Scenario: Login with valid credentials
    Given the user opens the banking application
    When the user logs in using Excel test data row 1
    Then the dashboard should be displayed
