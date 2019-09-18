package com.wp.employee.mapping;

import java.util.List;
import java.util.Scanner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wp.entity.Employee;
import com.wp.entity.Laptop;
import com.wp.entity.Project;
import com.wp.entity.Vehicle;
import com.wp.util.Util;

public class VehicleCRUD {

	public static void main(String args[]) {
		Session session = Util.getSF().openSession();

		Scanner sc = new Scanner(System.in);

		System.out.println("CRUD OPERATION");
		System.out.println("=============================");
		System.out.println("OPTIONS - 1.READ 2.INSERTION 3.UPDATION 4.DELETION");
		int choice = sc.nextInt();

		switch (choice) {
		case 1:
			// using named query
			List<Vehicle> list = session.getNamedQuery("GET_ALL_VEHICLE").list();
			for (Vehicle vehicle : list) {
				System.out.println(vehicle);
				System.out.println(vehicle.getEmployee().getEno() + " " + vehicle.getEmployee().getEname());
			}
			break;
		case 2:
			System.out.println("INSERTION");
			System.out.println("-----------------------------");
			System.out.println("enter regno");
			int regno1 = sc.nextInt();
			System.out.println("enter brand");
			String brand1 = sc.next();
			System.out.println("enter model");
			String model1 = sc.next();
			System.out.println("enter price");
			int price1 = sc.nextInt();

			System.out.println("choose employee code---------------");
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Integer> cq = cb.createQuery(Integer.class);
			Root<Employee> root1 = cq.from(Employee.class);
			cq.select(root1.<Integer>get("eno"));
			List<Integer> list1 = session.createQuery(cq).list();

			for (int eno : list1) {
				System.out.println(eno);
			}
			int eno1 = sc.nextInt();
			Employee employee1 = session.get(Employee.class, eno1);
			Transaction tr = session.beginTransaction();
			Vehicle vehicle1 = new Vehicle(regno1, brand1, model1, price1, employee1);
			session.save(vehicle1);
			tr.commit();
			break;
		case 3:
			System.out.println("UPDATION");
			System.out.println("-----------------------------");
			// assigning existing vehicle to (new/old) employee
			System.out.println("enter regno");
			int regno2 = sc.nextInt();
			Vehicle vehicle2 = session.get(Vehicle.class, regno2);

			System.out.println("enter brand");
			String brand2 = sc.next();
			System.out.println("enter model");
			String model2 = sc.next();
			System.out.println("enter price");
			int price2 = sc.nextInt();
			vehicle2.setBrand(brand2);
			vehicle2.setModel(model2);
			vehicle2.setPrice(price2);

			System.out.println("choose employee code---------------");
			CriteriaBuilder cb2 = session.getCriteriaBuilder();
			CriteriaQuery<Integer> cq2 = cb2.createQuery(Integer.class);
			Root<Employee> root2 = cq2.from(Employee.class);
			cq2.select(root2.<Integer>get("eno"));
			List<Integer> list2 = session.createQuery(cq2).list();
			for (int eno : list2) {
				System.out.println(eno);
			}
			int eno2 = sc.nextInt();
			Employee employee2 = session.get(Employee.class, eno2);
			vehicle2.setEmployee(employee2);

			Transaction tr1 = session.beginTransaction();
			session.saveOrUpdate(vehicle2);
			tr1.commit();

			break;
		case 4:
			System.out.println("DELETION");
			System.out.println("-----------------------------");

			System.out.println("enter regno");
			int regno3 = sc.nextInt();
			Vehicle vehicle4 = session.get(Vehicle.class, regno3);

			Transaction tr2 = session.beginTransaction();
			session.delete(vehicle4);
			tr2.commit();

			break;
		default:
			System.out.println("CHOOSE CORRECT OPTION");
		}
		sc.close();
		session.close();
	}
}
