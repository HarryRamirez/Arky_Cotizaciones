package com.arky.cotizaciones.dto;

public class ProductRequestDTO {

    private String name;
	private Double net;
	private String description;
	private Integer category;


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
    public Integer getCategory() {
        return category;
    }
    public void setCategory(Integer category) {
        this.category = category;
    }



    
}
