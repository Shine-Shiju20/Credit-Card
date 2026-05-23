Feature: Loan API Testing

  Background:
    Given User logs into the banking application

  @Complimentary
  Scenario: Fetch all loans
    When User fetches all loans
    Then Fetch loans response should be validated

  @Complimentary
  Scenario: Fetch active loans summary
    When User fetches active loans summary
    Then Active loans summary response should be validated

  @Complimentary
  Scenario: Apply for loan using Excel test data
    When User applies for loan using Excel test data
    Then Loan application response should be validated

  @Complimentary
  Scenario: Fetch loan by loan id
    When User applies for loan using Excel test data
    Then Loan application response should be validated
    When User fetches loan by loan id
    Then Fetch loan by id response should be validated

  @Complimentary
  Scenario: Fetch loan EMI schedule
    When User applies for loan using Excel test data
    Then Loan application response should be validated
    When User fetches loan EMI schedule
    Then Loan EMI schedule response should be validated

  @Complimentary
  Scenario: Pay loan EMI
    When User applies for loan using Excel test data
    Then Loan application response should be validated
    When User pays loan EMI
    Then Loan EMI payment response should be validated

  @Complimentary
  Scenario: Fetch foreclosure preview
    When User applies for loan using Excel test data
    Then Loan application response should be validated
    When User fetches foreclosure preview
    Then Foreclosure preview response should be validated


  Scenario: Foreclose loan
    When User applies for loan using Excel test data
    Then Loan application response should be validated
    When User forecloses loan
    Then Foreclose loan response should be validated





#  @applyIndividualLoans
#  Scenario Outline: Apply individual loan using Excel TC_ID
#    Given User is logged in for applying multiple loans
#    When User applies loan for "<TC_ID>"
#    Then Loan application for "<TC_ID>" should be validated
#
#    Examples:
#      | TC_ID       |
#      | TC_002 |
#      | TC_003 |
#      | TC_004 |
#      | TC_005 |


#maximum amount for homeloan
  @Minimum_homeLoan
  Scenario: Apply Home Loan With Minimum Amount
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_006"
    Then Loan application for "TC_006" should be validated

  @Maximum_homeLoan
  Scenario: Apply Home Loan With Maximum Amount
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_007"
    Then Loan application for "TC_007" should be validated

  @Zero_homeLoan
  Scenario: Apply Home Loan With Zero Amount
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_008"
    Then Loan application for "TC_008" should be validated

  @Positive_homeLoan @amount_check
  Scenario: Apply Home Loan With Positive Amount
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_009"
    Then Loan application for "TC_009" should be validated

  @Negative_homeLoan @amount_check
  Scenario: Apply Home Loan With Negative Amount
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_010"
    Then Loan application for "TC_010" should be validated


  @BelowMinimum_homeloan
  Scenario: Apply Home Loan With Below Minimum Amount
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_054"
    Then Loan application for "TC_054" should be validated


  @AboveMaximum_homeloan
  Scenario: Apply Home Loan With Above Maximum  Amount
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_055"
    Then Loan application for "TC_055" should be validated



  # VEHICLE LOAN TEST CASES

  @amount @Minimum_vehicleLoan
  Scenario: Apply Vehicle Loan With Minimum Amount
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_011"
    Then Loan application for "TC_011" should be validated

  @Maximum_vehicleLoan @amount
  Scenario: Apply Vehicle Loan With Maximum Amount
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_012"
    Then Loan application for "TC_012" should be validated

  @Zero_vehicleLoan @amount
  Scenario: Apply Vehicle Loan With Zero Amount
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_013"
    Then Loan application for "TC_013" should be validated

  @Positive_vehicleLoan @amount
  Scenario: Apply Vehicle Loan With Positive Amount
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_014"
    Then Loan application for "TC_014" should be validated

  @Negative_vehicleLoan @amount
  Scenario: Apply Vehicle Loan With Negative Amount
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_015"
    Then Loan application for "TC_015" should be validated

  @AboveMaximum_vehicleloan @amount
  Scenario: Apply Home Vehicle With Above Maximum  Amount
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_056"
    Then Loan application for "TC_056" should be validated


  @BelowMinimum_vehicleloan @amount
  Scenario: Apply Vehicle  Loan With Above Maximum  Amount
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_057"
    Then Loan application for "TC_057" should be validated



  # PERSONAL LOAN TEST CASES

  @Minimum_personalLoan @personal_amount
  Scenario: Apply Personal Loan With Minimum Amount
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_016"
    Then Loan application for "TC_016" should be validated

  @Maximum_personalLoan @personal_amount
  Scenario: Apply Personal Loan With Maximum Amount
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_017"
    Then Loan application for "TC_017" should be validated

  @Zero_personalLoan @personal_amount
  Scenario: Apply Personal Loan With Zero Amount
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_018"
    Then Loan application for "TC_018" should be validated

  @Positive_personalLoan @personal_amount
  Scenario: Apply Personal Loan With Positive Amount
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_019"
    Then Loan application for "TC_019" should be validated

  @Negative_personalLoan @personal_amount
  Scenario: Apply Personal Loan With Negative Amount
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_020"
    Then Loan application for "TC_020" should be validated


  @BelowMinimum_personalloan @personal_amount
  Scenario: Apply Personal Loan With Above Maximum  Amount
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_058"
    Then Loan application for "TC_058" should be validated


  @AboveMaximum_personalloan @personal_amount
  Scenario: Apply Personal Loan With Above Maximum  Amount
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_059"
    Then Loan application for "TC_059" should be validated


  # EDUCATION LOAN  AMOUNT TEST CASES

  @Minimum_educationLoan @education_amount
  Scenario: Apply Education Loan With Minimum Amount
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_021"
    Then Loan application for "TC_021" should be validated

  @Maximum_educationLoan @education_amount
  Scenario: Apply Education Loan With Maximum Amount
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_022"
    Then Loan application for "TC_022" should be validated

  @Zero_educationLoan @education_amount
  Scenario: Apply Education Loan With Zero Amount
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_023"
    Then Loan application for "TC_023" should be validated

  @Positive_educationLoan @education_amount
  Scenario: Apply Education Loan With Positive Amount
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_024"
    Then Loan application for "TC_024" should be validated

  @Negative_educationLoan @education_amount
  Scenario: Apply Education Loan With Negative Amount
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_025"
    Then Loan application for "TC_025" should be validated



  @Above @BelowMinimum_educationloan @education_amount
  Scenario: Apply Education Loan With Above Maximum  Amount
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_060"
    Then Loan application for "TC_060" should be validated


  @Above @BelowMinimum_educationloan @education_amount
  Scenario: Apply Education Loan With Above Maximum  Amount
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_061"
    Then Loan application for "TC_061" should be validated




  # HOME LOAN TENURE TEST CASES

  @homeloan_tenure @homeloan_tenure
  Scenario: Apply Home Loan With Minimum Tenure
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_026"
    Then Loan application for "TC_026" should be validated

  @homeloan_tenure @homeloan_tenure
  Scenario: Apply Home Loan With Maximum Tenure
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_027"
    Then Loan application for "TC_027" should be validated

  @homeloan_tenure @homeloan_tenure
  Scenario: Apply Home Loan With Zero Tenure
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_028"
    Then Loan application for "TC_028" should be validated

  @homeloan_tenure @homeloan_tenure
  Scenario: Apply Home Loan With Positive Tenure
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_029"
    Then Loan application for "TC_029" should be validated

  @homeloan_tenure @homeloan_tenure
  Scenario: Apply Home Loan With Negative Tenure
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_030"
    Then Loan application for "TC_030" should be validated

  @homeloan_tenure @homeloan_tenure
  Scenario: Apply Home Loan With Above Maximum Tenure
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_031"
    Then Loan application for "TC_031" should be validated

  @homeloan_tenure @homeloan_tenure
  Scenario: Apply Home Loan With Below Minimum Tenure
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_032"
    Then Loan application for "TC_032" should be validated


  # VEHICLE LOAN TENURE TEST CASES

  @vehicleloan_tenure @Minimum_vehicleLoan_Tenure
  Scenario: Apply Vehicle Loan With Minimum Tenure
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_033"
    Then Loan application for "TC_033" should be validated

  @vehicleloan_tenure @Maximum_vehicleLoan_Tenure
  Scenario: Apply Vehicle Loan With Maximum Tenure
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_034"
    Then Loan application for "TC_034" should be validated

  @vehicleloan_tenure @Zero_vehicleLoan_Tenure
  Scenario: Apply Vehicle Loan With Zero Tenure
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_035"
    Then Loan application for "TC_035" should be validated

  @vehicleloan_tenure @Positive_vehicleLoan_Tenure
  Scenario: Apply Vehicle Loan With Positive Tenure
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_036"
    Then Loan application for "TC_036" should be validated

  @vehicleloan_tenure @Negative_vehicleLoan_Tenure
  Scenario: Apply Vehicle Loan With Negative Tenure
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_037"
    Then Loan application for "TC_037" should be validated

  @vehicleloan_tenure @AboveMaximum_vehicleLoan_Tenure
  Scenario: Apply Vehicle Loan With Above Maximum Tenure
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_038"
    Then Loan application for "TC_038" should be validated

  @vehicleloan_tenure @BelowMinimum_vehicleLoan_Tenure
  Scenario: Apply Vehicle Loan With Below Minimum Tenure
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_039"
    Then Loan application for "TC_039" should be validated


  # PERSONAL LOAN TENURE TEST CASES

  @personalloan_tenure @Minimum_personalLoan_Tenure
  Scenario: Apply Personal Loan With Minimum Tenure
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_040"
    Then Loan application for "TC_040" should be validated

  @personalloan_tenure @Maximum_personalLoan_Tenure
  Scenario: Apply Personal Loan With Maximum Tenure
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_041"
    Then Loan application for "TC_041" should be validated

  @personalloan_tenure @Zero_personalLoan_Tenure
  Scenario: Apply Personal Loan With Zero Tenure
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_042"
    Then Loan application for "TC_042" should be validated

  @personalloan_tenure @Positive_personalLoan_Tenure
  Scenario: Apply Personal Loan With Positive Tenure
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_043"
    Then Loan application for "TC_043" should be validated

  @personalloan_tenure @Negative_personalLoan_Tenure
  Scenario: Apply Personal Loan With Negative Tenure
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_044"
    Then Loan application for "TC_044" should be validated

  @personalloan_tenure @AboveMaximum_personalLoan_Tenure
  Scenario: Apply Personal Loan With Above Maximum Tenure
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_045"
    Then Loan application for "TC_045" should be validated

  @personalloan_tenure @BelowMinimum_personalLoan_Tenure
  Scenario: Apply Personal Loan With Below Minimum Tenure
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_046"
    Then Loan application for "TC_046" should be validated


  # EDUCATION LOAN TENURE TEST CASES

  @educationloan_tenure @Minimum_educationLoan_Tenure
  Scenario: Apply Education Loan With Minimum Tenure
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_047"
    Then Loan application for "TC_047" should be validated

  @educationloan_tenure @Maximum_educationLoan_Tenure
  Scenario: Apply Education Loan With Maximum Tenure
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_048"
    Then Loan application for "TC_048" should be validated

  @educationloan_tenure @Zero_educationLoan_Tenure
  Scenario: Apply Education Loan With Zero Tenure
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_049"
    Then Loan application for "TC_049" should be validated

  @educationloan_tenure @Positive_educationLoan_Tenure
  Scenario: Apply Education Loan With Positive Tenure
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_050"
    Then Loan application for "TC_050" should be validated

  @educationloan_tenure @Negative_educationLoan_Tenure
  Scenario: Apply Education Loan With Negative Tenure
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_051"
    Then Loan application for "TC_051" should be validated

  @educationloan_tenure @AboveMaximum_educationLoan_Tenure
  Scenario: Apply Education Loan With Above Maximum Tenure
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_052"
    Then Loan application for "TC_052" should be validated

  @educationloan_tenure @BelowMinimum_educationLoan_Tenure
  Scenario: Apply Education Loan With Below Minimum Tenure
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_053"
    Then Loan application for "TC_053" should be validated



#EMI Payment Validation Testing

#Feature: EMI Payment Testing


  @Pay_One_EMI
  Scenario: Create one loan and pay one EMI
    Given User is logged in for EMI payment testing
    When User creates loan for EMI test using "TC_PAY_EMI_001"
    And User pays EMI for "TC_PAY_EMI_001"
    Then EMI payment for "TC_PAY_EMI_001" should be validated

  @Pay_Advance_EMI
  Scenario: Pay advance EMI installments
    Given User is logged in for EMI payment testing
    When User creates loan for EMI test using "TC_PAY_EMI_002"
    And User pays EMI for "TC_PAY_EMI_002"
    Then EMI payment for "TC_PAY_EMI_002" should be validated

  @Below_EMI_Amount
  Scenario: Try to pay EMI below EMI amount
    Given User is logged in for EMI payment testing
    When User creates loan for EMI test using "TC_PAY_EMI_003"
    And User pays EMI for "TC_PAY_EMI_003"
    Then EMI payment for "TC_PAY_EMI_003" should be validated

   @emi @Negative_EMI_Amount
  Scenario: Try to pay EMI with negative amount
    Given User is logged in for EMI payment testing
    When User creates loan for EMI test using "TC_PAY_EMI_004"
    And User pays EMI for "TC_PAY_EMI_004"
    Then EMI payment for "TC_PAY_EMI_004" should be validated

  @emi @Less_Than_Total_EMI
  Scenario: Try to pay less than total amount for multiple installments
    Given User is logged in for EMI payment testing
    When User creates loan for EMI test using "TC_PAY_EMI_005"
    And User pays EMI for "TC_PAY_EMI_005"
    Then EMI payment for "TC_PAY_EMI_005" should be validated

  @emi @More_Than_12_EMI
  Scenario: Reject EMI payment when installments are more than 12
    Given User is logged in for EMI payment testing
    When User creates loan for EMI test using "TC_PAY_EMI_006"
    And User pays EMI for "TC_PAY_EMI_006"
    Then EMI payment for "TC_PAY_EMI_006" should be validated




#Feature: Loan Foreclosure Testing

  @Valid_Foreclosure
  Scenario: Create one loan and foreclose it with valid details
    Given User is logged in for foreclosure testing
    When User creates loan for foreclosure using "TC_FORE_001"
    And User forecloses created loan using "TC_FORE_001"
    Then Foreclosure response for "TC_FORE_001" should be validated

  @Max_Loan_Then_Foreclose
  Scenario: Create three loans verify max limit foreclose one loan and create another
    Given User is logged in for foreclosure testing
    When User creates three active loans for max limit
    And User tries to create fourth loan using "TC_FORE_002_PERSONAL"
    Then Maximum loan limit message should be displayed for "TC_FORE_002_PERSONAL"
    When User forecloses any one active loan using "TC_FORE_002_PERSONAL"
    And User creates another loan after foreclosure using "TC_FORE_002_PERSONAL"
    Then New loan should be created successfully after foreclosure

#  @Insufficient_Balance_Foreclosure
#  Scenario: Try to foreclose loan with insufficient balance account
#    Given User is logged in for foreclosure testing
#    When User creates loan for foreclosure using "TC_FORE_003"
#    And User forecloses created loan using "TC_FORE_003"
#    Then Foreclosure response for "TC_FORE_003" should be validated




  @Decimal_homeLoan
  Scenario: Reject Home Loan With Decimal Amount
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_062"
    Then Loan application for "TC_062" should be validated

  @Decimal_vehicleLoan
  Scenario: Reject Vehicle Loan With Decimal Amount
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_063"
    Then Loan application for "TC_063" should be validated

  @Decimal_personalLoan
  Scenario: Reject Personal Loan With Decimal Amount
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_064"
    Then Loan application for "TC_064" should be validated

  @Decimal_educationLoan
  Scenario: Reject Education Loan With Decimal Amount
    Given User is logged in for applying multiple loans
    When User applies loan for "TC_065"
    Then Loan application for "TC_065" should be validated