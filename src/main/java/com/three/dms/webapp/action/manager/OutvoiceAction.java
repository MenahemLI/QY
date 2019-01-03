package com.three.dms.webapp.action.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import com.three.dms.bean.Goods;
import com.three.dms.bean.Odetails;
import com.three.dms.bean.Outvoice;
import com.three.dms.bean.Users;
import com.three.dms.service.Info.IGoodsService;
import com.three.dms.service.Info.IOdetailsService;
import com.three.dms.service.Info.IOutvoiceService;

public class OutvoiceAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String amount;
	private String unit;
	private String opendate;
	private String vendor;
	private String o_number;
	private String g_name;
	private String g_number;
	private String g_price;
	private String g_format;
	private String username;
	private Users user;
	private Double heji;
	private List<Outvoice> outvoiceList;

	private List outList = new Vector();
	Outvoice outvoice = new Outvoice();
	Odetails odetails = new Odetails();
	@Autowired
	IOutvoiceService outvoiceService;
	@Autowired
	IGoodsService goodsService;
	@Autowired
	IOdetailsService odetailsService;
	private String buyer;
	private String client_tel;
	private String price;
	private String taxes;
	private String date;
	private String address;
	private String goodsName;
	private String o_code;
	
	/**
	 * 跳转到添加进项数据
	 * @return
	 */
	@Action(value="toAddOutvoice",
			results={@Result(name=SUCCESS,
			location="/WEB-INF/jsp/manager/addOutvoice.jsp")})
	public String toAddOutvoice(){
		
	
		System.out.println("跳转到添加页面");
		HttpSession session = ServletActionContext.getRequest().getSession();
		user=(Users) session.getAttribute("user");
		System.out.println(user);
		System.out.println(user.getU_number());
		outvoiceList= outvoiceService.findByuser(user.getU_number());
		for (Outvoice outvoice : outvoiceList) {
			String[] gName = outvoice.getGoodsName().split(", ");
			Vector outs = new Vector();
			outs.add(outvoice);
			for (int i = 0; i < gName.length; i++) {
				List<Goods> goods =goodsService.findByG_name(gName[i]);
				for (Goods goods2 : goods) {
					outs.add(goods2);
				}
				
			}
			outList.add(outs);
		
		}
		System.out.println("应该得到的是"+outList);
		System.out.println("不看这个："+outvoiceList);
		System.out.println("session里面的user"+user);
		return SUCCESS;
	}
	/**
	 * 添加进项数据
	 * @return
	 */
	@Action("addOutvoice")
	public void addInvoice(){
			//获得从前抬的outvoice集合
			Outvoice outvoice =new Outvoice(null, goodsName, buyer, opendate, client_tel, unit, o_number, amount, price, taxes, username, date, null, o_code, null,address,null);
			System.out.println("WEB层:存储进项数据");
			//将前台获得的货物名称集合注入到outvoice集合中
			outvoice.setGoodsName(g_name);
			//格式化日期格式
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			outvoice.setDate(sdf.format(date));
			DecimalFormat df = new DecimalFormat("#.00");
			System.out.println("各个假哥"+outvoice.getPrice());
			//获得各个货物的总价相加得总价后注入到outvoice的addPrice中
			String[] oneprice=outvoice.getPrice().split(",");
			String[] onetaxes=outvoice.getTaxes().split(",");
			Double addprice=new Double(0);
			Double texesprice=new Double(0);
			for (int i = 0; i < oneprice.length; i++) {
				addprice = Double.parseDouble(oneprice[i])+addprice; 
				texesprice=texesprice+Double.parseDouble(oneprice[i])*(Double.parseDouble(onetaxes[i]));
			}
			texesprice=Double.parseDouble(df.format(texesprice));
			heji=texesprice+addprice;
			outvoice.setAddprice(Double.toString(addprice));
			outvoice.setTaxesprice(Double.toString(texesprice));
			outvoice.setHeji(heji);
			outvoiceService.save(outvoice);
			System.out.println(outvoice);
		    String[] spname = g_name.split(", ");
			String[] spformat = g_format.split(", ");
			String[] spprice = g_price.split(", ");	
			String[] sunit = unit.split(", ");
			String[] samount = amount.split(", ");
			String[] sprice = price.split(", ");
			
			for(int i=0;i<spname.length;i++)
			{
				if(goodsService.findByG_nameAndformat(spname[i],spformat[i])==null){
				Goods goods = new Goods(null, spname[i], null, spprice[i], spformat[i],"out");
				goodsService.save(goods); 
				}
			}
			
			for (int i = 0; i < spname.length; i++) {
				if (odetailsService.findbysizeandinvoicedata(spname[i], opendate.substring(0, 7), buyer,spformat[i]) == null) {
					System.out.println("i love you !" + spname[i] + opendate.substring(0, 7) + buyer);
					Odetails odetails = new Odetails(null, spname[i], spformat[i], sunit[i], sprice[i], samount[i], buyer,
							opendate.substring(0, 7));
					odetailsService.save(odetails);
				} else {
					// 创建一个double类型的n u m用来存放原来的货物数量
					int num;
					double pricenum;
					// System.out.println("i don't love you "+spname[i]+"
					// "+opendate.substring(0, 7)+" "+vendor);
					// 获取到需要更新的货物属性
					Odetails odetails = odetailsService.findbysizeandinvoicedata(spname[i], opendate.substring(0, 7),
							buyer,spformat[i]);
					// System.out.println(idetail.getAmount()+" "+samount[i]);
					// 将货物的数量进行更新
					num =Integer.parseInt(odetails.getAmount()) + Integer.parseInt(samount[i]);
					pricenum = Double.parseDouble(odetails.getUnitprice()) + Double.parseDouble(sprice[i]);
					// 将需要更新的货物单价写入
					odetails.setUnitprice(String.valueOf(pricenum));
					// 将更新的货物数量写入
					odetails.setAmount(String.valueOf(num));
					odetailsService.update(odetails);
				}
			}
			
	}
	
	/**
	 * 根据票据编号查找商品是否重复
	 * @throws IOException
	 */
	@Action(value="findOutvoice")
	public void findInvoice() throws IOException{
		HttpServletRequest reqest= ServletActionContext.getRequest();
		reqest.setCharacterEncoding("UTF-8");  
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = response.getWriter();
		String map = reqest.getParameter("o_number");
		System.out.println(map);
		boolean bool = outvoiceService.findByO_num_judge(map);
		if(bool==true){
			 String res="SUCCESS";
			 System.out.println(res);
			  out.println(res);
			  out.flush();  
			  out.close(); 
		}else {
			 String res="ERROR";
			  out.println(res);
			  out.flush();  
			  out.close(); 
		}
	}  
	
	public String getG_name() {
		return g_name;
	}
	public void setG_name(String g_name) {
		this.g_name = g_name;
	}
	public String getG_number() {
		return g_number;
	}
	public void setG_number(String g_number) {
		this.g_number = g_number;
	}
	public String getG_price() {
		return g_price;
	}
	public void setG_price(String g_price) {
		this.g_price = g_price;
	}
	public String getG_format() {
		return g_format;
	}
	public void setG_format(String g_format) {
		this.g_format = g_format;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getO_number() {
		return o_number;
	}
	public void setO_number(String o_number) {
		this.o_number = o_number;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public List<Outvoice> getOutvoiceList() {
		return outvoiceList;
	}
	public void setOutvoiceList(List<Outvoice> outvoiceList) {
		this.outvoiceList = outvoiceList;
	}
	@SuppressWarnings("rawtypes")
	public List getOutList() {
		return outList;
	}
	public void setOutList(List outList) {
		this.outList = outList;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getOpendate() {
		return opendate;
	}
	public void setOpendate(String opendate) {
		this.opendate = opendate;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	
	public Outvoice getOutvoice() {
		return outvoice;
	}
	public void setOutvoice(Outvoice outvoice) {
		this.outvoice = outvoice;
	}
	public Odetails getOdetails() {
		return odetails;
	}
	public void setOdetails(Odetails odetails) {
		this.odetails = odetails;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public String getClient_tel() {
		return client_tel;
	}
	public void setClient_tel(String client_tel) {
		this.client_tel = client_tel;
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
	public String getO_code() {
		return o_code;
	}
	public void setO_code(String o_code) {
		this.o_code = o_code;
	}
	public Double getHeji() {
		return heji;
	}
	public void setHeji(Double heji) {
		this.heji = heji;
	}
	
	

}
