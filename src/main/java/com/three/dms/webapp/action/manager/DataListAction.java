package com.three.dms.webapp.action.manager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.three.dms.bean.Invoice;
import com.three.dms.bean.Outvoice;
import com.three.dms.service.Info.IInvoiceService;
import com.three.dms.service.Info.IOutvoiceService;

public class DataListAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String YYYY;
	private String panduan;
	private boolean jieguo;
	private List<Invoice> invoiceList;
	private List<Outvoice> outvoiceList;
	@Autowired
	IInvoiceService invoiceService;
	@Autowired
	IOutvoiceService  outvoiceService;
	@Action(value="toDataList",
			results={@Result(name=SUCCESS,
			location="/WEB-INF/jsp/manager/DataList.jsp")})
	public String toDataManager(){
		System.out.println("YYYYshi "+YYYY);
		jieguo=true;
		if(YYYY==null){
			SimpleDateFormat sdfs = new SimpleDateFormat("YYYY");
			Date date = new Date();
			YYYY = sdfs.format(date);	
			
			
		}
		System.out.println("panduanshi:"+panduan);
		if(panduan!=null) {
			if(panduan.equals("0"))
			{jieguo=false;
			System.out.println("是否进入赋值");
			}
			if(panduan.equals("1"))jieguo=true;
			
		}
		
		System.out.println("jiesuan=="+jieguo);
		invoiceList = invoiceService.searchAllData(YYYY);
		outvoiceList = outvoiceService.searchAllData(YYYY);
		return SUCCESS;
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
	public List<Outvoice> getOutvoiceList() {
		return outvoiceList;
	}
	public void setOutvoiceList(List<Outvoice> outvoiceList) {
		this.outvoiceList = outvoiceList;
	}
	public String getPanduan() {
		return panduan;
	}
	public void setPanduan(String panduan) {
		this.panduan = panduan;
	}
	public boolean isJieguo() {
		return jieguo;
	}
	public void setJieguo(boolean jieguo) {
		this.jieguo = jieguo;
	}
	
}
