package com.arky.cotizaciones.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "descuento")
public class Discount {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_descuento")
	private Integer discountId;

	@Column(name = "porcentaje")
	private Integer percentage;




	public Integer getDiscountId() {
		return discountId;
	}

	public void setDiscountId(Integer discountId) {
		this.discountId = discountId;
	}

	public Integer getPercentage() {
		return percentage;
	}

	public void setPercentage(Integer percentage) {
		this.percentage = percentage;
	}


}
