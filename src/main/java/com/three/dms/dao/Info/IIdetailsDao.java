package com.three.dms.dao.Info;

import java.util.List;
import java.util.TreeSet;

import com.three.dms.bean.Idetails;

public interface IIdetailsDao {
	void save(Idetails idetails);
	void update(Idetails idetails);
	Idetails findbysize(String size);
	Idetails findbydata(String invoicedata);
	//查询到结果可能不唯一，将信息存储到集合中
	TreeSet<String> findwaresbyinvoicedata(String invoicedata);
	List<Idetails> findbyinvoicedata(String invoicedata);
	List<Idetails> findbyinvoicedatavague(String invoicedata);
	List<Idetails> findbyinvoicedataandwars(String invoicedata,String wares);
	Idetails findbysizeandinvoicedata(String wares,String invoicedata);
	Idetails findbysizeandinvoicedata(String wares,String invoicedata,String salesunit,String size);
	Idetails finbywares(String wares);
	
}
