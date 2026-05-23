Feature: KYC Field Editability Validation


  @KYCFieldValidation @TC_29
  Scenario: Verify KYC fields are editable when KYC is unverified
    Given User launches the banking application
    And User logs in using unverified KYC account
    And User navigates to the Profile page
    When User clicks Update KYC button
    Then Aadhaar field should be editable
    And PAN field should be editable



  @KYCFieldValidation @TC_30
  Scenario: Verify KYC fields are uneditable when KYC is verified
    Given User launches the banking application
    And User logs in using valid credentials
    And User navigates to the Profile page
    Then Update KYC button should be disabled
    And Aadhaar field should be disabled
    And PAN field should be disabled