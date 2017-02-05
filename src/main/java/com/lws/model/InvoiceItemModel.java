package com.lws.model;

import java.io.Serializable;

public class InvoiceItemModel implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private ProductModel product;
	private int quantity;
	private float vat;
	private float unitRate;
	private int discount;
	private float totalAmount;
	private String tax;
	private InvoiceModel invoice;
	private float availableUnitPrice;
	
	
	
	
	 
	
	public float getAvailableUnitPrice() {
		return availableUnitPrice;
	}
	public void setAvailableUnitPrice(float availableUnitPrice) {
		this.availableUnitPrice = availableUnitPrice;
	}
	
	public InvoiceModel getInvoice() {
		return invoice;
	}
	public void setInvoice(InvoiceModel invoice) {
		this.invoice = invoice;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	@Override
	public boolean equals(Object arg0) {
		final InvoiceItemModel model = (InvoiceItemModel) arg0;
		return id == model.getId();
	}
	@Override
	public int hashCode() {
		return id * 7 +  quantity;
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
	public float getVat() {
		return vat;
	}
	public void setVat(float vat) {
		this.vat = vat;
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
	public float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
	


}
