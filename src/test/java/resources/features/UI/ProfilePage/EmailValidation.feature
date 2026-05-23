Feature: Email Validation Functionality

  Background:
    Given User launches the banking application
    And User logs in using valid credentials
    And User navigates to the Profile page

  @EmailValidation @TC_11
  Scenario: Verify email field is non editable
    When User clicks Edit Profile button
    Then User should not be able to edit Email field