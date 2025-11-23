package com.arky.cotizaciones.dto;

import java.time.LocalDate;

import com.arky.cotizaciones.model.State;




public class QuotationSearchDTO {

	private Integer quotationId;
	private String customer;
	private String remitter;
	private LocalDate dateQuotation;
	private LocalDate dateEvent;
	private Double net;
	private Double total;
	private State state;

	public QuotationSearchDTO() {

	}

	public QuotationSearchDTO(Integer quotationId, String customer, String remitter, LocalDate dateQuotation,
			LocalDate dateEvent, Double net, Double total, State state) {
		super();
		this.quotationId = quotationId;
		this.customer = customer;
		this.remitter = remitter;
		this.dateQuotation = dateQuotation;
		this.dateEvent = dateEvent;
		this.net = net;
		this.total = total;
		this.state = state;
	}

	public Integer getQuotationId() {
		return quotationId;
	}

	public void setQuotationId(Integer quotationId) {
		this.quotationId = quotationId;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
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

	public Double getNet() {
		return net;
	}

	public void setNet(Double net) {
		this.net = net;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}





}
