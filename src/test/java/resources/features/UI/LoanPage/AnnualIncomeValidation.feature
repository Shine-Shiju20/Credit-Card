Feature: Annual Income Validation

  Background:
    Given User launches banking application

  @AnnualIncomeValidation @annual
  Scenario: Verify loan application fails for low annual income

    Given User logs into application with email "sahanasoudri@gmail.com" and password "Pass@123"
    And User navigates to Loan page

    When User clicks Apply Loan button
    And User enters low annual income loan details for "TC_45"


    Then Annual income field should be disabled


