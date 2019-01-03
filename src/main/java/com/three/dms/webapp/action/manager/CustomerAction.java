package com.three.dms.webapp.action.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.TreeMap;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.three.dms.bean.Goods;
import com.three.dms.bean.Odetails;
import com.three.dms.bean.Outvoice;
import com.three.dms.bean.ProdutNum;
import com.three.dms.bean.customer;
import com.three.dms.service.Info.IOutvoiceService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class CustomerAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private IOutvoiceService outVoiceService;
	private Goods goods = new Goods();
	//private StringBuffer list = new StringBuffer();
	private StringBuffer line = new StringBuffer();
	private StringBuffer list_name = new StringBuffer();
	private String YYYY;
	List<customer> cutomer=new ArrayList<>();
    
	/**
	 * 跳转到客户数据表
	 * @throws IOException 
	 */
	@Action(value = "customerData", results = {
			@Result(name = SUCCESS, location = "/WEB-INF/jsp/manager/customerData.jsp") })
	public String customerData() throws IOException {
		//定义一个时间 
		String day = null;
		if(YYYY==null){
			SimpleDateFormat time = new SimpleDateFormat("YYYY");
			Date date = new Date();
			day = time.format(date);
			YYYY=day;
		}
		// 获得的数据库中的集合信息
		List<Outvoice> outVoice = new ArrayList<>();
		// 获取信息
		outVoice = outVoiceService.searchAllData(YYYY);
		// 定义第一个集合用来存储查出来的人名
		List<String> name_list = new ArrayList<>();
		// 定义第二个集合来存储人名对应的交易量
		List<Double> num_list = new ArrayList<>();

		for (int i = 0; i < outVoice.size(); i++) {
			// 定义计算总数
			Double count = 0.0;
			// 获取集合中的客户名字
			String customer_name = outVoice.get(i).getBuyer();
			// 重新遍历集合
			for (int j = 0; j < outVoice.size(); j++) {
				if (customer_name.equals(outVoice.get(j).getBuyer())) {
					count = count + Double.parseDouble(outVoice.get(j).getAddprice());
				}
			}
			// 判断是否已经在集合中存入姓名，若存入，则不再存
			boolean judge = true;
			if (name_list.size() != 0) {
				for (int j = 0; j < name_list.size(); j++) {
					if (name_list.get(j).equals(customer_name)) {
						judge = false;
						break;
					}
				}
			}
			// 先将名字压入第一个集合
			if (judge) {
				name_list.add(customer_name);
				// 压入人名对应的金钱
				num_list.add(count);
			}
		}
		// 对集合中的元素进行排序
		for (int i = 0; i < num_list.size() - 1; i++) { 
			for (int j = 0; j < num_list.size() - i - 1; j++) {
				if (num_list.get(j) < num_list.get(j+1)) { 
					//将大的放到前面
					Double temp = num_list.get(j);
					String name_two = name_list.get(j);
					num_list.set(j, num_list.get(j+1));
					name_list.set(j, name_list.get(j+1));
					num_list.set(j+1,temp);
					name_list.set(j+1, name_two);			
				}
			}
		}
      /**
       * 下面对折线图的数据进行处理
       * */
      //获取交易量在前五名的客户名字,名字在name_list中存储
      for(int i = 0;i < name_list.size();i++)
      {
    	  String name = name_list.get(i);
    	  line.append(name+","+outVoiceService.findByNameDate(name,YYYY));
      }
		return SUCCESS;

	}
	@Action(value = "toCustomerdata")
	public void productdata() throws IOException {
		//定义一个时间 
				String day = null;
				if(YYYY==null){
					SimpleDateFormat time = new SimpleDateFormat("YYYY");
					Date date = new Date();
					day = time.format(date);
					YYYY=day;
				}
				// 获得的数据库中的集合信息
				List<Outvoice> outVoice = new ArrayList<>();
				// 获取信息
				outVoice = outVoiceService.searchAllData(YYYY);
				// 定义第一个集合用来存储查出来的人名
				List<String> name_list = new ArrayList<>();
				// 定义第二个集合来存储人名对应的交易量
				List<Double> num_list = new ArrayList<>();

				for (int i = 0; i < outVoice.size(); i++) {
					// 定义计算总数
					Double count = 0.0;
					// 获取集合中的客户名字
					String customer_name = outVoice.get(i).getBuyer();
					// 重新遍历集合
					for (int j = 0; j < outVoice.size(); j++) {
						if (customer_name.equals(outVoice.get(j).getBuyer())) {
							count = count + Double.parseDouble(outVoice.get(j).getAddprice());
						}
					}
					// 判断是否已经在集合中存入姓名，若存入，则不再存
					boolean judge = true;
					if (name_list.size() != 0) {
						for (int j = 0; j < name_list.size(); j++) {
							if (name_list.get(j).equals(customer_name)) {
								judge = false;
								break;
							}
						}
					}
					// 先将名字压入第一个集合
					if (judge) {
						name_list.add(customer_name);
						// 压入人名对应的金钱
						num_list.add(count);
					}
				}
				// 对集合中的元素进行排序
				for (int i = 0; i < num_list.size() - 1; i++) { 
					for (int j = 0; j < num_list.size() - i - 1; j++) {
						if (num_list.get(j) < num_list.get(j+1)) { 
							//将大的放到前面
							Double temp = num_list.get(j);
							String name_two = name_list.get(j);
							num_list.set(j, num_list.get(j+1));
							name_list.set(j, name_list.get(j+1));
							num_list.set(j+1,temp);
							name_list.set(j+1, name_two);			
						}
					}
				}
				/**
				 * 将排好序的信息封装到一个对象中，将对象放置到一个集合中
				 * */
				JSONArray json = new JSONArray();
				JSONObject obj = new JSONObject();
				for(int i = 0;i < num_list.size();i++)
					{			
						obj.put("customer_name", name_list.get(i));
						obj.put("sale", num_list.get(i));
						json.add(obj);
					    System.out.println("这是json" + json);		
				}
				
				HttpServletRequest reqest = ServletActionContext.getRequest();
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html;charset=utf-8");
				reqest.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();
				out.println(json);
				out.flush();
				out.close();
	}
	/**
	 * 跳转到客户信息
	 */
	@Action(value = "customerInformation", results = {
			@Result(name = SUCCESS, location = "/WEB-INF/jsp/manager/customerInformation.jsp") })
	public String customerInformation() {
		//对日期的操做
		String day = null;
		if(YYYY==null){
			SimpleDateFormat time = new SimpleDateFormat("YYYY");
			Date date = new Date();
			day = time.format(date);
			YYYY=day;
		}
		System.out.println("going");
		// 获得的数据库中的集合信息
		List<Outvoice> outVoice = new ArrayList<>();
		// 根据日期获取信息
		outVoice = outVoiceService.searchAllData(YYYY);
		// 定义第一个集合用来存储查出来的人名
		System.out.println(outVoice+"      获取的信息");
		for (Outvoice cusstomer : outVoice) {
			customer cus = new customer();
			cus.setAddress(cusstomer.getAddress());
			String db_addr = cusstomer.getAddress(); // 获取从数据库获取的地址
			cus.setDate(cusstomer.getOpendate());
			cus.setName(cusstomer.getBuyer());
			String db_name = cusstomer.getBuyer(); // 获取从数据库获取的名字
			cus.setPhone(cusstomer.getClient_tel());
			boolean judge = cutomer.isEmpty();
			boolean judge2 = true;
			System.out.println(judge);
			if(judge)
				judge2=true;
			else
			{
				for (customer cutomer2 : cutomer) {
					
					String list_name = cutomer2.getName();
							System.out.println(list_name + "      获取的集合名字");
							System.out.println(db_name+"    获取的名字信息");
					String list_addr = cutomer2.getAddress();
							System.out.println(list_addr + "      获取的集合地址");
							System.out.println(db_addr+"    获取的地址信息");
							if(list_name.equals(db_name)&&list_addr.equals(db_addr))
							{
								
									judge2=false;
									break;
							}
							else{
								judge2=true;
							}		
				}
			}
			if(judge2)
				cutomer.add(cus);
		}
		System.out.println(cutomer);
		return SUCCESS;
  
	}

	public String getYYYY() {
		return YYYY;
	}

	public void setYYYY(String yYYY) {
		YYYY = yYYY;
	}

	public StringBuffer getLine() {
		return line;
	}

	public void setLine(StringBuffer line) {
		this.line = line;
	}

	public List<customer> getCutomer() {
		return cutomer;
	}

	public void setCutomer(List<customer> cutomer) {
		this.cutomer = cutomer;
	}

	public StringBuffer getList_name() {
		return list_name;
	}

	public void setList_name(StringBuffer list_name) {
		this.list_name = list_name;
	}
 
}
