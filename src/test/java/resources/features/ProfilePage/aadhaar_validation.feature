Feature: Aadhaar Validation


  Background:
    Given User launches the banking application
    And User logs in using unverified KYC account
    And User navigates to the Profile page


  @AadhaarValidation
  Scenario Outline: Verify valid Aadhaar number
    When User clicks Update KYC button
    And User clears Aadhaar field
    And User enters Aadhaar number "<Aadhaar>"
    And User clicks Save KYC button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | Aadhaar     | ExpectedMessage            |
      | 123456789012| KYC updated successfully |


  @AadhaarValidation
  Scenario Outline: Verify Aadhaar number below 12 digits
    When User clicks Update KYC button
    And User clears Aadhaar field
    And User enters Aadhaar number "<Aadhaar>"
    And User clicks Save KYC button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | Aadhaar   | ExpectedMessage                                |
      | 1234567890| Aadhaar number must be exactly 12 numeric digits. |


  @AadhaarValidation
  Scenario Outline: Verify Aadhaar number above 12 digits
    When User clicks Update KYC button
    And User clears Aadhaar field
    And User enters Aadhaar number "<Aadhaar>"
    And User clicks Save KYC button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | Aadhaar        | ExpectedMessage                                |
      | 12345678901234 | Aadhaar number must be exactly 12 numeric digits. |


  @AadhaarValidation
  Scenario Outline: Verify Aadhaar number with special characters
    When User clicks Update KYC button
    And User clears Aadhaar field
    And User enters Aadhaar number "<Aadhaar>"
    And User clicks Save KYC button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | Aadhaar      | ExpectedMessage                                    |
      | 1234@567#901 | Aadhaar number must be exactly 12 numeric digits. |


  @AadhaarValidation
  Scenario Outline: Verify Aadhaar number with alphabets
    When User clicks Update KYC button
    And User clears Aadhaar field
    And User enters Aadhaar number "<Aadhaar>"
    And User clicks Save KYC button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | Aadhaar     | ExpectedMessage                                    |
      | ABCD5678EFGH| Aadhaar number must be exactly 12 numeric digits. |


  @AadhaarValidation
  Scenario Outline: Verify null Aadhaar validation
    When User clicks Update KYC button
    And User clears Aadhaar field
    And User clicks Save KYC button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | ExpectedMessage                             |
      | Aadhaar number cannot be empty or null.    |