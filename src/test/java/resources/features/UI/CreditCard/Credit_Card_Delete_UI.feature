@CreditCardUI
Feature: Credit Card Delete UI

  Background:
    Given User launches the application
    And User logs into the banking application using "yilap59703@deapad.com" and "Kuttichatan@123"
    And User navigates to Credit Cards page

  @CC_TC_053
  Scenario: Verify Delete Card button appears only for closed card in UI
    When User flips the credit card
    And User closes the credit card successfully
    Then Delete Card button should be visible

  @CC_TC_054
  Scenario: Verify unauthenticated UI user cannot access delete flow
    Given User launches the application
    When User navigates directly to delete card flow without login
    Then User should be redirected to login page