package com.three.dms.dao.Info;

import java.util.List;

import com.three.dms.bean.Outvoice;

public interface IOutvoiceDao {
	void save(Outvoice outvoice);
	Outvoice findById(long id);
	void update(Outvoice outvoice);
	Outvoice findByO_num(String o_number);
	List<Outvoice> findByuser(String username);
	boolean findByO_num_judge(String o_number);
	Double findByMM(String opendate);
	Double findByDay(String opendate);
	Double findTexesPrice(String opendate);
	List<Outvoice> searchAllData();
	StringBuffer findByNameDate(String name, String yYYY);
	List<Outvoice> searchAllData(String YYYY);
}
