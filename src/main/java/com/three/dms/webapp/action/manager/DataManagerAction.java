package com.three.dms.webapp.action.manager;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.three.dms.bean.Invoice;
import com.three.dms.bean.Users;
import com.three.dms.service.Info.IInvoiceService;
import com.three.dms.service.Info.IOutvoiceService;

public class DataManagerAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String YYYY;
	private StringBuilder TexesOutPrices=new StringBuilder();
	private StringBuilder TexesInPrices=new StringBuilder();
	private StringBuilder TexesPrices=new StringBuilder();
	private Double[] TexesOutPrice=new Double[12];
	private Double[] TexesInPrice=new Double[12];
	private Double[] TexesPrice=new Double[12];
	
	private List<Invoice> invoiceList;
	@Autowired
	IInvoiceService invoiceService;
	@Autowired
	IOutvoiceService  outvoiceService;
	/**
	 * 数据调取
	 * 
	 * @return
	 */
	// 完成注册
	@Action(value="toDataManager",
			results={@Result(name=SUCCESS,
			location="/WEB-INF/jsp/manager/DataManager.jsp")})
	public String toDataManager(){
		if(YYYY==null){
			SimpleDateFormat sdfs = new SimpleDateFormat("YYYY");
			Date date = new Date();
			YYYY = sdfs.format(date);	
		}
		invoiceList = invoiceService.searchAllData(YYYY);
		System.out.println("YYYY是"+YYYY);
		return SUCCESS;
	}
	@Action("toOriginal")
	public void toOriginal() throws Exception{
		System.out.println("到这里了");
		DecimalFormat    df   = new DecimalFormat("#.00");   
		for (int i = 0; i < 12; i++) {
			if(i<9){
				TexesOutPrice[i]=Double.parseDouble(df.format(outvoiceService.findTexesPrice(YYYY+"-"+"0"+(i+1))));
				
				TexesInPrice[i]=Double.parseDouble(df.format(invoiceService.findTexesPrice(YYYY+"-"+"0"+(i+1))));
				
				TexesPrice[i]=TexesOutPrice[i]-TexesInPrice[i];
				
			}else{
				TexesOutPrice[i]=Double.parseDouble(df.format(outvoiceService.findTexesPrice(YYYY+"-"+(i+1))));
				TexesInPrice[i]=Double.parseDouble(df.format(invoiceService.findTexesPrice(YYYY+"-"+(i+1))));
				TexesPrice[i]=TexesOutPrice[i]-TexesInPrice[i];
			}	
		}
		for(int i=0;i<12;i++){
			TexesOutPrices.append(TexesOutPrice[i]+",");
			TexesInPrices.append(TexesInPrice[i]+",");
			TexesPrices.append(TexesPrice[i]+",");
		}
		StringBuilder res = new StringBuilder();
		res.append(TexesOutPrices+";").append(TexesInPrices+";").append(TexesPrices);
		HttpServletRequest reqest = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		reqest.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.println(res);
		out.flush();
		out.close();
		System.out.println("womwnlianjiecheggong ");
		System.out.println(TexesOutPrices);
		System.out.println(TexesInPrices);
		System.out.println(TexesPrices);
	}

	public String getYYYY() {
		return YYYY;
	}

	public void setYYYY(String yYYY) {
		YYYY = yYYY;
	}

	public List<Invoice> getInvoiceList() {
		return invoiceList;
	}

	public void setInvoiceList(List<Invoice> invoiceList) {
		this.invoiceList = invoiceList;
	}
	public Double[] getTexesOutPrice() {
		return TexesOutPrice;
	}
	public void setTexesOutPrice(Double[] texesOutPrice) {
		TexesOutPrice = texesOutPrice;
	}
	public Double[] getTexesInPrice() {
		return TexesInPrice;
	}
	public void setTexesInPrice(Double[] texesInPrice) {
		TexesInPrice = texesInPrice;
	}
	public Double[] getTexesPrice() {
		return TexesPrice;
	}
	public void setTexesPrice(Double[] texesPrice) {
		TexesPrice = texesPrice;
	}
	public StringBuilder getTexesOutPrices() {
		return TexesOutPrices;
	}
	public void setTexesOutPrices(StringBuilder texesOutPrices) {
		TexesOutPrices = texesOutPrices;
	}
	public StringBuilder getTexesInPrices() {
		return TexesInPrices;
	}
	public void setTexesInPrices(StringBuilder texesInPrices) {
		TexesInPrices = texesInPrices;
	}
	public StringBuilder getTexesPrices() {
		return TexesPrices;
	}
	public void setTexesPrices(StringBuilder texesPrices) {
		TexesPrices = texesPrices;
	}
	
}
