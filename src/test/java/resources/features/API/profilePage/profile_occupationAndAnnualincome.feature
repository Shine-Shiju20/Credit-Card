Feature: User Profile Occupation and Annual Income API Validation

  Background:
    Given User has valid session cookie



  @TC_API_21 @ProfileUpdateAPI
  Scenario: Verify PUT /user/me with null occupation
    When User sends PUT request with occupation ""
    Then Response status code should be 400
    And Response message should be "occupation cannot be empty or null."



  @TC_API_22 @ProfileUpdateAPI
  Scenario: Verify PUT /user/me with numeric occupation
    When User sends PUT request with occupation "SDE223"
    Then Response status code should be 400
    And Response message should be "occupation must contain only alphabets and spaces."



  @TC_API_23 @ProfileUpdateAPI
  Scenario: Verify PUT /user/me with special characters in occupation
    When User sends PUT request with occupation "SDE@"
    Then Response status code should be 400
    And Response message should be "occupation must contain only alphabets and spaces."



  @TC_API_24 @ProfileUpdateAPI
  Scenario: Verify PUT /user/me with occupation exceeding maximum allowed length
    When User sends PUT request with occupation "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    Then Response status code should be 400
    And Response message should be "value too long for type character varying(100)"



  @TC_API_25 @ProfileUpdateAPI
  Scenario: Verify PUT /user/me with negative annual income
    When User sends PUT request with annual income "-500000"
    Then Response status code should be 400
    And Response message should be "annual_income must be greater than 0."



  @TC_API_26 @ProfileUpdateAPI
  Scenario: Verify PUT /user/me with zero annual income
    When User sends PUT request with annual income "0"
    Then Response status code should be 400
    And Response message should be "annual_income must be greater than 0."



  @TC_API_27 @ProfileUpdateAPI
  Scenario: Verify PUT /user/me with alphabetic annual income
    When User sends PUT request with annual income "ABCDE"
    Then Response status code should be 400
    And Response message should be "annual_income must be a numeric value"