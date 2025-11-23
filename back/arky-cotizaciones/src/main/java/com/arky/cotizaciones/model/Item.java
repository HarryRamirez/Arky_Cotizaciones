package com.arky.cotizaciones.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "itemcotizacion")
public class Item {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_item_cotizacion")
	private Integer itemId;

    @Column(name = "id_cotizacion")
    private Integer quotationId;

	@Column(name = "cantidad")
	private Integer quantity;

	@Column(name = "valor_unitario", columnDefinition = "DECIMAL(10, 2)")
	private Double unitValue;

	@Column(name = "valor_total", columnDefinition = "DECIMAL(10, 2)")
	private Double totalValue;



	@ManyToOne
	@JoinColumn(name = "id_servicio")
	private Product product;





	public Integer getItemId() {
		return itemId;
	}


	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}





	public Integer getQuotationId() {
		return quotationId;
	}


	public void setQuotationId(Integer quotationId) {
		this.quotationId = quotationId;
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


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


}
