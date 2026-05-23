Feature: Fixed Deposit functionality

  Background:
    Given user launches banking application
    And user logs in with valid credentials
    And user navigates to fixed deposit page

  @tc_01
  Scenario Outline: Verify valid FD creation
    When user creates FD with amount "<amount>" and tenure "<tenure>"
    Then FD should be created successfully

    Examples:
      | amount | tenure    |
      | 2000  | 12 months |

  @tc_02
  Scenario Outline: Verify modal close functionality
    When user clicks "<firstAction>"
    Then Create FD modal should be "<modalStateBefore>"
    When user clicks "<secondAction>"
    Then Create FD modal should be "<modalStateAfter>"
    And user should remain on "<pageName>" page

    Examples:
      | firstAction    | modalStateBefore | secondAction | modalStateAfter | pageName      |
      | Create New FD  | displayed        | Cancel       | closed          | fixed deposit |

  @tc_03
  Scenario Outline: Verify Create FD modal closes using close action
    When user clicks "<openAction>"
    Then Create FD modal should be "<initialState>"
    When user clicks "<closeAction>"
    Then Create FD modal should be "<finalState>"
    And user should remain on "<page>" page

    Examples:
      | openAction    | initialState | closeAction | finalState | page          |
      | Create New FD | displayed    | Close Icon  | closed     | fixed deposit |

  @tc_04
  Scenario Outline: Verify default account selection in Create FD modal
    When user clicks "<action>"
    Then "<selectionType>" account should be auto selected

    Examples:
      | action        | selectionType |
      | Create New FD | first         |

  @tc_05
  Scenario Outline: Verify deposit amount field accepts valid numeric input
    When user clicks "<action>"
    And user enters FD amount as "<amount>"
    Then deposit amount should be "<status>"

    Examples:
      | action        | amount | status   |
      | Create New FD | 5000   | accepted |

  @tc_06
  Scenario Outline: Verify minimum deposit amount validation
    When user clicks "<action>"
    And user enters FD amount as "<amount>"
    And user selects tenure as "<tenure>"
    And user selects account
    Then FD submission should be "<status>"

    Examples:
      | action        | amount | tenure    | status    |
      | Create New FD | 500    | 12 months | prevented |

  @tc_07
  Scenario Outline: Verify zero deposit amount validation
    When user clicks "<action>"
    And user enters FD amount as "<amount>"
    And user selects tenure as "<tenure>"
    Then FD submission for zero amount should be "<status>"

    Examples:
      | action        | amount | tenure    | status    |
      | Create New FD | 0      | 12 months | prevented |

  @tc_08
  Scenario Outline: Verify negative deposit amount validation
    When user clicks "<action>"
    And user enters FD amount as "<amount>"
    And user selects tenure as "<tenure>"
    Then FD submission for negative amount should be "<status>"

    Examples:
      | action        | amount | tenure    | status    |
      | Create New FD | -1000  | 12 months | prevented |

  @tc_09
  Scenario Outline: Verify insufficient balance validation
    When user clicks "<action>"
    And user selects "<accountType>" account
    And user enters FD amount as "<amount>"
    And user selects tenure as "<tenure>"
    And user clicks Create FD submit button
    Then "<validationType>" validation should be displayed

    Examples:
      | action        | accountType | amount | tenure    | validationType      |
      | Create New FD | low balance | 1000000  | 12 months | insufficient balance |


  @tc_10
  Scenario Outline: Verify tenure dropdown options display
    When user clicks "<action>"
    And user clicks "<dropdown>"
    Then "<result>" should be displayed

    Examples:
      | action        | dropdown | result         |
      | Create New FD | tenure   | tenure options |

  @tc_11
  Scenario Outline: Verify tenure selection mandatory validation
    When user clicks "<action>"
    And user enters FD amount as "<amount>"
    Then "<validation>" should be enforced

    Examples:
      | action        | amount | validation         |
      | Create New FD | 5000   | create fd disabled |

  @tc_12
  Scenario Outline: Verify tenure assigns correct interest rate
    When user clicks "<action>"
    And user selects tenure option "<tenure>"
    Then interest rate should be "<expectedRate>"

    Examples:
      | action        | tenure                           | expectedRate |
      | Create New FD | 3 months (4.5% p.a.)            | 4.5          |
      | Create New FD | 6 months (5.5% p.a.)            | 5.5          |
      | Create New FD | 12 months (7% p.a.) - 1 year    | 7            |
      | Create New FD | 24 months (8.5% p.a.) - 2 years | 8.5          |
      | Create New FD | 60 months (8.5% p.a.) - 5 years | 8.5          |
      | Create New FD | 61 months (9% p.a.)             | 9            |
      | Create New FD | 71 months (9% p.a.)             | 9            |
      | Create New FD | 72 months (10% p.a.) - 6 years  | 10           |
      | Create New FD | 84 months (10% p.a.) - 7 years  | 10           |
      | Create New FD | 96 months (12% p.a.) - 8 years  | 12           |
      | Create New FD | 108 months (12% p.a.) - 9 years | 12           |
      | Create New FD | 120 months (15% p.a.) - 10 years| 15           |

  @tc_13
  Scenario Outline: Verify interest rate field properties
    When user clicks "<action>"
    Then interest rate field should be "<fieldState>"

    Examples:
      | action        | fieldState |
      | Create New FD | read only  |

  @tc_14
  Scenario Outline: Verify investment summary visibility for valid FD input
    When user clicks "<action>"
    And user enters valid FD details with amount "<amount>" and tenure "<tenure>"
    Then "<component>" should be displayed

    Examples:
      | action        | amount | tenure                           | component          |
      | Create New FD | 5000   | 12 months (7% p.a.) - 1 year    | investment summary |

  @tc_15
  Scenario Outline: Verify FD summary values
    When user clicks "Create New FD"
    And user enters valid FD details with amount "<amount>" and tenure "<tenure>"
    Then "deposit amount" in summary should be "<deposit>"
    And "interest rate" in summary should be "<rate>"
    And "tenure" in summary should be "<tenureValue>"
    And "interest earned" in summary should be "<interest>"
    And "maturity amount" in summary should be "<maturity>"
    And "roi" in summary should be "<roi>"

    Examples:
      | amount | tenure                         | deposit | rate | tenureValue | interest | maturity | roi   |
      | 50000  | 24 months (8.5% p.a.) - 2 years | 50,000 | 8.5  | 24          | 8861     | 58861    | 17.72 |

  @tc_16
  Scenario Outline: Verify Create FD button state for invalid amount input
    When user clicks "<action>"
    And user enters amount state "<amountState>"
    And user selects tenure option "<tenure>"
    Then Create FD button should be "<buttonState>"

    Examples:
      | action        | amountState | tenure                        | buttonState |
      | Create New FD | blank       | 12 months (7% p.a.) - 1 year | disabled    |

  @tc_17
  Scenario Outline: Verify Create FD button validation
    When user clicks "<action>"
    And user enters FD amount "<amount>"
    And user selects tenure option "<tenure>"
    Then Create FD button should be "<buttonState>"

    Examples:
      | action        | amount | tenure                           | buttonState |
      | Create New FD |        | 12 months (7% p.a.) - 1 year    | disabled    |
      | Create New FD | 500    | 12 months (7% p.a.) - 1 year    | disabled    |
      | Create New FD | 0      | 12 months (7% p.a.) - 1 year    | disabled    |
      | Create New FD | -10000 | 12 months (7% p.a.) - 1 year    | disabled    |
      | Create New FD | 5000   |                                 | disabled    |
      | Create New FD | 5000   | 24 months (8.5% p.a.) - 2 years | enabled     |

  @tc_18
  Scenario Outline: Verify Create FD button state based on mandatory inputs
    When user clicks "<action>"
    And user enters FD amount "<amount>"
    And user selects tenure option "<tenure>"
    Then Create FD button should be "<buttonState>"

    Examples:
      | action        | amount | tenure                        | buttonState |
      | Create New FD | 5000  |                               | disabled    |

  @tc_19
  Scenario Outline: Verify successful FD creation
    When user clicks "<action>"
    And user selects account type "<accountType>"
    And user enters FD amount "<amount>"
    And user selects tenure option "<tenure>"
    And user clicks "<submitAction>"
    Then "<validation1>" should be displayed
    And "<validation2>" should be displayed

    Examples:
      | action        | accountType | amount | tenure                           | submitAction      | validation1     | validation2 |
      | Create New FD | current     | 5000   | 24 months (8.5% p.a.) - 2 years | Create FD Submit  | success message | new fd       |


  @tc_20
  Scenario Outline: Verify newly created FD appears in Active FD list
    When user creates FD with account type "<accountType>", amount "<amount>", and tenure "<tenure>"
    Then FD should appear in active list with amount "<expectedAmount>"

    Examples:
      | accountType | amount | tenure                           | expectedAmount |
      | any         | 5000  | 24 months (8.5% p.a.) - 2 years | 5000          |

  @tc_21
  Scenario Outline: Verify FD card deposit amount display
    When user creates FD with account type "<accountType>", amount "<amount>", and tenure "<tenure>"
    Then FD card amount should be "<expectedAmount>"

    Examples:
      | accountType | amount | tenure                           | expectedAmount |
      | any         | 2000   | 24 months (8.5% p.a.) - 2 years  | 2000      |


  @tc_22
  Scenario Outline: Verify FD status after creation
    When user creates FD with account type "<accountType>", amount "<amount>", and tenure "<tenure>"
    Then FD status should be "<expectedStatus>"

    Examples:
      | accountType | amount | tenure                           | expectedStatus |
      | current     | 5000  | 24 months (8.5% p.a.) - 2 years | active         |

  @tc_23
  Scenario Outline: Verify FD remaining days display
    When user creates FD with account type "<accountType>", amount "<amount>", and tenure "<tenure>"
    Then remaining days should be "<expectedResult>"

    Examples:
      | accountType | amount | tenure                           | expectedResult |
      | current     | 5000  | 24 months (8.5% p.a.) - 2 years | displayed      |

  @tc_24
  Scenario Outline: Verify Active FD count update after FD creation
    When user stores current active FD count
    And user creates FD with account type "<accountType>", amount "<amount>", and tenure "<tenure>"
    Then active FD count should be "<expectedResult>"

    Examples:
      | accountType | amount | tenure                           | expectedResult |
      | current     | 5000  | 24 months (8.5% p.a.) - 2 years | incremented    |