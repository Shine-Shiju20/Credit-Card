@CreditCardUI
Feature: Credit Card Statement UI

  Background:
    Given User launches the application
    And User logs into the banking application using "yilap59703@deapad.com" and "Kuttichatan@123"
    And User navigates to Credit Cards page

  @CC_TC_058
  Scenario: Verify UI opens CreditCardStatementModal for a valid card
    When User opens credit card statement section
    Then Credit card statement should be displayed successfully

  @CC_TC_059
  Scenario: Verify UI transaction type filter shows PURCHASE records
    When User opens credit card statement section
    And User filters statement by transaction type "PURCHASE"
    Then Statement should display only PURCHASE records

  @CC_TC_060
  Scenario: Verify unauthenticated user cannot open statement UI
    Given User launches the application
    When User navigates directly to statement page without login
    Then User should be redirected to login page