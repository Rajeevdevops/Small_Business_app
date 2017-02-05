package com.lws.model;

import java.io.Serializable;

public class PurchaseItemModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public PurchaseItemModel() {
		
	}
	
	
	
	public PurchaseItemModel(int id, ProductModel product, int quantity,
			int free, float unitRate, int discount, float vat, float amount,
			int totalQuantity) {
		super();
		this.id = id;
		this.product = product;
		this.quantity = quantity;
		this.free = free;
		this.unitRate = unitRate;
		this.discount = discount;
		this.vat = vat;
		this.amount = amount;
		this.totalQuantity = totalQuantity;
	}


	

	private int id;
	private ProductModel product;
	private int quantity;
	private int free;
	private float unitRate;
	private int discount;
	private float vat;
	private float amount;
	private int totalQuantity;
	private String tax;
	private PurchaseModel model;
	
	
	
	


	public PurchaseModel getModel() {
		return model;
	}



	public void setModel(PurchaseModel model) {
		this.model = model;
	}



	public String getTax() {
		return tax;
	}



	public void setTax(String tax) {
		this.tax = tax;
	}

 

	@Override
	public boolean equals(Object arg0) {
		final PurchaseItemModel model = (PurchaseItemModel) arg0;
		return id == model.getId();
	}



	@Override
	public int hashCode() {
		return id *  7 + quantity * 7;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ProductModel getProduct() {
		return product;
	}
	public void setProduct(ProductModel product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getFree() {
		return free;
	}
	public void setFree(int free) {
		this.free = free;
	}
	public float getUnitRate() {
		return unitRate;
	}
	public void setUnitRate(float unitRate) {
		this.unitRate = unitRate;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public float getVat() {
		return vat;
	}
	public void setVat(float vat) {
		this.vat = vat;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public int getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}



	@Override
	public String toString() {
		return product.getId() +" - "+quantity + " - "+free+" - "+discount +" - "+vat +" - "+totalQuantity +" - "+amount +" - "+totalQuantity;
	}
	
	
	
	

}
