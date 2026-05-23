@CreditCardUI
Feature: Multiple Credit Card Management UI

  Background:
    Given User launches the application
    And User logs into the banking application using "yilap59703@deapad.com" and "Kuttichatan@123"
    And User navigates to Credit Cards page

  @CC_TC_071
  Scenario: Verify UI displays multiple cards and sorts active, blocked, closed
    Then Multiple credit cards should be displayed
    And Cards should be sorted by status active blocked closed

  @CC_TC_072
  Scenario: Verify UI card flip shows management actions per status
    When User flips the credit card
    Then Card management actions should be displayed based on card status