package com.three.dms.webapp.action.manager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.three.dms.bean.Idetails;
import com.three.dms.bean.Odetails;
import com.three.dms.service.Info.IIdetailsService;
import com.three.dms.service.Info.IInvoiceService;
import com.three.dms.service.Info.IOdetailsService;
import com.three.dms.service.Info.IOutvoiceService;

public class MonthChartAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String YYYY;
	private String MM;
	private StringBuilder dayOutPrices=new StringBuilder();
	private StringBuilder dayInPrices =new StringBuilder();
	private StringBuilder salesOutList=new StringBuilder();
	private StringBuilder salesOutVolumeList =new StringBuilder();
	private StringBuilder salesInList=new StringBuilder();
	private StringBuilder salesInVolumeList =new StringBuilder();
	@Autowired
	IInvoiceService invoiceService;
	@Autowired
	IOutvoiceService outvoiceService;
	@Autowired
	IOdetailsService odetailService;
	@Autowired
	IIdetailsService idetailService;
	@Action(value="toMonthChart",
			results={@Result(name=SUCCESS,
			location="/WEB-INF/jsp/manager/monthChart.jsp")})
	public String toMonthChart(){
		String day;
		if(YYYY==null||MM==null){
			SimpleDateFormat sdfs = new SimpleDateFormat("YYYY-MM");
			Date date = new Date();
			day = sdfs.format(date);
			MM=day.split("-")[1];
			YYYY=day.split("-")[0];
		}else{
			if(Integer.parseInt(MM)<10)
			day = YYYY+"-0"+MM;
			else{
				day = YYYY+"-"+MM;
			}
		}
		List<Odetails> odetailsList=new ArrayList<>();
		odetailsList = odetailService.findbyinvoicedata(day);
		List<Idetails> idetailsList=new ArrayList<>();
		idetailsList = idetailService.findbyinvoicedata(day);
		String[] salesOut=new String[1000];
		String[] waresOut=new String[1000];
		String[] sizzOut=new String[1000];
		String[] salesIn=new String[1000];
		String[] waresIn=new String[1000];
		String[] sizzIn=new String[1000];
		String tempD;
		String tempI;
		double otherOutprice=0;
		double otherOutnum=0;
		double otherInprice=0;
		double otherInnum=0;
		String[] salesVolumeIn=new String[1000];
		String[] salesVolumeOut=new String[1000];
		int k=0,flag;
		//对销量和销售额赋值
		for (Odetails odetails : odetailsList) {
	
			System.out.println("查找的odetails"+odetails.getUnitprice());
			salesOut[k]=odetails.getUnitprice();
			salesVolumeOut[k]=odetails.getAmount();
			waresOut[k]=odetails.getWares();
			sizzOut[k]=odetails.getSize();
			otherOutprice=otherOutprice+Double.parseDouble(salesOut[k]);
			otherOutnum=otherOutnum+Double.parseDouble(salesVolumeOut[k]);
			k++;
		}
		
		System.out.println(k);
		//对销售额冒泡排序
		for(int i=0;i<k-1;i++){
			flag=1;
			for(int j=0;j<k-1-i;j++){
				
				if(Double.parseDouble(salesOut[j])<Double.parseDouble(salesOut[j+1])){
					flag=0;
					tempD = salesOut[j];
					salesOut[j]=salesOut[j+1];
					salesOut[j+1]=tempD;
					tempD=waresOut[j];
					waresOut[j]=waresOut[j+1];
					waresOut[j+1]=tempD;
					tempD=sizzOut[j];
					sizzOut[j]=sizzOut[j+1];
					sizzOut[j+1]=tempD;
				}
			}
			if(flag==1)break;
		}
		//给salesOutList和salesOutVolumeList赋值
		for(int i=0;i<k;i++){
			if(k>4){
				
				if(i<4){
					otherOutprice=otherOutprice-Double.parseDouble(salesOut[i]);
				}
			}else{
				
				otherOutprice=0;
			}
			
			System.out.println("sales排序结果"+salesOut[i]);
			salesOutList.append(salesOut[i]+","+waresOut[i]+","+sizzOut[i]+",");
		}	
		salesOutList.append(otherOutprice);
		//对销量冒泡排序
		for(int i=0;i<k-1;i++){
			flag=1;
			for(int j=0;j<k-1-i;j++){
				if(Double.parseDouble(salesVolumeOut[j])<Double.parseDouble(salesVolumeOut[j+1])){
					flag=0;
					tempI = salesVolumeOut[j];
					salesVolumeOut[j]=salesVolumeOut[j+1];
					salesVolumeOut[j+1]=tempI;
					tempI=waresOut[j];
					waresOut[j]=waresOut[j+1];
					waresOut[j+1]=tempI;
					tempI=sizzOut[j];
					sizzOut[j]=sizzOut[j+1];
					sizzOut[j+1]=tempI;
				}
			}
		}
		//给salesOutList和salesOutVolumeList赋值
		for(int i=0;i<k;i++){
			if(k>4){
				if(i<4){
					otherOutnum=otherOutnum-Double.parseDouble(salesVolumeOut[i]);
				}
			}else{
				
				otherOutnum=0;
			}
			
			System.out.println("salesVolume排序结果"+salesVolumeOut[i]);
			salesOutVolumeList.append(salesVolumeOut[i]+","+waresOut[i]+","+sizzOut[i]+",");
		}	
		salesOutVolumeList.append(otherOutnum);
		//对进项量和进项额额赋值
		k=0;
				for (Idetails idetails : idetailsList) {
			
					System.out.println("查找的odetails"+idetails.getUnitprice());
					salesIn[k]=idetails.getUnitprice();
					salesVolumeIn[k]=idetails.getAmount();
					waresIn[k]=idetails.getWares();
					sizzIn[k]=idetails.getSize();
					otherInprice=otherInprice+Double.parseDouble(salesIn[k]);
					otherInnum=otherInnum+Double.parseDouble(salesVolumeIn[k]);
					k++;
				}
				
				System.out.println(k);
				//对进项额冒泡排序
				for(int i=0;i<k-1;i++){
					flag=1;
					for(int j=0;j<k-1-i;j++){
						
						if(Double.parseDouble(salesIn[j])<Double.parseDouble(salesIn[j+1])){
							flag=0;
							tempD = salesIn[j];
							salesIn[j]=salesIn[j+1];
							salesIn[j+1]=tempD;
							tempD=waresIn[j];
							waresIn[j]=waresIn[j+1];
							waresIn[j+1]=tempD;
							tempD=sizzIn[j];
							sizzIn[j]=sizzIn[j+1];
							sizzIn[j+1]=tempD;
						}
					}
					if(flag==1)break;
				}
				//给salesInList和salesInVolumeList赋值
				for(int i=0;i<k;i++){
					if(k>4){
						if(i<4){
							otherInprice=otherInprice-Double.parseDouble(salesIn[i]);
						}
						
					}else{
						otherInprice=0;
						
					}
					
					System.out.println("sales进项排序结果"+salesIn[i]);
					salesInList.append(salesIn[i]+","+waresIn[i]+","+sizzIn[i]+",");
				}
				salesInList.append(otherInprice);
				//对进项量冒泡排序
				for(int i=0;i<k-1;i++){
					flag=1;
					for(int j=0;j<k-1-i;j++){
						if(Double.parseDouble(salesVolumeIn[j])<Double.parseDouble(salesVolumeIn[j+1])){
							flag=0;
							tempI = salesVolumeIn[j];
							salesVolumeIn[j]=salesVolumeIn[j+1];
							salesVolumeIn[j+1]=tempI;
							tempI=waresIn[j];
							waresIn[j]=waresIn[j+1];
							waresIn[j+1]=tempI;
							tempI=sizzIn[j];
							sizzIn[j]=sizzIn[j+1];
							sizzIn[j+1]=tempI;
						}
					}
				}
				//给salesInList和salesInVolumeList赋值
				for(int i=0;i<k;i++){
					if(k>4){
						if(i<4){
							otherInnum=otherInnum-Double.parseDouble(salesVolumeIn[i]);
						}
						
					}else{
						otherInnum=0;
						
					}
					
					System.out.println("salesVolume进项排序结果"+salesVolumeIn[i]);
					salesInVolumeList.append(salesVolumeIn[i]+","+waresIn[i]+","+sizzIn[i]+",");
				}	
				salesInVolumeList.append(otherOutnum);
				System.out.println("其他金额为"+salesInList);
		Double[] dayOutPrice =new Double[31];
		Double[] dayInPrice =new Double[31];
		
		//判断闰年
		int count=0;
		if(MM=="1"||MM=="3"||MM=="5"||MM=="7"||MM=="8"||MM=="10"||MM=="12"){
			count=31;
		}else if(MM=="2"){
			if((Integer.parseInt(YYYY)%4==0)&&(Integer.parseInt(YYYY)%100!=0)||(Integer.parseInt(YYYY)%400==0)){
				count=29;
			}else{
				count=28;
			}

		}else{
			count=30;
		}
		//获得每天的票据总价集合
		for (int i = 0; i < count; i++) {
			if(i<9){
				dayOutPrice[i]=outvoiceService.findByDay(day+"-"+"0"+(i+1));
				dayInPrice[i]=invoiceService.findByDay(day+"-"+"0"+(i+1));
			}else{
				dayOutPrice[i]=outvoiceService.findByDay(day+"-"+(i+1));
				dayInPrice[i]=invoiceService.findByDay(day+"-"+(i+1));
			}	
		}
		for (int i = 0; i < count; i++) {
			dayOutPrices.append(dayOutPrice[i]+",");
			dayInPrices.append(dayInPrice[i]+",");
		}
		System.out.println("action层："+dayOutPrices);
		System.out.println("action层："+dayInPrices);
		return SUCCESS;
	}

	public String getYYYY() {
		return YYYY;
	}

	public void setYYYY(String yYYY) {
		YYYY = yYYY;
	}

	public String getMM() {
		return MM;
	}

	public void setMM(String mM) {
		MM = mM;
	}

	public StringBuilder getDayOutPrices() {
		return dayOutPrices;
	}

	public void setDayOutPrices(StringBuilder dayOutPrices) {
		this.dayOutPrices = dayOutPrices;
	}

	public StringBuilder getDayInPrices() {
		return dayInPrices;
	}

	public void setDayInPrices(StringBuilder dayInPrices) {
		this.dayInPrices = dayInPrices;
	}

	public StringBuilder getSalesOutList() {
		return salesOutList;
	}

	public void setSalesOutList(StringBuilder salesOutList) {
		this.salesOutList = salesOutList;
	}

	public StringBuilder getSalesOutVolumeList() {
		return salesOutVolumeList;
	}

	public void setSalesOutVolumeList(StringBuilder salesOutVolumeList) {
		this.salesOutVolumeList = salesOutVolumeList;
	}

	public StringBuilder getSalesInList() {
		return salesInList;
	}

	public void setSalesInList(StringBuilder salesInList) {
		this.salesInList = salesInList;
	}

	public StringBuilder getSalesInVolumeList() {
		return salesInVolumeList;
	}

	public void setSalesInVolumeList(StringBuilder salesInVolumeList) {
		this.salesInVolumeList = salesInVolumeList;
	}

	
	
	
}
