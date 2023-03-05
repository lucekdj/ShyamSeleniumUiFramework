Feature: Transfer Fund feature

  @Smoke
  Scenario: Verify user is able to login with valid credentials
    Given user enters valid "valid.username" and "valid.password"
    When user clicks on Sign In button

    Then verify user is successfully logged in to the account
    When user clicks on transfer between accounts

    Then verify user is on Internal Transfer page
    When user select from account "transfer.from.account"
    And user select to account "transfer.to.account"
    And user enter amount "transfer.amount"
    And clicks on submit button

    Then verify user is on View Accounts Page
    And verify transaction history displayed for the new transaction
    And verify transaction details getting updated into the database

