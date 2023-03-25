@smoke
Feature: Pet Store
  Scenario: Create a pet
    Given User has valid URL
    When User sends POST request to create a pet
    Then Statue code is 200
    And pet name, pet id, pet status are correct