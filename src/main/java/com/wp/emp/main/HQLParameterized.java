package com.wp.emp.main;

import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wp.entity.Emp;
import com.wp.util.Util;

public class HQLParameterized {

	@SuppressWarnings("deprecation")
	public static void main(String args[]) {
		Session session = Util.getSF(Emp.class).openSession();
		System.out.println("USING NAMED PARAMETERS");
		Query query1 = session.createQuery("update Emp set ename=:ename,esal=:esal where eno=:eno");
		Scanner sc = new Scanner(System.in);
		System.out.println("enter eno");
		
		int eno1 = sc.nextInt();
		System.out.println("enter ename,esal");
		String ename1 = sc.next();
		int esal1 = sc.nextInt();
		query1.setParameter("ename", ename1);
		query1.setParameter("esal", esal1);
		query1.setParameter("eno", eno1);
		Transaction tr1 = session.beginTransaction();
		int count1 = query1.executeUpdate();
		tr1.commit();
		System.out.println(count1 + " rows affected"); 

		System.out.println("=============================");
		System.out.println("USING POSITIONED PARAMETERS");
		Query query2 = session.createQuery("update Emp set ename=?1,esal=?2 where eno=?3");
		
		System.out.println("enter eno");
		
		int eno2 = sc.nextInt();
		System.out.println("enter ename,esal");
		String ename2 = sc.next();
		int esal2 = sc.nextInt();
		query2.setParameter(1, ename2);
		query2.setParameter(2, esal2);
		query2.setParameter(3, eno2);
		Transaction tr2 = session.beginTransaction();
		int count2 = query2.executeUpdate();
		tr2.commit();
		System.out.println(count2 + " rows affected"); 
	
		session.close();
	}
}
