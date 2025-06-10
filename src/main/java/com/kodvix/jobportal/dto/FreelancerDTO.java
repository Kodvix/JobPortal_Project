package com.kodvix.jobportal.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class FreelancerDTO {

	private Long id;

	@NotBlank(message = "Username cannot be blank")
	private String userName;

	@NotBlank(message = "First name cannot be blank")
	private String firstName;

	@NotBlank(message = "Last name cannot be blank")
	private String lastName;

	@NotBlank(message = "Password cannot be blank")
	private String password;

	public FreelancerDTO() {
		super();
	}

	public FreelancerDTO(Long id, String userName, String firstName, String lastName, String password) {
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
