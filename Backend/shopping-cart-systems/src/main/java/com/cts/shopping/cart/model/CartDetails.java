package com.cts.shopping.cart.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CART")
public class CartDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long userid;
	private String sku;
	private int quantity;
	private double cost;
	private String image;
	private String status;
	private String description;
	private String mfr;
	private int vendor;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
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

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public CartDetails(long id, long userid, String sku, int quantity, double cost, String image, String status,
			String description, String mfr, int vendor) {
		super();
		this.id = id;
		this.userid = userid;
		this.sku = sku;
		this.quantity = quantity;
		this.cost = cost;
		this.image = image;
		this.status = status;
		this.description = description;
		this.mfr = mfr;
		this.vendor = vendor;
	}

	public CartDetails(long userid, String sku, int quantity, double cost, String image, String status,
			String description, String mfr, int vendor) {
		super();
		this.userid = userid;
		this.sku = sku;
		this.quantity = quantity;
		this.cost = cost;
		this.image = image;
		this.status = status;
		this.description = description;
		this.mfr = mfr;
		this.vendor = vendor;
	}

	public CartDetails() {
		super();
	}

	@Override
	public String toString() {
		return "CartDetails [id=" + id + ", userid=" + userid + ", sku=" + sku + ", quantity=" + quantity + ", cost="
				+ cost + ", image=" + image + ", status=" + status + ", description=" + description + ", mfr=" + mfr
				+ ", vendor=" + vendor + "]";
	}

}
