package com.lws.model;

import java.io.Serializable;
import java.util.Date;

public class PurchaseAccountModel implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private Date date;
	private PurchaseModel purchase;
	private int amount;
	private String cashorBank;
	private String chequeNumber;
	private String remarks;
	private String desc1;
	private String desc2;
	@Override
	public boolean equals(Object arg0) {
		final PurchaseAccountModel purchaseAccount = (PurchaseAccountModel) arg0;
		return id == purchaseAccount.getId();
	}
	@Override
	public int hashCode() {
		return id * 7 + date.hashCode();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public PurchaseModel getPurchase() {
		return purchase;
	}
	public void setPurchase(PurchaseModel purchase) {
		this.purchase = purchase;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getCashorBank() {
		return cashorBank;
	}
	public void setCashorBank(String cashorBank) {
		this.cashorBank = cashorBank;
	}
	public String getChequeNumber() {
		return chequeNumber;
	}
	public void setChequeNumber(String chequeNumber) {
		this.chequeNumber = chequeNumber;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	
	
	
	
	
	
	

}
