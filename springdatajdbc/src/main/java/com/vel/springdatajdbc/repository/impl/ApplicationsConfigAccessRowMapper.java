package com.vel.springdatajdbc.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.vel.springdatajdbc.entities.ApplicationsConfig;

public class ApplicationsConfigAccessRowMapper implements RowMapper<ApplicationsConfig>  {

	@Override
	public ApplicationsConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
		ApplicationsConfig appconfig = new ApplicationsConfig();	 
		appconfig.setApplication_config_id(rs.getString("app_config_id"));
		appconfig.setApplication_config_name(rs.getString("app_config_name"));
		appconfig.setApplication_id(rs.getString("app_id")); 
        return appconfig;
	}

}
