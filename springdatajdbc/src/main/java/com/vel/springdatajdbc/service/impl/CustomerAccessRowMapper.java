package com.vel.springdatajdbc.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.vel.springdatajdbc.entities.Access;

public class CustomerAccessRowMapper implements RowMapper<Access>  {

	@Override
	public Access mapRow(ResultSet rs, int rowNum) throws SQLException {
		Access access = new Access();	 
		access.setApp_access_id(rs.getString("access_id"));
		access.setApp_access_name(rs.getString("access_name")); 
        return access;
	}

}
