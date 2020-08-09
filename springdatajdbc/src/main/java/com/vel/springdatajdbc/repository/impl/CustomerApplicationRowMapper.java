package com.vel.springdatajdbc.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.vel.springdatajdbc.entities.Applications;

public class CustomerApplicationRowMapper implements RowMapper<Applications> {

	@Override
	public Applications mapRow(ResultSet rs, int rowNum) throws SQLException {
		Applications application = new Applications();	 
		application.setApplication_id(rs.getString("app_id"));
 		application.setApplication_name(rs.getString("app_name")); 
        return application;
	}

}
