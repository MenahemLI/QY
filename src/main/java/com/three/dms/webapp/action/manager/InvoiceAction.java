package com.three.dms.webapp.action.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
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
import com.three.dms.bean.Idetails;
import com.three.dms.bean.Invoice;
import com.three.dms.bean.Users;
import com.three.dms.service.Info.IGoodsService;
import com.three.dms.service.Info.IIdetailsService;
import com.three.dms.service.Info.IInvoiceService;

public class InvoiceAction extends ActionSupport {

	/**
	 * 从jsp传过来的值是通过get方法获取，而给jsp传值是通过set设置;出现的问题就是传值没反应， 第一步，检测jstl的包是否导入
	 * 第二步，检测jsp页面的属性名称是否和action中一样
	 * 第三步（可能出现这种情况），在传值的action方法中使用的对象是重新创建的，也就是说可能你在action方法中使用了 User user=
	 * new User(***),这种
	 * 方式又创建了一个对象，因为局部对象覆盖全局对象的原因，传建完后的对象并不会执行set方法，也就是不会吧值给到jsp页面
	 * 那么，给jsp页面的那个是创建的对象呢，对，就是全局设置的对象，而此时，全局对象并没有拿到数据库值，也就没有值返回给jsp了
	 */
	private String vendor;
	private Long idi;
	private Integer idg;
	private String i_number;
	private Goods goods;
	private String client_tel;
	private String price;
	private String taxes;
	private String date;
	private String address;
	private String goodsName;
	private String amount;
	private String unit;
	private Double heji;
	private String opendate;
	private String g_name;
	private String i_code;
	private String g_number;
	private String g_price;
	private String g_format;
	private String username;
	private String tag="int";
	private Users user;
	private List inList = new Vector();
	private List<Invoice> invoiceList;
	private static final long serialVersionUID = 1L;

	@Autowired
	IInvoiceService invoiceService;
	@Autowired
	IGoodsService goodsService;
	@Autowired
	IIdetailsService idetailsService;

	Invoice invoice = new Invoice();
	Idetails idetails = new Idetails();

	/**
	 * 跳转到添加进项数据
	 * 
	 * @return
	 */
	@Action(value = "toAddInvoice", results = {
			@Result(name = SUCCESS, location = "/WEB-INF/jsp/manager/addInvoice.jsp") })
	public String toAddInvoice() {
		System.out.println("跳转到添加页面");
		HttpSession session = ServletActionContext.getRequest().getSession();

		user = (Users) session.getAttribute("user");
		invoiceList = invoiceService.findByuser(user.getU_number());
		for (Invoice invoice : invoiceList) {
			String[] gName = invoice.getGoodsName().split(", ");
			Vector ins = new Vector();
			ins.add(invoice);
			for (int i = 0; i < gName.length; i++) {
				List<Goods> goods =goodsService.findByG_name(gName[i]);
				for (Goods goods2 : goods) {
					ins.add(goods2);
				}
				
			}
			inList.add(ins);
		}
		System.out.println("应该得到的是"+inList);
		System.out.println("不看这个："+invoiceList);
		System.out.println("session里面的user"+user);
		return SUCCESS;
	}

	/**
	 * 添加进项数据
	 * 
	 * @return
	 * @throws ParseException 
	 */
	@Action("addInvoice")
	public void addInvoice() throws ParseException {
		boolean estate;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdft = (SimpleDateFormat)DateFormat.getDateInstance();  
		sdft.applyPattern("yyyy-MM-dd");  
		Date nowd=new Date();
        Date ddTest = sdft.parse(opendate);  
        System.err.println("这是时间偏移量"+(nowd.getTime()- 180* 24 * 60 * 60 * 1000));
        long bijiao=nowd.getTime()-(long)180 * 24 * 60 * 60 * 1000;
        System.err.println("这是180天之前的时间"+sdf.format(bijiao));
		Invoice invoices = new Invoice(null, vendor, opendate, client_tel, unit, i_number, amount, price, taxes, username, date, null, i_code,null, address, goodsName,false,null);
		
		
		System.out.println("WEB层:存储进项数据");
		System.out.println(bijiao<ddTest.getTime());
		if(bijiao>ddTest.getTime()){
        	estate=true;
        	invoices.setEstate(estate);
        }
		invoices.setGoodsName(g_name);
		
		DecimalFormat df = new DecimalFormat("#.00");
		Date date = new Date();
		invoices.setDate(sdf.format(date));
		System.out.println(invoices + "11111111111111111111");
		System.out.println("各个假哥" + price);
		String[] oneprice = price.split(", ");
		String[] onetaxes=invoices.getTaxes().split(",");
		Double addprice = new Double(0);
		Double texesprice=new Double(0);
		for (int i = 0; i < oneprice.length; i++) {
			addprice = Double.parseDouble(oneprice[i]) + addprice;
			texesprice=texesprice+Double.parseDouble(oneprice[i])*(Double.parseDouble(onetaxes[i]));
		}
		texesprice=Double.parseDouble(df.format(texesprice));
		heji=texesprice+addprice;
		System.out.println("ffwefwfrgregergerfregerg" + addprice);
		invoices.setAddprice(Double.toString(addprice));
		invoices.setTaxesprice(Double.toString(texesprice));
		invoices.setHeji(heji);
		invoiceService.save(invoices);
		System.out.println(invoice + "222222222222222222222");

		
		String[] spname = g_name.split(", ");
		String[] spformat = g_format.split(",");
		String[] spprice = g_price.split(", ");
		String[] sunit = unit.split(", ");
		String[] samount = amount.split(", ");
		String[] sprice = price.split(", ");
		String[] staxes = taxes.split(", ");
		System.out.println(spformat);
		for (int i = 0; i < spname.length; i++) {
			if (goodsService.findByG_nameAndformat(spname[i],spformat[i]) == null) {
				Goods goods = new Goods(null, spname[i], null, spprice[i], spformat[i],"int");
				goodsService.save(goods);
			}
		}

		// 用来存放税额
		String taxra;

		for (int i = 0; i < spname.length; i++) {
			if (idetailsService.findbysizeandinvoicedata(spname[i], opendate.substring(0, 7), vendor,
					spformat[i]) == null) {
				System.out.println("i love you !" + spname[i] + opendate.substring(0, 7) + vendor + spformat[i]);
				taxra = df.format(Double.parseDouble(staxes[i]) * Double.parseDouble(sprice[i])*0.01);
				Idetails idetail = new Idetails(null, spname[i], spformat[i], sunit[i], sprice[i], samount[i], vendor,
						opendate.substring(0, 7), taxra);
				idetailsService.save(idetail);
			} else {
				// 创建一个double类型的n u m用来存放原来的货物数量
				int num;
				double pricenum;
				// System.out.println("i don't love you "+spname[i]+"
				// "+opendate.substring(0, 7)+" "+vendor);
				// 获取到需要更新的货物属性
				Idetails idetail = idetailsService.findbysizeandinvoicedata(spname[i], opendate.substring(0, 7), vendor,
						spformat[i]);
				// System.out.println(idetail.getAmount()+" "+samount[i]);
				// 将货物的数量进行更新
				num = Integer.parseInt(idetail.getAmount()) + Integer.parseInt(samount[i]);
				pricenum = Double.parseDouble(idetail.getUnitprice()) + Double.parseDouble(sprice[i]);
				taxra =df.format( Double.parseDouble(staxes[i]) * Double.parseDouble(sprice[i])*0.01 + Double.parseDouble(idetail.getTaxrate()));
				// 将需要更新的货物单价写入
				idetail.setUnitprice(String.valueOf(pricenum));
				// 将更新的货物数量写入
				idetail.setTaxrate(taxra);
				idetail.setAmount(String.valueOf(num));
				idetailsService.update(idetail);
			}
		}
	}

	/**
	 * 查询自己添加的进项数据
	 * 
	 * @return
	 */
	@Action(value = "searchMyInvoice", results = {
			@Result(name = SUCCESS, location = "/WEB-INF/jsp/manager/addInvoice.jsp") })
	public String searchMyInvoice() {

		return SUCCESS;

	}

	/**
	 * 根据票据编号查找商品是否重复
	 * 
	 * @throws IOException
	 */
	@Action(value = "findInvoice")
	public void findInvoice() throws IOException {
		HttpServletRequest reqest = ServletActionContext.getRequest();
		reqest.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String map = reqest.getParameter("i_number");
		System.out.println(map);
		boolean bool = invoiceService.findByI_num_judge(map);
		if (bool == true) {
			String res = "SUCCESS";
			System.out.println(res);
			out.println(res);
			out.flush();
			out.close();
		} else {
			String res = "ERROR";
			out.println(res);
			out.flush();
			out.close();
		}
	}

	/**
	 * 跳转到修改(需要改进或重写)
	 * 
	 * @return
	 */


	/**
	 * 修改(需要改进或重写)
	 * 
	 * @return
	 */
	@Action(value = "updateInvoice", results = {
			@Result(name = SUCCESS, location = "/WEB-INF/jsp/manager/updateInvoice.jsp") })
	public String updateInvoice() {

		invoice.setId(idi);
		invoice.setGoodsName(g_number);
		invoiceService.update(invoice);
		goods = new Goods(idg, g_name, g_number, g_price, g_format,tag);
		goodsService.update(goods);
		return SUCCESS;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
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

	public Long getIdi() {
		return idi;
	}

	public void setIdi(Long idi) {
		this.idi = idi;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Integer getIdg() {
		return idg;
	}

	public void setIdg(Integer idg) {
		this.idg = idg;
	}

	public String getI_number() {
		return i_number;
	}

	public void setI_number(String i_number) {
		this.i_number = i_number;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public List<Invoice> getInvoiceList() {
		return invoiceList;
	}

	public void setInvoiceList(List<Invoice> invoiceList) {
		this.invoiceList = invoiceList;
	}

	public String getOpendate() {
		return opendate;
	}

	public void setOpendate(String opendate) {
		this.opendate = opendate;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Idetails getIdetails() {
		return idetails;
	}

	public void setIdetails(Idetails idetails) {
		this.idetails = idetails;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
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

	public String getI_code() {
		return i_code;
	}

	public void setI_code(String i_code) {
		this.i_code = i_code;
	}

	public List getInList() {
		return inList;
	}

	public void setInList(List inList) {
		this.inList = inList;
	}

	public Double getHeji() {
		return heji;
	}

	public void setHeji(Double heji) {
		this.heji = heji;
	}
	
}
