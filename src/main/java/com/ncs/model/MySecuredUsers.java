package com.ncs.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
public class MySecuredUsers implements Serializable, Comparable<MySecuredUsers> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Size(min = 5)
	@NotNull
	private String username;
	@Size(min = 5)
	@NotNull
	private String password;
	@Min(message = "Minimum age is 18", value = 18)
	private int age;
	@Column(length = 8)
	private long pincode;
	@NotNull
	private String role;

	public MySecuredUsers() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MySecuredUsers(int id, @Size(min = 5) @NotNull String username, @Size(min = 5) @NotNull String password,
			int age, long pincode, @NotNull String role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.age = age;
		this.pincode = pincode;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public long getPincode() {
		return pincode;
	}

	public void setPincode(long pincode) {
		this.pincode = pincode;
	}

	public String getRole() {
		return role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, id, password, pincode, role, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MySecuredUsers other = (MySecuredUsers) obj;
		return age == other.age && id == other.id && Objects.equals(password, other.password)
				&& pincode == other.pincode && Objects.equals(role, other.role)
				&& Objects.equals(username, other.username);
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int compareTo(MySecuredUsers o) {
		return this.id - o.id;
	}

}
