@CreditCardAPI
Feature: CC-S-018 Eligibility Edge Cases
  Requirement: CC-REQ-018
  Description: Verify card expiry and renewal edge cases

  @skip
  @CC_S_018
  @CC_TC_104
  Scenario Outline: Expiry date field present in response
    Given test data is loaded for "<TestcaseID>"
    When user fetches credit card details
    Then API response status code should be 400

    Examples:
      | TestcaseID |
      | CC_TC_104 |

  @skip
  @CC_S_018
  @CC_TC_105
  Scenario Outline: Card renewal - new card issued, old deactivated (UI)
    Given test data is loaded for "<TestcaseID>"
    When user submits credit card application
    Then API response status code should be 400

    Examples:
      | TestcaseID |
      | CC_TC_105 |

  @skip
  @CC_S_018
  @CC_TC_106
  Scenario Outline: Renewal on non-expired card - too early (UI)
    Given test data is loaded for "<TestcaseID>"
    When user submits credit card application
    Then API response status code should be 400

    Examples:
      | TestcaseID |
      | CC_TC_106 |
