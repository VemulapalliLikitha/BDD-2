@account
Feature: Create new Account
Create new Account for the valid customers

Scenario: For valid customers create new account
Given customer details
When  Valid customer
And   valid opening balance
Then  create new Account

Scenario: For Invalid Customer
Given   Customer details
When    Invalid Customer
Then    throw 'Invalid Customer' error message

Scenario: For Invalid Opening Balance
Given  Customer details and opening balance
When   Invalid opening balance
Then throw 'Insufficient Balance' error message

Scenario: Find account details
find account details for the given account number
Given  Account Number
When   Valid account Number
Then   find account details


Scenario: Withdraw amount from Account
Find account details and withdraw amount
Given   Account number 1001 and amount 1000
When   Find account and do withdraw
Then   Update the account details.


Scenario: Deposit amount to Account
find account details and deposit amount
Given   Account Number 1001 and Amount 2000
When    Find account and deposit amount
Then    Update the account details after deposit.