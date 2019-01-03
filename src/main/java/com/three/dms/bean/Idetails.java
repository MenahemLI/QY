package com.three.dms.bean;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="db_idetails")
public class Idetails implements Serializable{

	/**
	 * 建立月进项明细类
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String wares;//货物名称
	private String size;//货物规格
	private String unit;//货物单位
	private String unitprice;//购买货物的钱
	private String amount;//货物数量
	private String salesunit;//销货单位
	private String invoicedata;//开票日期
	private String taxrate;//税额
	
	 //无参的构造函数	
	public Idetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	//有参的构造函数
	public Idetails(Long id, String wares, String size, String unit, String unitprice, String amount, String salesunit,
			String invoicedata, String taxrate) {
		super();
		this.id = id;
		this.wares = wares;
		this.size = size;
		this.unit = unit;
		this.unitprice = unitprice;
		this.amount = amount;
		this.salesunit = salesunit;
		this.invoicedata = invoicedata;
		this.taxrate = taxrate;
	}
	
	//提供set、get方法
		@Id
		@GeneratedValue(strategy = IDENTITY)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getWares() {
		return wares;
	}
	public void setWares(String wares) {
		this.wares = wares;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getSalesunit() {
		return salesunit;
	}
	public void setSalesunit(String salesunit) {
		this.salesunit = salesunit;
	}
	public String getInvoicedata() {
		return invoicedata;
	}
	public void setInvoicedata(String invoicedata) {
		this.invoicedata = invoicedata;
	}
	public String getTaxrate() {
		return taxrate;
	}
	public void setTaxrate(String taxrate) {
		this.taxrate = taxrate;
	}
	@Override
	public String toString() {
		return "Idetails [id=" + id + ", wares=" + wares + ", size=" + size + ", unit=" + unit + ", unitprice="
				+ unitprice + ", amount=" + amount + ", salesunit=" + salesunit + ", invoicedata=" + invoicedata
				+ ", taxrate=" + taxrate + "]";
	}
	
	
	
	
   
	
	
}
