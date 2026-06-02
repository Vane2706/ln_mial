Feature: Register API and Controller Workflow

  Scenario: Successful user registration
    Given the register application is up
    When I submit the registration form with name "Juan", email "juan_cucumber@gmail.com", password "123456" and cellphone "987654321"
    Then the response should redirect to "/login"

  Scenario: Fail registration when email already exists
    Given the register application is up
    # Primero lo registramos una vez en este escenario
    When I submit the registration form with name "Juan", email "juan_cucumber@gmail.com", password "123456" and cellphone "987654321"
    # Intentamos registrarlo de nuevo inmediatamente para forzar el error de duplicidad
    And I submit the registration form with name "Juan Duplicate", email "juan_cucumber@gmail.com", password "123456" and cellphone "987654321"
    Then the response should redirect to "/register"