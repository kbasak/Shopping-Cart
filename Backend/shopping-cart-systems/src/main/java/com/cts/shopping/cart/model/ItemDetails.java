package com.cts.shopping.cart.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ITEM")
public class ItemDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String sku;
	private String description;
	private int quantity;
	private double cost;
	private String mfr;
	private int vendor;
	private String image;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getMfr() {
		return mfr;
	}

	public void setMfr(String mfr) {
		this.mfr = mfr;
	}

	public int getVendor() {
		return vendor;
	}

	public void setVendor(int vendor) {
		this.vendor = vendor;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public ItemDetails(long id, String sku, String description, int quantity, double cost, String mfr, int vendor,
			String image) {
		super();
		this.id = id;
		this.sku = sku;
		this.description = description;
		this.quantity = quantity;
		this.cost = cost;
		this.mfr = mfr;
		this.vendor = vendor;
		this.image = image;
	}

	public ItemDetails(String sku, String description, int quantity, double cost, String mfr, int vendor,
			String image) {
		super();
		this.sku = sku;
		this.description = description;
		this.quantity = quantity;
		this.cost = cost;
		this.mfr = mfr;
		this.vendor = vendor;
		this.image = image;
	}

	public ItemDetails() {
		super();
	}

	@Override
	public String toString() {
		return "ItemDetails [id=" + id + ", sku=" + sku + ", description=" + description + ", quantity=" + quantity
				+ ", cost=" + cost + ", mfr=" + mfr + ", vendor=" + vendor + ", image=" + image + "]";
	}

}
