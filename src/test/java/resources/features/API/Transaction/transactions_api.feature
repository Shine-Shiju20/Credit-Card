Feature: Banking Transaction APIs

  Background:
    Given the API base URI is "http://localhost:3000"
    And the user authenticates with email "kvphs1981@gmail.com" and password "Harish@2003"

  # =========================================================
  # Positive Scenarios
  # =========================================================

  Scenario: Verify valid internal transfer
    When the user sends a POST request to "/transactions/transfer" with JSON:
      """
     {
  "from_account_number": "1025000122964314",
  "to_account_number": "1025000178288290",
  "amount": 5000,
  "transaction_type": "internal",
  "transaction_pin": "123456"
}
      """
    Then the response status code should be 200
    And the response JSON path "success" should be true

  Scenario: Verify valid deposit
    When the user sends a POST request to "/transactions/deposit" with JSON:
      """
      {
        "account_number": "1025000122964314",
        "amount": 10000,
        "transaction_pin": "123456"
      }
      """
    Then the response status code should be 200
    And the response JSON path "success" should be true

  Scenario: Verify valid withdrawal
    When the user sends a POST request to "/transactions/withdraw" with JSON:
      """
      {
        "account_number": "1025000122964314",
        "amount": 2000,
        "transaction_pin": "123456"
      }
      """
    Then the response status code should be 200
    And the response JSON path "success" should be true

  # =========================================================
  # Negative Scenarios
  # =========================================================

  Scenario: Verify self transfer validation
    When the user sends a POST request to "/transactions/transfer" with JSON:
      """
      {
        "from_account_number": "1025000193741429",
        "to_account_number": "1025000193741429",
        "amount": 1000,
        "transaction_type": "internal",
        "transaction_pin": "123456"
      }
      """
    Then the response status code should be 500
    And the response JSON path "success" should be false

  Scenario: Verify insufficient balance during transfer
    When the user sends a POST request to "/transactions/transfer" with JSON:
      """
      {
        "from_account_number": "1025000122964314",
        "to_account_number": "1025000193741423",
        "amount": 100000,
        "transaction_type": "internal",
        "transaction_pin": "123456"
      }
      """
    Then the response status code should be 500
    And the response JSON path "success" should be false

  # API_TXN_011
  Scenario: Verify insufficient balance during withdrawal
    When the user sends a POST request to "/transactions/withdraw" with JSON:
      """
      {
        "account_number": "1025000193741423",
        "amount": 100000000,
        "transaction_pin": "123456"
      }
      """
    Then the response status code should be 500
    And the response JSON path "success" should be false

  Scenario: Verify invalid recipient account
    When the user sends a POST request to "/transactions/transfer" with JSON:
      """
      {
        "from_account_number": "1025000193741423",
        "to_account_number": "1025000922984314",
        "amount": 1234,
        "transaction_type": "internal",
        "transaction_pin": "123456"
      }
      """
    Then the response status code should be 500
    And the response JSON path "success" should be false

  Scenario: Verify invalid transaction PIN
    When the user sends a POST request to "/transactions/transfer" with JSON:
      """
      {
        "from_account_number": "1025000193741429",
        "to_account_number": "1025000193741423",
        "amount": 5000,
        "transaction_type": "internal",
        "transaction_pin": "000000"
      }
      """
    Then the response status code should be 401
    And the response JSON path "success" should be false

  Scenario: Verify missing transaction PIN
    When the user sends a POST request to "/transactions/transfer" with JSON:
      """
      {
        "from_account_number": "1025000193741429",
        "to_account_number": "1025000193741423",
        "amount": 1234,
        "transaction_type": "internal",
        "transaction_pin": ""
      }
      """
    Then the response status code should be 400
    And the response JSON path "success" should be false

  Scenario: Verify zero amount transfer
    When the user sends a POST request to "/transactions/transfer" with JSON:
      """
      {
        "from_account_number": "1025000193741423",
        "to_account_number": "1025000122964314",
        "amount": 0,
        "transaction_type": "rtgs",
        "transaction_pin": "123456"
      }
      """
    Then the response status code should be 400
    And the response JSON path "success" should be false

  Scenario: Verify transfer with empty amount
    When the user sends a POST request to "/transactions/transfer" with JSON:
      """
      {
        "from_account_number": "1025000193741423",
        "to_account_number": "1025000122964314",
        "amount": "",
        "transaction_type": "rtgs",
        "transaction_pin": "123456"
      }
      """
    Then the response status code should be 400
    And the response JSON path "success" should be false

  Scenario: Verify invalid transaction type
    When the user sends a POST request to "/transactions/transfer" with JSON:
      """
      {
        "from_account_number": "1025000193741429",
        "to_account_number": "1025000193741423",
        "amount": 1234,
        "transaction_type": "payment",
        "transaction_pin": "123456"
      }
      """
    Then the response status code should be 500
    And the response JSON path "success" should be false

  Scenario: Verify unauthorized request without login
    Given the authorization token is cleared
    When the user sends a POST request to "/transactions/transfer" with JSON:
      """
      {
        "from_account_number": "1025000193741429",
        "to_account_number": "1025000193741423",
        "amount": 5000,
        "transaction_type": "internal",
        "transaction_pin": "123456"
      }
      """
    Then the response status code should be 401