Feature: Login Functionality

  Background:
    Given User launches the banking application
    And User clicks on Home Login button


  @ValidLogin
  Scenario: Verify valid email login

    When User enters login credentials from excel for "VALID_LOGIN"
    And User clicks on Secure Login button
    Then User should login successfully


  @UppercaseEmail
  Scenario: Verify uppercase email normalization

    When User enters login credentials from excel for "UPPERCASE_EMAIL"
    And User clicks on Secure Login button
    Then User should login successfully


  @TrimmedEmail
  Scenario: Verify trimmed email handling

    When User enters login credentials from excel for "TRIMMED_EMAIL"
    And User clicks on Secure Login button
    Then Spaces should be removed from email input


  @InvalidEmailWithoutAt
  Scenario: Verify invalid email without @ symbol

    When User enters login credentials from excel for "INVALID_EMAIL_WITHOUT_@"
    And User clicks on Secure Login button
    Then Proper email validation message should be displayed


  @InvalidEmailWithoutDomain
  Scenario: Verify invalid email without domain

    When User enters login credentials from excel for "INVALID_EMAIL_WITHOUT_DOMAIN"
    And User clicks on Secure Login button
    Then Proper email validation message should be displayed


  @EmptyEmail
  Scenario: Verify empty email validation

    When User enters login credentials from excel for "EMPTY_EMAIL"
    And User clicks on Secure Login button
    Then Proper email validation message should be displayed


    @StrongPassword
  Scenario: Verify strong password login

    When User enters login credentials from excel for "VALID_PASSWORD"
    And User clicks on Secure Login button
    Then User should login successfully


  @PasswordWithoutUppercase
  Scenario: Verify password without uppercase character

    When User enters login credentials from excel for "PASSWORD_WITHOUT_UPPERCASE"
    And User clicks on Secure Login button
    Then Invalid credentials message should be displayed


  @PasswordWithoutLowercase
  Scenario: Verify password without lowercase character

    When User enters login credentials from excel for "PASSWORD_WITHOUT_LOWERCASE"
    And User clicks on Secure Login button
    Then Invalid credentials message should be displayed


  @PasswordWithoutNumber
  Scenario: Verify password without numeric character

    When User enters login credentials from excel for "PASSWORD_WITHOUT_NUMBER"
    And User clicks on Secure Login button
    Then Invalid credentials message should be displayed


  @PasswordWithoutSpecialCharacter
  Scenario: Verify password without special character

    When User enters login credentials from excel for "PASSWORD_WITHOUT_SPECIAL_CHARACTER"
    And User clicks on Secure Login button
    Then Invalid credentials message should be displayed


  @PasswordMismatch
  Scenario: Verify password mismatch validation

    When User enters login credentials from excel for "PASSWORD_MISMATCH"
    And User clicks on Secure Login button
    Then Invalid credentials message should be displayed