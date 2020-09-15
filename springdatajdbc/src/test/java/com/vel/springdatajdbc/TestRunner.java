package com.vel.springdatajdbc;

import com.intuit.karate.junit5.Karate;

public class TestRunner {
	
	 
	 @Karate.Test 
	 Karate testCreateUser() { 
		 return Karate.run("createUser").relativeTo(getClass()); }
	 }
