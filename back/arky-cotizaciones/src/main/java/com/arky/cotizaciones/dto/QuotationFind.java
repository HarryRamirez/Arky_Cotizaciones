package com.arky.cotizaciones.dto;

import java.time.LocalDate;
import java.util.List;

import com.arky.cotizaciones.model.Customer;
import com.arky.cotizaciones.model.Discount;
import com.arky.cotizaciones.model.State;
import com.arky.cotizaciones.model.User;

public class QuotationFind {

	private Integer quotationId;
	private User user;
	private Customer customer;
	private Discount discount;
	private String remitter;
	private LocalDate dateQuotation;
	private LocalDate dateEvent;
	private String reference;
	private State state;
	private Double net;
	private Double iva;
	private Double total;
	private List<ItemDTO> items;

	public QuotationFind() {

	}


	public QuotationFind(Integer quotationId, User user, Customer customer, Discount discount, String remitter,
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


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Discount getDiscount() {
		return discount;
	}


	public void setDiscount(Discount discount) {
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
