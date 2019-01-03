package com.three.dms.bean;


import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "db_goods")
public class Goods implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String g_name;//货物名称
	private String g_number;//货物编号
	private String g_price;//货物价格
	private String g_format;//货物规格型号
	private String tag;
	
	
	public Goods() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Goods [id=" + id + ", g_name=" + g_name + ", g_number=" + g_number + ", g_price=" + g_price
				+ ", g_format=" + g_format + ", tag=" + tag + "]";
	}
	public Goods(Integer id, String g_name, String g_number, String g_price, String g_format, String tag) {
		super();
		this.id = id;
		this.g_name = g_name;
		this.g_number = g_number;
		this.g_price = g_price;
		this.g_format = g_format;
		this.tag = tag;
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getG_name() {
		return g_name;
	}
	public void setG_name(String g_name) {
		this.g_name = g_name;
	}
	public String getG_number() {
		return g_number;
	}
	public void setG_number(String g_number) {
		this.g_number = g_number;
	}
	public String getG_price() {
		return g_price;
	}
	public void setG_price(String g_price) {
		this.g_price = g_price;
	}
	public String getG_format() {
		return g_format;
	}
	public void setG_format(String g_format) {
		this.g_format = g_format;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	
}
