package com.three.dms.service.Info;

import java.util.List;

import com.three.dms.bean.Invoice;
import com.three.dms.bean.Outvoice;

public interface IInvoiceService {
	
	void save(Invoice invoice);
	Invoice findById(long id);
	void update(Invoice invoice);
	Invoice findByI_num(String i_number);
	List<Invoice> findByuser(String username);
	boolean findByI_num_judge(String i_number);
	Double findByMM(String opendate);
	Double findByDay(String opendate);
	Double findTexesPrice(String opendate);
	List<Invoice> searchAllData(String YYYY);
}
