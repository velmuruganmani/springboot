Feature: Login
 Background:
    * url 'http://localhost:9090/springdatajdbc'
    * header Accept = 'application/json'
 
 Scenario: Edit Customers
   	Given path '/api/users/login'
    And request {"username":"velmani9","password":"myaxalmpassword"}
    When method POST
    Then status 200 