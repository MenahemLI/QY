package com.three.dms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.three.dms.bean.Invoice;
import com.three.dms.bean.Outvoice;
import com.three.dms.dao.Info.IIvoiceDao;
import com.three.dms.service.Info.IInvoiceService;
@Service
public class InvoiceService implements IInvoiceService{

	@Autowired
	IIvoiceDao invoiceDao;
	@Override
	public void save(Invoice invoice) {
		// TODO Auto-generated method stub
		System.out.println("业务层：保存进项");
		invoiceDao.save(invoice);
	}
	@Override
	public Invoice findById(long id) {
		// TODO Auto-generated method stub
		
		System.out.println("查询业务层");
		Invoice invoice = invoiceDao.findById(id);
		return invoice;
	}
	@Override
	public void update(Invoice invoice) {
		// TODO Auto-generated method stub
		System.out.println("更新业务层");
		invoiceDao.update(invoice);
		
	}
	@Override
	public Invoice findByI_num(String i_number) {
		// TODO Auto-generated method stub
		Invoice invoice = invoiceDao.findByI_num(i_number);
		return invoice;
	}
	@Override
	public List<Invoice> findByuser(String username) {
		// TODO Auto-generated method stub
		List<Invoice> list = new ArrayList<>();
		list =invoiceDao.findByuser(username);
		
		return list;
	}
	@Override
	public boolean findByI_num_judge(String i_number) {
		boolean bool = invoiceDao.findByI_num_judge(i_number);
		return bool;
	}
	@Override
	public Double findByMM(String opendate) {
		double monthPrice = invoiceDao.findByMM(opendate);
		System.out.println("业务层："+monthPrice);
		return monthPrice;
	}
	@Override
	public Double findByDay(String opendate) {
		double dayPrice = invoiceDao.findByDay(opendate);
		return dayPrice;
	}
	@Override
	public Double findTexesPrice(String opendate) {
		double texesPrice = invoiceDao.findTexesPrice(opendate);
		return texesPrice;
	}
	@Override
	public List<Invoice> searchAllData(String YYYY) {
		 List<Invoice> list =new ArrayList<>();
	      list =  invoiceDao.searchAllData(YYYY);
			return list;
	}
	
	

}
