package com.wp.employee.mapping;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.wp.entity.Laptop;
import com.wp.util.Util;

public class LaptopEntry {

	public static void main(String[] args) {
		Session session = Util.getSF(Laptop.class).openSession();
		
		Laptop l1 = new Laptop(511, "DELL", 50000);
		Laptop l2 = new Laptop(512, "HCL", 70000);
		Laptop l3 = new Laptop(513, "HP", 40000);

		Transaction tr = session.beginTransaction();
		session.save(l1); session.save(l2); session.save(l3);
		tr.commit();
		session.close();
		System.out.println("RECORD ADDED");
		
	}

}
