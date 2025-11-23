package com.arky.cotizaciones.dto;

import java.util.List;

public class UpdateProductDTO {

	private Integer productId;
	private String name;
	private Double net;
	private String description;
	private List<CategoryResponseDTO> category;

	public UpdateProductDTO(Integer productId, String name, Double net, String description,
			List<CategoryResponseDTO> category) {
		super();
		this.productId = productId;
		this.name = name;
		this.net = net;
		this.description = description;
		this.category = category;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
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

	public List<CategoryResponseDTO> getCategory() {
		return category;
	}

	public void setCategory(List<CategoryResponseDTO> category) {
		this.category = category;
	}




}
