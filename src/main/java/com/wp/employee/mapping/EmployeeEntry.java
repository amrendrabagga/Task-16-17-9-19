package com.wp.employee.mapping;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wp.entity.Employee;
import com.wp.entity.Laptop;
import com.wp.entity.Project;
import com.wp.util.Util;

public class EmployeeEntry {

	public static void main(String[] args) {
		Session session = Util.getSF().openSession();
		Employee employee1 = new Employee(201, "amrendra", 12345, new Laptop(511));
		employee1.getProjects().add(new Project(301));
		employee1.getProjects().add(new Project(302));
		employee1.getProjects().add(new Project(303));
		employee1.getProjects().add(new Project(305));
		Employee employee2 = new Employee(202, "raman", 56421, new Laptop(512));
		employee2.getProjects().add(new Project(302));
		employee2.getProjects().add(new Project(304));
		employee2.getProjects().add(new Project(305));
		Employee employee3 = new Employee(203, "bani", 45678, new Laptop(513));
		employee3.getProjects().add(new Project(301));
		employee3.getProjects().add(new Project(302));
		Transaction tr = session.beginTransaction();
		session.save(employee1);
		session.save(employee2);
		session.save(employee3);
		tr.commit();
		System.out.println("RECORD INSERTED");
		session.close();
	}

}
