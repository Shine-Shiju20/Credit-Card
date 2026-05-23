Feature: Fixed Deposit Retrieval API

  Background:
    Given user logs in with email "sk.shreya651@gmail.com" and password "Shreya@26"

  Scenario: Verify fetch all FD
    When user fetches all fixed deposits
    Then API status code should be 200

  Scenario Outline: Verify fetch FD by ID
    When user fetches FD by id "<fdId>"
    Then API status code should be <status>

    Examples:
      | fdId                                  | status |
      | 4441a8e2-ea72-4d4c-a49e-ea02c6af2c56  | 200    |
      | invalid-id                            | 404    |