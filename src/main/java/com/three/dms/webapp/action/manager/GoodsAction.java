package com.three.dms.webapp.action.manager;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.three.dms.bean.Goods;
import com.three.dms.service.Info.IGoodsService;

public class GoodsAction extends ActionSupport implements ModelDriven<Goods>{

	/**
	 * 实现Java.io.Serializable这个接口是为序列化,serialVersionUID 用来表明实现序列化类的不同版本间的兼容性。
	 * 如果你修改了此类, 要修改此值。否则以前用老版本的类序列化的类恢复时会出错。
	 */
	private static final long serialVersionUID = 1L;
	public Goods goods = new Goods();
	@Override
	public Goods getModel() {
		return goods;
	}
	
	//GoodsService 层
	@Autowired
	IGoodsService goodsService;
	//与jsp 进行连接
	/**
	 * 添加方法的测试
	 * */
	//	@Action(value="addGoods",
	//			results={@Result(name=SUCCESS,
	//			location="/find.jsp")})
	//	public String addGoods(){
	//		System.out.println(goods.toString());
	//		//所有栏目的信息
	//		System.out.println("访问到Action 层，下层函数为 Service ");
	//		goodsService.save(goods);		
	//		return SUCCESS;
	//	}
	/**
	 * 查询方法
	 * */
	//	@Action(value="findGoods",
	//			results={@Result(name=SUCCESS,
	//			location="/find.jsp")})
	//	public String findByG_format(){
	//		//所有栏目的信息
	//		String g_format = goods.getG_format();
	//		System.out.println("访问到Action 层，下层函数为 Service "+g_format);
	//		goodsService.findByG_format(g_format);	
	//		return SUCCESS;
	//}
	

	


}
