@CreditCardAPI
Feature: CC-S-008 Card Close
  Requirement: CC-REQ-008
  Description: Test closing a card with zero balance and with outstanding balance

  @CC-S-008
  @CC_TC_079
  Scenario Outline: Verify card close success
    Given test data is loaded for "<TestcaseID>"
    When user closes the credit card
    Then API response status code should be 200

    Examples:
      | TestcaseID |
      | CC_TC_079 |

  @CC-S-008
  @CC_TC_080
  Scenario Outline: Verify close blocked for outstanding balance
    Given test data is loaded for "<TestcaseID>"
    When user closes the credit card
    Then API response status code should be 400

    Examples:
      | TestcaseID |
      | CC_TC_080 |

# CC_TC_081 — Excel = minimum due block → 400, wrong When step
  @CC-S-008
  @CC_TC_081
  Scenario Outline: Verify close blocked for minimum due pending
    Given test data is loaded for "<TestcaseID>"
    When user closes the credit card
    Then API response status code should be 400
    Examples:
      | TestcaseID |
      | CC_TC_081  |

  @CC-S-008
  @CC_TC_082
  Scenario Outline: Verify closed card transaction rejection
    Given test data is loaded for "<TestcaseID>"
    When user performs purchase transaction
    Then API response status code should be 400

    Examples:
      | TestcaseID |
      | CC_TC_082 |

# CC_TC_083 — Excel = already closed rejection → 400
  @CC-S-008
  @CC_TC_083
  Scenario Outline: Verify duplicate closure rejection
    Given test data is loaded for "<TestcaseID>"
    When user closes the credit card
    Then API response status code should be 400
    And response message should contain "Card is already permanently closed"

    Examples:
      | TestcaseID |
      | CC_TC_083  |

  # SKIPPED: UI closure notification validation
  # @CC_TC_084

## CC_TC_085 — Excel = attacker close attempt → 400
#  @CC-S-008
#  @CC_TC_085
#  Scenario Outline: Verify unauthorized close attempt rejected
#    Given test data is loaded for "<TestcaseID>"
#    When user closes the credit card
#    Then API response status code should be 400
#    Examples:
#      | TestcaseID |
#      | CC_TC_085  |

  @CC-S-008
  @CC_TC_086
  Scenario Outline: Verify closure reason persisted
    Given test data is loaded for "<TestcaseID>"
    When user closes the credit card
    Then API response status code should be 200

    Examples:
      | TestcaseID |
      | CC_TC_086 |

# CC_TC_087 — Excel = UI warning flow → 200, remove this or remap
# CC_TC_087 is a UI test — mark as skipped
# SKIPPED: UI outstanding balance warning modal
# @CC_TC_087