package com.lws.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class QuotationModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private ClientModel client;
	private String dateFormat;
	private Date date;
	private int totalAmount;
	private int totalQuantity;
	private String reference;
	private String phone;
	private int claimPercentage;
	private float claimAmount;
	private Set<QuotationItemModel> quotationItemSet = new HashSet<QuotationItemModel>();

	public QuotationModel(int id, ClientModel client, Date date,
			int totalAmount, int totalQuantity, String reference, String phone,
			int claimPercentage, int claimAmount) {
		super();
		this.id = id;
		this.client = client;
		this.date = date;
		this.totalAmount = totalAmount;
		this.totalQuantity = totalQuantity;
		this.reference = reference;
		this.phone = phone;
		this.claimPercentage = claimPercentage;
		this.claimAmount = claimAmount;
	}

	public Set<QuotationItemModel> getQuotationItemSet() {
		return quotationItemSet;
	}

	public void setQuotationItemSet(Set<QuotationItemModel> quotationItemSet) {
		this.quotationItemSet = quotationItemSet;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	@Override
	public boolean equals(Object arg0) {
		final QuotationModel model = (QuotationModel) arg0;
		return id == model.getId();
	}

	@Override
	public int hashCode() {
		return id * 7 + date.hashCode();
	}

	public QuotationModel() {
		super();
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getClaimPercentage() {
		return claimPercentage;
	}

	public void setClaimPercentage(int claimPercentage) {
		this.claimPercentage = claimPercentage;
	}

	public float getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(float claimAmount) {
		this.claimAmount = claimAmount;
	}

}
