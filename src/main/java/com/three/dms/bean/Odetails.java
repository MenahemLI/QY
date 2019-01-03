package com.three.dms.bean;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="db_odetails")
public class Odetails implements Serializable{

	/**
	 * 建立月销项明细
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String wares;//货物名称
	private String size;//货物规格
	private String unit;//货物单位
	private String unitprice;//货物单价
	private String amount;//货物数量
	private String salesunit;//销货单位
	private String invoicedata;//开票日期
	
	//有参的构造函数
	public Odetails(Long id, String wares, String size, String unit, String unitprice, String amount, String salesunit,
			String invoicedata) {
		super();
		this.id = id;
		this.wares = wares;
		this.size = size;
		this.unit = unit;
		this.unitprice = unitprice;
		this.amount = amount;
		this.salesunit = salesunit;
		this.invoicedata = invoicedata;
	}
	//无参的构造函数
	public Odetails() {
		super();
		// TODO Auto-generated constructor stub
	}
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
	@Override
	public String toString() {
		return "Odetails [id=" + id + ", wares=" + wares + ", size=" + size + ", unit=" + unit + ", unitprice="
				+ unitprice + ", amount=" + amount + ", salesunit=" + salesunit + ", invoicedata=" + invoicedata + "]";
	}
	
	
	
}
