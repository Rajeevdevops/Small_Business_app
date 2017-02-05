package com.lws.model;

import java.io.Serializable;


public class ProductModel implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private int id, discount, quantityRate, quantity;
	private String name, desc1, desc2, desc3, desc4, units;
	
	
	
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getQuantityRate() {
		return quantityRate;
	}

	public void setQuantityRate(int quantityRate) {
		this.quantityRate = quantityRate;
	}

	@Override
	public boolean equals(Object obj) {
		final ProductModel product = (ProductModel) obj;
		return this.id == product.getId();
	}
	
	@Override
	public int hashCode() {
		return id * 7 + name.hashCode() * 7;
	}
	
	
	
	
	public ProductModel() {
		super();
	}

	public ProductModel(int id, int discount, int quantityRate, String name,
			String desc1, String desc2, String desc3, String desc4, String units) {
		super();
		this.id = id;
		this.discount = discount;
		this.quantityRate = quantityRate;
		this.name = name;
		this.desc1 = desc1;
		this.desc2 = desc2;
		this.desc3 = desc3;
		this.desc4 = desc4;
		this.units = units;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc1() {
		return desc1;
	}
	public void setDesc1(String desc1) {
		this.desc1 = desc1;
	}
	public String getDesc2() {
		return desc2;
	}
	public void setDesc2(String desc2) {
		this.desc2 = desc2;
	}
	public String getDesc3() {
		return desc3;
	}
	public void setDesc3(String desc3) {
		this.desc3 = desc3;
	}
	public String getDesc4() {
		return desc4;
	}
	public void setDesc4(String desc4) {
		this.desc4 = desc4;
	}
	
	
	
}
