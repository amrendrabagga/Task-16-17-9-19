package com.wp.emp.main;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.wp.entity.Emp;
import com.wp.util.Util;

public class QBCOrdering {

	public static void main(String args[]) {
		Session session = Util.getSF(Emp.class).openSession();
		Criteria criteria = session.createCriteria(Emp.class);
		Order order = Order.desc("esal");
		criteria.addOrder(order);
		List<Emp> list = criteria.list();
		for(Emp emp: list)
			System.out.println(emp);
		session.close();
	}
}
