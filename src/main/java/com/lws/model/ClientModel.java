package com.lws.model;

import java.io.Serializable;

public class ClientModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String address;
	private String tinNo;
	private String cstNo;
	private String phoneNumber1;
	private String phoneNumber2;
	private String desc1;
	private String desc2;
	private String remarks;
	private String email;
	private String fax;
	
	
	
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getPhoneNumber1() {
		return phoneNumber1;
	}
	public void setPhoneNumber1(String phoneNumber1) {
		this.phoneNumber1 = phoneNumber1;
	}
	public String getPhoneNumber2() {
		return phoneNumber2;
	}
	public void setPhoneNumber2(String phoneNumber2) {
		this.phoneNumber2 = phoneNumber2;
	}
	@Override
	public boolean equals(Object obj) {
		final ClientModel model = (ClientModel) obj;
		return model.getId() == this.getId(); 
	}
	@Override
	public int hashCode() {
		return id * 7 + name.hashCode() * 7;
	}
	
	
	
	public ClientModel() {
		super();
	}
	public ClientModel(int id, String name, String address, String tinNo,
			String cstNo, String desc1, String desc2, String remarks) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.tinNo = tinNo;
		this.cstNo = cstNo;
		this.desc1 = desc1;
		this.desc2 = desc2;
		this.remarks = remarks;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTinNo() {
		return tinNo;
	}
	public void setTinNo(String tinNo) {
		this.tinNo = tinNo;
	}
	public String getCstNo() {
		return cstNo;
	}
	public void setCstNo(String cstNo) {
		this.cstNo = cstNo;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	
	
}
