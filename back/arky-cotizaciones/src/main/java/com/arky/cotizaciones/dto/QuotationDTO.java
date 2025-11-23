package com.arky.cotizaciones.dto;

import java.time.LocalDate;
import java.util.List;

import com.arky.cotizaciones.model.State;



public class QuotationDTO {

	private Integer quotationId;
	private String user;
	private String customer;
	private Integer discount;
	private String remitter;
	private LocalDate dateQuotation;
	private LocalDate dateEvent;
	private String reference;
	private State state;
	private Double net;
	private Double iva;
	private Double total;
	private List<ItemDTO> items;

    public QuotationDTO() {
    }


	public QuotationDTO(Integer quotationId, String user, String customer, Integer discount, String remitter,
			LocalDate dateQuotation, LocalDate dateEvent, String reference, State state, Double net, Double iva,
			Double total, List<ItemDTO> items) {
		super();
		this.quotationId = quotationId;
		this.user = user;
		this.customer = customer;
		this.discount = discount;
		this.remitter = remitter;
		this.dateQuotation = dateQuotation;
		this.dateEvent = dateEvent;
		this.reference = reference;
		this.state = state;
		this.net = net;
		this.iva = iva;
		this.total = total;
		this.items = items;
	}


	public Integer getQuotationId() {
		return quotationId;
	}


	public void setQuotationId(Integer quotationId) {
		this.quotationId = quotationId;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getCustomer() {
		return customer;
	}


	public void setCustomer(String customer) {
		this.customer = customer;
	}


	public Integer getDiscount() {
		return discount;
	}


	public void setDiscount(Integer discount) {
		this.discount = discount;
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


	public State getState() {
		return state;
	}


	public void setState(State state) {
		this.state = state;
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


	public List<ItemDTO> getItems() {
		return items;
	}


	public void setItems(List<ItemDTO> items) {
		this.items = items;
	}



}
