Feature: Account Creation Functionality

  Background:
    Given user launches banking application
    And user logs into the application

# Creation / Field Validation

  @validAccount
  Scenario Outline: Verify valid Bank account creation
    And user navigates to accounts route
    When user creates "<accountType>" account with deposit "<amount>"
    Then account should be created successfully with message "<expectedMessage>"

    Examples:

      | accountType      | amount  | expectedMessage                  |
      | Savings Account  | 18000   | Account created successfully!    |
      | Current Account  | 150000  | Account created successfully!    |
      | Salary Account   | 0       | Account created successfully!    |
      | Savings Account  | 1000    | Account created successfully!    |
      | Current Account  | 5000    | Account created successfully!    |

  @invalidDeposit
  Scenario Outline: Verify invalid minimum deposit validation
    And user navigates to accounts route
    When user creates "<accountType>" account with deposit "<amount>"
    Then browser validation message should be displayed as "<expectedMessage>"

    Examples:

      | accountType      | amount | expectedMessage                                  |
      | Savings Account  | 0      | Value must be greater than or equal to 1000.     |
      | Current Account  | 500    | Value must be greater than or equal to 5000.     |
      | Savings Account  | 999    | Value must be greater than or equal to 1000.     |
      | Current Account  | 4999   | Value must be greater than or equal to 5000.     |
      | Salary Account   | -1     | Value must be greater than or equal to 0.        |


  @invalidCharacters
  Scenario Outline: Verify Bank account creation form rejects alphabetic and special character deposit values
    And user navigates to accounts route
    When user enters invalid deposit value "<amount>"
    Then invalid deposit value should be rejected with value "<expectedValue>"

    Examples:

      | amount    | expectedValue |
      | abcd      |               |
      | @#$%      |               |
      |           |               |
      |abc_53(\@  |   53          |
      | 1v4@%ac   |   14          |


  @mandatoryFields
  Scenario: Verify mandatory fields cannot be left empty during Bank account creation
    Given user navigates to accounts route
    When user opens account creation form
    And user submits account form without entering mandatory fields
    Then browser validation message should be displayed as "Please fill out this field."

  @largeDepositValidation
  Scenario Outline: Verify suspicious large deposit validation
    Given user navigates to accounts route
    When user creates "<accountType>" account with deposit "<amount>"
    Then compliance validation message should be displayed as "<expectedMessage>"

    Examples:
      | accountType      | amount   | expectedMessage                                       |
      | Savings Account  | 90000000 | Large deposits require manual compliance verification |
      | Current Account  | 50000000 | Large deposits require manual compliance verification |
      | Salary Account   | 60000000 | Large deposits require manual compliance verification |

  @maxSavingsBalance
  Scenario: Verify maximum savings Bank account balance validation
    Given user navigates to accounts route
    When user creates "Savings Account" account with deposit "900000000"
    Then savings validations should be displayed


  @nonKycUser
  Scenario: Verify Bank account creation blocked without KYC
    And user logs in with non-KYC user credentials
    And user navigates to accounts route
    When user creates "Savings Account" account with deposit "5000"
    Then KYC validation message should be displayed as "KYC verification required before account creation."


# Business Rule Validations

  @uniqueAccountNumber
  Scenario: Verify unique Bank account number generation
    Given user stores existing account numbers
    And user navigates to accounts route
    When user creates "Savings Account" account with deposit "5000"
    Then new account number should be unique

  @duplicateSalaryAccount
  Scenario: Verify duplicate salary account restriction

    Given user logs in with max account user credentials
    And user navigates to accounts route
    When user creates "Salary Account" account with deposit "0"
    Then duplicate salary account validation should be displayed as "Only one salary account is allowed per user."

  @duplicateSalaryUpdate
  Scenario Outline: Verify salary account restriction during account update

    Given user logs in with max account user credentials
    And user navigates to accounts route
    When user updates "<existingType>" account to "Salary"
    Then duplicate salary account validations should be displayed as "Only one salary account is allowed per user."

    Examples:
      | existingType |
      | Savings |
      | Current |

# Update Functionality

  @updateAccountType
  Scenario Outline: Verify Bank account type update functionality
    Given  user logs in with new user credentials
    And user navigates to accounts route
    When user updates "<existingType>" account to "<newType>"
    Then account update should be successful with message "<expectedMessage>"

    Examples:

      | existingType | newType    | expectedMessage                               |
      | savings      | Current    | Account type updated                          |
      | current      | Savings    | Account type updated                          |
      | savings      | Salary     | Only one salary account is allowed per user.  |
      | current      | Salary     | Only one salary account is allowed per user.  |


  @updatedDetailsReflection
  Scenario Outline: Verify updated Bank account details are reflected correctly after account update
    Given user navigates to accounts route
    When user updates "<existingType>" account to "<newType>"
    Then updated account type should be displayed as "<newType>"

    Examples:

      | existingType | newType |
      | Savings      | Current |
      | Current      | Savings |

  @sameAccountUpdate
  Scenario Outline: Verify same Bank account type update restriction
    Given user navigates to accounts route
    When user updates "<existingType>" account to "<newType>"
    Then same account update should be restricted with message "No changes detected"

    Examples:

      | existingType | newType |
      | Savings      | Savings |
      | Current      | Current |

  @salaryUpdateDefect
  Scenario Outline: Verify salary account update with insufficient balance

    Given user logs in with new user credentials
    And user navigates to accounts route
    When user updates "salary" account to "<newType>"
    Then account update should fail due to insufficient balance

    Examples:
      | newType |
      | Savings |
      | Current |

  @maxAccountRestriction
  Scenario Outline: Verify maximum Bank account restriction
    And user logs in with max account user credentials
    And user navigates to accounts route
    When user creates "<accountType>" account with deposit "<amount>"
    Then account restriction message should be displayed as "<expectedMessage>"

    Examples:

      | accountType      | amount | expectedMessage                              |
      | Savings Account  | 5000   | Maximum 5 savings accounts allowed per user. |
      | Current Account  | 10000  | Maximum 5 current accounts allowed per user. |


# Retrieval / Fetch

  @fetchAccounts
  Scenario: Verify retrieval of user accounts
    Given user navigates to accounts route
    Then user accounts should be displayed

  @balancePersistence
  Scenario Outline: Verify updated Bank account balance persists correctly after page refresh
    Given user logs in with new user credentials
    And user navigates to transactions route
    When user withdraws amount "<amount>" from "<accountType>" account
    And user navigates to accounts route
    And user stores updated balance
    And user refreshes application
    Then updated balance should persist after refresh

    Examples:

      | accountType | amount |
      | salary      |  50    |
      | savings     |  500   |
      | current     | 1000   |


# Closure Scenarios (LAST)

  @closeAccountWithBalance
  Scenario Outline: Verify Bank account closure blocked with balance
    Given user navigates to accounts route
    When user closes "<accountType>" account
    Then closure should fail with message "Account balance must be zero before closure."

    Examples:
      | accountType |
      | savings     |
      | current     |


  @validAccountClosure
  Scenario Outline: Verify valid Bank account closure
    Given user navigates to transactions route
    And user withdraws complete balance from "<accountType>" account
    And user navigates to accounts route
    When user closes selected account
    Then account should be closed successfully with message "Account closed successfully"

    Examples:

      | accountType |
      | savings     |
      | current     |
