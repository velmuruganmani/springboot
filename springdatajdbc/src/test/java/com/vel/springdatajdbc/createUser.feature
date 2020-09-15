Feature: Create User
 Background:
    * url 'http://localhost:9090/springdatajdbc/customers'
    * header Accept = 'application/json'
 Scenario: Add users
    Given path '/post/addUser'
    And request {"cus_id": "", "cus_login": "example123", "cus_first_name": "sample", "cus_last_name": "samplegout", "cus_telephone": "123456", "cus_email": "tests1@gmail.com", "cus_status": "0", "cus_iso_code": "EUR", "cus_fax": "12345", "cus_language": "ENG", "cus_serial_no": "0","applicationAccessDetails": [{"app_module_name": "Global Profile","app_access_name": "Admin", "app_name": "Customers Application 1"},{"app_module_name": "Global Profile","app_access_name": "Reader", "app_name": "Customers Application 2"},{"app_module_name": "Global Profile","app_access_name": "Admin","app_name": "Customers Application 3"}]} 
    When method POST
    Then status 201   

Scenario: Edit users
    Given path '/post/editUser'
    And request {"cus_id": "127","cus_login": "brind", "cus_first_name": "Brind", "cus_last_name": "GB","cus_telephone": "9677443203","cus_email": "gout@gmail.com","cus_status": "0",us_iso_code": "EUR","cus_fax": "12345",  "cus_language": "ENG",  "cus_serial_no": "0",  "applicationAccessDetails": [{"app_module_name": "Global Profile","app_access_name": "Admin", "app_name": "Customers Application 1"},{"app_module_name": "Global Profile","app_access_name": "Reader","app_name": "Customers Application 2"},{"app_module_name": "Global Profile", "app_access_name": "Admin","app_name": "Customers Application 3"}]} 
    When method POST
    Then status 200 
        