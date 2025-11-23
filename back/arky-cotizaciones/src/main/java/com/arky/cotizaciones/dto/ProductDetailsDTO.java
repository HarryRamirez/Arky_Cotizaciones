package com.arky.cotizaciones.dto;

public class ProductDetailsDTO {

    private Integer productId;
    private String name;
	private Double net;
	private String description;
	private CategoryResponseDTO category;

    
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
    public CategoryResponseDTO getCategory() {
        return category;
    }
    public void setCategory(CategoryResponseDTO category) {
        this.category = category;
    }

    

    
}
