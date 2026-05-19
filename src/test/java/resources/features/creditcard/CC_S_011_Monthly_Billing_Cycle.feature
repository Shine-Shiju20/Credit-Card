@CreditCardAPI
Feature: CC-S-011 Monthly Billing Cycle
  Requirement: CC-REQ-011
  Description: Verify interest accrual, minimum due calculation, and due date advancement

  # SKIPPED: CC_TC_061 — monthly billing interest + minimum due
  # Cat 4 arch blocker: billingCycle.processMonthlyBilling is a cron job
  # No REST endpoint exists to trigger it; DB updates not assertable via API
  # @CC_TC_061

  # SKIPPED: CC_TC_062 — zero balance card skipped by billing job
  # Cat 4 arch blocker: same cron job; no HTTP trigger available
  # @CC_TC_062

  # SKIPPED: CC_TC_063 — blocked card still accrues interest
  # Cat 4 arch blocker: same cron job; requires job-level harness
  # @CC_TC_063

  # SKIPPED: CC_TC_064 — unauthorized user cannot trigger billing
  # Cat 4 arch blocker: no REST route exists; auth test not applicable
  # @CC_TC_064
