Feature: Registration API Validation

  Background:
    Given User has registration API endpoint
    And Request content type is JSON

  @TC_API_001
  Scenario: Validate valid transaction PAN
    When User sends registration request with following data
      | email            | prajwalprajwal222@gmail.com |
      | phone            | 9632222204 |
      | password         | Dharwad@123 |
      | confirm_password | Dharwad@123 |
      | transaction_pin  | 7890 |
      | full_name        | Royal king |
      | dob              | 10-05-2000 |
      | gender           | male |
      | address          | Hyderabad, Telangana, India |
      | aadhaar_number   | 333337862333 |
      | pan_number       | BANFE2121A |
      | occupation       | Software Engineer |
      | annual_income    | 750000 |
    Then API should return status code 201
    And response message should be "Registration successful. Please verify your email OTP to activate your account."

  @TC_API_002
  Scenario: Validate lowercase PAN conversion
    When User sends registration request with following data
      | email            | prajwalprajwal222@gmail.com |
      | phone            | 9632222204 |
      | password         | Dharwad@123 |
      | confirm_password | Dharwad@123 |
      | transaction_pin  | 7890 |
      | full_name        | Royal king |
      | dob              | 10-05-2000 |
      | gender           | male |
      | address          | Hyderabad, Telangana, India |
      | aadhaar_number   | 333337862333 |
      | pan_number       | sdfgh0985y |
      | occupation       | Software Engineer |
      | annual_income    | 750000 |
    Then API should return status code 201
    And response message should be "Registration successful"

  @TC_API_003
  Scenario: Validate invalid PAN format
    When User sends registration request with following data
      | email            | prajwalprajwal222@gmail.com |
      | phone            | 9632222204 |
      | password         | Dharwad@123 |
      | confirm_password | Dharwad@123 |
      | transaction_pin  | 7890 |
      | full_name        | Royal king |
      | dob              | 10-05-2000 |
      | gender           | male |
      | address          | Hyderabad, Telangana, India |
      | aadhaar_number   | 333337862333 |
      | pan_number       | BANFE212A |
      | occupation       | Software Engineer |
      | annual_income    | 750000 |
    Then API should return status code 400
    And response message should be "Valid PAN number is required."

  @TC_API_004
  Scenario: Validate PAN special characters
    When User sends registration request with following data
      | email            | prajwalprajwal222@gmail.com |
      | phone            | 9632222204 |
      | password         | Dharwad@123 |
      | confirm_password | Dharwad@123 |
      | transaction_pin  | 7890 |
      | full_name        | Royal king |
      | dob              | 10-05-2000 |
      | gender           | male |
      | address          | Hyderabad, Telangana, India |
      | aadhaar_number   | 333337862333 |
      | pan_number       | BANFE@22A1 |
      | occupation       | Software Engineer |
      | annual_income    | 750000 |
    Then API should return status code 400
    And response message should be "Valid PAN number is required."

  @TC_API_005
  Scenario: Validate valid Aadhaar
    When User sends registration request with following data
      | email            | prajwalprajwal222@gmail.com |
      | phone            | 9632222204 |
      | password         | Dharwad@123 |
      | confirm_password | Dharwad@123 |
      | transaction_pin  | 7890 |
      | full_name        | Royal king |
      | dob              | 10-05-2000 |
      | gender           | male |
      | address          | Hyderabad, Telangana, India |
      | aadhaar_number   | 333337862333 |
      | pan_number       | BANFE2121A |
      | occupation       | Software Engineer |
      | annual_income    | 750000 |
    Then API should return status code 201
    And response message should be "Registration successful. Please verify your email OTP to activate your account."

  @TC_API_006
  Scenario: Validate Aadhaar less than 12 digits
    When User sends registration request with following data
      | email            | prajwalprajwal222@gmail.com |
      | phone            | 9632222204 |
      | password         | Dharwad@123 |
      | confirm_password | Dharwad@123 |
      | transaction_pin  | 7890 |
      | full_name        | Royal king |
      | dob              | 10-05-2000 |
      | gender           | male |
      | address          | Hyderabad, Telangana, India |
      | aadhaar_number   | 9898453478 |
      | pan_number       | BANFE2121A |
      | occupation       | Software Engineer |
      | annual_income    | 750000 |
    Then API should return status code 400
    And response message should be "Valid 12-digit Aadhaar number is required."

  @TC_API_007
  Scenario: Validate Aadhaar with alphabets
    When User sends registration request with following data
      | email            | prajwalprajwal222@gmail.com |
      | phone            | 9632222204 |
      | password         | Dharwad@123 |
      | confirm_password | Dharwad@123 |
      | transaction_pin  | 7890 |
      | full_name        | Royal king |
      | dob              | 10-05-2000 |
      | gender           | male |
      | address          | Hyderabad, Telangana, India |
      | aadhaar_number   | 98984AB47890 |
      | pan_number       | BANFE2121A |
      | occupation       | Software Engineer |
      | annual_income    | 750000 |
    Then API should return status code 400
    And response message should be "Valid 12-digit Aadhaar number is required."

  @TC_API_008
  Scenario: Validate Full Name does not accept numerics
    When User sends registration request with following data
      | email            | prajwalprajwal222@gmail.com |
      | phone            | 9632222204 |
      | password         | Dharwad@123 |
      | confirm_password | Dharwad@123 |
      | transaction_pin  | 7890 |
      | full_name        | Royal123 |
      | dob              | 10-05-2000 |
      | gender           | male |
      | address          | Hyderabad, Telangana, India |
      | aadhaar_number   | 333337862333 |
      | pan_number       | BANFE2121A |
      | occupation       | Software Engineer |
      | annual_income    | 750000 |
    Then API should return status code 400
    And response message should be "Full name Must Only contain Alphabets"

  @TC_API_009
  Scenario: Validate Full Name does not accept special characters
    When User sends registration request with following data
      | email            | prajwalprajwal222@gmail.com |
      | phone            | 9632222204 |
      | password         | Dharwad@123 |
      | confirm_password | Dharwad@123 |
      | transaction_pin  | 7890 |
      | full_name        | Royal@@ |
      | dob              | 10-05-2000 |
      | gender           | male |
      | address          | Hyderabad, Telangana, India |
      | aadhaar_number   | 333337862333 |
      | pan_number       | BANFE2121A |
      | occupation       | Software Engineer |
      | annual_income    | 750000 |
    Then API should return status code 400
    And response message should be "Full name Must Only contain Alphabets"