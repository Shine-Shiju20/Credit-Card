Feature: Account Creation API Validation

  Background:
    Given User logs in with new user account

  @CreateAccount
  Scenario Outline: Verify account creation scenarios

    When User creates account with "<accountType>" and "<deposit>"
    Then Response status code should be <statusCode>

    Examples:
      | accountType | deposit | statusCode |
      | savings     | 5000    |   201      |
      | current     | 10000   | 201        |
      | savings     | 1000    | 201        |
      | current     | 5000    | 201        |
      | savings     | 500     | 400        |
      | current     | 3000    | 400        |
      | salary      | -1000   | 400        |
      | business    | 5000    | 400        |
      | savings     | 1900.59 | 400        |
      | current     | 5899.876| 400        |


  @CreateAccount @SkipCleanup
  Scenario Outline: Verify account maximum limit validations

    Given User logs in with limit test account
    When User creates maximum "<accountType>" account
    Then Response status code should be <statusCode>

    Examples:
      | accountType | statusCode |
      | savings     | 500 |
      | salary      | 500 |


  @CreateAccount
  Scenario Outline: Verify invalid deposit values

    When User creates account with invalid deposit "<deposit>"
    Then Response status code should be 400

    Examples:
      | deposit |
      | abcd |
      | @#$% |

  @CreateAccount
  Scenario: Verify request without account type

    When User creates account without account type
    Then Response status code should be 400

  @CreateAccount
  Scenario: Verify request without deposit

    When User creates account without deposit
    Then Response status code should be 400