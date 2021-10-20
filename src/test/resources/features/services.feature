@service
  Feature: Best Buy Services

@smoke
   Scenario: user is able to see Services
     When user sends a GET request to see services

   Scenario: user able to skip services
     When after user skipped 6 times, service id should be started from 7

@smoke @regression
   Scenario: CRUD positive scenario of Services
     When user creates a new service called "Best Buy Germany"
     And user updates a service with createdNewID and a new name "Best Buy Italy"
     And user is able to delete a service updated
     Then user is able to see success message as "Best Buy Italy"

@regression
   Scenario: user is NOT able to see service with invalid ID - Negative Scenario
     When user sends an invalid service ID as "x8"
     Then user is able to see error message as "No record found for id 'x8'"

   Scenario: user is able to get service info with ID
     When user sends a GET request with 5
     Then user is able to see requested service 5







