Feature: Fixed Deposit Closure API

  Background:
    Given user logs in with email "sk.shreya651@gmail.com" and password "Shreya@26"

  Scenario Outline: Verify FD closure
    When user creates FD with account_id "<accountId>", amount "<amount>", tenure "<tenure>", interest "<interest>"
    And user closes created FD
    Then API status code should be <status>

    Examples:
      | accountId                              | amount | tenure | interest | status |
      | 5f778492-9dc4-40ef-91e6-3e727cd17ade   | 5000  | 12     | 7        | 200    |