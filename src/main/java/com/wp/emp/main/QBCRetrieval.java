package com.wp.emp.main;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.wp.entity.Emp;
import com.wp.util.Util;

public class QBCRetrieval {
	public static void main(String args[]) {
		Session session = Util.getSF(Emp.class).openSession();
		Criteria criteria = session.createCriteria(Emp.class);
		List<Emp> list = criteria.list();
		
		for(Emp emp : list){
			System.out.println(emp);
		}
		session.close();
	}

}
