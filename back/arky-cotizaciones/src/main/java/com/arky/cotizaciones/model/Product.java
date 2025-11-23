package com.arky.cotizaciones.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "servicio")
@AllArgsConstructor @NoArgsConstructor
public class Product {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
	private Integer productId;

    @Column(name = "nombre")
	private String name;

    @Column(name = "neto", columnDefinition = "DECIMAL(10, 2)")
	private Double net;

    @Column(name = "descripcion")
	private String description;




    @ManyToOne
    @JoinColumn(name = "id_tipo_serv")
    private Category category;





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


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}




}
