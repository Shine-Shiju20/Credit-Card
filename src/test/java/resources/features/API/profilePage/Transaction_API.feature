Feature: Transaction PIN Reset API Validation

  Background:
    Given User has valid session cookie



  @TC_API_38
  Scenario: Verify successful transaction PIN reset
    When User sends POST request to "/user/reset-transaction-pin" with valid PIN details
    Then Response status code should be 200
    And Response message should be "Transaction PIN reset successfully."



  @TC_API_39
  Scenario: Verify transaction PIN reset with incorrect account password
    When User sends POST request with invalid account password
    Then Response status code should be 400
    And Response message should be "Incorrect account password."



  @TC_API_40
  Scenario: Verify transaction PIN reset with weak PIN
    When User sends POST request with weak transaction PIN
    Then Response status code should be 400
    And Response message should be "PIN is too weak. Please choose a more secure PIN."



  @TC_API_41
  Scenario: Verify transaction PIN reset with PIN less than 4 digits
    When User sends POST request with transaction PIN "12"
    Then Response status code should be 400
    And Response message should be "Transaction PIN must be numeric and contain 4 to 6 digits only."



  @TC_API_42
  Scenario: Verify transaction PIN reset with alphabetic PIN
    When User sends POST request with transaction PIN "ABCD"
    Then Response status code should be 400
    And Response message should be "Transaction PIN must be numeric and contain 4 to 6 digits only."