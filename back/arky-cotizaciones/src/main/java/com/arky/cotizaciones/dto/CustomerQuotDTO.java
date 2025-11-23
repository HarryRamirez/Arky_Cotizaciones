package com.arky.cotizaciones.dto;

import java.util.List;

public class CustomerQuotDTO {

	private Integer customerId;
	private String name;
	private String email;
	private String phone;
	private String addres;
	private String rut;
	private String rubro;
	private List<QuotationDTO> quotations;


	public CustomerQuotDTO(Integer customerId, String name, String email, String phone, String addres, String rut,
			String rubro, List<QuotationDTO> quotations) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.addres = addres;
		this.rut = rut;
		this.rubro = rubro;
		this.quotations = quotations;
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


	public String getAddres() {
		return addres;
	}


	public void setAddres(String addres) {
		this.addres = addres;
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


	public List<QuotationDTO> getQuotations() {
		return quotations;
	}


	public void setQuotations(List<QuotationDTO> quotations) {
		this.quotations = quotations;
	}




}
