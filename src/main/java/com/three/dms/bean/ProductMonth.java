package com.three.dms.bean;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ProductMonth implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Double jan;
	private Double feb;
	private Double mar;
	private Double apr;
	private Double may;
	private Double jun;
	private Double jul;
	private Double eig;
	private Double sep;
	private Double oct;
	private Double nov;
	private Double dec;
	private String proname;
	public ProductMonth() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductMonth(Double jan, Double feb, Double mar, Double apr, Double may, Double jun, Double jul, Double eig,
			Double sep, Double oct, Double nov, Double dec, String proname) {
		super();
		this.jan = jan;
		this.feb = feb;
		this.mar = mar;
		this.apr = apr;
		this.may = may;
		this.jun = jun;
		this.jul = jul;
		this.eig = eig;
		this.sep = sep;
		this.oct = oct;
		this.nov = nov;
		this.dec = dec;
		this.proname = proname;
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	public Double getJan() {
		return jan;
	}
	public void setJan(Double jan) {
		this.jan = jan;
	}
	public Double getFeb() {
		return feb;
	}
	public void setFeb(Double feb) {
		this.feb = feb;
	}
	public Double getMar() {
		return mar;
	}
	public void setMar(Double mar) {
		this.mar = mar;
	}
	public Double getApr() {
		return apr;
	}
	public void setApr(Double apr) {
		this.apr = apr;
	}
	public Double getMay() {
		return may;
	}
	public void setMay(Double may) {
		this.may = may;
	}
	public Double getJun() {
		return jun;
	}
	public void setJun(Double jun) {
		this.jun = jun;
	}
	public Double getJul() {
		return jul;
	}
	public void setJul(Double jul) {
		this.jul = jul;
	}
	public Double getEig() {
		return eig;
	}
	public void setEig(Double eig) {
		this.eig = eig;
	}
	public Double getSep() {
		return sep;
	}
	public void setSep(Double sep) {
		this.sep = sep;
	}
	public Double getOct() {
		return oct;
	}
	public void setOct(Double oct) {
		this.oct = oct;
	}
	public Double getNov() {
		return nov;
	}
	public void setNov(Double nov) {
		this.nov = nov;
	}
	public Double getDec() {
		return dec;
	}
	public void setDec(Double dec) {
		this.dec = dec;
	}
	public String getProname() {
		return proname;
	}
	public void setProname(String proname) {
		this.proname = proname;
	}
	@Override
	public String toString() {
		return ",|"+jan + "," + feb + "," + mar + "," + apr + "," + may + ","
				+ jun + "," + jul + "," + eig + "," + sep + "," + oct + "," + nov + ","
				+ dec + "," + proname +",|";
	}
	
}
	