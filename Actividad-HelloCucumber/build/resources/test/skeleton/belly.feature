# Escenarios de acuerdo a la tabla
    # Caso 1
    # cukes > 10 && time >= 2
    # Caso 2
    # cukes > 10 && time < 2
    # Caso 3
    # cukes <= 10 && time >= 2
    # Caso 4
    # cukes <= 10 && time < 2
    
Feature: BellyFeature
    
    Scenario: eaten many cukes and growl
        Given I have eaten 11 cukes
        When I wait 2 hour
        Then My belly should growl

    Scenario: eaten many cukes and not growl
        Given I have eaten 11 cukes
        When I wait 1 hour
        Then My belly should not growl

    Scenario: eaten few cukes and not growl
        Given I have eaten 10 cukes
        When I wait 2 hour
        Then My belly should not growl

    Scenario: eaten few cukes and not growl
        Given I have eaten 10 cukes
        When I wait 1 hour
        Then My belly should not growl