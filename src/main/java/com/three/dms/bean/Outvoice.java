package com.three.dms.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="db_Outvoice")
public class Outvoice implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String goodsName;//对应着Goods类的货物名臣
	private String buyer;//购买方
	private String opendate;//开票日期，因为是手动录入，所以String类型
	private String client_tel;//客户联系方式
	private String unit;//货物单位（个/批）
	private String o_number;//增值税号码，在发票的No,获取
	private String amount;//数量，多少（单位）
	private String price;//总计
	private String taxes;//税率
	private String username;//录入人员
	private String date;//录入时间
	private String addprice;//合计
	private String o_code;//发票代码
	private String taxesprice;//税额
	private String address;//地址
	private Double heji;
	/**
	 * toString（）
	 */
	
	@Override
	public String toString() {
		return "Outvoice [id=" + id + "; goodsName=" + goodsName + "; buyer=" + buyer + "; opendate=" + opendate
				+ "; client_tel=" + client_tel + "; unit=" + unit + "; o_number=" + o_number + "; amount=" + amount
				+ "; price=" + price + "; taxes=" + taxes + "; username=" + username + "; date=" + date   + "; addprice="
				+ addprice + "; o_code=" + o_code + "; taxesprice=" + taxesprice+ "; address=" + address+ "; heji=" + heji + "]";
	}


	

	/**
	 * 无参构造器
	 */
	public Outvoice() {
		super();
		// TODO Auto-generated constructor stub
	}






	public Outvoice(Long id, String goodsName, String buyer, String opendate, String client_tel, String unit,
			String o_number, String amount, String price, String taxes, String username, String date, String addprice,
			String o_code, String taxesprice, String address, Double heji) {
		super();
		this.id = id;
		this.goodsName = goodsName;
		this.buyer = buyer;
		this.opendate = opendate;
		this.client_tel = client_tel;
		this.unit = unit;
		this.o_number = o_number;
		this.amount = amount;
		this.price = price;
		this.taxes = taxes;
		this.username = username;
		this.date = date;
		this.addprice = addprice;
		this.o_code = o_code;
		this.taxesprice = taxesprice;
		this.address = address;
		this.heji = heji;
	}




	/**
	 * get,set方法
	 * @return
	 */
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	


	public void setId(Long id) {
		this.id = id;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
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

	public String getO_number() {
		return o_number;
	}

	public void setO_number(String o_number) {
		this.o_number = o_number;
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




	public String getO_code() {
		return o_code;
	}




	public void setO_code(String o_code) {
		this.o_code = o_code;
	}




	public String getTaxesprice() {
		return taxesprice;
	}




	public void setTaxesprice(String taxesprice) {
		this.taxesprice = taxesprice;
	}




	public Double getHeji() {
		return heji;
	}




	public void setHeji(Double heji) {
		this.heji = heji;
	}

	
	
	
}
