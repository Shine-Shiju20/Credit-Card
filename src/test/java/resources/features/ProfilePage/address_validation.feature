Feature: Address Validation

  Background:
    Given User launches the banking application
    And User logs in using valid credentials
    And User navigates to the Profile page


  @AddressValidation
  Scenario Outline: Verify address field accepts valid data
    When User clicks Edit Profile button
    And User clears Address field
    And User enters Address "<Address>"
    And User clicks Save Profile button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | Address                     | ExpectedMessage      |
      | Bangalore Karnataka 560064 | Profile updated successfully |


  @AddressValidation
  Scenario Outline: Verify address field rejects null value
    When User clicks Edit Profile button
    And User clears Address field
    And User clicks Save Profile button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | ExpectedMessage                   |
      | address cannot be empty or null. |


  @AddressValidation
  Scenario Outline: Verify address field rejects values below minimum length
    When User clicks Edit Profile button
    And User clears Address field
    And User enters Address "<Address>"
    And User clicks Save Profile button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | Address | ExpectedMessage                                |
      | A       | Address must contain at least 3 characters     |


  @AddressValidation
  Scenario Outline: Verify address field rejects values above maximum length
    When User clicks Edit Profile button
    And User clears Address field
    And User enters Address "<Address>"
    And User clicks Save Profile button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | Address                                                                                                                                                                                                                                                           | ExpectedMessage                              |
      | AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA | Address exceeds maximum length               |