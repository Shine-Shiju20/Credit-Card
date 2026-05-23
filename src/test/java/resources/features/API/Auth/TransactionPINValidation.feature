Feature: Registration API Transaction PIN Validation

  Background:
    Given User has registration API endpoint
    And Request content type is JSON

  @TC_API_016
  Scenario: Validate Weak/Predictable PIN
    When User sends registration request with following data
      | email            | prajwalprajwal222@gmail.com |
      | phone            | 9632222204 |
      | password         | Dharwad@123 |
      | confirm_password | Dharwad@123 |
      | transaction_pin  | 123456 |
      | full_name        | Royal King |
      | dob              | 10-05-2000 |
      | gender           | male |
      | address          | Hyderabad, Telangana, India |
      | aadhaar_number   | 333337862333 |
      | pan_number       | BANFE2121A |
      | occupation       | Software Engineer |
      | annual_income    | 750000 |
    Then API should return status code 400
    And response message should be "Pin is too weak. Please Choose a more secure pin"