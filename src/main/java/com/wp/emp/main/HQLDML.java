package com.wp.emp.main;

import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wp.entity.Emp;
import com.wp.util.Util;

public class HQLDML {

	public static void main(String[] args) {
		
		Session session = Util.getSF(Emp.class).openSession();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("DML OPERATION");
		System.out.println("=============================");
		System.out.println("OPTIONS - 1.INSERTION 2.UPDATION 3.DELETION");
		int choice = sc.nextInt();
		switch(choice) {
		case 1:
			System.out.println("INSERTION");
			System.out.println("-----------------------------");
			System.out.println("Enter eno,ename,esal");
			Transaction tr = session.beginTransaction();
			Query query1 = session.createQuery("insert into Emp(eno,ename,esal)" +"select pcode,pname,price from Product where pcode=112");
			int count1 = query1.executeUpdate();
			tr.commit();
			System.out.println(count1+" rows affected");
			break;
		case 2:
			System.out.println("UPDATION");
			System.out.println("-----------------------------");
			Query query2 = session.createQuery("update Emp set ename=:ename,esal=:esal where eno=:eno");
			System.out.println("enter eno");
			int eno1 = sc.nextInt();
			System.out.println("enter ename,esal");
			String ename = sc.next();
			int esal = sc.nextInt();
			query2.setParameter("ename", ename);
			query2.setParameter("esal", esal);
			query2.setParameter("eno", eno1);
			Transaction tr1 = session.beginTransaction();
			int count2 = query2.executeUpdate();
			tr1.commit();
			System.out.println(count2 + " rows affected");
			break;
		
		case 3:
			System.out.println("DELETION");
			System.out.println("-----------------------------");
			Query query3 = session.createQuery("delete Emp where eno=:eno");
			System.out.println("enter eno");
			int eno2 = sc.nextInt();
			query3.setParameter("eno", eno2);
			Transaction tr2 = session.beginTransaction();
			int count3 = query3.executeUpdate();
			tr2.commit();
			System.out.println(count3 + " rows affected");
			break;
		default:
			System.out.println("SELECT CORRECT OPTION");
			
		}
		sc.close();
		session.close();
	}

}
