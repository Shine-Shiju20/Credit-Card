Feature: Profile Page Field Enable Validation

  Background:
    Given User launches the banking application
    And User logs in using unverified KYC account
    And User navigates to the Profile page


  @DOBValidation @TC_58
  Scenario: Verify Date of Birth field is enabled
    Then Date of Birth field should be enabled


  @GenderValidation @TC_59
  Scenario: Verify Gender dropdown field is enabled
    Then Gender dropdown field should be enabled