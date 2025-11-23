package com.arky.cotizaciones.dto;

public class LoginResponseDTO {

	private String token;
	private Integer userId;
    private String email;
	private String firstname;
	private String lastname;


    public LoginResponseDTO(String token, Integer userId, String email, String firstname, String lastname) {
        this.token = token;
        this.userId = userId;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;

    }

    // Getters y Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }



    public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
