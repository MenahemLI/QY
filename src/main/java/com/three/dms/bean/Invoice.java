package com.three.dms.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name="db_Invoice")
public class Invoice implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String vendor;//供应商
	private String opendate;//开票日期，因为是手动录入，所以String类型
	private String client_tel;//客户联系方式
	private String unit;//货物单位（个/批）
	private String i_number;//增值税编号，在发票的No,获取
	private String amount;//数量，多少（单位）
	private String price;//总计
	private String taxes;//税率
	private String username;//录入人员
	private String date;//录入时间
	private String addprice;//合计
	private String i_code;//发票代码
	private String taxesprice;//税额
	private String address;
	private String goodsName;//对应着Goods类的货物名称
	private boolean estate; //认证
	private Double heji;//价税合计




	public Invoice(Long id, String vendor, String opendate, String client_tel, String unit, String i_number,
			String amount, String price, String taxes, String username, String date, String addprice, String i_code,
			String taxesprice, String address, String goodsName, boolean estate, Double heji) {
		super();
		this.id = id;
		this.vendor = vendor;
		this.opendate = opendate;
		this.client_tel = client_tel;
		this.unit = unit;
		this.i_number = i_number;
		this.amount = amount;
		this.price = price;
		this.taxes = taxes;
		this.username = username;
		this.date = date;
		this.addprice = addprice;
		this.i_code = i_code;
		this.taxesprice = taxesprice;
		this.address = address;
		this.goodsName = goodsName;
		this.estate = estate;
		this.heji = heji;
	}




	public Invoice() {
		super();
		// TODO Auto-generated constructor stub
	}




	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getOpendate() {
		return opendate;
	}
	public void setOpendate(String opendate) {
		this.opendate = opendate;
	}
	public String getClient_tel() {
		return client_tel;
	}
	public void setClient_tel(String client_tel) {
		this.client_tel = client_tel;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getI_number() {
		return i_number;
	}
	public void setI_number(String i_number) {
		this.i_number = i_number;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getTaxes() {
		return taxes;
	}
	public void setTaxes(String taxes) {
		this.taxes = taxes;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}




	public String getGoodsName() {
		return goodsName;
	}




	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}




	public String getAddprice() {
		return addprice;
	}




	public void setAddprice(String addprice) {
		this.addprice = addprice;
	}




	public String getI_code() {
		return i_code;
	}




	public void setI_code(String i_code) {
		this.i_code = i_code;
	}




	public String getTaxesprice() {
		return taxesprice;
	}




	public void setTaxesprice(String taxesprice) {
		this.taxesprice = taxesprice;
	}

	


	public boolean getEstate() {
		return estate;
	}




	public void setEstate(boolean estate) {
		this.estate = estate;
	}




	public Double getHeji() {
		return heji;
	}




	public void setHeji(Double heji) {
		this.heji = heji;
	}




	@Override
	public String toString() {
		return "Invoice [id=" + id + "; goodsName=" + goodsName + "; vendor=" + vendor + "; opendate=" + opendate + "; client_tel=" + client_tel
				+ "; unit=" + unit + "; i_number=" + i_number + "; amount=" + amount + "; price=" + price + "; taxes="
				+ taxes + "; username=" + username + "; date=" + date + "; addprice=" + addprice  + "; i_code=" + i_code+ "; taxesprice=" + taxesprice + "; address="
				+ address + "; estate="+ estate+ "; heji="+ heji + "]";
	}




	
	
	
	

}
