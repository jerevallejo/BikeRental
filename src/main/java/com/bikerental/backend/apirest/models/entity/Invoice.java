package com.bikerental.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "invoices")
public class Invoice implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;

	@JsonIgnoreProperties(value ={ "invoices", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	@ManyToOne(fetch = FetchType.LAZY)
	private Client client;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "invoice_id")
	private List<ItemInvoice> items;

	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}

	public Invoice() {
		items = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<ItemInvoice> getItems() {
		return items;
	}

	public void setItems(List<ItemInvoice> items) {
		this.items = items;
	}

	public int getTotalItems() {
		int totalITems = 0;
		for (ItemInvoice item : this.items) {
			totalITems += item.getQuantity();
		}
		return totalITems;
	}

	public Double getTotalAmount() {
		Double total = 0.00;
		for (ItemInvoice item : this.items) {
			total += item.getAmount();
		}
		if (getTotalItems() >= 3 && getTotalItems() <= 5) {
			total -= calculateDiscount(total);
		}
		return total;
	}

	public Double calculateDiscount(Double total) {

		return (total * 30) / 100;

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
