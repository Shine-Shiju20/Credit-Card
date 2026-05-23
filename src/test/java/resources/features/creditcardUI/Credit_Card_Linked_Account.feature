@CreditCardUI
Feature: Credit Card Application UI

  Background:
    Given User launches the application
    And User logs into the banking application using "attacker@inboxorigin.com" and "Kuttichatan@123"
    And User navigates to Credit Cards page

  @CC_TC_004
  Scenario: Verify application fails when no linked account is selected
    When User clicks on Apply Credit Card button
    And User enters requested limit "500"
    And User clicks Continue button
    Then Profile or account data missing reason should be displayed