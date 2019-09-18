package com.wp.employee.mapping;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wp.entity.Project;
import com.wp.util.Util;

public class ProjectEntry {

	public static void main(String[] args) {
		Project p1 = new Project(301, "AcSw", 5000000);
		Project p2 = new Project(302, "ERP", 6000000);
		Project p3 = new Project(303, "BnkSw", 9000000);
		Project p4 = new Project(304, "SecApp", 450000);
		Project p5 = new Project(305, "ECommerce", 345000);

		Session session = Util.getSF().openSession();
		Transaction tr = session.beginTransaction();
		session.save(p1);
		session.save(p2);
		session.save(p3);
		session.save(p4);
		session.save(p5);
		tr.commit();
		session.close();
		System.out.println("PROJECT STORED");

	}

}
