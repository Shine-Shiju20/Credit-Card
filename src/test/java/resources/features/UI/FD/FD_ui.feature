Feature: Fixed Deposit functionality

  Background:
    Given user launches banking application
    And user logs in with valid credentials
    And user navigates to fixed deposit page

  @tc_25
  Scenario Outline: Verify modal closes automatically after successful FD creation
    When user clicks "<action>"
    And user selects account type "<accountType>"
    And user enters FD amount "<amount>"
    And user selects tenure option "<tenure>"
    And user clicks "<submitAction>"
    Then Create FD modal should be "<modalState>"

    Examples:
      | action        | accountType | amount | tenure                        | submitAction     | modalState |
      | Create New FD | current     | 1000   | 12 months (7% p.a.) - 1 year | Create FD Submit | closed     |

  @tc_26
  Scenario Outline: Verify Create FD form reset on modal reopen
    When user clicks "<firstAction>"
    And user enters FD amount "<amount>"
    And user selects tenure option "<tenure>"
    And user clicks "<secondAction>"
    And user clicks "<thirdAction>"
    Then Create FD form should be "<formState>"

    Examples:
      | firstAction   | amount | tenure                        | secondAction | thirdAction   | formState |
      | Create New FD | 2500  | 12 months (7% p.a.) - 1 year | Cancel       | Create New FD | reset     |

  @tc_27
  Scenario Outline: Verify FD page stability after browser refresh
    When user refreshes browser "<refreshCount>" times
    Then FD page should be "<pageState>"

    Examples:
      | refreshCount | pageState |
      | 3            | loaded    |

  @tc_28
  Scenario Outline: Verify account number masking in account dropdown
    When user clicks "<action>"
    Then account number masking should be "<maskingStatus>"

    Examples:
      | action        | maskingStatus |
      | Create New FD | masked        |

  @tc_29
  Scenario Outline: Verify currency formatting in investment summary
    When user clicks "<action>"
    And user enters valid FD details with amount "<amount>" and tenure "<tenure>"
    Then summary currency values should be in "<currencyFormat>" format

    Examples:
      | action        | amount | tenure                        | currencyFormat |
      | Create New FD | 5000  | 12 months (7% p.a.) - 1 year | INR            |

  @tc_30
  Scenario Outline: Verify currency formatting in FD cards
    When user creates FD with account type "<accountType>", amount "<amount>", and tenure "<tenure>"
    Then FD card currency values should be in "<currencyFormat>" format

    Examples:
      | accountType | amount | tenure                           | currencyFormat |
      | current     | 5000  | 24 months (8.5% p.a.) - 2 years | INR            |

  @tc_31
  Scenario Outline: Verify keyboard navigation in Create FD modal
    When user clicks "<action>"
    Then Create FD keyboard navigation should be "<navigationStatus>"

    Examples:
      | action        | navigationStatus |
      | Create New FD | working          |

  @tc_32
  Scenario Outline: Verify FD data persistence after logout/login
    When user creates FD with account type "<accountType>", amount "<amount>", and tenure "<tenure>"
    And user performs session action "<action1>"
    And user performs session action "<action2>"
    And user navigates to "<page>"
    Then FD persistence should be "<expectedResult>"

    Examples:
      | accountType | amount | tenure                           | action1 | action2 | page          | expectedResult |
      | current     | 5000   | 24 months (8.5% p.a.) - 2 years | logout  | login   | fixed deposit | displayed      |



