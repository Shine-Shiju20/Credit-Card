Feature: Full Name Validation Functionality

  Background:
    Given User launches the banking application
    And User logs in using valid credentials
    And User navigates to the Profile page


  @NameValidation @TC_10
  Scenario: Verify maximum length validation for full name
    When User enters invalid full name "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAaAAAAAAAAAAAAAABABABABABABABABABABABABABAB"
    And User clicks on Save Profile button
    Then Proper validation message "Full name exceeds maximum length" should be displayed



  @NameValidation @TC_5
  Scenario: Verify null full name validation
    When User enters invalid full name ""
    And User clicks on Save Profile button
    Then Proper validation message "Full Name cannot be empty" should be displayed



  @NameValidation @TC_6
  Scenario: Verify numeric values are not allowed in full name
    When User enters invalid full name "12345"
    And User clicks on Save Profile button
    Then Proper validation message "Full Name must contain only alphabets" should be displayed



  @NameValidation @TC_8
  Scenario: Verify special characters are not allowed in full name
    When User enters invalid full name "@@@@@"
    And User clicks on Save Profile button
    Then Proper validation message "Full Name must contain only alphabets" should be displayed



  @NameValidation @TC_9
  Scenario Outline: Verify minimum length validation for full name
    When User enters invalid full name "<fullName>"
    And User clicks on Save Profile button
    Then Proper validation message "<validationMessage>" should be displayed

    Examples:
      | fullName | validationMessage                                   |
      | Ab       | full_name must be at least 3 characters long.       |
      | A        | full_name must be at least 3 characters long.       |