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
import com.wp.entity.Vehicle;
import com.wp.util.Util;

public class LaptopCRUD {

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
			List<Laptop> list = session.getNamedQuery("GET_ALL_LAPTOP").list();
			for (Laptop laptop : list)
				System.out.println(laptop);
			break;
		case 2:
			System.out.println("INSERTION");
			System.out.println("-----------------------------");
			System.out.println("enter code");
			int lcode1 = sc.nextInt();
			System.out.println("enter brand");
			String brand1 = sc.next();
			System.out.println("enter price");
			int price1 = sc.nextInt();

			Transaction tr = session.beginTransaction();
			Laptop laptop1 = new Laptop(lcode1, brand1, price1);
			session.save(laptop1);
			tr.commit();
			break;
		case 3:
			System.out.println("UPDATION");
			System.out.println("-----------------------------");

			System.out.println("enter lcode");
			int lcode2 = sc.nextInt();
			Laptop laptop2 = session.get(Laptop.class, lcode2);
			System.out.println("enter brand");
			String brand2 = sc.next();
			System.out.println("enter price");
			int price2 = sc.nextInt();

			laptop2.setBrand(brand2);
			laptop2.setPrice(price2);

			Transaction tr1 = session.beginTransaction();
			session.saveOrUpdate(laptop2);
			tr1.commit();

			break;
		case 4:
			System.out.println("DELETION");
			System.out.println("-----------------------------");

			System.out.println("enter code");
			int code3 = sc.nextInt();
			Laptop laptop3 = session.get(Laptop.class, code3);

			Transaction tr2 = session.beginTransaction();

			session.delete(laptop3);
			tr2.commit();

			break;
		default:
			System.out.println("CHOOSE CORRECT OPTION");
		}
		sc.close();
		session.close();
	}

}
