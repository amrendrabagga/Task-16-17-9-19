package com.wp.emp.main;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.wp.entity.Emp;
import com.wp.util.Util;

public class SQLRetrieval {

	public static void main(String[] args) {
		Session session = Util.getSF(Emp.class).openSession();
		SQLQuery query = session.createSQLQuery("select *from emp");
		query.addEntity(Emp.class);
		List<Emp> list = query.list();
		for(Emp emp : list)
			System.out.println(emp);
		session.close();
	}

}
