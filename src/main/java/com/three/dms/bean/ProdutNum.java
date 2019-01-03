package com.three.dms.bean;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ProdutNum implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String productn;//产品名称
	private Double sale;//销量
	private Integer salenum;//销售额
	private String unit;//单位
	private String datey;//日期
	public ProdutNum(Integer id, String productn, Double sale, Integer salenum, String unit, String datey) {
		super();
		this.id = id;
		this.productn = productn;
		this.sale = sale;
		this.salenum = salenum;
		this.unit = unit;
		this.datey = datey;
	}
	public ProdutNum() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProductn() {
		return productn;
	}
	public void setProductn(String productn) {
		this.productn = productn;
	}
	public Double getSale() {
		return sale;
	}
	public void setSale(Double sale) {
		this.sale = sale;
	}
	public Integer getSalenum() {
		return salenum;
	}
	public void setSalenum(Integer salenum) {
		this.salenum = salenum;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getDatey() {
		return datey;
	}
	public void setDatey(String datey) {
		this.datey = datey;
	}
	@Override
	public String toString() {
		return "ProdutNum [id=" + id + ", productn=" + productn + ", sale=" + sale + ", salenum=" + salenum + ", unit="
				+ unit + ", datey=" + datey + "]";
	}
	
	
	
	
	
	
}
