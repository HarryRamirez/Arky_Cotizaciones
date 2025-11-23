package com.arky.cotizaciones.dto;



public class RequestProductDTO {

	private String name;
	private Double net;
	private String description;
	private Integer categoryId;

	public RequestProductDTO(String name, Double net, String description, Integer categoryId) {
		super();
		this.name = name;
		this.net = net;
		this.description = description;
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getNet() {
		return net;
	}

	public void setNet(Double net) {
		this.net = net;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}




}
