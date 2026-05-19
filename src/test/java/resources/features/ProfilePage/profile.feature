Feature: User Profile Functionality

  Background:
    Given User launches the banking application
    And User logs in using valid credentials
    And User navigates to the Profile page

  @ProfilePageLoad
  Scenario: Verify profile page loads successfully
    Then User profile page should load successfully

  @ExpiredSession
  Scenario: Verify expired session handling in profile page
    When User clicks on Logout button
    And User manually navigates to Profile page URL
    Then User should get redirected to Home page


  @NameValidation
  Scenario Outline: Verify invalid full name validations

    When User enters invalid full name "<fullName>"
    And User clicks on Save Profile button
    Then Proper validation message "<validationMessage>" should be displayed

    Examples:
      | fullName        | validationMessage                  |
      | AAAAAAAAAAAAAAAAAAAAAAAAAAAAAaAAAAAAAAAAAAAABABABABABABABABABABABABABAB  | Full name exceeds maximum length   |
      |                 | Full Name cannot be empty         |
      | 12345           | Full Name must contain only alphabets   |
      | @@@@@           | Full Name must contain only alphabets|
      | Ab              |full_name must be at least 3 characters long.|
      | A               |full_name must be at least 3 characters long.                     |


    # ---------------- PHONE NUMBER VALIDATIONS ----------------
  @UpdateProfile
  Scenario: Verify if user can update profile details successfully
    When User updates profile details
    Then User should see message "Profile updated successfully"
    And Details to be Displayed Correctly After updation


  @EmailValidation
  Scenario: Verify email field is non editable
    When User clicks Edit Profile button
    Then User should not be able to edit Email field


  @PhoneValidation
  Scenario Outline: Verify null phone number validation
    When User clicks Edit Profile button
    And User clears Phone number field
    And User clicks Save Profile button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | ExpectedMessage                              |
      | phone cannot be empty or null.         |


  @PhoneValidation
  Scenario Outline: Validate phone number more than 10 digits
    When User clicks Edit Profile button
    And User clears Phone number field
    And User enters Phone number "<PhoneNumber>"
    And User clicks Save Profile button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | PhoneNumber   | ExpectedMessage                                  |
      | 733769531423 | Phone number must contain exactly 10 digits.      |


  @PhoneValidation
  Scenario Outline: Verify negative phone number validation
    When User clicks Edit Profile button
    And User clears Phone number field
    And User enters Phone number "<PhoneNumber>"
    And User clicks Save Profile button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | PhoneNumber | ExpectedMessage                                   |
      | -9019980767 | Phone number must contain exactly 10 digits.       |


  @PhoneValidation
  Scenario Outline: Validate phone number less than 10 digits
    When User clicks Edit Profile button
    And User clears Phone number field
    And User enters Phone number "<PhoneNumber>"
    And User clicks Save Profile button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | PhoneNumber | ExpectedMessage                                   |
      | 7337695     | Phone number must contain exactly 10 digits.       |


  @PhoneValidation
  Scenario Outline: Verify duplicate phone number validation

    When User clicks Edit Profile button
    And User clears Phone number field
    And User enters Phone number "<PhoneNumber>"
    And User clicks Save Profile button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | PhoneNumber | ExpectedMessage         |
      | 7337695222  | Duplicate Phone number  |


  @PhoneValidation
  Scenario Outline: Validate phone number with special characters

    When User clicks Edit Profile button
    And User clears Phone number field
    And User enters Phone number "<PhoneNumber>"
    And User clicks Save Profile button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | PhoneNumber | ExpectedMessage                                   |
      | 9019980@767 | Phone number must contain exactly 10 digits.      |


  @PhoneValidation
  Scenario Outline: Validate phone number with alphabets
    When User clicks Edit Profile button
    And User clears Phone number field
    And User enters Phone number "<PhoneNumber>"
    And User clicks Save Profile button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | PhoneNumber | ExpectedMessage                                   |
      | 733769531A  | Phone number must contain exactly 10 digits.      |


  @AnnualIncomeValidation
  Scenario Outline: Verify annual income field accepts valid values
    When User clicks Edit Profile button
    And User clears Annual Income field
    And User enters Annual Income "<AnnualIncome>"
    And User clicks Save Profile button
    Then User profile should be updated successfully

    Examples:
      | AnnualIncome |
      | 20000        |


  @AnnualIncomeValidation
  Scenario Outline: Verify annual income field rejects values below 10000
    When User clicks Edit Profile button
    And User clears Annual Income field
    And User enters Annual Income "<AnnualIncome>"
    And User clicks Save Profile button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | AnnualIncome | ExpectedMessage                              |
      | 5000         | Annual Income cannot be below 10,000        |


  @AnnualIncomeValidation
  Scenario Outline: Verify annual income field rejects alphabets
    When User clicks Edit Profile button
    And User clears Annual Income field
    And User enters Annual Income "<AnnualIncome>"
    Then User should not be able to enter Annual Income "<AnnualIncome>"

    Examples:
      | AnnualIncome |
      | ABC          |


  @AnnualIncomeValidation
  Scenario Outline: Verify annual income field rejects special characters
    When User clicks Edit Profile button
    And User clears Annual Income field
    And User enters Annual Income "<AnnualIncome>"
    Then User should not be able to enter Annual Income "<AnnualIncome>"

    Examples:
      | AnnualIncome |
      | 12@34        |


  @AnnualIncomeValidation
  Scenario Outline: Verify annual income field rejects negative values
    When User clicks Edit Profile button
    And User clears Annual Income field
    And User enters Annual Income "<AnnualIncome>"
    And User clicks Save Profile button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | AnnualIncome | ExpectedMessage                              |
      | -234         | Annual Income cannot be below 10,000        |


  @ProfileValidation @CancelProfileValidation
  Scenario: Verify user can cancel profile edit operation
    When User clicks Edit Profile button
    And User edits profile details
    And User clicks Cancel Profile button
    Then Profile data should revert back to previous data


  @ResponsiveValidation
  Scenario: Verify profile data is displayed properly in different screen sizes
    When User changes browser screen size
    Then Updated profile data should be visible properly


  @ProfileValidation @profileDataPersistence
  Scenario: Verify updated profile data persists after relogin
    When User clicks Edit Profile button
    And User edits profile details
    And User clicks Save Profile button
    And User logs out from application
    And User relogins with valid credentials
    Then Saved profile data should persist after relogin


