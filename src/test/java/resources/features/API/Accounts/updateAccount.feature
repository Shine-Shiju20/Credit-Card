Feature: Update Account API Validation

  Background:
    Given User logs in with new user account


  @UpdateAccount
  Scenario Outline: Verify update account scenarios

    Given User creates account with "current" and "10000"
    When User updates account to "<accountType>"
    Then Response status code should be <statusCode>

    Examples:
      | accountType | statusCode |
      | savings     | 200        |
      | current     | 400        |
      | salary      | 400        |


  @UpdateAccount
  Scenario: Verify savings account with insufficient balance cannot convert to current

    Given User creates account with "savings" and "2000"
    When User updates account to "current"
    Then Response status code should be 400


  @UpdateAccount
  Scenario: Verify update using invalid account id

    Given User creates account with "current" and "10000"
    When User updates invalid account
    Then Response status code should be 400
  @UpdateAccount
  Scenario: Verify salary account with insufficient balance conversion restrictions

    Given User uses existing salary account
    When User updates account to "savings"
    Then Response status code should be 400


  @UpdateAccount
  Scenario: Verify update without authentication

    Given User creates account with "current" and "10000"
    And User removes authentication cookies
    When User updates account to "savings"
    Then Response status code should be 401