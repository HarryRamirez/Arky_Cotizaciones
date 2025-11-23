package com.arky.cotizaciones.dto;

public class ItemDTO {

	private String product;
	private String description;
	private Integer quantity;
	private Double valueUnit;
	private Double valueTotal;

    public ItemDTO() {
    }

	public ItemDTO( String product, String description, Integer quantity, Double valueUnit, Double valueTotal) {
		super();
		this.product = product;
		this.description = description;
		this.quantity = quantity;
		this.valueUnit = valueUnit;
		this.valueTotal = valueTotal;
	}



	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getValueUnit() {
		return valueUnit;
	}

	public void setValueUnit(Double valueUnit) {
		this.valueUnit = valueUnit;
	}

	public Double getValueTotal() {
		return valueTotal;
	}

	public void setValueTotal(Double valueTotal) {
		this.valueTotal = valueTotal;
	}




}
