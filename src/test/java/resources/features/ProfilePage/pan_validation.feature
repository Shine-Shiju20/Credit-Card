Feature: PAN Validation

  Background:
    Given User launches the banking application
    And User logs in using valid credentials
    And User navigates to the Profile page


  @PANValidation
  Scenario Outline: Verify valid PAN card format
    When User clicks Update KYC button
    And User clears PAN field
    And User enters PAN number "<PAN>"
    And User clicks Save KYC button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | PAN        | ExpectedMessage           |
      | ABCDE1234F | KYC updated successfully. |


  @PANValidation
  Scenario Outline: Verify PAN card with less than 10 characters
    When User clicks Update KYC button
    And User clears PAN field
    And User enters PAN number "<PAN>"
    And User clicks Save KYC button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | PAN      | ExpectedMessage                                 |
      | ABC1234F | PAN number must contain exactly 10 characters.  |


  @PANValidation
  Scenario Outline: Verify PAN card with more than 10 characters
    When User clicks Update KYC button
    And User clears PAN field
    And User enters PAN number "<PAN>"
    And User clicks Save KYC button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | PAN          | ExpectedMessage                                 |
      | ABCDE1234FGH | PAN number must contain exactly 10 characters.  |


  @PANValidation
  Scenario Outline: Verify PAN card with special characters
    When User clicks Update KYC button
    And User clears PAN field
    And User enters PAN number "<PAN>"
    And User clicks Save KYC button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | PAN        | ExpectedMessage                                                  |
      | ABC@#1234F | PAN number should contain only valid alphanumeric characters.    |
