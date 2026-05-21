@CreditCardAPI
Feature: CC-S-013 Multiple Card Management
  Requirement: CC-REQ-013
  Description: Test viewing and managing multiple credit cards per user

  @CC-S-013
  @CC_TC_069
  Scenario Outline: Verify API returns all cards for logged-in user
    Given test data is loaded for "<TestcaseID>"
    When user fetches user credit cards
    Then API response status code should be 200

    Examples:
      | TestcaseID |
      | CC_TC_069  |

  @CC-S-013
  @CC_TC_070
  Scenario Outline: Verify unauthenticated user cannot list cards
    Given test data is loaded for "<TestcaseID>"
    When user fetches user credit cards
    Then API response status code should be 401

    Examples:
      | TestcaseID |
      | CC_TC_070  |

  # SKIPPED: CC_TC_071 — UI card sort (active, blocked, closed order)
  # Cat 4 arch blocker: sort logic in React frontend only
  # Backend returns unsorted array; API layer cannot assert UI sort order
  # @CC_TC_071

  # SKIPPED: CC_TC_072 — UI card flip management actions per status
  # Cat 4 arch blocker: conditional UI rendering in React (is-flipped class)
  # No API equivalent; backend does not expose card action visibility state
  # @CC_TC_072

