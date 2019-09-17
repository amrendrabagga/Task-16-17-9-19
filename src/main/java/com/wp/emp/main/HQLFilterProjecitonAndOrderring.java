package com.wp.emp.main;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.wp.entity.Emp;
import com.wp.util.Util;

public class HQLFilterProjecitonAndOrderring {

	public static void main(String[] args) {

		Session session = Util.getSF(Emp.class).openSession();
		System.out.println("RETRIEVAL");
		System.out.println("==========================");
		
		Query query = session.createQuery("from Emp");
		List<Emp> list = query.list();
		
		for(Emp emp : list)
			System.out.println(emp);
		
		System.out.println("-----------------------------");
		System.out.println("PROJECTION");
		System.out.println("==========================");
		Query query1 = session.createQuery("select eno,ename from Emp");
		List<Object[]> list1 = query1.list();
		for(Object[] arr : list1) {
			System.out.println("ENO and ENAME are ");
			for(Object ob : arr)
				System.out.println(ob);
			System.out.println("-----------------------------");
		}
		
		System.out.println("FILTERATION");
		System.out.println("==========================");	
		Query query2 = session.createQuery("from Emp where esal>20000 and esal <100000");
		List<Emp> list2 = query2.list();
		
		for(Emp emp2 : list2)
			System.out.println(emp2);
		
		System.out.println("ORDERING");
		System.out.println("==========================");
		System.out.println("-----------------------------");
		Query query3 = session.createQuery("from Emp order by esal desc");
		List<Emp> list3 = query3.list();
		
		for(Emp emp3 : list3)
			System.out.println(emp3);
		
		session.close();
	}

}
