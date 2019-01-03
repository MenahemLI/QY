package com.three.dms.bean;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity

public class customer implements Serializable{
	
private static final long serialVersionUID = 1L;
   private Long id;
   private String name;
   private String address;
   private String date;
   private String phone;
   
public customer() {
super();
}
@Override
public String toString() {
	return "customer [id=" + id + ", name=" + name + ", address=" + address + ", date=" + date + ", phone=" + phone
			+ "]";
}
@Id
@GeneratedValue(strategy = IDENTITY)
public Long getId() {
	return id;
}
public void setId(Long id) {
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
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}

   
}
