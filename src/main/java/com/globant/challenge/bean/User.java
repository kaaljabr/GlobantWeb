package com.globant.challenge.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
@NamedQueries({ @NamedQuery(name = "Users.findAll", query = "SELECT u FROM User u"), @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
		@NamedQuery(name = "Users.findByUsernameAndPassword", query = "SELECT u FROM User u WHERE u.username = :username and u.password = :password"),
		@NamedQuery(name = "Users.findUsersByProfession", query = "SELECT u FROM User u WHERE u.profession = :profession ORDER BY u.state asc") })
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class User implements Serializable {

	private static final long serialVersionUID = 2005866398776663100L;

	@Id
	@GeneratedValue
	private Integer id;
	@Column(name = "username")
	@Size(min = 4, max = 12, message = "username.min.max")
	private String username;
	@Column(name = "password")
	@Size(min = 6, message = "password.min.max")
	@JsonIgnore
	private String password;
	@Column(name = "state")
	@Size(min = 1, message = "state.required")
	private String state;
	@Column(name = "profession")
	@Size(min = 1, message = "profession.required")
	private String profession;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

}
