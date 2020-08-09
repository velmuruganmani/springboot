package com.vel.springdatajdbc.repository.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import com.vel.springdatajdbc.entities.Access;
import com.vel.springdatajdbc.entities.AddCustomersRequest;
import com.vel.springdatajdbc.entities.Applications;
import com.vel.springdatajdbc.entities.ApplicationsConfig;
import com.vel.springdatajdbc.entities.Customers;
import com.vel.springdatajdbc.entities.GetAllCustomersRequest;
import com.vel.springdatajdbc.entities.GetAllCustomersResponse;
import com.vel.springdatajdbc.repository.CustomersRepository;
import com.vel.springdatajdbc.service.impl.ApplicationsConfigAccessRowMapper;
import com.vel.springdatajdbc.service.impl.CustomerAccessRowMapper;
import com.vel.springdatajdbc.service.impl.CustomerApplicationRowMapper;
import com.vel.springdatajdbc.service.impl.CustomerResultSetExtractor;


@Repository
public class CustomersRepositoryImpl extends JdbcDaoSupport implements CustomersRepository{
	
	@Autowired 
	DataSource dataSource;
	
	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}
	
	@Autowired 
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired 
	JdbcTemplate jdbcTemplate;
	
	public static Customers customer;
	
	@Override
	public List<Customers> getCustomersById(String loginId) {

		String sql = "select \n" + 
				"u.cus_id, u.cus_first_name, u.cus_last_name,\n" + 
				"p.access_id, p.access_name,\n" + 
				"a.app_id, a.app_name\n" + 
				"from \n" + 
				"springdatajdbc.customers u, \n" + 
				"springdatajdbc.customers_access up, \n" + 
				"springdatajdbc.applications_config f,\n" + 
				"springdatajdbc.applications a, \n" + 
				"springdatajdbc.access p \n" + 
				"where\n" + 
				"up.cus_id = u.cus_id\n" + 
				"and up.access_id=p.access_id\n" + 
				"and up.appconfig_id=f.app_id\n" + 
				"and up.app_id=f.app_config_id\n" + 
				"and f.app_id=a.app_id\n" + 
				"and u.cus_status=0\n" + 
				"and u.cus_login='"+loginId+"'";
		
		      List <Customers> customers = jdbcTemplate.query(sql, new ResultSetExtractor<List<Customers>>(){
		         
		         public List<Customers> extractData(ResultSet rs) throws SQLException, DataAccessException {
		            
		        	 
		        	List<Customers> list = new ArrayList<Customers>();
		        	Customers customer = new Customers();
		        	Applications application = new Applications();
		        	List<Applications> appsList = new ArrayList<Applications>();
		        	Access access = new Access();
		        	List<Access> profileList = new ArrayList<Access>();
		        	int i=0;
				 	while(rs.next()){
				 		if(i==0) {		 		
				 			customer.setCustomer_login(loginId);
				 			customer.setCustomer_id(rs.getString("cus_id"));
				 			customer.setCustomer_first_name(rs.getString("cus_first_name"));
				 			customer.setCustomer_last_name(rs.getString("cus_last_name"));
				 		}			 			
				 		application = new Applications();
				 		application.setApplication_id(rs.getString("app_id"));
				 		application.setApplication_name(rs.getString("app_name"));				 		
				 		access = new Access();
				 		profileList = new ArrayList<Access>();
				 		access.setApp_access_id(rs.getString("access_id"));
				 		access.setApp_access_name(rs.getString("access_name"));
				 		profileList.add(access);				 		
				 		application.setAccessDetails(profileList);
				 		
				 		appsList.add(application);				 		
		            	i++;
		            }
				 	customer.setApplicationsDetails(appsList);				 		
			 		list.add(customer);			 	
		            return list;  
				 	}   	  
		      });
		      return customers;
	}

	@Override
	public List<Customers> getAllCustomers(GetAllCustomersRequest request) {

		List<Customers> customersList = new ArrayList<Customers>();
		String sql = "select \n" + 
				"u.cus_id, u.cus_login, u.cus_first_name, u.cus_last_name,\n" + 
				"p.access_id, p.access_name,\n" + 
				"a.app_id, a.app_name\n" + 
				"from \n" + 
				"springdatajdbc.customers u, \n" + 
				"springdatajdbc.customers_access up, \n" + 
				"springdatajdbc.applications_config f,\n" + 
				"springdatajdbc.applications a, \n" + 
				"springdatajdbc.access p \n" + 
				"where\n" + 
				"up.cus_id = u.cus_id\n" + 
				"and up.access_id=p.access_id\n" + 
				"and up.appconfig_id=f.app_id\n" + 
				"and up.app_id=f.app_config_id\n" + 
				"and f.app_id=a.app_id\n" + 
				"and u.cus_status=0\n";
		
		if((request.getCus_name()!=null || request.getCus_app_name()!=null || request.getCus_app_access()!=null)&
				(!request.getCus_app_access().equalsIgnoreCase("all"))) {
			
			sql = sql+"and ((u.cus_first_name like\n"
					+"('"+(request.getCus_name()!=null?request.getCus_name().toLowerCase()+"%":"")+"')\n"
					+ "or u.cus_last_name like\n"
					+"('"+(request.getCus_name()!=null?request.getCus_name().toLowerCase()+"%":"")+"'))\n"
					+ "or a.app_name='"+request.getCus_app_name()+"'\n"
					+ "or p.access_name='"+request.getCus_app_access()+"')\n"
					+ "order by u.cus_id";
			
		}
		
		 	jdbcTemplate.query(sql, new ResultSetExtractor<List<Customers>>(){

			@Override
			public List<Customers> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				Customers customer = new Customers();
				Applications application = new Applications();
				List<Applications> appsList = new ArrayList<Applications>();
				int cus_id=0;
				while(rs.next()){
					if(rs.getInt("cus_id") != cus_id) {
						if(cus_id != 0) {
							customer.setApplicationsDetails(appsList);
							customersList.add(customer);
							appsList = new ArrayList<Applications>();
						}
						customer = new Customers();
						customer.setCustomer_login(rs.getString("cus_login"));
						customer.setCustomer_id(rs.getString("cus_id"));
			 			customer.setCustomer_first_name(rs.getString("cus_first_name"));
			 			customer.setCustomer_last_name(rs.getString("cus_last_name"));
					}
					application = new Applications();
					application.setApplication_id(rs.getString("app_id"));
			 		application.setApplication_name(rs.getString("app_name"));
			 		appsList.add(application);
			 		
			 		cus_id = rs.getInt("cus_id");
					
				}
				customer.setApplicationsDetails(appsList);				 		
				customersList.add(customer);			 	
	            return customersList;
			}
			 
		 });
		
		return customersList;
	}

	@Override
	public GetAllCustomersResponse addCustomers(AddCustomersRequest addCustomersRequest) {

		GetAllCustomersResponse response = new GetAllCustomersResponse();
		int noOfCustomerRowsAffected = 0;
		int noOfApplicationRowsAffected = 0;
		customer = new Customers();
			
		//Check for duplicate loginId
		try {
			String sql = "select \n" + 
				"* \n" + 
				"from \n" + 
				"springdatajdbc.customers u \n" + 
				"where \n" + 
				"u.cus_login='"+addCustomersRequest.getCus_login()+"'";
			
			List <Customers> customerList = jdbcTemplate.query(sql, new CustomerResultSetExtractor());
			
			if(customerList.size()>0) {
				customer = customerList.get(0);
				response.setErrorMessage("Customer Login ID is already exits");
			}else {	
				String cussql = "select max(u.cus_id) \n" +
						"from \n" + 
						"springdatajdbc.customers u";
				
				int cusid = jdbcTemplate.queryForObject(cussql, Integer.class);
				cusid = cusid+1;
				
				List<Applications> applications = new ArrayList<Applications>();
				List<Applications> applicationList = new ArrayList<Applications>();
				List<Access> accessList = new ArrayList<Access>();
				List<ApplicationsConfig> applicationsConfigList = new ArrayList<ApplicationsConfig>();
				List<ApplicationsConfig> applicationsConfig = new ArrayList<ApplicationsConfig>();
				
				for (int i = 0; i < addCustomersRequest.getApplicationAccessDetails().size(); i++) 
	             {
					String cus_app_name = addCustomersRequest.getApplicationAccessDetails().get(i).getApp_name();
					String cus_app_access_name = addCustomersRequest.getApplicationAccessDetails().get(i).getApp_access_name();
					String appsql = "select \n" + 
							"* \n" + 
							"from \n" + 
							"springdatajdbc.applications a \n" + 
							"where \n" + 
							"a.app_name='"+cus_app_name+"'";
					applicationList  = jdbcTemplate.query(appsql, new CustomerApplicationRowMapper());
					
					String accesssql = "select \n" + 
							"* \n" + 
							"from \n" + 
							"springdatajdbc.access ac \n" + 
							"where \n" + 
							"ac.access_name='"+cus_app_access_name+"'";
					accessList  = jdbcTemplate.query(accesssql, new CustomerAccessRowMapper());
										
					if(i<=addCustomersRequest.getApplicationAccessDetails().size()) {
						applicationList.get(0).setAccessDetails(accessList);
						applications.add(applicationList.get(0));
	
						String appconfigsql = "select \n" + 
								"* \n" + 
								"from \n" + 
								"springdatajdbc.applications_config c \n" + 
								"where \n" + 
								"c.app_id='"+applicationList.get(0).getApplication_id()+"'"; 					
						applicationsConfigList = jdbcTemplate.query(appconfigsql, new ApplicationsConfigAccessRowMapper());
						applicationsConfig.add(applicationsConfigList.get(0));
					}
									
	             }
				customer.setApplicationsDetails(applications);
				
				String customersql="insert into springdatajdbc.customers values (:id,:login,:firstname,:lastname,"
						+ ":telephone,:email,:status,:isocode,:fax,:language,:serialno)";
				Map<String,Object> customerMap=new HashMap<String,Object>();  
				customerMap.put("id",cusid);  
				customerMap.put("login",addCustomersRequest.getCus_login());  
				customerMap.put("firstname",addCustomersRequest.getCus_first_name());
				customerMap.put("lastname",addCustomersRequest.getCus_last_name());
				customerMap.put("telephone",addCustomersRequest.getCus_telephone());
				customerMap.put("email",addCustomersRequest.getCus_email());
				customerMap.put("status",addCustomersRequest.getCus_status());
				customerMap.put("isocode",addCustomersRequest.getCus_iso_code());
				customerMap.put("fax",addCustomersRequest.getCus_fax());
				customerMap.put("language",addCustomersRequest.getCus_language());
				customerMap.put("serialno",addCustomersRequest.getCus_serial_no());
				noOfCustomerRowsAffected = namedParameterJdbcTemplate.update(customersql, customerMap);			
				
				if(noOfCustomerRowsAffected>0) {
					for (int i = 0; i < applications.size(); i++) 
		            {
						String applicationsql="insert into springdatajdbc.customers_access values "
								+ "(:id,:appconfigid,:appid,:accessid)";
						Map<String,Object> accessMap=new HashMap<String,Object>(); 
						accessMap.put("id",addCustomersRequest.getCus_id());  
						if(applicationsConfig.get(i).getApplication_id().equals(applications.get(i).getApplication_id())) {
						accessMap.put("appconfigid",applicationsConfig.get(i).getApplication_config_id());
						}
						accessMap.put("appid", applications.get(i).getApplication_id());
						accessMap.put("accessid",applications.get(i).getAccessDetails().get(0).getApp_access_id());	            
						noOfApplicationRowsAffected = namedParameterJdbcTemplate.update(applicationsql, accessMap);
		            
						if(noOfApplicationRowsAffected>0) {
							customer.setCustomer_first_name(addCustomersRequest.getCus_first_name());
							customer.setCustomer_login(addCustomersRequest.getCus_last_name());
							customer.setCustomer_email(addCustomersRequest.getCus_email());
							customer.setCustomer_fax(addCustomersRequest.getCus_fax());
							customer.setCustomer_id(String.valueOf(cusid));
							customer.setCustomer_iso_code(addCustomersRequest.getCus_iso_code());
							customer.setCustomer_language(addCustomersRequest.getCus_language());
							customer.setCustomer_last_name(addCustomersRequest.getCus_last_name());
							customer.setCustomer_serial_no(addCustomersRequest.getCus_serial_no());
							customer.setCustomer_status(addCustomersRequest.getCus_status());
							customer.setCustomer_telephone(addCustomersRequest.getCus_telephone());
							response.setCustomers(customer);
							response.setSuccessMessage("Customer Login ID created successfully");
						}else {
							response.setCustomers(customer);
							response.setErrorMessage("Customer Login ID created but application access is not created");
						}
		            }
				}else {
					response.setCustomers(customer);
					response.setErrorMessage("Customer Login ID is not created");
				}
								
				return response;
			
			}
		
		}catch(Exception e) {
			response.setErrorMessage(e.toString());
		}
		
		response.setCustomers(customer);
		return response;
	}

}
