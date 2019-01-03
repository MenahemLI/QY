package com.three.dms.webapp.action.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.three.dms.bean.Odetails;
import com.three.dms.bean.ProductMonth;
import com.three.dms.bean.ProdutNum;
import com.three.dms.service.Info.IGoodsService;
import com.three.dms.service.Info.IOdetailsService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ProductAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String productn;
	private Double sale;
	private Integer salenum;
	private String unit;
	private String dyear;
	private String wares;
	private String YYYY;
	private String FIRST;
	private String SECOND;
	private String THREE;
	@Autowired
	IOdetailsService iOdetailsService;
	@Autowired
	IGoodsService iGoodsService;

	List<Odetails> outdata = new ArrayList<>();
	List<ProdutNum> productdatalist = new ArrayList<>();
	List<ProductMonth> promonthlist = new ArrayList<>();
	TreeSet<String> productname = new TreeSet<>();
	List<String>  productnames =  new ArrayList<>();
	List<String> maxname = new ArrayList<>();
	private Double InputPrice = 0.00;

	@Action(value = "toProtuct", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsp/manager/product.jsp") })
	public String nowData() {
		String day = null;
		if (YYYY == null) {
			SimpleDateFormat time = new SimpleDateFormat("YYYY");
			Date date = new Date();
			day = time.format(date);
			YYYY = day;
		}

		// 查询出所有的销售产产品的名称
		productname = iGoodsService.findbytag("out");
		/**
		 * 第一步查询出没类产品一年的数据
		 */
		// 遍历产品名称，通过名称和年份查询产品
		
//		if(productname.size()==0){
//			productname.add("yi");
//			productname.add("er");
//			productname.add("san");
//		}else{
//			if(productname.size()>0&&productname.size()<=1){
//				productname.add("er");
//				productname.add("san");
//			}else{
//				if(productname.size()>1&&productname.size()<=2){
//					productname.add("san");
//				}
//			}
//		}
//		
		Integer id = 0;
		for (String name : productname) {
			outdata = iOdetailsService.findbyinvoicedatavague(YYYY, name);
			Double salenumber = 0.00;
			Integer sales = 0;
			// 叠加产品的销量和销售额
			for (Odetails odetails : outdata) {
				salenumber = salenumber + Double.parseDouble(odetails.getUnitprice());
				sales = sales + Integer.parseInt(odetails.getAmount());
			}
			ProdutNum produtNum = new ProdutNum(id, name, salenumber, sales, iOdetailsService.findunit(name), YYYY);
			if (outdata.size() == 0 || outdata == null) {

			} else {
				productdatalist.add(produtNum);
				id++;
				InputPrice = InputPrice + salenumber;
			}
		}
		/**
		 * 第二部查询出每类产品一年每个月的数据
		 */

		// 按照每类产品的销售总额进行冒泡排序
		for (int i = 0; i < productdatalist.size() - 1; i++) {
			for (int j = 1; j < productdatalist.size() - i; j++) {
				ProdutNum a;
				if (productdatalist.get(j - 1).getSale() < productdatalist.get(j).getSale()) { // 比较两个整数的大小
					a = productdatalist.get(j - 1);
					productdatalist.set((j - 1), productdatalist.get(j));
					productdatalist.set(j, a);// 交换数据
				}
			}
		}

		// 以下代码处理没有的情况

		if (productdatalist.size() < 1) {
			maxname.add("");
			
		} else {
			if (productdatalist.size() > 0 && productdatalist.size() <= 1) {
				maxname.add(productdatalist.get(0).getProductn());
				maxname.add(productdatalist.get(0).getProductn());
				maxname.add(productdatalist.get(0).getProductn());
			} else {
				if (productdatalist.size() > 1 && productdatalist.size() <= 2) {
					maxname.add(productdatalist.get(0).getProductn());
					maxname.add(productdatalist.get(1).getProductn());
					maxname.add(productdatalist.get(0).getProductn());
				} else {
					if (productdatalist.size() > 2) {
						maxname.add(productdatalist.get(0).getProductn());
						maxname.add(productdatalist.get(1).getProductn());
						maxname.add(productdatalist.get(2).getProductn());
					}
				}
			}
		}
		Double x[] = new Double[12];
		for (String name : maxname) {
			for (int i = 1; i <= 12; i++) {
				Double salenumber = 0.00;
				if (i >= 10) {
					outdata = iOdetailsService.findbyinvoicedatavagues(YYYY + "-" + i, name);
					System.out.println(YYYY + "-" + i);
					if (outdata != null && !outdata.isEmpty()) {
						for (Odetails odetails : outdata) {
							salenumber = salenumber + Double.parseDouble(odetails.getUnitprice());
						}
						x[i - 1] = salenumber;
					} else {
						x[i - 1] = 0.00;
					}
				} else {
					outdata = iOdetailsService.findbyinvoicedatavagues(YYYY + "-0" + i, name);
					System.out.println(YYYY + "-0" + i);
					if (outdata != null && !outdata.isEmpty()) {
						for (Odetails odetails : outdata) {
							salenumber = salenumber + Double.parseDouble(odetails.getUnitprice());
						}
						x[i - 1] = salenumber;
					} else {
						x[i - 1] = 0.00;
					}
				}
			}
			ProductMonth productMonth = new ProductMonth(x[0], x[1], x[2], x[3], x[4], x[5], x[6], x[7], x[8], x[9],
					x[10], x[11], name);
			promonthlist.add(productMonth);
		}
		return SUCCESS;
	}

	@Action(value = "tofindProtuctdate")
	public void findproductdata() {

	}

	@Action(value = "toProtuctdata")
	public void productdata() throws IOException {
		String Time;
		Time = YYYY;
		// 查询出所有的销售产产品的名称
		productname = iGoodsService.findbytag("out");
		/**
		 * 第一步查询出没类产品一年的数据
		 */
		System.out.println(productname + "这是产品的名称");
		Integer id = 0;
		for (String name : productname) {
			outdata = iOdetailsService.findbyinvoicedatavague(Time, name);
			Double salenumber = 0.00;
			Integer sales = 0;
			// 叠加产品的销量和销售额
			for (Odetails odetails : outdata) {
				salenumber = salenumber + Double.parseDouble(odetails.getUnitprice());
				sales = sales + Integer.parseInt(odetails.getAmount());
			}
			ProdutNum produtNum = new ProdutNum(id, name, salenumber, sales, iOdetailsService.findunit(name), Time);
			if (outdata.size() == 0 || outdata == null) {

			} else {
				productdatalist.add(produtNum);
				id++;
				InputPrice = InputPrice + salenumber;
			}
		}
		for (int i = 0; i < productdatalist.size() - 1; i++) {
			for (int j = 1; j < productdatalist.size() - i; j++) {
				ProdutNum a;
				if (productdatalist.get(j - 1).getSale() < productdatalist.get(j).getSale()) { // 比较两个整数的大小
					a = productdatalist.get(j - 1);
					productdatalist.set((j - 1), productdatalist.get(j));
					productdatalist.set(j, a);// 交换数据
				}
			}
		}

		Double elseSale = 0.0;

		if (productdatalist.size() > 10) {
			for (int i = 10; i < productdatalist.size(); i++) {
				elseSale = elseSale + productdatalist.get(i).getSale();
			}
		}
		JSONArray json = new JSONArray();
		JSONObject obj = new JSONObject();
		if (productdatalist.size() <= 10) {
			for (ProdutNum string : productdatalist) {
				System.out.println(string.getSale() + "我试一下行不行");
				obj.put("productname", string.getProductn());
				obj.put("salenum", string.getSale());
				json.add(obj);
			}
		} else {
			for (int i = 0; i < 9; i++) {
				obj.put("productname", productdatalist.get(i).getProductn());
				obj.put("salenum", productdatalist.get(i).getSale());
				json.add(obj);
			}
			obj.put("productname", "其它");
			obj.put("salenum", elseSale);
			json.add(obj);
		}
		System.out.println(json);

		HttpServletRequest reqest = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		reqest.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}

	@Action(value = "toProtuctname")
	public void productname() throws IOException {
		// 查询出所有的销售产产品的名称
		productname = iGoodsService.findbytag("out");

		JSONArray json = new JSONArray();
		JSONObject obj = new JSONObject();

		for (String name : productname) {
			obj.put("proname", name);
			json.add(obj);
		}

		System.out.println(json);

		HttpServletRequest reqest = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		reqest.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.println(json);

		out.flush();
		out.close();
	}

	@Action(value = "toProtucdatass", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsp/manager/tubiao.jsp") })
	public String productdatadata() {
		String Time;
		String firstname;
		String secondname;
		String threename;
		Time = YYYY;
		String A = "#";
		
      if(!FIRST.equals(A)&&!SECOND.equals(A)&&THREE.equals(A)){
    	  THREE=FIRST;
      }else{
    	  if(!FIRST.equals(A)&&SECOND.equals(A)&&!THREE.equals(A)){
    		  SECOND=FIRST;
          }else{
        	  if(!FIRST.equals(A)&&SECOND.equals(A)&&THREE.equals(A)){
        		  SECOND=FIRST;
        		  THREE=FIRST;
              }else{
            	  if(FIRST.equals(A)&&!SECOND.equals(A)&&!THREE.equals(A)){
            		  FIRST=SECOND;
                  }else{
                	  if(FIRST.equals(A)&&!SECOND.equals(A)&&THREE.equals(A)){
                		  FIRST=SECOND;
                		  THREE=SECOND;
                      }else{
                    	  if(FIRST.equals(A)&&SECOND.equals(A)&&!THREE.equals(A)){
                    		  FIRST=THREE;
                    		  SECOND=THREE;
                          }
                      }
                  }
              }
          }
      }
		
		firstname = FIRST;
		secondname = SECOND;
		threename  = THREE;
		
		
		// 获得到要查询的货物名称
		productname.add(firstname);
		productname.add(secondname);
		productname.add(threename);
		
		productnames.add(firstname);
		productnames.add(secondname);
		productnames.add(threename);
		
		/**
		 * 第一步查询出所要查询产品一年的数据
		 */
		Integer id = 0;
		for (String name : productname) {
			outdata = iOdetailsService.findbyinvoicedatavague(Time, name);
			Double salenumber = 0.00;
			Integer sales = 0;
			// 叠加产品的销量和销售额
			for (Odetails odetails : outdata) {
				salenumber = salenumber + Double.parseDouble(odetails.getUnitprice());
				sales = sales + Integer.parseInt(odetails.getAmount());
			}
			ProdutNum produtNum = new ProdutNum(id, name, salenumber, sales, iOdetailsService.findunit(name), Time);
			if (outdata.size() == 0 || outdata == null) {

			} else {
				productdatalist.add(produtNum);
				id++;
				InputPrice = InputPrice + salenumber;
			}
		}

		System.out.println("woceshiyixia"+InputPrice+"+++++++++++"+productdatalist);
		
		Double x[] = new Double[12];
		for (String name : productnames) {
			for (int i = 1; i <= 12; i++) {
				Double salenumber = 0.00;
				if (i >= 10) {
					outdata = iOdetailsService.findbyinvoicedatavagues(YYYY + "-" + i, name);
					System.out.println(YYYY + "-" + i);
					if (outdata != null && !outdata.isEmpty()) {
						for (Odetails odetails : outdata) {
							salenumber = salenumber + Double.parseDouble(odetails.getUnitprice());
						}
						x[i - 1] = salenumber;
					} else {
						x[i - 1] = 0.00;
					}
				} else {
					outdata = iOdetailsService.findbyinvoicedatavagues(YYYY + "-0" + i, name);
					System.out.println(YYYY + "-0" + i);
					if (outdata != null && !outdata.isEmpty()) {
						for (Odetails odetails : outdata) {
							salenumber = salenumber + Double.parseDouble(odetails.getUnitprice());
						}
						x[i - 1] = salenumber;
					} else {
						x[i - 1] = 0.00;
					}
				}
			}
			ProductMonth productMonth = new ProductMonth(x[0], x[1], x[2], x[3], x[4], x[5], x[6], x[7], x[8], x[9],
					x[10], x[11], name);
			promonthlist.add(productMonth);
		}
		return   SUCCESS;
	}

	public String getProductn() {
		return productn;
	}

	public void setProductn(String productn) {
		this.productn = productn;
	}

	public Double getSale() {
		return sale;
	}

	public void setSale(Double sale) {
		this.sale = sale;
	}

	public Integer getSalenum() {
		return salenum;
	}

	public void setSalenum(Integer salenum) {
		this.salenum = salenum;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getDyear() {
		return dyear;
	}

	public void setDyear(String dyear) {
		this.dyear = dyear;
	}

	public String getWares() {
		return wares;
	}

	public void setWares(String wares) {
		this.wares = wares;
	}

	public IOdetailsService getiOdetailsService() {
		return iOdetailsService;
	}

	public void setiOdetailsService(IOdetailsService iOdetailsService) {
		this.iOdetailsService = iOdetailsService;
	}

	public IGoodsService getiGoodsService() {
		return iGoodsService;
	}

	public void setiGoodsService(IGoodsService iGoodsService) {
		this.iGoodsService = iGoodsService;
	}

	public List<Odetails> getOutdata() {
		return outdata;
	}

	public void setOutdata(List<Odetails> outdata) {
		this.outdata = outdata;
	}

	public List<ProdutNum> getProductdatalist() {
		return productdatalist;
	}

	public void setProductdatalist(List<ProdutNum> productdatalist) {
		this.productdatalist = productdatalist;
	}

	public List<ProductMonth> getPromonthlist() {
		return promonthlist;
	}

	public void setPromonthlist(List<ProductMonth> promonthlist) {
		this.promonthlist = promonthlist;
	}

	public TreeSet<String> getProductname() {
		return productname;
	}

	public void setProductname(TreeSet<String> productname) {
		this.productname = productname;
	}

	public List<String> getMaxname() {
		return maxname;
	}

	public void setMaxname(List<String> maxname) {
		this.maxname = maxname;
	}

	public Double getInputPrice() {
		return InputPrice;
	}

	public void setInputPrice(Double inputPrice) {
		InputPrice = inputPrice;
	}

	public String getYYYY() {
		return YYYY;
	}

	public void setYYYY(String yYYY) {
		YYYY = yYYY;
	}

	public String getFIRST() {
		return FIRST;
	}

	public void setFIRST(String fIRST) {
		FIRST = fIRST;
	}

	public String getSECOND() {
		return SECOND;
	}

	public void setSECOND(String sECOND) {
		SECOND = sECOND;
	}

	public String getTHREE() {
		return THREE;
	}

	public void setTHREE(String tHREE) {
		THREE = tHREE;
	}
 
	
}
