package com.globant.challenge.rest;

import java.io.Serializable;

public class UserV1 implements Serializable {

	private static final long serialVersionUID = 3001560109834200355L;

	private String username;

	private String profession;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

}
