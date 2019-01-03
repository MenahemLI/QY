package com.three.dms.service.Info;

import java.util.List;
import java.util.TreeSet;

import com.three.dms.bean.Odetails;

public interface IOdetailsService {
	void save (Odetails odetails);
	void update(Odetails odetails);
	Odetails findbydata(String invoicedata);
	//查询到结果可能不唯一，将信息存储到集合中
	List<Odetails> findbyinvoicedata(String invoicedata);
	List<Odetails> findbyinvoicedatavague(String invoicedata,String wase);
	List<Odetails> findbyinvoicedatavagues(String invoicedata,String wase);
	TreeSet<String> findwaresbyinvoicedata(String invoicedata);
	List<Odetails> findbyinvoicedataandwars(String invoicedata,String wares);
	Odetails findbysizeandinvoicedata(String wares,String invoicedata);
	Odetails findbysizeandinvoicedata(String wares,String invoicedata,String salesunit,String size);
	Odetails finbywares(String wares);
	String findunit(String warse);
}
