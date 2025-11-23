package com.arky.cotizaciones.dto;



public class UpdateItemDTO {


    private Integer itemId;
    private Integer productId;
    private Integer quantity;
    private Double unitValue;
    private Double totalValue;



	public UpdateItemDTO(Integer itemId, Integer productId, Integer quantity, Double unitValue, Double totalValue) {
		super();
		this.itemId = itemId;
		this.productId = productId;
		this.quantity = quantity;
		this.unitValue = unitValue;
		this.totalValue = totalValue;
	}



	public Integer getItemId() {
		return itemId;
	}



	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}



	public Integer getProductId() {
		return productId;
	}



	public void setProductId(Integer productId) {
		this.productId = productId;
	}



	public Integer getQuantity() {
		return quantity;
	}



	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}



	public Double getUnitValue() {
		return unitValue;
	}



	public void setUnitValue(Double unitValue) {
		this.unitValue = unitValue;
	}



	public Double getTotalValue() {
		return totalValue;
	}



	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}




}
