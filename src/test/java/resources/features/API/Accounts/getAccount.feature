Feature: Get Account API Validation

  Background:
    Given User logs in successfully


  @GetAccount
  Scenario Outline: Verify get account details scenarios

    When User fetches "<requestType>" account
    Then Response status code should be <statusCode>

    Examples:
      | requestType | statusCode |
      | valid | 200 |
      | invalid | 404 |


  @GetAccount
  Scenario: Verify fetch all active accounts

    When User fetches all accounts
    Then Response status code should be 200


  @GetAccount
  Scenario: Verify fetch accounts for user having no accounts

    Given User logs in with account having no accounts
    When User fetches all accounts
    Then Response status code should be 200


  @GetAccount
  Scenario: Verify API access without JWT token

    Given User removes authentication cookies
    When User fetches all accounts
    Then Response status code should be 401