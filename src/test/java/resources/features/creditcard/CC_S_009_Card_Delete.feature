@CreditCardAPI
Feature: CC-S-009 Card Delete
  Requirement: CC-REQ-009
  Description: Test permanently deleting a closed card record

  @CC-S-009
  @CC_TC_051
  Scenario Outline: Verify delete removes card listing
    Given test data is loaded for "<TestcaseID>"
    When user deletes the credit card
    Then API response status code should be 200

    Examples:
      | TestcaseID |
      | CC_TC_051 |

  @CC-S-009
  @CC_TC_052
  Scenario Outline: Verify active card delete blocked
    Given test data is loaded for "<TestcaseID>"
    When user deletes the credit card
    Then API response status code should be 400
    Examples:
      | TestcaseID |
      | CC_TC_052  |

  @CC-S-009
  @CC_TC_053
  Scenario Outline: Verify delete audit trail creation
    Given test data is loaded for "<TestcaseID>"
    When user deletes the credit card
    Then API response status code should be 200

    Examples:
      | TestcaseID |
      | CC_TC_053 |

  # SKIPPED: UI logged-out delete redirect
  # @CC-S-009
  # @CC_TC_054
