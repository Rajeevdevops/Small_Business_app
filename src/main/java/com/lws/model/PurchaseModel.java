package com.lws.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PurchaseModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private ClientModel client;
	private String dateFormat;
	private Date date;
	private Integer totalAmount;
	private String remarks1;
	private String remarks2;
	private Set<PurchaseItemModel> purchaseItemList = new HashSet<PurchaseItemModel>();
	
	
	
	
	public String getDateFormat() {
		return dateFormat;
	}
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public boolean equals(Object obj) {
		final PurchaseModel model = (PurchaseModel) obj;
		return id == model.getId();
	}
	@Override
	public int hashCode() {
		return id * 7;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ClientModel getClient() {
		return client;
	}
	public void setClient(ClientModel client) {
		this.client = client;
	}
	public Integer getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getRemarks1() {
		return remarks1;
	}
	public void setRemarks1(String remarks1) {
		this.remarks1 = remarks1;
	}
	public String getRemarks2() {
		return remarks2;
	}
	public void setRemarks2(String remarks2) {
		this.remarks2 = remarks2;
	}
	
	public Set<PurchaseItemModel> getPurchaseItemList() {
		return purchaseItemList;
	}
	public void setPurchaseItemList(Set<PurchaseItemModel> purchaseItemList) {
		this.purchaseItemList = purchaseItemList;
	}
	@Override
	public String toString() {
		String s = id + client.getName() + date + totalAmount + remarks1 + remarks2 ;
		for(PurchaseItemModel model : purchaseItemList) {
			s += model.toString();
		}
		return s;
	}
	
	
	
	

}
