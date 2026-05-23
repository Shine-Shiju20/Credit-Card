Feature: Loan Application Validation

  Background:
    Given User launches banking application
    And User logs into application with email "rohansnaik2004@gmail.com" and password "$Naik@123"
    And User navigates to Loan page

  @LoanValidation
  Scenario: Verify Loan page loads successfully

    Then Loan page should display successfully


  @LoanValidation
  Scenario: Verify user has no active loans

    Then User should see no active loans message


  @LoanValidation
  Scenario: Verify Apply Loan button is enabled

    Then Apply Loan button should be enabled

  @Modifying-liabilities
  Scenario: Verify user can apply for Loan by Modifying Liabilities field

    When User clicks Apply Loan button
    And User enters valid loan details
      | LoanType | Amount | Tenure | Income  | Liabilities |
      | Personal | 500000 | 24     | 1500000 | 10000       |
    And User clicks Submit Loan button
    Then Loan should be approved

#  @LiabilityValidation
#  Scenario: Verify liabilities auto populate from active EMI
#
#    And User clicks Apply Loan button
#    Then Liabilities field should be auto-filled with "5335.06"


#  @LiabilityValidation
#  Scenario: Verify liabilities below EMI floor are not allowed
#
#    And User clicks Apply Loan button
#    And User enters liability validation details with liabilities "500"
#    Then Liability validation should display


  @labilities_below-emi
  Scenario Outline: Verify liabilities below EMI floor are not allowed
    When User clicks Apply Loan button
    And User selects "<LoanType>" loan type
    And User enters amount "<Amount>"
    And User enters tenure "<Tenure>"
    And User enters liability validation details with liabilities "-1"
    And User clicks Submit Loan button
    Then Liability validation should display

    Examples:
      | TC_ID | LoanType | Amount  | Tenure |
      | TC_39 | Personal | 2000000 | 60     |


  @liabilities-auto-populate
  Scenario Outline: Verify liabilities auto populate from active EMI

    When User clicks Apply Loan button
    And User selects "<LoanType>" loan type
    And User enters amount "<Amount>"
    And User enters tenure "<Tenure>"
    And User clicks Submit Loan button
    Then Loan should be approved

    When User clicks Apply Loan button
    Then Liabilities field should be auto populated

    Examples:
      | LoanType | Amount  | Tenure |
      | Personal | 2000000 | 60     |


