package com.three.dms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.three.dms.bean.Odetails;
import com.three.dms.dao.OdetailsDao;
import com.three.dms.service.Info.IOdetailsService;

@Service
public class OdetailsService implements IOdetailsService {

	@Autowired
	OdetailsDao odetailsdao;
	@Override
	public void save(Odetails odetails) {
		odetailsdao.save(odetails);
	}

	@Override
	public void update(Odetails odetails) {
		odetailsdao.update(odetails);
	}

	@Override
	public Odetails findbydata(String invoicedata) {
		Odetails odetails = new Odetails();
		odetails = odetailsdao.findbydata(invoicedata);
		return odetails;
	}

	@Override
	public List<Odetails> findbyinvoicedata(String invoicedata) {
		System.out.println("zhaomam");
		List<Odetails> odetails = new ArrayList<>();
		odetails = odetailsdao.findbyinvoicedata(invoicedata);
		return odetails;
	}

	@Override
	public Odetails findbysizeandinvoicedata(String wares, String invoicedata) {
		Odetails odetails = new Odetails();
		odetails = odetailsdao.findbysizeandinvoicedata(wares, invoicedata);
		return odetails;
	}

	@Override
	public Odetails findbysizeandinvoicedata(String wares, String invoicedata, String salesunit,String size) {
		Odetails odetails = new Odetails();
		odetails = odetailsdao.findbysizeandinvoicedata(wares, invoicedata, salesunit,size);
		return odetails;
	}

	@Override
	public Odetails finbywares(String wares) {
		Odetails odetails = new Odetails();
		odetails = odetailsdao.finbywares(wares);
		return odetails;
	}

	@Override
	public TreeSet<String> findwaresbyinvoicedata(String invoicedata) {
		TreeSet<String> tree = new TreeSet<>();
		tree = odetailsdao.findwaresbyinvoicedata(invoicedata);
		return tree;
	}

	@Override
	public List<Odetails> findbyinvoicedataandwars(String invoicedata, String wares) {
		List<Odetails> odetailslist = new ArrayList<>();
		odetailslist = odetailsdao.findbyinvoicedataandwars(invoicedata,wares);
		return odetailslist;
	}

	@Override
	public List<Odetails> findbyinvoicedatavague(String invoicedata,String warse) {
		List<Odetails> odetailslist = new ArrayList<>();
		odetailslist = odetailsdao.findbyinvoicedatavague(invoicedata,warse);
		System.out.println("laidaolezheli");
		return odetailslist;
	}

	@Override
	public String findunit(String warse) {
		String unit;
		unit = odetailsdao.findunit(warse);
		return unit;
	}

	@Override
	public List<Odetails> findbyinvoicedatavagues(String invoicedata, String wase) {
		List<Odetails> odetailslist = new ArrayList<>();
		odetailslist = odetailsdao.findbyinvoicedatavagues(invoicedata,wase);
		System.out.println("laidaolezheli456");
		return odetailslist;
	}

}
