Feature: Create User
 Background:
    * url 'http://localhost:9090/springdatajdbc/customers'
    * header Accept = 'application/json'
 Scenario: Add users
    Given path '/post/addUser'
    And request {"cus_id": "", "cus_login": "testguser", "cus_first_name": "test", "cus_last_name": "test'g", "cus_telephone": "123456", "cus_email": "tests1@gmail.com", "cus_status": "0", "cus_iso_code": "EUR", "cus_fax": "12345", "cus_language": "ENG", "cus_serial_no": "0","applicationAccessDetails": [{"app_module_name": "Global Profile","app_access_name": "Admin", "app_name": "Customers Application 1"},{"app_module_name": "Global Profile","app_access_name": "Reader", "app_name": "Customers Application 2"},{"app_module_name": "Global Profile","app_access_name": "Admin","app_name": "Customers Application 3"}]} 
    When method POST
    Then status 201   
    