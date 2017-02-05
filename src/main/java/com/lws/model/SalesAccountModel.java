package com.lws.model;

import java.io.Serializable;
import java.util.Date;

public class SalesAccountModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private Date date;
	private InvoiceModel invoice;
	private String cashOrBank;
	private String chequeNumber;
	private int amount;
	private String remarks;
	private String desc1;
	private String desc2;
	
	
	
	
	public String getChequeNumber() {
		return chequeNumber;
	}
	public void setChequeNumber(String chequeNumber) {
		this.chequeNumber = chequeNumber;
	}
	@Override
	public boolean equals(Object arg0) {
		final SalesAccountModel sales = (SalesAccountModel) arg0;
		return id == sales.id;
	}
	@Override
	public int hashCode() {
		return id * 7 + date.hashCode();
	}
	public SalesAccountModel() {
		super();
	}
	public SalesAccountModel(int id, Date date,
			InvoiceModel invoice, String cashOrBank, String chequeNumber, int amount,
			String remarks, String desc1, String desc2) {
		super();
		this.id = id;
		this.date = date;
		this.invoice = invoice;
		this.cashOrBank = cashOrBank;
		this.amount = amount;
		this.remarks = remarks;
		this.desc1 = desc1;
		this.desc2 = desc2;
		this.chequeNumber = chequeNumber;
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
	public InvoiceModel getInvoice() {
		return invoice;
	}
	public void setInvoice(InvoiceModel invoice) {
		this.invoice = invoice;
	}
	public String getCashOrBank() {
		return cashOrBank;
	}
	public void setCashOrBank(String cashOrBank) {
		this.cashOrBank = cashOrBank;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
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
