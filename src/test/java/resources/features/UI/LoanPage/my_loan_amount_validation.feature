Feature: Loan Amount Validation From Feature File

  Background:
    Given User launches banking application
    And User logs into application with email "rohansnaik2004@gmail.com" and password "$Naik@123"
    And User navigates to Loan page

  @HomeLoan_Above-maximumAmount
  Scenario Outline: Verify Home loan amount validations from feature file
    When User clicks Apply Loan button
    And User selects "<LoanType>" loan type
    And User enters amount "<Amount>"
    And User enters tenure "<Tenure>"
    And User clicks Submit Loan button
    Then "<ExpectedMessage>" validation should display

    Examples:
      | LoanType | Amount   | Tenure | ExpectedMessage                                      |
      | Home     | 30000000 | 360    | Maximum loan amount for Home Loan is ₹2,00,00,000.   |


  @HomeLoan_below-minimumAmount
  Scenario Outline: Verify Home loan amount validations from feature file
    When User clicks Apply Loan button
    And User selects "<LoanType>" loan type
    And User enters amount "<Amount>"
    And User enters tenure "<Tenure>"
    And User clicks Submit Loan button
    Then "<ExpectedMessage>" validation should display

    Examples:
      | LoanType | Amount   | Tenure | ExpectedMessage                                      |
      | Home     | 400000 | 360    | Minimum loan amount for Home Loan is ₹5,00,000.   |


  @HomeLoan_decimal-Amount
  Scenario Outline: Verify Home loan amount validations from feature file
    When User clicks Apply Loan button
    And User selects "<LoanType>" loan type
    And User enters amount "<Amount>"
    And User enters tenure "<Tenure>"
    And User clicks Submit Loan button
    Then "<ExpectedMessage>" validation should display

    Examples:
      | LoanType | Amount   | Tenure | ExpectedMessage                                      |
      | Home     | 400000.50 | 360    | Please Enter a valid value.   |





  @vehicle_above_maximumAmount
  Scenario Outline: Verify Vehicle Loan Above Maximum Amount validation
    When User clicks Apply Loan button
    And User selects "<LoanType>" loan type
    And User enters amount "<Amount>"
    And User enters tenure "<Tenure>"
    And User clicks Submit Loan button
    Then "<ExpectedMessage>" validation should display

    Examples:
      | LoanType | Amount  | Tenure | ExpectedMessage                                    |
      | Vehicle  | 5100000 | 84     | Maximum loan amount for Vehicle Loan is ₹50,00,000 |

  @vehicle_below_minimumAmount
  Scenario Outline: Verify Vehicle Loan Below Minimum Amount validation
    When User clicks Apply Loan button
    And User selects "<LoanType>" loan type
    And User enters amount "<Amount>"
    And User enters tenure "<Tenure>"
    And User clicks Submit Loan button
    Then "<ExpectedMessage>" validation should display

    Examples:
      | LoanType | Amount | Tenure | ExpectedMessage                                    |
      | Vehicle  | 95000  | 84     | Minimum loan amount for Vehicle Loan is ₹1,00,000. |


  @vehicle_decimal_Amount
  Scenario Outline: Verify Education Loan Above decimal Amount validation
    When User clicks Apply Loan button
    And User selects "<LoanType>" loan type
    And User enters amount "<Amount>"
    And User enters tenure "<Tenure>"
    And User clicks Submit Loan button
    Then "<ExpectedMessage>" validation should display

    Examples:
      | LoanType  | Amount  | Tenure | ExpectedMessage                                       |
      | Vehicle | 200000.50 | 84   | Please Enter a valid value. |



  @personal_Below_minimum_amount
  Scenario Outline: Verify Personal Loan Below Minimum Amount validation
    When User clicks Apply Loan button
    And User selects "<LoanType>" loan type
    And User enters amount "<Amount>"
    And User enters tenure "<Tenure>"
    And User clicks Submit Loan button
    Then "<ExpectedMessage>" validation should display

    Examples:
      | LoanType | Amount | Tenure | ExpectedMessage                                   |
      | Personal | 45000  | 60     | Minimum loan amount for Personal Loan is ₹50,000. |

  @personal_above_maximumAmount
  Scenario Outline: Verify Personal Loan Above Maximum Amount validation
    When User clicks Apply Loan button
    And User selects "<LoanType>" loan type
    And User enters amount "<Amount>"
    And User enters tenure "<Tenure>"
    And User clicks Submit Loan button
    Then "<ExpectedMessage>" validation should display

    Examples:
      | LoanType | Amount  | Tenure | ExpectedMessage                                      |
      | Personal | 2100000 | 60     | Maximum loan amount for Personal Loan is ₹20,00,000. |


  @Personal_decimal_Amount
  Scenario Outline: Verify Education Loan Above decimal Amount validation
    When User clicks Apply Loan button
    And User selects "<LoanType>" loan type
    And User enters amount "<Amount>"
    And User enters tenure "<Tenure>"
    And User clicks Submit Loan button
    Then "<ExpectedMessage>" validation should display

    Examples:
      | LoanType  | Amount  | Tenure | ExpectedMessage                                       |
      | Personal | 200000.50 | 120    | Please Enter a valid value. |



  @education_below_minimumAmount
  Scenario Outline: Verify Education Loan Below Minimum Amount validation
    When User clicks Apply Loan button
    And User selects "<LoanType>" loan type
    And User enters amount "<Amount>"
    And User enters tenure "<Tenure>"
    And User clicks Submit Loan button
    Then "<ExpectedMessage>" validation should display

    Examples:
      | LoanType  | Amount | Tenure | ExpectedMessage                                      |
      | Education | 95000  | 120    | Minimum loan amount for Education Loan is ₹1,00,000. |

  @education_Above_maximumAmount
  Scenario Outline: Verify Education Loan Above Maximum Amount validation
    When User clicks Apply Loan button
    And User selects "<LoanType>" loan type
    And User enters amount "<Amount>"
    And User enters tenure "<Tenure>"
    And User clicks Submit Loan button
    Then "<ExpectedMessage>" validation should display

    Examples:
      | LoanType  | Amount  | Tenure | ExpectedMessage                                       |
      | Education | 8000000 | 120    | Maximum loan amount for Education Loan is ₹75,00,000. |


  @education_decimal_Amount
  Scenario Outline: Verify Education Loan Above decimal Amount validation
    When User clicks Apply Loan button
    And User selects "<LoanType>" loan type
    And User enters amount "<Amount>"
    And User enters tenure "<Tenure>"
    And User clicks Submit Loan button
    Then "<ExpectedMessage>" validation should display

    Examples:
      | LoanType  | Amount  | Tenure | ExpectedMessage                                       |
      | Education | 200000.50 | 120    | Please Enter a valid value. |





  @EMI @TC_PAY_EMI_001
  Scenario Outline: Pay One EMI
    When User creates loan with following data
      | LoanType | Amount | Tenure | Income  | Liabilities |
      | Home     | 500000 | 60     | 1200000 | 0           |
    And User pays EMI with following data
      | PaymentType | InstallmentsToPay |
      | NORMAL      | 1                 |
    Then "<ExpectedMessage>" payment message should display

    Examples:
      | ExpectedMessage                 |
      | Payment processed successfully! |


  @EMI @TC_PAY_EMI_002
  Scenario Outline: Pay Advance EMI Installments
    When User creates loan with following data
      | LoanType | Amount | Tenure | Income  | Liabilities |
      | Home     | 500000 | 60     | 1200000 | 0           |
    And User pays EMI with following data
      | PaymentType | InstallmentsToPay |
      | NORMAL      | 3                 |
    Then "<ExpectedMessage>" payment message should display

    Examples:
      | ExpectedMessage                 |
      | Payment processed successfully! |

#  @TC_PAY_EMI_003
#  Scenario Outline: Pay EMI Below EMI Amount
#    When User creates loan with following data
#      | LoanType | Amount | Tenure | Income  | Liabilities |
#      | Home     | 500000 | 60     | 1200000 | 0           |
#    And User pays EMI with following data
#      | PaymentType | InstallmentsToPay |
#      | BELOW_EMI   | 1                 |
#    Then "<ExpectedMessage>" payment message should display
#
#    Examples:
#      | ExpectedMessage |
#      | amount          |

  @EMI @TC_PAY_EMI_006
  Scenario Outline: Pay EMI of 12 Installments
    When User creates loan with following data
      | LoanType | Amount | Tenure | Income  | Liabilities |
      | Home     | 500000 | 60     | 1200000 | 0           |
    And User pays EMI with following data
      | PaymentType | InstallmentsToPay |
      | NORMAL      | 13                |
    Then "<ExpectedMessage>" payment message should display

    Examples:
      | ExpectedMessage                                             |
      | Payment processed successfully! |



  @Foreclosure @TC_FORE_001
  Scenario Outline: Verify valid loan foreclosure
    When User creates loan with following data
      | LoanType | Amount | Tenure | Income  | Liabilities |
      | Home     | 500000 | 360    | 1200000 | 0           |
    And User forecloses the created loan
    Then "<ExpectedMessage>" foreclosure message should display

    Examples:
      | ExpectedMessage                 |
      | Payment processed successfully! |

#  @Foreclosure @TC_FORE_003
#  Scenario Outline: Verify foreclosure with insufficient balance
#    When User creates loan with following data
#      | LoanType | Amount | Tenure | Income  | Liabilities |
#      | Home     | 500000 | 360    | 1200000 | 0           |
#    And User forecloses the created loan using insufficient balance account
#    Then "<ExpectedMessage>" foreclosure message should display
#
#    Examples:
#      | ExpectedMessage            |
#      | Minimum balance violation. |





























