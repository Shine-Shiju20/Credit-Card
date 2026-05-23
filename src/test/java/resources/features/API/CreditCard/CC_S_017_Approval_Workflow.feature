@CreditCardAPI
Feature: CC-S-017 Approval Workflow
  Requirement: CC-REQ-017
  Description: Verify approval workflow, verification checks, and card issuance lifecycle

  @CC-S-017
  @CC_TC_099
  Scenario Outline: Verify eligible application moves to approval state
    Given test data is loaded for "<TestcaseID>"
    When user submits credit card application
    Then API response status code should be 201

    Examples:
      | TestcaseID |
      | CC_TC_099 |

  @CC-S-017
  @CC_TC_100
  Scenario Outline: Verify rejected application status handling
    Given test data is loaded for "<TestcaseID>"
    When user submits credit card application
    Then API response status code should be 400

    Examples:
      | TestcaseID |
      | CC_TC_100 |

  @CC-S-017
  @CC_TC_101
  Scenario Outline: Verify approved card is retrievable by customer
    Given test data is loaded for "<TestcaseID>"
    When user fetches credit card details
    Then API response status code should be 200

    Examples:
      | TestcaseID |
      | CC_TC_101 |
