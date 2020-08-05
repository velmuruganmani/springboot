package com.vel.springdatajdbc.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import com.vel.springdatajdbc.entities.Access;
import com.vel.springdatajdbc.entities.Applications;
import com.vel.springdatajdbc.entities.Customers;
import com.vel.springdatajdbc.entities.GetAllCustomersRequest;
import com.vel.springdatajdbc.repository.CustomersRepository;


@Repository
public class CustomersRepositoryImpl extends JdbcDaoSupport implements CustomersRepository {
	
	@Autowired 
	DataSource dataSource;
	
	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}
	
	@Autowired 
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Customers> getCustomersById(String loginId) {
		// TODO Auto-generated method stub
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
				 			customer.setCus_login(loginId);
				 			customer.setCus_id(rs.getString("cus_id"));
				 			customer.setCus_first_name(rs.getString("cus_first_name"));
				 			customer.setCus_last_name(rs.getString("cus_last_name"));
				 		}			 			
				 		application = new Applications();
				 		application.setApp_id(rs.getString("app_id"));
				 		application.setApp_name(rs.getString("app_name"));				 		
				 		access = new Access();
				 		profileList = new ArrayList<Access>();
				 		access.setAccess_id(rs.getString("access_id"));
				 		access.setAccess_name(rs.getString("access_name"));
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
		// TODO Auto-generated method stub
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
		
		if(request.getCus_first_name()!=null || request.getCus_last_name()!=null || 
				request.getCus_app_name()!=null || request.getCus_app_access()!=null) {
			
			sql = sql+"and ((u.cus_first_name like\n"
					+"('"+(request.getCus_first_name()!=null?request.getCus_first_name().toLowerCase()+"%":"")+"')\n"
					+ "or u.cus_last_name like\n"
					+"('"+(request.getCus_last_name()!=null?request.getCus_last_name().toLowerCase()+"%":"")+"'))\n"
					+ "or a.app_name='"+request.getCus_app_name()+"'\n"
					+ "or p.access_name='"+request.getCus_app_access()+"')\n"
					+ "order by u.cus_id";
			
		}
		
		 	jdbcTemplate.query(sql, new ResultSetExtractor<List<Customers>>(){

			@Override
			public List<Customers> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				
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
						customer.setCus_login(rs.getString("cus_login"));
						customer.setCus_id(rs.getString("cus_id"));
			 			customer.setCus_first_name(rs.getString("cus_first_name"));
			 			customer.setCus_last_name(rs.getString("cus_last_name"));
					}
					application = new Applications();
					application.setApp_id(rs.getString("app_id"));
			 		application.setApp_name(rs.getString("app_name"));
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

}
