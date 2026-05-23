Feature: Close Account API Validation

  Background:
    Given User logs in with new user account
    And User creates account with "savings" and "100000"


  @CloseAccount
  Scenario: Verify successful account closure

    When User withdraws complete account balance
    And User closes account
    Then Response status code should be 200


  @CloseAccount
  Scenario: Verify account closure with balance restriction

    When User closes account
    Then Response status code should be 400


  @CloseAccount
  Scenario: Verify account closure without authentication

    Given User removes authentication cookies
    When User closes account
    Then Response status code should be 401