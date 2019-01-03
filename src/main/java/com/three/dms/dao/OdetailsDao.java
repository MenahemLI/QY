package com.three.dms.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.three.dms.bean.Odetails;
import com.three.dms.dao.Info.IOdetailsDao;

@Repository
public class OdetailsDao implements IOdetailsDao{

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void save(Odetails odetails) {
		getSession().save(odetails);
	}

	@Override
	public void update(Odetails odetails) {
		getSession().update(odetails);
	}


	@Override
	public Odetails findbydata(String invoicedata) {
		Odetails odetails = new Odetails();
		String hql = "from Odetails odeta where odeta.invoicedata=?";
		odetails = (Odetails) getSession().createQuery(hql).setString(0, invoicedata).uniqueResult();
		return odetails;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Odetails> findbyinvoicedata(String invoicedata) {
		System.out.println("zhaoyeye");
		List<Odetails> odetails = new ArrayList<>();
		String hql = "from Odetails odeta where odeta.invoicedata=?";
		odetails = getSession().createQuery(hql).setString(0, invoicedata).list();
		return odetails;
	}

	@Override
	public Odetails findbysizeandinvoicedata(String wares, String invoicedata) {
		Odetails odetails = new Odetails();
		String hql = "from Odetails odeta where odeta.wares=? and odeta.invoicedata=?";
		odetails = (Odetails) getSession().createQuery(hql).setString(0, wares).setString(1, invoicedata)
				.uniqueResult();
		return odetails;
	}

	@Override
	public Odetails findbysizeandinvoicedata(String wares, String invoicedata, String salesunit,String size) {
		Odetails odetails = new Odetails();
		String hql = "from Odetails odeta where odeta.wares=? and odeta.invoicedata=? and odeta.salesunit=? and odeta.size=?";
		odetails = (Odetails) getSession().createQuery(hql).setString(0, wares).setString(1, invoicedata)
				.setString(2, salesunit).setString(3, size).uniqueResult();
		return odetails;
	}

	@Override
	public Odetails finbywares(String wares) {
		Odetails odetails = new Odetails();
		String hql = "from Odetails odeta where odeta.wares=?";
		odetails = (Odetails) getSession().createQuery(hql).setString(0, wares).uniqueResult();
		return odetails;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TreeSet<String> findwaresbyinvoicedata(String invoicedata) {
		TreeSet<String> tree = new TreeSet<>();
		List<Odetails> odetails = new ArrayList<>();
		String hql = "from Odetails odeta where odeta.invoicedata=?";
		odetails = getSession().createQuery(hql).setString(0, invoicedata).list();
		for (Odetails odetails2 : odetails) {
			tree.add(odetails2.getWares());
		}
		return tree;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Odetails> findbyinvoicedataandwars(String invoicedata, String wares) {
		List<Odetails> odetails = new ArrayList<>();
		String hql = "from Odetails odeta where odeta.invoicedata=? and odeta.wares=?";
		odetails = getSession().createQuery(hql).setString(0, invoicedata).setString(1, wares).list();
		return odetails;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Odetails> findbyinvoicedatavague(String invoicedata,String warse) {
		List<Odetails> odetails = new ArrayList<>();
		String hql = "from Odetails odeta where odeta.wares=? and odeta.invoicedata like '%"+invoicedata+"%'";
		odetails = getSession().createQuery(hql).setString(0, warse).list();
		System.out.println(odetails+"nibabalaidaolezheli ");
		return odetails;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String findunit(String warse) {
		String unit;
		List<String> lisr = new ArrayList<>();
		String hql = "select odeta.unit from Odetails odeta where odeta.wares=?";
		lisr =  getSession().createQuery(hql).setString(0, warse).list();
		unit = lisr.get(0);
		return unit;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Odetails> findbyinvoicedatavagues(String invoicedata, String wares) {
		List<Odetails> odetails = new ArrayList<>();
		String hql = "from Odetails odeta where odeta.invoicedata=? and odeta.wares=?";
		odetails = getSession().createQuery(hql).setString(0, invoicedata).setString(1, wares).list();
		System.out.println(odetails+"nibabalaidaolezheli1562");
		return odetails;
	}

}
