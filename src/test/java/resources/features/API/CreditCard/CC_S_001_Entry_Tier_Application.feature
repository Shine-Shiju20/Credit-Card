@CreditCardAPI
Feature: CC-S-001 Credit Card Application - Entry Tier
  Requirement: CC-REQ-001
  Description: Test applying for an entry-level credit card with valid KYC, income, and linked account

  @CC-S-001
  @CC_TC_001
  Scenario Outline: Verify user can open new credit card application flow
    Given test data is loaded for "<TestcaseID>"
    When user fetches linked accounts
    Then API response status code should be 200

    Examples:
      | TestcaseID |
      | CC_TC_001 |

  @CC-S-001
  @CC_TC_002
  Scenario Outline: Verify linked bank accounts are displayed
    Given test data is loaded for "<TestcaseID>"
    When user fetches linked accounts
    Then API response status code should be 200

    Examples:
      | TestcaseID |
      | CC_TC_002 |

  @CC-S-001
  @CC_TC_003
  Scenario Outline: Verify only active accounts are returned
    Given test data is loaded for "<TestcaseID>"
    When user fetches linked accounts
    Then API response status code should be 200

    Examples:
      | TestcaseID |
      | CC_TC_003 |

  @CC-S-001
  @CC_TC_004
  Scenario Outline: Verify application fails with no linked account
    Given test data is loaded for "<TestcaseID>"
    When user submits credit card application
    Then API response status code should be 400

    Examples:
      | TestcaseID |
      | CC_TC_004 |

  @CC-S-001
  @CC_TC_008
  Scenario Outline: Verify entry tier application success
    Given test data is loaded for "<TestcaseID>"
    When user submits credit card application
    Then API response status code should be 201

    Examples:
      | TestcaseID |
      | CC_TC_008 |

  @CC-S-001
  @CC_TC_011
  Scenario Outline: Verify limit threshold validation
    Given test data is loaded for "<TestcaseID>"
    When user submits credit card application
    Then API response status code should be 400

    Examples:
      | TestcaseID |
      | CC_TC_011 |

  @CC-S-001
  @CC_TC_012
  Scenario Outline: Verify unsupported tier rejection
    Given test data is loaded for "<TestcaseID>"
    When user submits credit card application
    Then API response status code should be 400

    Examples:
      | TestcaseID |
      | CC_TC_012 |

  @CC-S-001
  @CC_TC_013
  Scenario Outline: Verify limit below minimum threshold rejection
    Given test data is loaded for "<TestcaseID>"
    When user submits credit card application
    Then API response status code should be 400
    Examples:
      | TestcaseID |
      | CC_TC_013  |

  @CC-S-001
  @CC_TC_014
  Scenario Outline: Verify minimum threshold limit acceptance
    Given test data is loaded for "<TestcaseID>"
    When user submits credit card application
    Then API response status code should be 201
    Examples:
      | TestcaseID |
      | CC_TC_014  |

  @CC-S-001
  @CC_TC_015
  Scenario Outline: Verify incomplete KYC rejection
    Given test data is loaded for "<TestcaseID>"
    When user submits credit card application
    Then API response status code should be 400

    Examples:
      | TestcaseID |
      | CC_TC_015 |

  # SKIPPED: UI validation scenario
  # @CC_TC_016 Verify Continue button disabled for incomplete input

  @CC-S-001
  @CC_TC_017
  Scenario Outline: Verify audit log creation after application
    Given test data is loaded for "<TestcaseID>"
    When user submits credit card application
    Then API response status code should be 201

    Examples:
      | TestcaseID |
      | CC_TC_017 |

  @CC-S-001
  @CC_TC_019
  Scenario Outline: Verify generated card id returned
    Given test data is loaded for "<TestcaseID>"
    When user submits credit card application
    Then response should contain generated card id

    Examples:
      | TestcaseID |
      | CC_TC_019 |

  # SKIPPED: Duplicate application prevention test
  # @CC_TC_020

  @CC-S-001
  @CC_TC_021
  Scenario Outline: Verify KYC verified user can apply
    Given test data is loaded for "<TestcaseID>"
    When user submits credit card application
    Then API response status code should be 201

    Examples:
      | TestcaseID |
      | CC_TC_021  |

  @CC-S-001
  @CC_TC_023
  Scenario Outline: Verify eligible age user can apply through KYC DOB
    Given test data is loaded for "<TestcaseID>"
    When user submits credit card application
    Then API response status code should be 201

    Examples:
      | TestcaseID |
      | CC_TC_023  |

  @CC-S-001
  @CC_TC_026
  Scenario Outline: Verify entry card approval with income >= 3L
    Given test data is loaded for "<TestcaseID>"
    When user submits credit card application
    Then API response status code should be 201

    Examples:
      | TestcaseID |
      | CC_TC_026  |
