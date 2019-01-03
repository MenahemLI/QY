package com.three.dms.service.Info;

import java.util.List;

import com.three.dms.bean.Outvoice;

public interface IOutvoiceService {
	void save(Outvoice outvoice);
	Outvoice findById(long id);
	void update(Outvoice outvoice);
	Outvoice findByO_num(String o_number);
	List<Outvoice> findByuser(String username);
	boolean findByO_num_judge(String o_number);
	Double findByMM(String opendate);
	Double findByDay(String opendate);
	Double findTexesPrice(String opendate);
	//查询数据库中的所有数据
		List<Outvoice> searchAllData(String YYYY);
		StringBuffer findByNameDate(String name,String YYYY);
}
