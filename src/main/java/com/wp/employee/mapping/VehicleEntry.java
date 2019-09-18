package com.wp.employee.mapping;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wp.entity.Employee;
import com.wp.entity.Vehicle;
import com.wp.util.Util;

public class VehicleEntry {

	public static void main(String[] args) {
		Session session = Util.getSF().openSession();

		Transaction tr = session.beginTransaction();
		Vehicle v1 = new Vehicle(611, "audi", "Q5", 5000000, new Employee(201));
		Vehicle v2 = new Vehicle(612, "bmw", "m4", 6000000, new Employee(202));
		Vehicle v3 = new Vehicle(613, "mercedes", "s2", 5000000, new Employee(201));
		Vehicle v4 = new Vehicle(614, "bmw", "m4", 6000000, new Employee(203));
		Vehicle v5 = new Vehicle(615, "audi", "Q5", 5000000, new Employee(203));
		Vehicle v6 = new Vehicle(616, "bmw", "m4", 6000000, new Employee(202));

		session.save(v1);
		session.save(v2);
		session.save(v3);
		session.save(v4);
		session.save(v5);
		session.save(v6);
		tr.commit();
		session.close();
		System.out.println("RECORD INSERTED");
	}

}
