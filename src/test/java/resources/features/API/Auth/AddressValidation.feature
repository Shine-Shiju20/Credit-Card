Feature: Registration API Address Validation

  Background:
    Given User has registration API endpoint
    And Request content type is JSON

  @TC_API_010
  Scenario: Validate Full Name minimum 3 characters
    When User sends registration request with following data
      | email            | prajwalprajwal222@gmail.com |
      | phone            | 9632222204 |
      | password         | Dharwad@123 |
      | confirm_password | Dharwad@123 |
      | transaction_pin  | 7890 |
      | full_name        | Ro |
      | dob              | 10-05-2000 |
      | gender           | male |
      | address          | Hyderabad, Telangana, India |
      | aadhaar_number   | 333337862333 |
      | pan_number       | BANFE2121A |
      | occupation       | Software Engineer |
      | annual_income    | 750000 |
    Then API should return status code 400
    And response message should be "Full name must be at least 3 characters long."

  @TC_API_011
  Scenario: Validate Full Name maximum 50 characters
    When User sends registration request with following data
      | email            | prajwalprajwal222@gmail.com |
      | phone            | 9632222204 |
      | password         | Dharwad@123 |
      | confirm_password | Dharwad@123 |
      | transaction_pin  | 7890 |
      | full_name        | Venkateshwara Narasimha Raghavendra Prasad Sharma |
      | dob              | 10-05-2000 |
      | gender           | male |
      | address          | Hyderabad, Telangana, India |
      | aadhaar_number   | 333337862333 |
      | pan_number       | BANFE2121A |
      | occupation       | Software Engineer |
      | annual_income    | 750000 |
    Then API should return status code 400
    And response message should be "Full Name cannot Exceed 50 Characters"



  @TC_API_012
  Scenario: Validate Address maximum 250 characters
    When User sends registration request with following data
      | email            | prajwalprajwal222@gmail.com |
      | phone            | 9632222204 |
      | password         | Dharwad@123 |
      | confirm_password | Dharwad@123 |
      | transaction_pin  | 7890 |
      | full_name        | Royal King |
      | dob              | 10-05-2000 |
      | gender           | male |
      | address          | Flat No 407 Sri Lakshmi Venkateshwara Residency Apartments Rajajinagar Extension Basaveshwara Circle Vijayapura District Karnataka India PIN 586101 Near Government High School Opposite Central Public Library Very Long Address Validation Data Extra Characters |
      | aadhaar_number   | 333337862333 |
      | pan_number       | BANFE2121A |
      | occupation       | Software Engineer |
      | annual_income    | 750000 |
    Then API should return status code 400
    And response message should be "address must be at least 10 characters long."