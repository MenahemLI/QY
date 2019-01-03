package com.three.dms.webapp.action.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.opensymphony.xwork2.ActionSupport;
import com.three.dms.bean.Goods;
import com.three.dms.bean.Idetails;
import com.three.dms.bean.Invoice;
import com.three.dms.bean.Outvoice;
import com.three.dms.bean.Users;
import com.three.dms.service.Info.IGoodsService;
import com.three.dms.service.Info.IInvoiceService;
import com.three.dms.service.Info.IOutvoiceService;


public class invoiceDataAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    /**
     * 注入各种方法
     * */
	

	private String fileFileName;
	private String fileContentType;
	private List<Invoice> invoiceList;
	private List<Outvoice> outvoiceList;
	private String YYYY;
	private Double[] TexesOutPrice=new Double[12];
	private Double[] TexesInPrice=new Double[12];
	private Double[] TexesPrice=new Double[12];
	private Integer[] num={0,1,2,3,4,5,6,7,8,9,10,11};
	private StringBuilder TexesOutPrices=new StringBuilder();
	private StringBuilder TexesInPrices=new StringBuilder();
	private StringBuilder TexesPrices=new StringBuilder();
	private StringBuilder monthOutPrices=new StringBuilder();
	private StringBuilder monthInPrices =new StringBuilder();
	@Autowired
	IInvoiceService invoiceService;
	@Autowired
	IGoodsService goodsService;
	@Autowired
	IOutvoiceService  outvoiceService;
	/**
	 * 创建全局对象
	 * */
	Invoice invoice = new Invoice();
	Idetails idetails = new Idetails();
	Goods goods = new Goods();
	Users user = new Users();
	/**
	 * 在右侧显示发票数据的跳转页面
	 * */
	@Action(value="toInvoiceData",
			results={@Result(name=SUCCESS,
			location="/WEB-INF/jsp/manager/invoiceData.jsp")})
	public String toInvoiceData()
	{
		String day;
		if(YYYY==null){
			SimpleDateFormat sdfs = new SimpleDateFormat("YYYY");
			Date date = new Date();
			day = sdfs.format(date);
			YYYY=day;
		}
		DecimalFormat    df   = new DecimalFormat("#.00");   
		for (int i = 0; i < 12; i++) {
			if(i<9){
				TexesOutPrice[i]=Double.parseDouble(df.format(outvoiceService.findTexesPrice(YYYY+"-"+"0"+(i+1))));
				
				TexesInPrice[i]=Double.parseDouble(df.format(invoiceService.findTexesPrice(YYYY+"-"+"0"+(i+1))));
				
				TexesPrice[i]=Double.parseDouble(df.format(TexesOutPrice[i]-TexesInPrice[i]));
				
			}else{
				TexesOutPrice[i]=Double.parseDouble(df.format(outvoiceService.findTexesPrice(YYYY+"-"+(i+1))));
				TexesInPrice[i]=Double.parseDouble(df.format(invoiceService.findTexesPrice(YYYY+"-"+(i+1))));
				TexesPrice[i]=Double.parseDouble(df.format(TexesOutPrice[i]-TexesInPrice[i]));
			}	
		}
		for(int i=0;i<12;i++){
			
			System.out.println(TexesOutPrice[i]);
			System.out.println(TexesInPrice[i]);
			System.out.println(TexesPrice[i]);
		}
		
		
		return SUCCESS;		
	}
	@Action(value="findInvoiceData",
			results={@Result(name=SUCCESS,
			location="/WEB-INF/jsp/manager/invoiceData.jsp")})
	public String findInvoiceData()
	{   HttpSession session = ServletActionContext.getRequest().getSession();
		user= (Users) session.getAttribute("user");
		String user_name = user.getU_name();
		invoiceList =invoiceService.findByuser(user_name);
		outvoiceList = outvoiceService.findByuser(user_name);
		return SUCCESS;		
	}
	@Action(value="toInvoiceChart",
			results={@Result(name=SUCCESS,
			location="/WEB-INF/jsp/manager/invoiceChart.jsp")})
	public String toInvoiceChart()
	{String day;
	if(YYYY==null){
		SimpleDateFormat sdfs = new SimpleDateFormat("YYYY");
		Date date = new Date();
		day = sdfs.format(date);
		YYYY=day;
	}
	Double[] monthOutPrice =new Double[12];
	Double[] monthInPrice =new Double[12];
	DecimalFormat    df   = new DecimalFormat("#.00");   
	for (int i = 0; i < 12; i++) {
		if(i<9){
			TexesOutPrices.append(df.format(outvoiceService.findTexesPrice(YYYY+"-"+"0"+(i+1)))+",") ;
			TexesInPrices.append(df.format(invoiceService.findTexesPrice(YYYY+"-"+"0"+(i+1)))+",");
			TexesPrices.append(df.format(outvoiceService.findTexesPrice(YYYY+"-"+"0"+(i+1))-invoiceService.findTexesPrice(YYYY+"-"+"0"+(i+1)))+",");
			monthOutPrice[i]=outvoiceService.findByMM(YYYY+"-"+"0"+(i+1));
			monthInPrice[i]=invoiceService.findByMM(YYYY+"-"+"0"+(i+1));
		}else{
			TexesOutPrices.append(df.format(outvoiceService.findTexesPrice(YYYY+"-"+(i+1)))+",");
			TexesInPrices.append(df.format(invoiceService.findTexesPrice(YYYY+"-"+(i+1)))+",");
			TexesPrices.append(df.format(outvoiceService.findTexesPrice(YYYY+"-"+(i+1))-invoiceService.findTexesPrice(YYYY+"-"+(i+1)))+",");
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
	
		
		System.out.println(TexesOutPrices);
		System.out.println(TexesInPrices);
		System.out.println(TexesPrices);
		

		
		
		return SUCCESS;		
	}
	
	
	/**
	 * 上传图片测试部分
	 * */
	@Action(value="toOnloadPicture",
			results={@Result(name=SUCCESS,
			location="/WEB-INF/jsp/addtouxiang.jsp")})
	public String touploadpicture()
	{
		System.out.println("成功条状");
		return SUCCESS;
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

	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	public Idetails getIdetails() {
		return idetails;
	}
	public void setIdetails(Idetails idetails) {
		this.idetails = idetails;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public String getYYYY() {
		return YYYY;
	}
	public void setYYYY(String yYYY) {
		YYYY = yYYY;
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
	public Integer[] getNum() {
		return num;
	}
	public void setNum(Integer[] num) {
		this.num = num;
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
