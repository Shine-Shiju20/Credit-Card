Feature: Loan Amount Validation

  Background:

    Given User launches banking application
    And User logs into application with email "rohansnaik2004@gmail.com" and password "$Naik@123"
    And User navigates to Loan page


  @Applyloan_active-account
  Scenario Outline: Apply loan with active account

    When User clicks Apply Loan button
    And User selects "<LoanType>" loan type
    And User enters amount "<Amount>"
    And User enters tenure "<Tenure>"
    And User clicks Submit Loan button
    Then Loan should be approved

    Examples:
      | TC_ID | LoanType | Amount  | Tenure |
      | TC_39 | Personal | 500000  | 60      |




  @Personal-valid-loan
  Scenario Outline: Apply loan with active account

    When User clicks Apply Loan button
    And User selects "<LoanType>" loan type
    And User enters amount "<Amount>"
    And User enters tenure "<Tenure>"
    And User clicks Submit Loan button
    Then Loan should be approved

    Examples:
      | TC_ID | LoanType | Amount  | Tenure |
      | TC_39 | Personal | 400000   | 60      |


  @Personal_Minimum_Amount
  Scenario Outline: Verify personal loan amount validations

    When User clicks Apply Loan button
    And User selects "<LoanType>" loan type
    And User enters amount "<Amount>"
    And User enters tenure "<Tenure>"
    And User clicks Submit Loan button
    Then Loan should be approved

    Examples:
      | TC_ID | LoanType | Amount  | Tenure |
      | TC_39 | Personal | 2000000   | 60      |


  @@Personal_Maximum_Amount
  Scenario Outline: Verify personal loan amount validations

    When User clicks Apply Loan button
    And User selects "<LoanType>" loan type
    And User enters amount "<Amount>"
    And User enters tenure "<Tenure>"
    And User clicks Submit Loan button
    Then Loan should be approved

    Examples:
      | TC_ID | LoanType | Amount  | Tenure |
      | TC_39 | Personal | 50000   | 60      |



  @PersonalL_zero_negative_amount
  Scenario Outline: Verify personal loan amount validations

    When User clicks Apply Loan button
    And User selects "<LoanType>" loan type
    And User enters amount "<Amount>"
    And User enters tenure "<Tenure>"
    And User clicks Submit Loan button
    Then "<ExpectedMessage>" validation should display

    Examples:
      | TC_ID | LoanType | Amount  | Tenure | ExpectedMessage |
      | TC_13 | Personal | -50000  | 24      | greater than or equal to 0 |
      | TC_37 | Personal | 0       | 24      | valid positive number |



  @PersonalLoanAmountValidation
  Scenario: Verify non numeric personal loan amount is rejected

    When User clicks Apply Loan button
    And User selects "Personal" loan type
    And User enters amount "abc"
    Then Non numeric amount should not be accepted



  @Vehicle-valid-loan
  Scenario Outline: Apply loan with active account

    When User clicks Apply Loan button
    And User selects "<LoanType>" loan type
    And User enters amount "<Amount>"
    And User enters tenure "<Tenure>"
    And User clicks Submit Loan button
    Then Loan should be approved

    Examples:
      | TC_ID | LoanType | Amount  | Tenure |
      | TC_39 | Vehicle | 100000  | 14      |

  @Vehicle_Maximum_Amount
  Scenario Outline: Verify vehicle loan amount validations

    When User clicks Apply Loan button
    And User selects "<LoanType>" loan type
    And User enters amount "<Amount>"
    And User enters tenure "<Tenure>"
    And User clicks Submit Loan button
    Then Loan should be approved

    Examples:
      | TC_ID | LoanType | Amount  | Tenure |
      | TC_21 | Vehicle  | 5000000       | 36      |



  @Vehicle_Minimum_Amount
  Scenario Outline: Verify vehicle loan amount validations

    When User clicks Apply Loan button
    And User selects "<LoanType>" loan type
    And User enters amount "<Amount>"
    And User enters tenure "<Tenure>"
    And User clicks Submit Loan button
    Then Loan should be approved

    Examples:
      | TC_ID | LoanType | Amount  | Tenure |
      | TC_21 | Vehicle  | 100000       | 36      |


  @Vehicle-non-numeric-amount
  Scenario: Verify non numeric education loan amount is rejected

    When User clicks Apply Loan button
    And User selects "Vehicle" loan type
    And User enters amount "abc"
    Then Non numeric amount should not be accepted




  @Education-loan-valid
  Scenario Outline: Apply loan with active account

    When User clicks Apply Loan button
    And User selects "<LoanType>" loan type
    And User enters amount "<Amount>"
    And User enters tenure "<Tenure>"
    And User clicks Submit Loan button
    Then Loan should be approved

    Examples:
      | TC_ID | LoanType | Amount  | Tenure |
      | TC_39 | Education | 100000   | 60      |


  @Education_min_max_Amount
  Scenario Outline: Verify education loan amount validations

    When User clicks Apply Loan button
    And User selects "<LoanType>" loan type
    And User enters amount "<Amount>"
    And User enters tenure "<Tenure>"
    And User clicks Submit Loan button
    Then Loan should be approved

    Examples:
      | TC_ID | LoanType | Amount   | Tenure |
      | TC_29 | Education | 100000   | 48      |
      | TC_30 | Education | 7500000  | 120     |



  @Education_zero_negative_Amount
  Scenario Outline: Verify education loan amount validations

    When User clicks Apply Loan button
    And User selects "<LoanType>" loan type
    And User enters amount "<Amount>"
    And User enters tenure "<Tenure>"
    And User clicks Submit Loan button
    Then "<ExpectedMessage>" validation should display

    Examples:
      | TC_ID | LoanType | Amount   | Tenure | ExpectedMessage |
      | TC_31 | Education | 0        | 48      | valid positive number |
      | TC_33 | Education | -20000   | 48      | greater than or equal to 0 |



  @EducationLoanAmountValidation
  Scenario: Verify non numeric education loan amount is rejected

    When User clicks Apply Loan button
    And User selects "Education" loan type
    And User enters amount "abc"
    Then Non numeric amount should not be accepted




  @Home-loan-valid
  Scenario Outline: Apply loan with active account

    When User clicks Apply Loan button
    And User selects "<LoanType>" loan type
    And User enters amount "<Amount>"
    And User enters tenure "<Tenure>"
    And User clicks Submit Loan button
    Then Loan should be approved

    Examples:
      | TC_ID | LoanType | Amount  | Tenure |
      | TC_39 | Home | 1000000   | 60      |


  @HomeLoanAmountValidation
  Scenario Outline: Verify Home loan amount validations

    When User clicks Apply Loan button
    And User selects "<LoanType>" loan type
    And User enters amount "<Amount>"
    And User enters tenure "<Tenure>"
    And User clicks Submit Loan button
    Then Loan should be approved

    Examples:
      | TC_ID | LoanType | Amount   | Tenure |
      | TC_29 | Home | 500000   | 360    |
      | TC_30 | Home | 20000000  | 360    |


  @Home-zero-negativeAmount
  Scenario Outline: Verify Home loan amount validations

    When User clicks Apply Loan button
    And User selects "<LoanType>" loan type
    And User enters amount "<Amount>"
    And User enters tenure "<Tenure>"
    And User clicks Submit Loan button
    Then "<ExpectedMessage>" validation should display

    Examples:
      | TC_ID | LoanType | Amount   | Tenure | ExpectedMessage |
      | TC_31 | Education | 0        | 48      | valid positive number |
      | TC_33 | Education | -20000   | 48      | greater than or equal to 0 |


  @Home-amount-non-numeric
  Scenario: Verify non numeric education loan amount is rejected

    When User clicks Apply Loan button
    And User selects "Home" loan type
    And User enters amount "abc"
    Then Non numeric amount should not be accepted