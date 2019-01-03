package com.three.dms.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.three.dms.bean.Idetails;
import com.three.dms.dao.Info.IIdetailsDao;

@Repository
public class IdetailsDao implements IIdetailsDao {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 保存明细时，根据开票时间和货物规格唯一定位到货物获取到货物的数量 获取前台刚刚得到的货物的数量 将货物的数量跟新 通过set方法将跟新后的数据更新
	 */
	@Override
	public void save(Idetails idetails) {
		getSession().save(idetails);
	}

	@Override
	public void update(Idetails idetails) {
		getSession().update(idetails);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Idetails> findbyinvoicedata(String invoicedata) {
		List<Idetails> idetails = new ArrayList<>();
		String hql = "from Idetails ideta where ideta.invoicedata=?";
		idetails = getSession().createQuery(hql).setString(0, invoicedata).list();
		return idetails;
	}

	@Override
	public Idetails findbysize(String size) {
		Idetails idetails = new Idetails();
		String hql = "from Idetails ideta where ideta.size=?";
		idetails = (Idetails) getSession().createQuery(hql).setString(0, size).uniqueResult();
		return idetails;
	}

	@Override
	public Idetails findbydata(String invoicedata) {
		Idetails idetails = new Idetails();
		String hql = "from Idetails ideta where ideta.invoicedata=?";
		idetails = (Idetails) getSession().createQuery(hql).setString(0, invoicedata).uniqueResult();
		return idetails;
	}

	// 通过录入时间和货物唯一的查询货物信息
	@Override
	public Idetails findbysizeandinvoicedata(String wares, String invoicedata) {
		Idetails idetails = new Idetails();
		String hql = "from Idetails ideta where ideta.wares=? and ideta.invoicedata=?";
		idetails = (Idetails) getSession().createQuery(hql).setString(0, wares).setString(1, invoicedata)
				.uniqueResult();
		return idetails;
	}

	@Override
	public Idetails finbywares(String wares) {
		Idetails idetails = new Idetails();
		String hql = "from Idetails ideta where ideta.wares=?";
		idetails = (Idetails) getSession().createQuery(hql).setString(0, wares).uniqueResult();
		return idetails;
	}

	@Override
	public Idetails findbysizeandinvoicedata(String wares, String invoicedata, String salesunit,String size) {
		Idetails idetails = new Idetails();
		String hql = "from Idetails ideta where ideta.wares=? and ideta.invoicedata=? and ideta.salesunit=? and ideta.size=?";
		idetails = (Idetails) getSession().createQuery(hql).setString(0, wares).setString(1, invoicedata)
				.setString(2, salesunit).setString(3, size).uniqueResult();
		return idetails;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Idetails> findbyinvoicedataandwars(String invoicedata, String wares) {
		List<Idetails> idetails = new ArrayList<>();
		String hql = "from Idetails ideta where ideta.invoicedata=? and ideta.wares=?";
		idetails = getSession().createQuery(hql).setString(0, invoicedata).setString(1, wares).list();
		return idetails;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TreeSet<String> findwaresbyinvoicedata(String invoicedata) {
		System.out.println("niyeye");
		TreeSet<String> tree = new TreeSet<>();
		List<Idetails> idetails = new ArrayList<>();
		String hql = "from Idetails ideta where ideta.invoicedata=?";
		idetails = getSession().createQuery(hql).setString(0, invoicedata).list();
		System.out.println(idetails);
		for (Idetails idetails2 : idetails) {
			tree.add(idetails2.getWares());
		}
		return tree;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Idetails> findbyinvoicedatavague(String invoicedata) {
		List<Idetails> idetails = new ArrayList<>();
		String hql = "from Idetails ideta where ideta.invoicedata like '%"+invoicedata+"%'";
		idetails = getSession().createQuery(hql).list();
		System.out.println(idetails+"nibabalaidaolezheli ");
		return idetails;
	}

}
