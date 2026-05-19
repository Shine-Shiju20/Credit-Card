Feature: Occupation Validation

  Background:
    Given User launches the banking application
    And User logs in using valid credentials
    And User navigates to the Profile page


  @OccupationValidation
  Scenario Outline: Verify occupation field rejects numeric values
    When User clicks Edit Profile button
    And User clears Occupation field
    And User enters Occupation "<Occupation>"
    And User clicks Save Profile button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | Occupation | ExpectedMessage                                   |
      | Softwa2    | occupation must contain only alphabets and spaces. |


  @OccupationValidation
  Scenario Outline: Verify occupation field rejects special characters
    When User clicks Edit Profile button
    And User clears Occupation field
    And User enters Occupation "<Occupation>"
    And User clicks Save Profile button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | Occupation | ExpectedMessage                                   |
      | Softwa@    | occupation must contain only alphabets and spaces. |


  @OccupationValidation
  Scenario Outline: Verify occupation field rejects null value
    When User clicks Edit Profile button
    And User clears Occupation field
    And User clicks Save Profile button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | ExpectedMessage                     |
      | occupation cannot be empty or null.|


  @OccupationValidation
  Scenario Outline: Verify occupation minimum length validation
    When User clicks Edit Profile button
    And User clears Occupation field
    And User enters Occupation "<Occupation>"
    And User clicks Save Profile button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | Occupation | ExpectedMessage                              |
      | B          | Occupation must contain Atleast 3 characters |


  @OccupationValidation
  Scenario Outline: Verify occupation field rejects values above maximum length
    When User clicks Edit Profile button
    And User clears Occupation field
    And User enters Occupation "<Occupation>"
    And User clicks Save Profile button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | Occupation                                                        | ExpectedMessage                          |
      | AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA | Occupation exceeds maximum length        |