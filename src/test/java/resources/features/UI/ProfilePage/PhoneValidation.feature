Feature: Phone Number Validation Functionality

  Background:
    Given User launches the banking application
    And User logs in using valid credentials
    And User navigates to the Profile page
    And User clicks Edit Profile button

  @PhoneValidation @TC_12
  Scenario Outline: Verify null phone number validation
    When User clears Phone number field
    And User clicks Save Profile button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | ExpectedMessage                  |
      | phone cannot be empty or null.  |

  @PhoneValidation @TC_13
  Scenario Outline: Validate phone number more than 10 digits
    When User clears Phone number field
    And User enters Phone number "<PhoneNumber>"
    And User clicks Save Profile button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | PhoneNumber  | ExpectedMessage                             |
      | 733769531423 | Phone number must contain exactly 10 digits.|

  @PhoneValidation @TC_14
  Scenario Outline: Verify negative phone number validation
    When User clears Phone number field
    And User enters Phone number "<PhoneNumber>"
    And User clicks Save Profile button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | PhoneNumber | ExpectedMessage                             |
      | -9019980767 | Phone number must contain exactly 10 digits.|

  @PhoneValidation @TC_15
  Scenario Outline: Validate phone number less than 10 digits
    When User clears Phone number field
    And User enters Phone number "<PhoneNumber>"
    And User clicks Save Profile button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | PhoneNumber | ExpectedMessage                             |
      | 7337695     | Phone number must contain exactly 10 digits.|

  @PhoneValidation @TC_16
  Scenario Outline: Verify duplicate phone number validation
    When User clears Phone number field
    And User enters Phone number "<PhoneNumber>"
    And User clicks Save Profile button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | PhoneNumber | ExpectedMessage        |
      | 7337695322  | Duplicate Phone number |

  @PhoneValidation @TC_17
  Scenario Outline: Validate phone number with special characters
    When User clears Phone number field
    And User enters Phone number "<PhoneNumber>"
    And User clicks Save Profile button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | PhoneNumber | ExpectedMessage                             |
      | 9019980@767 | Phone number must contain exactly 10 digits.|

  @PhoneValidation @TC_18
  Scenario Outline: Validate phone number with alphabets
    When User clears Phone number field
    And User enters Phone number "<PhoneNumber>"
    And User clicks Save Profile button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | PhoneNumber | ExpectedMessage                             |
      | 733769531A  | Phone number must contain exactly 10 digits.|