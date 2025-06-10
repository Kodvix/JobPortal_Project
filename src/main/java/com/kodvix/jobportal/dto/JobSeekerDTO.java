package com.kodvix.jobportal.dto;

import javax.validation.constraints.NotEmpty;

public class JobSeekerDTO {

    @NotEmpty(message = "userName cannot be empty")
    private String userName;
    @NotEmpty(message = "firstName cannot be empty")
    private String firstName;
    @NotEmpty(message = "lastName cannot be empty")
    private String lastName;
    @NotEmpty(message = "password cannot be empty")
    private String password;
    @NotEmpty(message = "resumePath cannot be empty")
    private String resumePath;

    public JobSeekerDTO() {
    }

    public JobSeekerDTO(String userName, String firstName, String lastName, String password) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public @NotEmpty(message = "userName cannot be empty") String getUserName() {
        return userName;
    }

    public void setUserName(@NotEmpty(message = "userName cannot be empty") String userName) {
        this.userName = userName;
    }

    public @NotEmpty(message = "firstName cannot be empty") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotEmpty(message = "firstName cannot be empty") String firstName) {
        this.firstName = firstName;
    }

    public @NotEmpty(message = "lastName cannot be empty") String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getResumePath() {
        return resumePath;
    }

    public void setResumePath(String resumePath) {
        this.resumePath = resumePath;
    }
}
