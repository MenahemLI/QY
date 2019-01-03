package com.three.dms.dao.Info;

import java.util.List;
import java.util.TreeSet;

import com.three.dms.bean.Odetails;

public interface IOdetailsDao {
	void save (Odetails odetails);
	void update(Odetails odetails);
	Odetails findbydata(String invoicedata);
	//查询到结果可能不唯一，将信息存储到集合中
	TreeSet<String> findwaresbyinvoicedata(String invoicedata);
	List<Odetails> findbyinvoicedata(String invoicedata);
	List<Odetails> findbyinvoicedatavague(String invoicedata,String warse);
	List<Odetails> findbyinvoicedatavagues(String invoicedata,String warse);
	List<Odetails> findbyinvoicedataandwars(String invoicedata,String wares);
	Odetails findbysizeandinvoicedata(String wares,String invoicedata);
	Odetails findbysizeandinvoicedata(String wares,String invoicedata,String salesunit,String size);
	Odetails finbywares(String wares);
	String findunit(String warse);
}
