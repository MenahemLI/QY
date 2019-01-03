package com.three.dms.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.three.dms.bean.Outvoice;
import com.three.dms.dao.Info.IOutvoiceDao;
@Repository
public class OutvoiceDao implements IOutvoiceDao{

	Outvoice outvoice = new Outvoice();
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	@Override
	public void save(Outvoice outvoice) {
		System.out.println("持久层：存储进项数据");
		getSession().save(outvoice);
		
	}

	@Override
	public Outvoice findById(long id) {
		String hql = "from Outvoice outvoice where outvoice.id = ? ";
		Outvoice outvoice  =(Outvoice) getSession().createQuery(hql).setLong(0, id).uniqueResult();		
		System.out.println("查询持久层");
		return outvoice;
	}

	@Override
	public void update(Outvoice outvoice) {
		// TODO Auto-generated method stub
		System.out.println("更新持久层");
		System.out.println(outvoice);
		getSession().update(outvoice);
	}

	@Override
	public Outvoice findByO_num(String o_number) {
		// TODO Auto-generated method stub
		String hql = "from Outvoice outvoice where outvoice.o_number = ? ";
		Outvoice outvoice  =(Outvoice) getSession().createQuery(hql).setString(0, o_number).uniqueResult();		
		return outvoice;
	}

	@Override
	public List<Outvoice> findByuser(String username) {
		System.out.println("持久层User"+username);
		List<Outvoice> list = new ArrayList<>();
		String hql = "from Outvoice outvoice where outvoice.username = ? ";
		list = getSession().createQuery(hql).setString(0, username).list();	
		return list;
	}

	@Override
	public boolean findByO_num_judge(String o_number) {
		String hql = "from Outvoice outvoice where outvoice.o_number = ? ";
		Outvoice outvoice = (Outvoice) getSession().createQuery(hql).setString(0, o_number).uniqueResult();
		//若为空，则前台可以保存，则可以进行，返回真
		if (outvoice == null)
			return true;
		else
			return false;
	
	}
	@Override
	public Double findByMM(String opendate) {
		double monthPrice=0;
		List<Outvoice> list = new ArrayList<>();
		String hql = "from Outvoice outvoice where outvoice.opendate like '%"+opendate+"%'";
		list = getSession().createQuery(hql).list();
		for (Outvoice outvoice : list) {
			System.out.println(outvoice.getAddprice());
			monthPrice = monthPrice+Double.parseDouble(outvoice.getAddprice());
		}
		System.out.println("持久层："+monthPrice);
		return monthPrice;
	}
	@Override
	public Double findByDay(String opendate) {
		double dayPrice=0;
		List<Outvoice>list =new ArrayList<>();
		String hql = "from Outvoice outvoice where outvoice.opendate = ? ";
		list = getSession().createQuery(hql).setString(0, opendate).list();
		for (Outvoice outvoice : list) {
			dayPrice = dayPrice+Double.parseDouble(outvoice.getAddprice());
		}
		return dayPrice;
	}
	@Override
	public Double findTexesPrice(String opendate) {
		double texesPrice=0;
		List<Outvoice>list =new ArrayList<>();
		String hql = "from Outvoice outvoice where outvoice.opendate like '%"+opendate+"%'";
		list=getSession().createQuery(hql).list();
		for (Outvoice outvoice : list) {
			texesPrice = texesPrice+Double.parseDouble(outvoice.getTaxesprice());
		}
		return texesPrice;
	}
	/*
	 * 查询数据库中的所有数据
	 * **/
	@Override
	public List<Outvoice> searchAllData() {
		List<Outvoice> list =new ArrayList<>();
		String hql = "from Outvoice outvoice";  
		list =getSession().createQuery(hql).list(); 
		return list;
	}
	//通过年查找所有销项发票数据
	@Override
	public List<Outvoice> searchAllData(String YYYY) {
		List<Outvoice> list = new ArrayList<>();
		String hql = "from Outvoice outvoice where outvoice.opendate like '%" + YYYY + "%'";
		list = getSession().createQuery(hql).list();
		return list;
	}

	@Override
	public StringBuffer findByNameDate(String name, String YYYY) {
		List<Outvoice> list =new ArrayList<>();
		//返回值
		StringBuffer string = new StringBuffer();
		//记录每个月的金钱
		double []num = {0,0,0,0,0,0,0,0,0,0,0,0};
		//hql语句中 name 为已知的  时间暂时为不确定，后期处理
		String hql = "from Outvoice outvoice where outvoice.buyer = ? and outvoice.opendate like '%"+YYYY+"%'";  
		//获得根据条件查出来的信息
		list = getSession().createQuery(hql).setString(0, name).list(); 
		for (Outvoice element : list) {
			//获取 YYYY-MM 中的MM
			String MM = element.getOpendate().substring(5,7);
			//先将不同的月份信息分配到不同的case中，分别作处理s
			switch(MM){
			case "01":{
				         num[0] = Double.parseDouble(element.getAddprice())+num[0];
				         continue;
				         }
			case "02":{
				         num[1] = Double.parseDouble(element.getAddprice())+num[1];
				         continue;
				         }
			case "03":{
				         num[2] = Double.parseDouble(element.getAddprice())+num[2];
				         continue;
				         }
			case "04":{
				         num[3] = Double.parseDouble(element.getAddprice())+num[3];
				         continue;
				         }
			case "05":{
				         num[4] = Double.parseDouble(element.getAddprice())+num[4];
				         continue;
				         }
			case "06":{
				         num[5] = Double.parseDouble(element.getAddprice())+num[5];
				         continue;
				         }
			case "07":{
				         num[6] = Double.parseDouble(element.getAddprice())+num[6];
				         continue;
				         }
			case "08":{
				         num[7] = Double.parseDouble(element.getAddprice())+num[7];
				         continue;
				         }
			case "09":{
				         num[8] = Double.parseDouble(element.getAddprice())+num[8];
				         continue;
				         }
			case "10":{
				         num[9] = Double.parseDouble(element.getAddprice())+num[9];
				         continue;
				         }
			case "11":{
				         num[10] = Double.parseDouble(element.getAddprice())+num[10];
				         continue;
				         }
			case "12":{
				         num[11] = Double.parseDouble(element.getAddprice())+num[11];
				         continue;
				         }
		}
	}
		for (int i = 0; i < num.length; i++) {
			string.append(num[i]+",");
			
		}
		System.out.println(string+"0000000000000000000000000000000000000000000000000");
		return string;
	}
}
