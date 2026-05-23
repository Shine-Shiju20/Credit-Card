Feature: User Profile Update API Validation

  Background:
    Given User has valid session cookie



  @TC_API_06 @ProfileUpdateAPI
  Scenario: Verify successful profile update using PUT /user/me
    When User sends PUT request to "/user/me" with valid profile details
    Then Response status code should be 200
    And Response message should be "Profile updated successfully."



  @TC_API_07 @ProfileUpdateAPI
  Scenario: Verify PUT /user/me with null full_name
    When User sends PUT request with full_name ""
    Then Response status code should be 400
    And Response message should be "full_name cannot be empty or null."



  @TC_API_08 @ProfileUpdateAPI
  Scenario: Verify PUT /user/me with empty full_name
    When User sends PUT request with full_name ""
    Then Response status code should be 400
    And Response message should be "full_name cannot be empty or null."



  @TC_API_09 @ProfileUpdateAPI
  Scenario: Verify PUT /user/me with full_name less than 3 characters
    When User sends PUT request with full_name "Ab"
    Then Response status code should be 400
    And Response message should be "full_name must be at least 3 characters long."



  @TC_API_10 @ProfileUpdateAPI
  Scenario: Verify PUT /user/me with numeric full_name
    When User sends PUT request with full_name "123"
    Then Response status code should be 400
    And Response message should be "full_name must not contain numerics"



  @TC_API_11 @ProfileUpdateAPI
  Scenario: Verify PUT /user/me with special characters in full_name
    When User sends PUT request with full_name "Abhirm@342"
    Then Response status code should be 400
    And Response message should be "full_name must not contain special character"



  @TC_API_12 @ProfileUpdateAPI
  Scenario: Verify PUT /user/me with null phone number
    When User sends PUT request with phone ""
    Then Response status code should be 400
    And Response message should be "phone cannot be empty or null."



  @TC_API_13 @ProfileUpdateAPI
  Scenario: Verify PUT /user/me with phone number less than 10 digits
    When User sends PUT request with phone "73376953"
    Then Response status code should be 400
    And Response message should be "Phone number must contain exactly 10 digits."



  @TC_API_14 @ProfileUpdateAPI
  Scenario: Verify PUT /user/me with phone number greater than 10 digits
    When User sends PUT request with phone "733769531414"
    Then Response status code should be 400
    And Response message should be "Phone number must contain exactly 10 digits."



  @TC_API_15 @ProfileUpdateAPI
  Scenario: Verify PUT /user/me with alphabetic phone number
    When User sends PUT request with phone "73376953A"
    Then Response status code should be 400
    And Response message should be "Phone number must contain exactly 10 digits."



  @TC_API_16 @ProfileUpdateAPI
  Scenario: Verify PUT /user/me with special characters in phone number
    When User sends PUT request with phone "73376@5314"
    Then Response status code should be 400
    And Response message should be "Phone number must contain exactly 10 digits."



  @TC_API_17 @ProfileUpdateAPI
  Scenario: Verify PUT /user/me with null address
    When User sends PUT request with address ""
    Then Response status code should be 400
    And Response message should be "address cannot be empty or null."



  @TC_API_18 @ProfileUpdateAPI
  Scenario: Verify PUT /user/me with empty address
    When User sends PUT request with address ""
    Then Response status code should be 400
    And Response message should be "address cannot be empty or null."



  @TC_API_19 @ProfileUpdateAPI
  Scenario: Verify PUT /user/me with address less than minimum length
    When User sends PUT request with address "ab"
    Then Response status code should be 400
    And Response message should be "Address should be Above 10 characters"



  @TC_API_20 @ProfileUpdateAPI
  Scenario: Verify PUT /user/me with address exceeding maximum length
    When User sends PUT request with address "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    Then Response status code should be 400
    And Response message should be "Address cannot exceed 100 characters"