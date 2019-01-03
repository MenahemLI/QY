package com.three.dms.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.three.dms.bean.Goods;
import com.three.dms.bean.Invoice;
import com.three.dms.bean.Outvoice;
import com.three.dms.dao.Info.IIvoiceDao;

import javassist.expr.NewArray;

@Repository
public class InvoiceDao implements IIvoiceDao {

	Invoice invoice =  new Invoice();
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	@Override
	public void save(Invoice invoice) {
		// TODO Auto-generated method stub
		System.out.println("持久层：存储进项数据");
		getSession().save(invoice);
	}
	@Override
	public Invoice findById(long id) {
		// TODO Auto-generated method stub
		String hql = "from Invoice invoice where invoice.id = ? ";
		Invoice invoice  =(Invoice) getSession().createQuery(hql).setLong(0, id).uniqueResult();		
		
		
		
		System.out.println("查询持久层");
		return invoice;
	}
	@Override
	public void update(Invoice invoice) {
		// TODO Auto-generated method stub
		System.out.println("更新持久层");
		System.out.println(invoice);
		getSession().update(invoice);
		
	}
	@Override
	public Invoice findByI_num(String i_number) {
		// TODO Auto-generated method stub
		String hql = "from Invoice invoice where invoice.i_number = ? ";
		Invoice invoice  =(Invoice) getSession().createQuery(hql).setString(0, i_number).uniqueResult();		
		return invoice;
	}
	@Override
	public List<Invoice> findByuser(String username) {
		// TODO Auto-generated method stub
		List<Invoice> list = new ArrayList<>();
		String hql = "from Invoice invoice where invoice.username = ? ";
		list = getSession().createQuery(hql).setString(0, username).list();	
		return list;
	}
	@Override
	public boolean findByI_num_judge(String i_number) {
		String hql = "from Invoice invoice where invoice.i_number = ? ";
		Invoice invoice = (Invoice) getSession().createQuery(hql).setString(0, i_number).uniqueResult();
		//若为空，则前台可以保存，则可以进行，返回真
		if (invoice == null)
			return true;
		else
			return false;
	}
	@Override
	public Double findByMM(String opendate) {
		double monthPrice=0;
		List<Invoice> list = new ArrayList<>();
		String hql = "from Invoice invoice where invoice.opendate like '%"+opendate+"%'";
		list = getSession().createQuery(hql).list();
		for (Invoice invoice : list) {
			System.out.println(invoice.getAddprice());
			monthPrice = monthPrice+Double.parseDouble(invoice.getAddprice());
		}
		System.out.println("持久层："+monthPrice);
		return monthPrice;
	}
	@Override
	public Double findByDay(String opendate) {
		double dayPrice=0;
		List<Invoice>list =new ArrayList<>();
		String hql = "from Invoice invoice where invoice.opendate = ? ";
		list = getSession().createQuery(hql).setString(0, opendate).list();
		for (Invoice invoice : list) {
			dayPrice = dayPrice+Double.parseDouble(invoice.getAddprice());
		}
		return dayPrice;
	}
	@Override
	public Double findTexesPrice(String opendate) {
		double texesPrice=0;
		List<Invoice>list =new ArrayList<>();
		String hql = "from Invoice invoice where invoice.opendate like '%"+opendate+"%'";
		list=getSession().createQuery(hql).list();
		for (Invoice invoice : list) {
			texesPrice = texesPrice+Double.parseDouble(invoice.getTaxesprice());
		}
		return texesPrice;
	}
	//按照年来查找所有进项
	@Override
	public List<Invoice> searchAllData(String YYYY) {
		List<Invoice> list = new ArrayList<>();
		String hql = "from Invoice invoice where invoice.opendate like '%" + YYYY + "%'";
		list = getSession().createQuery(hql).list();
		return list;
	}

}
