package com.wp.employee.mapping;

import org.hibernate.Session;

import com.wp.entity.Laptop;
import com.wp.util.Util;

public class LaptopRetrieval {

	public static void main(String[] args) {
		
		Session session = Util.getSF(Laptop.class).openSession();
		Laptop laptop = session.get(Laptop.class, 511);
		System.out.println(laptop.getCode());
		System.out.println(laptop.getBrand());
		System.out.println(laptop.getPrice());
		System.out.println(laptop.getEmployee().getEno());
		System.out.println(laptop.getEmployee().getEname());
		System.out.println(laptop.getEmployee().getEsal());
		System.out.println(laptop.getEmployee().getVehicle().getBrand());
		System.out.println(laptop.getEmployee().getVehicle().getModel());
		System.out.println(laptop.getEmployee().getVehicle().getPrice());
		
		session.close();

	}

}
