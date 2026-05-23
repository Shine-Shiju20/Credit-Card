Feature: Active Loan Limit Validation

  Background:

    Given User launches banking application
    And User logs into application with email "rohansnaik2004@gmail.com" and password "$Naik@123"
    And User navigates to Loan page


  @Maximum-loan-slot
  Scenario Outline: Verify user cannot apply loan after reaching maximum limit

     When User clicks Apply Loan button
     And User selects "<LoanType>" loan type
     And User enters amount "<Amount>"
     And User enters tenure "<Tenure>"
     And User clicks Submit Loan button
     Then Loan should be approved

     When User clicks Apply Loan button
     And User selects "<LoanType>" loan type
     And User enters amount "<Amount>"
     And User enters tenure "<Tenure>"
     And User clicks Submit Loan button
     Then Loan should be approved

     When User clicks Apply Loan button
     And User selects "<LoanType>" loan type
     And User enters amount "<Amount>"
     And User enters tenure "<Tenure>"
     And User clicks Submit Loan button
     Then Loan should be approved

     When User clicks Apply Loan button
     Then Loan limit reached message should display

    Examples:
      | TC_ID | LoanType | Amount  | Tenure |
      | TC_39 | Personal | 50000   | 60      |
