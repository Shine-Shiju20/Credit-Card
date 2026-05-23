Feature: Fixed Deposit Creation API

  Background:
    Given user logs in with email "sk.shreya651@gmail.com" and password "Shreya@26"


  @fdCreation
  Scenario Outline: Verify FD creation validations
    When user creates FD with account_id "<accountId>", amount "<amount>", tenure "<tenure>", interest "<interest>"
    Then API status code should be <status>

    Examples:
      | accountId                              | amount  | tenure | interest | status |
      | 5f778492-9dc4-40ef-91e6-3e727cd17ade   | 50000   | 12     | 7        | 200    |
      | 5f778492-9dc4-40ef-91e6-3e727cd17ade   | -10000  | 12     | 7        | 400    |
      | 5f778492-9dc4-40ef-91e6-3e727cd17ade   | 0       | 12     | 7        | 400    |
      | abc123                                | 50000   | 12     | 7        | 400    |
      | invalid-uuid                          | 50000   | 12     | 7        | 400    |
      | 5f778492-9dc4-40ef-91e6-3e727cd17ade   | 50000   | 5      | 7        | 400    |
      | 5f778492-9dc4-40ef-91e6-3e727cd17ade   | 50000   | 12     | 5        | 400    |
      | 5f778492-9dc4-40ef-91e6-3e727cd17ade   | abc     | 12     | 7        | 400    |