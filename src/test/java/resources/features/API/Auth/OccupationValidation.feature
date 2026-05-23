Feature: Registration API Occupation Validation

  Background:
    Given User has registration API endpoint
    And Request content type is JSON

  @TC_API_013
  Scenario: Validate Occupation accepts only alphabets
    When User sends registration request with following data
      | email            | prajwalprajwal222@gmail.com |
      | phone            | 9632222204 |
      | password         | Dharwad@123 |
      | confirm_password | Dharwad@123 |
      | transaction_pin  | 7890 |
      | full_name        | Royal King |
      | dob              | 10-05-2000 |
      | gender           | male |
      | address          | Hyderabad, Telangana, India |
      | aadhaar_number   | 333337862333 |
      | pan_number       | BANFE2121A |
      | occupation       | Software Engineer123 |
      | annual_income    | 750000 |
    Then API should return status code 400
    And response message should be "Occupation must contain only Alphabets"

  @TC_API_014
  Scenario: Validate Occupation minimum 2 characters
    When User sends registration request with following data
      | email            | prajwalprajwal222@gmail.com |
      | phone            | 9632222204 |
      | password         | Dharwad@123 |
      | confirm_password | Dharwad@123 |
      | transaction_pin  | 7890 |
      | full_name        | Royal King |
      | dob              | 10-05-2000 |
      | gender           | Male |
      | address          | Hyderabad, Telangana, India |
      | aadhaar_number   | 333337862333 |
      | pan_number       | BANFE2121A |
      | occupation       | S |
      | annual_income    | 750000 |
    Then API should return status code 400
    And response message should be "Occupation is required."

  @TC_API_015
  Scenario: Validate Occupation maximum 50 characters
    When User sends registration request with following data
      | email            | prajwalprajwal222@gmail.com |
      | phone            | 9632222204 |
      | password         | Dharwad@123 |
      | confirm_password | Dharwad@123 |
      | transaction_pin  | 7890 |
      | full_name        | Royal King |
      | dob              | 10-05-2000 |
      | gender           | male |
      | address          | Hyderabad, Telangana, India |
      | aadhaar_number   | 333337862333 |
      | pan_number       | BANFE2121A |
      | occupation       | Senior Executive Financial Risk Analysis Operations Specialist |
      | annual_income    | 750000 |
    Then API should return status code 400
    And response message should be "Occupation Cannot Exceed 50 Characters."