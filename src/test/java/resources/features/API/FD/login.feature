Feature: Login API functionality


  //23 ,24, 25
  @login
  Scenario Outline: Verify login API validations
    Given user logs in with email "<email>" and password "<password>"
    Then API status code should be <status>

    Examples:
      | email                     | password    | status |
      | sk.shreya651@gmail.com    | wrongpass   | 401    |
      | sk.shreya651@gmail.com    |             | 400    |
      | abc.com                   | pass123     | 400    |
      | sk.shreya651@gmail.com    | Shreya@26   | 200    |