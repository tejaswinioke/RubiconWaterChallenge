
@search
Feature: search feature
  I want to use verify search feature

  
  Scenario: verify search scenario
    Given I am on home page    
    When I search for "FarmConnect Software"
    And click on search result for "FarmConnect"  
    Then I should be able to see details of "Crop Monitoring"
   

  
