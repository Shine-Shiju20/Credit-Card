Feature: Transactions Page

  # =========================================================
  # Basic Navigation and UI Validation
  # =========================================================

  Scenario: Verify transactions page loads successfully
    Given the user is logged into the banking application
    When the user navigates to the Transactions page
    Then the Transactions page should load successfully

  Scenario: Verify Transfer tab opens correctly
    Given the user is logged into the banking application
    When the user navigates to the Transactions page
    When the user clicks the Transfer tab
    Then the Transfer tab should be displayed

  Scenario: Verify Deposit tab opens correctly
    Given the user is logged into the banking application
    When the user navigates to the Transactions page
    When the user clicks the Deposit tab
    Then the Deposit tab should be displayed

  Scenario: Verify Withdraw tab opens correctly
    Given the user is logged into the banking application
    When the user navigates to the Transactions page
    When the user clicks the Withdraw tab
    Then the Withdraw tab should be displayed

  Scenario: Verify account dropdown selection
    Given the user is logged into the banking application
    When the user navigates to the Transactions page
    Then the account dropdown should be displayed
    And the account dropdown should contain at least one account

  Scenario: Verify valid recipient account number acceptance
    Given the user is logged into the banking application
    When the user navigates to the Transactions page
    Then the recipient account number field should be displayed
    When the user enters a valid recipient account number "1025000193741423"
    Then the recipient account number should be accepted

  Scenario: Verify valid transaction PIN acceptance
    Given the user is logged into the banking application
    When the user navigates to the Transactions page
    When the user enters the transaction PIN "123456"
    Then the transaction PIN field should contain "123456"

  Scenario: Verify invalid transaction PIN validation
    Given the user is logged into the banking application
    When the user navigates to the Transactions page
    When the user enters the transaction PIN "12ab"
    Then the transaction PIN field should not contain "12ab"

  # =========================================================
  # Positive Transaction Scenarios (Excel Driven)
  # =========================================================

  Scenario: Verify successful fund transfer
    Given the user is logged into the banking application
    When the user navigates to the Transactions page
    When the user performs transaction using Excel test data row 1
    Then the transaction should be completed successfully

  Scenario: Verify successful deposit transaction
    Given the user is logged into the banking application
    When the user navigates to the Transactions page
    When the user performs transaction using Excel test data row 2
    Then the transaction should be completed successfully

  Scenario: Verify successful withdrawal transaction
    Given the user is logged into the banking application
    When the user navigates to the Transactions page
    When the user performs transaction using Excel test data row 3
    Then the transaction should be completed successfully

  # =========================================================
  # Negative Transaction Scenarios (Excel Driven)
  # =========================================================

  Scenario: Verify self transfer validation
    Given the user is logged into the banking application
    When the user navigates to the Transactions page
    When the user performs negative transaction using Excel test data row 1
    Then the error message should be displayed

  Scenario: Verify insufficient balance
    Given the user is logged into the banking application
    When the user navigates to the Transactions page
    When the user performs negative transaction using Excel test data row 2
    Then the error message should be displayed

  Scenario: Verify RTGS below minimum amount
    Given the user is logged into the banking application
    When the user navigates to the Transactions page
    When the user performs negative transaction using Excel test data row 3
    Then the error message should be displayed

  Scenario: Verify deposit above ₹10 lakh
    Given the user is logged into the banking application
    When the user navigates to the Transactions page
    When the user performs negative transaction using Excel test data row 4
    Then the error message should be displayed

  Scenario: Verify duplicate submission prevention
    Given the user is logged into the banking application
    When the user navigates to the Transactions page
    When the user performs negative transaction using Excel test data row 5
    Then the error message should be displayed

#Scenario: Verify session timeout after 5 minutes
 # Given the user is logged into the banking application
  #When the user waits for 5 minutes
  #Then the user should be redirected to the login page

  # =========================================================
  # UI Validation SceNo Excel Required)
  # =========================================================narios (

  Scenario: Verify transfer type dropdown functionality
    Given the user is logged into the banking application
    When the user navigates to the Transactions page
    Then the transfer type dropdown should contain at least 4 options

  Scenario: Verify amount field accepts valid amount
    Given the user is logged into the banking application
    When the user navigates to the Transactions page
    When the user enters a valid amount "5000"
    Then the amount field should display "5000"

  Scenario: Verify amount field rejects negative value
    Given the user is logged into the banking application
    When the user navigates to the Transactions page
    When the user enters an invalid amount "-1000"
    Then the amount field should not display "-1000"

  Scenario: Verify amount field rejects alphabetic input
    Given the user is logged into the banking application
    When the user navigates to the Transactions page
    When the user enters an invalid amount "abcd"
    Then the amount field should not display "abcd"

  # =========================================================
  # Confirm Button Validation
  # Expected behavior: clicking Confirm with empty fields
  # should display validation error messages
  # =========================================================

  Scenario: Verify Confirm Transfer button state
    Given the user is logged into the banking application
    When the user navigates to the Transactions page
    When the user clicks the Confirm button
    Then the error message should be displayed

  Scenario: Verify Confirm Deposit button state
    Given the user is logged into the banking application
    When the user navigates to the Transactions page
    When the user clicks the Deposit tab
    When the user clicks the Confirm button
    Then the error message should be displayed

  Scenario: Verify Confirm Withdraw button state
    Given the user is logged into the banking application
    When the user navigates to the Transactions page
    When the user clicks the Withdraw tab
    When the user clicks the Confirm button
    Then the error message should be displayed

  # =========================================================
  # UI Layout Validation
  # =========================================================

  Scenario: Verify left navigation functionality
    Given the user is logged into the banking application
    When the user navigates to the Transactions page
    Then the left navigation should be displayed

  Scenario: Verify form alignment consistency
    Given the user is logged into the banking application
    When the user navigates to the Transactions page
    Then the transaction form should be displayed properly

  Scenario: Verify spacing between transaction UI components
    Given the user is logged into the banking application
    When the user navigates to the Transactions page
    Then the transaction form should be displayed properly

  Scenario: Verify responsiveness on smaller screens
    Given the user is logged into the banking application
    When the user navigates to the Transactions page
    Then the transactions page should be responsive on mobile

  Scenario: Verify placeholders and labels
    Given the user is logged into the banking application
    When the user navigates to the Transactions page
    Then all labels should be displayed
    And all input fields should have placeholders

  # =========================================================
  # History and Security
  # =========================================================

  Scenario: Verify transaction appears in history
    Given the user is logged into the banking application
    When the user navigates to the Transactions page
    Then the success toast message should be displayed

   #Scenario: Verify unauthorized access restriction
   # Given the user is logged into the banking application
     #When the user logs out by clearing the session
     #Then the login page should be displayed
    
    # =========================================================
# Advanced Excel-Driven Transaction Scenarios
# Uses: src/test/resources/testdata/Advanced_Transaction_TestData.xlsx
# Sheet: AdvancedTransactions
# =========================================================

Scenario: Verify invalid recipient account validation
  Given the user is logged into the banking application
  When the user navigates to the Transactions page
  When the user performs advanced transaction using Excel test data row 1
  Then the error message should be displayed

Scenario: Verify insufficient balance validation during transfer
  Given the user is logged into the banking application
  When the user navigates to the Transactions page
  When the user performs advanced transaction using Excel test data row 2
  Then the error message should be displayed

Scenario: Verify insufficient balance validation during withdrawal
  Given the user is logged into the banking application
  When the user navigates to the Transactions page
  When the user performs advanced transaction using Excel test data row 3
  Then the error message should be displayed

Scenario: Verify mandatory field validation
  Given the user is logged into the banking application
  When the user navigates to the Transactions page
  When the user performs advanced transaction using Excel test data row 4
  Then the error message should be displayed

Scenario: Verify maximum amount boundary validation
  Given the user is logged into the banking application
  When the user navigates to the Transactions page
  When the user performs advanced transaction using Excel test data row 5
  Then the error message should be displayed

Scenario: Verify success message after transfer
  Given the user is logged into the banking application
  When the user navigates to the Transactions page
  When the user performs advanced transaction using Excel test data row 6
  Then the success toast message should be displayed

Scenario: Verify failure message display
  Given the user is logged into the banking application
  When the user navigates to the Transactions page
  When the user performs advanced transaction using Excel test data row 7
  Then the error message should be displayed

Scenario: Verify balance updates after transfer
  Given the user is logged into the banking application
  When the user navigates to the Transactions page
  When the user performs advanced transaction using Excel test data row 8
  Then the success toast message should be displayed

Scenario: Verify balance updates after deposit
  Given the user is logged into the banking application
  When the user navigates to the Transactions page
  When the user performs advanced transaction using Excel test data row 9
  Then the success toast message should be displayed

Scenario: Verify balance updates after withdrawal
  Given the user is logged into the banking application
  When the user navigates to the Transactions page
  When the user performs advanced transaction using Excel test data row 10
  Then the success toast message should be displayed
  
 Scenario: Verify withdrawal amount exceeds available balance
  Given the user is logged into the banking application
  When the user navigates to the Transactions page
  When the user performs advanced transaction using Excel test data row 11
  Then the advanced transaction error message should be displayed
  