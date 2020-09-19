Feature: Customers
 Background:
    * url 'http://localhost:9090/springdatajdbc/api/users/customers'
    * header Accept = 'application/json'
    * header Authorization = 'Bearer ' + res.jwtToken
 
 Scenario: Edit Customers
   	Given path '/post/editUser'
    And request {"cus_id": "109","cus_login": "velmani9","cus_first_name": "Vel Murugan","cus_last_name": "Mani","cus_telephone": "123456","cus_email": "velmani1@gmail.com","cus_status": "0","cus_iso_code": "EUR","cus_fax": "12345","cus_language": "ENG","cus_serial_no": "0","applicationAccessDetails": [{"app_module_name": "Global Profile","app_access_name": "Admin","app_name": "Customers Application 1"},{"app_module_name": "Global Profile","app_access_name": "Reader","app_name": "Customers Application 2"},{ "app_module_name": "Global Profile", "app_access_name": "Admin","app_name": "Customers Application 3"}]}
    When method POST
    Then status 200 