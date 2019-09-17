package com.wp.employee.mapping;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wp.entity.Vehicle;
import com.wp.util.Util;

public class VehicleEntry {

	public static void main(String[] args) {
		Session session = Util.getSF(Vehicle.class).openSession();
		
		Transaction tr = session.beginTransaction();
		Vehicle v1 = new Vehicle(611, "audi", "Q5", 5000000);
		Vehicle v2 = new Vehicle(612, "bmw", "m4", 6000000);
		session.save(v1);
		session.save(v2);
		tr.commit();
		session.close();
		System.out.println("RECORD INSERTED");
	}

}
