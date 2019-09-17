package com.wp.employee.mapping;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wp.entity.Employee;
import com.wp.entity.Laptop;
import com.wp.entity.Vehicle;
import com.wp.util.Util;

public class SaveEmployee {

	public static void main(String[] args) {
		Session session = Util.getSF(Employee.class).openSession();
		Employee employee1 = new Employee(201,"amrendra",12345,new Laptop(511),new Vehicle(611));
		Employee employee2 = new Employee(202,"raman",56421,new Laptop(512),new Vehicle(612));
		Employee employee3 = new Employee(203,"bani",45678,new Laptop(513),new Vehicle(613));
		Transaction tr = session.beginTransaction();
		session.save(employee1);
		session.save(employee2);
		session.save(employee3);
		tr.commit();
		System.out.println("RECORD INSERTED");
		session.close();
	}

}
