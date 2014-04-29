package com.smartbear.sso;

import java.util.Set;

public class User {
	private String firstName;
	private String lastName;
	private String guid;
	private String email;
	private long expiresAfter = 0;
	private Set<String> organizations ;
	


	public User() {
		super();
	}
	
	

	
	public User(String guid, String firstName, String lastName, String email, long expires, Set<String> organizations) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.guid = guid;
		this.email=email;
		this.expiresAfter = System.currentTimeMillis() + (expires * 1000);
		this.organizations=organizations;
		
	}
	
	public Set<String> getOrganizations() {
		return organizations;
	}


	public void setOrganizations(Set<String> organizations) {
		this.organizations = organizations;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getExpiresAfter() {
		return expiresAfter;
	}
}
