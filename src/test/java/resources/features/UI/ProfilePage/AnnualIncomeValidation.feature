Feature: Annual Income Validation Functionality

  Background:
    Given User launches the banking application
    And User logs in using valid credentials
    And User navigates to the Profile page
    And User clicks Edit Profile button

  @AnnualIncomeValidation @TC_19
  Scenario Outline: Verify annual income field accepts valid values
    When User clears Annual Income field
    And User enters Annual Income "<AnnualIncome>"
    And User clicks Save Profile button
    Then User profile should be updated successfully

    Examples:
      | AnnualIncome |
      | 20000        |

  @AnnualIncomeValidation @TC_20
  Scenario Outline: Verify annual income field rejects values below 10000
    When User clears Annual Income field
    And User enters Annual Income "<AnnualIncome>"
    And User clicks Save Profile button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | AnnualIncome | ExpectedMessage                       |
      | 5000         | Annual Income cannot be below 10,000 |

  @AnnualIncomeValidation @TC_21
  Scenario Outline: Verify annual income field rejects alphabets
    When User clears Annual Income field
    And User enters Annual Income "<AnnualIncome>"
    Then User should not be able to enter Annual Income "<AnnualIncome>"

    Examples:
      | AnnualIncome |
      | ABC          |

  @AnnualIncomeValidation @TC_22
  Scenario Outline: Verify annual income field rejects special characters
    When User clears Annual Income field
    And User enters Annual Income "<AnnualIncome>"
    Then User should not be able to enter Annual Income "<AnnualIncome>"

    Examples:
      | AnnualIncome |
      | @/>,<)(@     |

  @AnnualIncomeValidation @TC_23
  Scenario Outline: Verify annual income field rejects negative values
    When User clears Annual Income field
    And User enters Annual Income "<AnnualIncome>"
    And User clicks Save Profile button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | AnnualIncome | ExpectedMessage                       |
      | -234         | Annual Income cannot be below 10,000 |