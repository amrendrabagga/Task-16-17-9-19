package com.wp.emp.main;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wp.entity.Emp;
import com.wp.util.Util;

public class SaveEmp {

	public static void main(String args[]) {
		Session session = Util.getSF(Emp.class).openSession();
		Emp emp1 = new Emp();
		emp1.setEno(111);
		emp1.setEname("ameya");
		emp1.setEsal(20301);
		
		Emp emp2 = new Emp(112,"ajay",90291);
		Emp emp3 = new Emp(113,"abhay",50000);
		
		Transaction tr = session.beginTransaction();
		session.save(emp1);
		session.save(emp2);
		session.save(emp3);
		tr.commit();
		session.close();
		
	}
}
