package com.wp.employee.mapping;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wp.entity.Employee;
import com.wp.entity.Laptop;
import com.wp.entity.Vehicle;
import com.wp.util.Util;

public class EmployeeCRUD {

	public static void main(String args[]) {
		Session session = Util.getSF(Employee.class).openSession();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("CRUD OPERATION");
		System.out.println("=============================");
		System.out.println("OPTIONS - 1.READ 2.INSERTION 3.UPDATION 4.DELETION");
		int choice = sc.nextInt();
//		Transaction tr = session.beginTransaction();
		switch(choice) {
		case 1:
			List<Employee> list = session.createCriteria(Employee.class).list();
			for(Employee employee : list)
				System.out.println(employee);
			break;
		case 2:
			System.out.println("INSERTION");
			System.out.println("-----------------------------");
			System.out.println("enter eno");
			int eno1 = sc.nextInt();
			System.out.println("enter ename");
			String ename1 = sc.next();
			System.out.println("enter esal");
			int esal1 = sc.nextInt();
			System.out.println("enter laptop_code");
			int lcode1 = sc.nextInt();
			System.out.println("enter vehicle_regno");
			int rgno1 = sc.nextInt();
			Transaction tr = session.beginTransaction();
			Employee employee1 = new Employee(eno1,ename1,esal1,new Laptop(lcode1),new Vehicle(rgno1));
			session.save(employee1);
			tr.commit();
			break;
		case 3:
			System.out.println("UPDATION");
			System.out.println("-----------------------------");
			Query query2 = session.createQuery("update Employee set ename=:ename,esal=:esal,laptop_code=:lcode,vehicle_regno=:rgno where eno=:eno");
			System.out.println("enter eno");
			int eno2 = sc.nextInt();
			System.out.println("enter ename");
			String ename2 = sc.next();
			System.out.println("enter esal");
			int esal2 = sc.nextInt();
			System.out.println("enter laptop_code");
			int lcode2 = sc.nextInt();
			System.out.println("enter vehicle_regno");
			int rgno2 = sc.nextInt();
			query2.setParameter("ename", ename2);
			query2.setParameter("esal", esal2);
			query2.setParameter("eno", eno2);
			query2.setParameter("lcode", lcode2);
			query2.setParameter("rgno", rgno2);
			Transaction tr1 = session.beginTransaction();
			int count2 = query2.executeUpdate();
			tr1.commit();
			System.out.println(count2 +" rows updated");
			break;
		case 4:
			System.out.println("DELETION");
			System.out.println("-----------------------------");
			Query query3 = session.createQuery("delete Employee where eno=:eno");
			System.out.println("enter eno");
			int eno3 = sc.nextInt();
			query3.setParameter("eno", eno3);
			Transaction tr2 = session.beginTransaction();
			int count3 = query3.executeUpdate();
			tr2.commit();
			System.out.println(count3 + " rows affected");
			break;
		default:
			System.out.println("CHOOSE CORRECT OPTION");
		}
		sc.close();
		session.close();
	}
	
}
