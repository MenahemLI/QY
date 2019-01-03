package com.three.dms.webapp.action.manager;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.three.dms.service.Info.IInvoiceService;
import com.three.dms.service.Info.IOutvoiceService;

public class YearChartAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String YYYY;
	private StringBuilder monthOutPrices=new StringBuilder();
	private StringBuilder monthInPrices =new StringBuilder();
	@Autowired
	IOutvoiceService outvoiceService;
	@Autowired
	IInvoiceService invoiceService;
	@Action(value="toYearChart",
			results={@Result(name=SUCCESS,
			location="/WEB-INF/jsp/manager/yearChart.jsp")})
	public String toYearChart(){
		if(YYYY==null){
			SimpleDateFormat sdfs = new SimpleDateFormat("YYYY");
			Date date = new Date();
			YYYY = sdfs.format(date);	
		}
		System.out.println("YYYY是"+YYYY);
		Double[] monthOutPrice =new Double[12];
		Double[] monthInPrice =new Double[12];
		for (int i = 0; i < 12; i++) {
			if(i<9){
				monthOutPrice[i]=outvoiceService.findByMM(YYYY+"-"+"0"+(i+1));
				monthInPrice[i]=invoiceService.findByMM(YYYY+"-"+"0"+(i+1));
			}else{
				monthOutPrice[i]=outvoiceService.findByMM(YYYY+"-"+(i+1));
				monthInPrice[i]=invoiceService.findByMM(YYYY+"-"+(i+1));
			}	
		}
		for (int i = 0; i < 12; i++) {
			
			System.out.println("第"+i+"个月的"+monthOutPrice[i]);
			monthOutPrices.append(monthOutPrice[i]+",");
			monthInPrices.append(monthInPrice[i]+",");
			
		}
		
		System.out.println("action层："+monthOutPrices);
		System.out.println("action层："+monthInPrices);
		return SUCCESS;
	}

	

	public String getYYYY() {
		return YYYY;
	}

	public void setYYYY(String yYYY) {
		YYYY = yYYY;
	}



	public StringBuilder getMonthOutPrices() {
		return monthOutPrices;
	}



	public void setMonthOutPrices(StringBuilder monthOutPrices) {
		this.monthOutPrices = monthOutPrices;
	}



	public StringBuilder getMonthInPrices() {
		return monthInPrices;
	}



	public void setMonthInPrices(StringBuilder monthInPrices) {
		this.monthInPrices = monthInPrices;
	}

	
	
}
