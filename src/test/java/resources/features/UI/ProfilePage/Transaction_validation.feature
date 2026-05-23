Feature: Transaction PIN Reset Validation

  Background:
    Given User launches the banking application
    And User logs in using valid credentials
    And User navigates to the Profile page



  @TransactionPINValidation @TC_53
  Scenario Outline: Verify transaction PIN reset with null account password
    When User clicks Reset Transaction PIN button
    And User enters account password "<Password>"
    And User enters new transaction PIN "<NewPIN>"
    And User enters confirm transaction PIN "<ConfirmPIN>"
    And User clicks Confirm Reset PIN button
    Then User should see required field validation message

    Examples:
      | Password | NewPIN | ConfirmPIN |
      |          | 1290   | 1290       |



  @TransactionPINValidation @TC_54
  Scenario Outline: Verify transaction PIN reset with invalid account password
    When User clicks Reset Transaction PIN button
    And User enters account password "<Password>"
    And User enters new transaction PIN "<NewPIN>"
    And User enters confirm transaction PIN "<ConfirmPIN>"
    And User clicks Confirm Reset PIN button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | Password  | NewPIN | ConfirmPIN | ExpectedMessage                |
      | Wrong@123 | 1290   | 1290       | Incorrect account password. |



  @TransactionPINValidation @TC_55
  Scenario Outline: Verify transaction PIN reset with PIN less than 4 digits
    When User clicks Reset Transaction PIN button
    And User enters account password "<Password>"
    And User enters new transaction PIN "<NewPIN>"
    And User enters confirm transaction PIN "<ConfirmPIN>"
    And User clicks Confirm Reset PIN button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | Password    | NewPIN | ConfirmPIN | ExpectedMessage                              |
      |Strong@123 | 12     | 12         | PIN must be 4 to 6 numeric digits     |



  @TransactionPINValidation @TC_56
  Scenario Outline: Verify transaction PIN reset with alphabetic PIN
    When User clicks Reset Transaction PIN button
    And User enters account password "<Password>"
    And User enters new transaction PIN "<NewPIN>"
    And User enters confirm transaction PIN "<ConfirmPIN>"
    And User clicks Confirm Reset PIN button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | Password    | NewPIN | ConfirmPIN | ExpectedMessage                            |
      | Strong@123 | ABCD   | ABCD       | PIN must be 4 to 6 numeric digits   |


  @TransactionPINValidation @TC_64
  Scenario Outline: Verify transaction PIN reset with null new PIN
    When User clicks Reset Transaction PIN button
    And User enters account password "<Password>"
    And User enters new transaction PIN "<NewPIN>"
    And User enters confirm transaction PIN "<ConfirmPIN>"
    And User clicks Confirm Reset PIN button
    Then User should see required validation message for new transaction PIN field

    Examples:
      | Password   | NewPIN | ConfirmPIN |
      | Strong@123 |        | 1290       |



  @TransactionPINValidation @TC_64
  Scenario Outline: Verify transaction PIN reset with null confirm transaction PIN
    When User clicks Reset Transaction PIN button
    And User enters account password "<Password>"
    And User enters new transaction PIN "<NewPIN>"
    And User enters confirm transaction PIN "<ConfirmPIN>"
    And User clicks Confirm Reset PIN button
    Then User should see required validation message for confirm transaction PIN field

    Examples:
      | Password   | NewPIN | ConfirmPIN |
      | Strong@123 | 1290   |            |


  @TransactionPINValidation @TC_60
  Scenario Outline: Verify successful transaction PIN reset
    When User clicks Reset Transaction PIN button
    And User enters account password "<Password>"
    And User enters new transaction PIN "<NewPIN>"
    And User enters confirm transaction PIN "<ConfirmPIN>"
    And User clicks Confirm Reset PIN button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | Password   | NewPIN | ConfirmPIN | ExpectedMessage                        |
      | Strong@123 | 1290   | 1290       | Transaction PIN has been reset successfully |




  @TransactionPINValidation @PINAbove6 @TC_61
  Scenario Outline: Verify transaction PIN field restricts values greater than 6 digits
    When User clicks Reset Transaction PIN button
    And User enters new transaction PIN "<NewPIN>"
    Then Transaction PIN field should restrict input of "<NewPIN>" to 6 digits

    Examples:
      | NewPIN |
      | 1234567 |



  @TransactionPINValidation @TC_62
  Scenario Outline: Verify transaction PIN reset with special character PIN
    When User clicks Reset Transaction PIN button
    And User enters account password "<Password>"
    And User enters new transaction PIN "<NewPIN>"
    And User enters confirm transaction PIN "<ConfirmPIN>"
    And User clicks Confirm Reset PIN button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | Password   | NewPIN | ConfirmPIN | ExpectedMessage                      |
      | Strong@123 | 12@#   | 12@#       | PIN must be 4 to 6 numeric digits |




  @TransactionPINValidation @TC_63
  Scenario Outline: Verify transaction PIN mismatch validation
    When User clicks Reset Transaction PIN button
    And User enters account password "<Password>"
    And User enters new transaction PIN "<NewPIN>"
    And User enters confirm transaction PIN "<ConfirmPIN>"
    And User clicks Confirm Reset PIN button
    Then User should see toast message "<ExpectedMessage>"

    Examples:
      | Password   | NewPIN | ConfirmPIN | ExpectedMessage                          |
      | Strong@123 | 1290   | 5678       | New PIN and Confirm PIN do not match |