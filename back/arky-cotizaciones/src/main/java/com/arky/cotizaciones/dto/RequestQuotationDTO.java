package com.arky.cotizaciones.dto;

import java.time.LocalDate;
import java.util.List;

public class RequestQuotationDTO {

	private Integer userId;
	private Integer customerId;
	private Integer discountId;
	private String remitter;
	private LocalDate dateQuotation;
	private LocalDate dateEvent;
	private String reference;
	private Double net;
	private Double iva;
	private Double total;
	private List<RequestItemDTO> items;

	public RequestQuotationDTO(Integer userId, Integer customerId, Integer discountId, String remitter,
			LocalDate dateQuotation, LocalDate dateEvent, String reference,  Double net, Double iva,
			Double total, List<RequestItemDTO> items) {
		super();
		this.userId = userId;
		this.customerId = customerId;
		this.discountId = discountId;
		this.remitter = remitter;
		this.dateQuotation = dateQuotation;
		this.dateEvent = dateEvent;
		this.reference = reference;
		this.net = net;
		this.iva = iva;
		this.total = total;
		this.items = items;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getDiscountId() {
		return discountId;
	}

	public void setDiscountId(Integer discountId) {
		this.discountId = discountId;
	}

	public String getRemitter() {
		return remitter;
	}

	public void setRemitter(String remitter) {
		this.remitter = remitter;
	}

	public LocalDate getDateQuotation() {
		return dateQuotation;
	}

	public void setDateQuotation(LocalDate dateQuotation) {
		this.dateQuotation = dateQuotation;
	}

	public LocalDate getDateEvent() {
		return dateEvent;
	}

	public void setDateEvent(LocalDate dateEvent) {
		this.dateEvent = dateEvent;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}



	public Double getNet() {
		return net;
	}

	public void setNet(Double net) {
		this.net = net;
	}

	public Double getIva() {
		return iva;
	}

	public void setIva(Double iva) {
		this.iva = iva;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public List<RequestItemDTO> getItems() {
		return items;
	}

	public void setItems(List<RequestItemDTO> items) {
		this.items = items;
	}



}
