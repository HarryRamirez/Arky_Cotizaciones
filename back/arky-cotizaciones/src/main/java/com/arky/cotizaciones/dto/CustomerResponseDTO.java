package com.arky.cotizaciones.dto;

public class CustomerResponseDTO {
	private Integer customerId;
	private String name;
	private String email;
	private String phone;
	private String address;
	private String rut;
	private String rubro;



	
	public CustomerResponseDTO() {
	}


	public CustomerResponseDTO(Integer customerId, String name, String email, String phone, String address, String rut,
			String rubro) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.rut = rut;
		this.rubro = rubro;
	}


	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getRubro() {
		return rubro;
	}

	public void setRubro(String rubro) {
		this.rubro = rubro;
	}


}
