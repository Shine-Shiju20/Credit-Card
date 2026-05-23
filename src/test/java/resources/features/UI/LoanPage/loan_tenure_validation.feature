Feature: Loan Tenure Validation

  Background:
    Given User launches banking application
    And User logs into application with email "rohansnaik2004@gmail.com" and password "$Naik@123"
    And User navigates to Loan page


  @PersonalTenure
  Scenario: Verify valid tenure accepts successfully

    When User clicks Apply Loan button
    And User selects "Personal" loan type
    And User enters amount "500000"
    And User enters tenure "36"
    And User clicks Submit Loan button
    Then Loan should be approved



  @PersonalTenure
  Scenario Outline: Verify invalid tenure validations

    When User clicks Apply Loan button
    And User selects "<LoanType>" loan type
    And User enters amount "<Amount>"
    And User enters tenure "<Tenure>"
    And User clicks Submit Loan button
    Then Loan should be approved

    Examples:
      | TC_ID | LoanType | Amount | Tenure |
      | TC_43 | Personal | 50000 | 6      |
      | TC_44 | Personal | 50000 | 60    |



  @PersonalTenureValidation @personal
  Scenario Outline: Verify invalid tenure validations

    When User clicks Apply Loan button
    And User selects "<LoanType>" loan type
    And User enters amount "<Amount>"
    And User enters tenure "<Tenure>"
    And User clicks Submit Loan button
    Then "<ExpectedMessage>" tenure validation should display

    Examples:
      | TC_ID | LoanType | Amount | Tenure | ExpectedMessage |
      | TC_15 | Personal | 50000 | 0      | greater than or equal to 1 |
      | TC_41 | Personal | 50000 | -12    | greater than or equal to 1 |
      | TC_42 | Personal | 50000 | 12.5   | nearest valid values |
      | TC_43 | Personal | 50000 | 3      | Minimum tenure for Personal Loan is 6 months. |
      | TC_44 | Personal | 50000 | 100    | Maximum tenure for Personal Loan is 60 months. |


  @HomeLoanTenureValidation
  Scenario Outline: Verify home loan tenure validations

    When User clicks Apply Loan button
    And User selects "<LoanType>" loan type
    And User enters amount "<Amount>"
    And User enters tenure "<Tenure>"
    And User clicks Submit Loan button
    Then "<ExpectedMessage>" tenure validation should display

    Examples:
      | TC_ID | LoanType | Amount | Tenure | ExpectedMessage |
      | TC_36 | Home | 500000 | 0 |  greater than or equal to 1 |
      | TC_38 | Home | 500000 | -12 | greater than or equal to 1 |
      | TC_39 | Home | 500000 | 361 | Maximum tenure for Home Loan is 360 months. |
      | TC_40 | Home | 500000 | 11 | Minimum tenure for Home Loan is 12 months. |




  @HomeLoanTenureSuccess @home
  Scenario Outline: Verify successful home loan tenure cases

    When User clicks Apply Loan button
    And User selects "<LoanType>" loan type
    And User enters amount "<Amount>"
    And User enters tenure "<Tenure>"
    And User clicks Submit Loan button
    Then Loan should be approved

    Examples:
      | TC_ID | LoanType | Amount | Tenure |
      | TC_34 | Home | 500000 | 12 |
      |       | Home |  500000 | 360 |



  @VehicleLoanTenureValidation
  Scenario Outline: Verify vehicle loan tenure validations

    When User clicks Apply Loan button
    And User selects "<LoanType>" loan type
    And User enters amount "<Amount>"
    And User enters tenure "<Tenure>"
    And User clicks Submit Loan button
    Then "<ExpectedMessage>" tenure validation should display

    Examples:
      | TC_ID | LoanType | Amount | Tenure | ExpectedMessage |
      | TC_43 | Vehicle | 300000 | 0 | greater than or equal to 1 |
      | TC_45 | Vehicle | 300000 | -12 | greater than or equal to 1 |
      | TC_46 | Vehicle | 300000 | 85 | Maximum tenure for Vehicle Loan is 84 months. |
      | TC_47 | Vehicle | 300000 | 11 | Minimum tenure for Vehicle Loan is 12 months. |


  @VehicleLoanTenureSuccess @vehicle
  Scenario Outline: Verify successful vehicle loan tenure cases

    When User clicks Apply Loan button
    And User selects "<LoanType>" loan type
    And User enters amount "<Amount>"
    And User enters tenure "<Tenure>"
    And User clicks Submit Loan button
    Then Loan should be approved

    Examples:
      | TC_ID | LoanType | Amount | Tenure |
      | TC_41 | Vehicle | 300000 | 12 |
      |       | Vehicle | 300000 | 84 |




  @EducationLoanTenureValidation
  Scenario Outline: Verify education loan tenure validations

    When User clicks Apply Loan button
    And User selects "<LoanType>" loan type
    And User enters amount "<Amount>"
    And User enters tenure "<Tenure>"
    And User clicks Submit Loan button
    Then "<ExpectedMessage>" tenure validation should display

    Examples:
      | TC_ID | LoanType | Amount | Tenure | ExpectedMessage |
      | TC_57 | Education | 100000 | 0 | greater than or equal to 1 |
      | TC_59 | Education | 100000 | -12 | greater than or equal to 1 |
      | TC_60 | Education | 100000 | 121 | Maximum tenure for Education Loan is 120 months. |
      | TC_61 | Education | 100000 | 11 | Minimum tenure for Education Loan is 12 months. |





