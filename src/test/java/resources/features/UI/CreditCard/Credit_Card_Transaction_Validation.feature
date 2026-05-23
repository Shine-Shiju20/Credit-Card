@CreditCardUI
Feature: Credit Card Application UI

  Background:
    Given User launches the application
    And User logs into the banking application using "yilap59703@deapad.com" and "Kuttichatan@123"
    And User navigates to Credit Cards page

  @CC_TC_006
  Scenario: Verify successful card transaction

    When User selects an active credit card
    And User performs purchase transaction with amount "1000"
    Then Credit card transaction should be processed successfully


  @CC_TC_007
  Scenario: Verify insufficient limit validation

    When User selects a credit card with low available limit
    And User performs purchase transaction with amount "50000"
    Then Insufficient available limit validation message should be displayed
    And Transaction should be declined


  @CC_TC_008
  Scenario: Verify inactive card validation
    When User scrolls to bottom of Credit Cards page
    And User selects an inactive credit card
    And User performs purchase transaction with amount "500"
    Then Inactive card validation message should be displayed
    And Transaction should not be processed


  @CC_TC_009
  Scenario: Verify available limit updates after transaction

    When User completes a successful purchase transaction
    And User refreshes the Credit Cards page
    Then Available credit limit should be updated correctly

