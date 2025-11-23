package com.arky.cotizaciones.model;



import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cotizacion")
public class Quotation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cotizacion")
	private Integer quotationId;

	@Column(name = "remitente")
	private String remitter;

	@Column(name = "fecha_cot")
	private LocalDate dateQuotation;

	@Column(name = "fecha_evento")
	private LocalDate dateEvent;

	@Column(name = "referencia")
	private String reference;


	@Column(name = "neto", columnDefinition = "DECIMAL(10, 2)")
	private Double net;

	@Column(name = "iva", columnDefinition = "DECIMAL(5, 2)")
	private Double iva;

	@Column(name = "total", columnDefinition = "DECIMAL(10, 2)")
	private Double total;





    @ManyToOne
    @JoinColumn(name = "id_usuario")
	private User user;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "id_descuento")
    private Discount discount;

    @ManyToOne
    @JoinColumn(name = "id_estado")
    private State state;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name= "id_cotizacion")
    private List<Item> items;





	public Integer getQuotationId() {
		return quotationId;
	}



	public void setQuotationId(Integer quotationId) {
		this.quotationId = quotationId;
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



	public List<Item> getItems() {
		return items;
	}



	public void setItems(List<Item> items) {
		this.items = items;
	}






















}
