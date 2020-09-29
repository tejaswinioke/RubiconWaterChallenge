
@products
Feature: Validate product catalog
  I want to validate product features

  @flumegate @specification
  Scenario: Validate FlumeGate Specifications
    Given I am on the home page    
    When I go to sub menu "FlumeGate" in menu "Control Gates & Valves"    
    And I open "Specifications" tab
    Then I should be able to find "Solar panel" details

 @download @BladeMeter
  Scenario: Validate download brochure 
    Given I am on the home page    
    When I go to sub menu "BladeMeter" in menu "Flow Meters"    
    And I click on "Download brochure" link on sidebar
    Then I should be able to see PDF file in browser


