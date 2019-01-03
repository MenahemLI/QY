package com.three.dms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.three.dms.bean.Idetails;
import com.three.dms.dao.IdetailsDao;
import com.three.dms.service.Info.IIdetailsService;

@Service
public class IdetailsService implements IIdetailsService{
	
	@Autowired
	IdetailsDao idetailsdao ;
	@Override
	public void save(Idetails idetails) {
		idetailsdao.save(idetails);
	}

	@Override
	public void update(Idetails idetails) {
		idetailsdao.update(idetails);
	}

	@Override
	public List<Idetails> findbyinvoicedata(String invoicedata) {
		List<Idetails> idetailslist = new ArrayList<>();
		idetailslist = idetailsdao.findbyinvoicedata(invoicedata);
		return idetailslist;
	}

	@Override
	public Idetails findbysize(String size) {
		Idetails idetails = new Idetails();
		idetails = idetailsdao.findbysize(size);
		return idetails;
	}

	@Override
	public Idetails findbydata(String invoicedata) {
		Idetails idetails = new Idetails();
		idetails = idetailsdao.findbydata(invoicedata);
		return idetails;
	}

	@Override
	public Idetails findbysizeandinvoicedata(String wares, String invoicedata) {
		Idetails idetails = new Idetails();
		idetails = idetailsdao.findbysizeandinvoicedata(wares,invoicedata);
		return idetails;
	}

	@Override
	public Idetails finbywares(String wares) {
		Idetails idetails = new Idetails();
		idetails = idetailsdao.finbywares(wares);
		return idetails;
	}

	@Override
	public Idetails findbysizeandinvoicedata(String wares, String invoicedata, String salesunit,String size) {
		Idetails idetails = new Idetails();
		idetails = idetailsdao.findbysizeandinvoicedata(wares,invoicedata,salesunit,size);
		return idetails;
	}

	@Override
	public List<Idetails> findbyinvoicedataandwars(String invoicedata, String wares) {
		List<Idetails> idetailslist = new ArrayList<>();
		idetailslist = idetailsdao.findbyinvoicedataandwars(invoicedata,wares);
		return idetailslist;
	}

	@Override
	public TreeSet<String> findwaresbyinvoicedata(String invoicedata) {
		TreeSet<String> tree = new TreeSet<>();
		tree = idetailsdao.findwaresbyinvoicedata(invoicedata);
		return tree;
	}

	@Override
	public List<Idetails> findbyinvoicedatavague(String invoicedata) {
		List<Idetails> idetailslist = new ArrayList<>();
		idetailslist = idetailsdao.findbyinvoicedatavague(invoicedata);
		System.out.println("laidaolezheli");
		return idetailslist;
	}

}
