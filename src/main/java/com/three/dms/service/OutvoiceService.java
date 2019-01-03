package com.three.dms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.three.dms.bean.Invoice;
import com.three.dms.bean.Outvoice;
import com.three.dms.dao.OutvoiceDao;
import com.three.dms.dao.Info.IOutvoiceDao;
import com.three.dms.service.Info.IOutvoiceService;
@Service
public class OutvoiceService implements IOutvoiceService{

	@Autowired
	IOutvoiceDao outvoiceDao ;
	@Override
	public void save(Outvoice outvoice) {
		System.out.println("业务层：保存进项");
		outvoiceDao.save(outvoice);
		
	}

	@Override
	public Outvoice findById(long id) {

		System.out.println("查询业务层");
		Outvoice invoice = outvoiceDao.findById(id);
		
		
		
		return invoice;
	}

	@Override
	public void update(Outvoice outvoice) {
		System.out.println("更新业务层");
		outvoiceDao.update(outvoice);
		
	}

	@Override
	public Outvoice findByO_num(String o_number) {
		// TODO Auto-generated method stub
		Outvoice outvoice = outvoiceDao.findByO_num(o_number);
		return outvoice;
	}

	@Override
	public List<Outvoice> findByuser(String username) {
		System.out.println("业务层User"+username);
		List<Outvoice> list = new ArrayList<>();
		list =outvoiceDao.findByuser(username);
		return list;
	}

	@Override
	public boolean findByO_num_judge(String o_number) {
		boolean bool = outvoiceDao.findByO_num_judge(o_number);
		return bool;
	}

	@Override
	public Double findByMM(String opendate) {
		double monthPrice = outvoiceDao.findByMM(opendate);
		System.out.println("业务层："+monthPrice);
		return monthPrice;
	}

	@Override
	public Double findByDay(String opendate) {
		double dayPrice = outvoiceDao.findByDay(opendate);
		return dayPrice;
	}

	@Override
	public Double findTexesPrice(String opendate) {
		double texesPrice = outvoiceDao.findTexesPrice(opendate);
		return texesPrice;

	}
	@Override
	public List<Outvoice> searchAllData(String YYYY) {
      List<Outvoice> list =new ArrayList<>();
      list =  outvoiceDao.searchAllData(YYYY);
		return list;
	}

	@Override
	public StringBuffer findByNameDate(String name, String YYYY) {
		return outvoiceDao.findByNameDate(name,YYYY);
	}
}
