package com.three.dms.webapp.action.manager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.three.dms.bean.Idetails;
import com.three.dms.bean.Odetails;
import com.three.dms.service.Info.IIdetailsService;
import com.three.dms.service.Info.IOdetailsService;

public class IdetailsAction extends ActionSupport{
	
	/**
	 * 
	 * 进项数据明细Action
	 */
	 
	private static final long serialVersionUID = 1L;
	private List<List<Idetails>> ientitylist=new ArrayList<List<Idetails>>();
	private List<List<Odetails>> oentitylist=new ArrayList<List<Odetails>>();
	private List<Idetails> idetailslist;
	private List<Odetails> odetailslist;
	
	
	private TreeSet<String> inputwares;
	private TreeSet<String> outputwares;
	 
	@Autowired
	IIdetailsService iIdetailsService;
	@Autowired
	IOdetailsService iOdetailsService;
	
	
	private Double InputPrice= 0.00;
	private Double OutputPrice= 0.00;
	private Double taxrates =  0.00;
	private String years;
	private String months;
	private String presentMonth;
	
	@Action(value="todetails",results={@Result(name=SUCCESS,location="/WEB-INF/jsp/manager/details.jsp")})
	public String search(){
		List<Idetails> inputDtail = new ArrayList<>();
		List<Odetails> outputDtail = new ArrayList<>();
		//获取到当前的年月
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat sdfs = new SimpleDateFormat("yyyy年MM");
		Date date = new Date();
		presentMonth = sdfs.format(date);
		//查询到本月所购买的货物名称
		inputwares = iIdetailsService.findwaresbyinvoicedata(sdf.format(date));
		System.out.println(inputwares+"mama");
		outputwares = iOdetailsService.findwaresbyinvoicedata(sdf.format(date));
		System.out.println(outputwares+"mama2");
		//遍历货物，目的是将每种货物分类储存
		for (String  inputwares: inputwares) {
			//通过货物名称和开票月份找到货物
			inputDtail = iIdetailsService.findbyinvoicedataandwars(sdf.format(date),inputwares);
			//将货物储存到entitylist集合中
			ientitylist.add(inputDtail);
			System.out.println(ientitylist+"baba");
		}
		for (String  outputwares: outputwares) {
			outputDtail = iOdetailsService.findbyinvoicedataandwars(sdf.format(date), outputwares);
			oentitylist.add(outputDtail);
			System.out.println(oentitylist+"baba");
		}
		
		idetailslist = iIdetailsService.findbyinvoicedata(sdf.format(date));
		odetailslist = iOdetailsService.findbyinvoicedata(sdf.format(date));
		System.out.println(odetailslist+"zhaoyeye");
		for (Idetails idetails : idetailslist) {
			InputPrice=InputPrice+Double.parseDouble(idetails.getUnitprice());
			taxrates = taxrates + Double.parseDouble(idetails.getTaxrate());
		}
		for (Odetails odetails : odetailslist) {
			OutputPrice=OutputPrice+Double.parseDouble(odetails.getUnitprice());
		}
		
		//将查询到月进项的结果传到idetails集合中
		return SUCCESS;
	}
	
	
	@Action(value="toregister",results={@Result(name=SUCCESS,location="/WEB-INF/jsp/manager/details.jsp")})
	public String Timesearch(){
		List<Idetails> inputDtail = new ArrayList<>();
		List<Odetails> outputDtail = new ArrayList<>();
		String Time;
		
		if(Integer.parseInt(getMonths())<=9){
			Time = getYears() + "-0" +getMonths();
		}else{
			Time = getYears() + "-" +getMonths();
		}
		presentMonth = getYears() + "年0" +getMonths();
		inputwares = iIdetailsService.findwaresbyinvoicedata(Time);
		System.out.println(inputwares+"5165");
		outputwares = iOdetailsService.findwaresbyinvoicedata(Time);
		//遍历货物，目的是将每种货物分类储存
		for (String  inputwares: inputwares) {
			//通过货物名称和开票月份找到货物
			inputDtail = iIdetailsService.findbyinvoicedataandwars(Time,inputwares);
			//将货物储存到entitylist集合中
			ientitylist.add(inputDtail);
			System.out.println(ientitylist+"baba");
		}
		for (String  outputwares: outputwares) {
			outputDtail = iOdetailsService.findbyinvoicedataandwars(Time, outputwares);
			oentitylist.add(outputDtail);
		}
		
		idetailslist = iIdetailsService.findbyinvoicedata(Time);
		odetailslist = iOdetailsService.findbyinvoicedata(Time);
		for (Idetails idetails : idetailslist) {
			InputPrice=InputPrice+Double.parseDouble(idetails.getUnitprice());
			taxrates = taxrates + Double.parseDouble(idetails.getTaxrate());
		}
		for (Odetails odetails : odetailslist) {
			OutputPrice=OutputPrice+Double.parseDouble(odetails.getUnitprice());
		}
		return SUCCESS;
	}

	public List<Idetails> getIdetailslist() {
		return idetailslist;
	}

	public void setIdetailslist(List<Idetails> idetailslist) {
		this.idetailslist = idetailslist;
	}

	public List<Odetails> getOdetailslist() {
		return odetailslist;
	}

	public void setOdetailslist(List<Odetails> odetailslist) {
		this.odetailslist = odetailslist;
	}

	public TreeSet<String> getInputwares() {
		return inputwares;
	}

	public void setInputwares(TreeSet<String> inputwares) {
		this.inputwares = inputwares;
	}

	public TreeSet<String> getOutputwares() {
		return outputwares;
	}

	public void setOutputwares(TreeSet<String> outputwares) {
		this.outputwares = outputwares;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

	public Double getInputPrice() {
		return InputPrice;
	}

	public void setInputPrice(Double inputPrice) {
		InputPrice = inputPrice;
	}

	public Double getOutputPrice() {
		return OutputPrice;
	}

	public void setOutputPrice(Double outputPrice) {
		OutputPrice = outputPrice;
	}

	public String getYears() {
		return years;
	}

	public void setYears(String years) {
		this.years = years;
	}

	public String getMonths() {
		return months;
	}

	public void setMonths(String months) {
		this.months = months;
	}

	public String getPresentMonth() {
		return presentMonth;
	}

	public void setPresentMonth(String presentMonth) {
		this.presentMonth = presentMonth;
	}


	public List<List<Idetails>> getIentitylist() {
		return ientitylist;
	}


	public void setIentitylist(List<List<Idetails>> ientitylist) {
		this.ientitylist = ientitylist;
	}


	public List<List<Odetails>> getOentitylist() {
		return oentitylist;
	}


	public void setOentitylist(List<List<Odetails>> oentitylist) {
		this.oentitylist = oentitylist;
	}


	public Double getTaxrates() {
		return taxrates;
	}


	public void setTaxrates(Double taxrates) {
		this.taxrates = taxrates;
	}
	
	
	

	

	


	

	
}
