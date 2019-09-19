package com.wp.employee.mapping;

import java.util.List;
import java.util.Scanner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wp.entity.Employee;
import com.wp.entity.Project;
import com.wp.entity.Vehicle;
import com.wp.util.Util;

public class ProjectCRUD {

	public static void main(String[] args) {

		Session session = Util.getSF().openSession();
		Scanner sc = new Scanner(System.in);

		System.out.println("CRUD OPERATION");
		System.out.println("=============================");
		System.out.println("OPTIONS - 1.READ 2.INSERTION 3.UPDATION 4.DELETION");
		int choice = sc.nextInt();

		switch (choice) {
		case 1:
			// using named query
			List<Project> list = session.getNamedQuery("GET_ALL_PROJECT").list();
			for (Project project : list) {
				System.out.println(project);
				System.out.println("eno and ename");
				project.getEmployees().stream().forEach(x -> System.out.println(x.getEno() + " " + x.getEname()));
				System.out.println("=========================");
			}
			break;
		case 2:
			System.out.println("INSERTION");
			System.out.println("-----------------------------");
			System.out.println("enter pcode");
			int pcode1 = sc.nextInt();
			System.out.println("enter cost");
			int cost1 = sc.nextInt();
			System.out.println("enter pname");
			String pname1 = sc.next();

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
			Employee employee1 = session.get(Employee.class, eno2);
			Project project1 = new Project();
			project1.setPcode(pcode1);
			project1.setCost(cost1);
			project1.setPname(pname1);
			project1.getEmployees().add(employee1);
			Transaction tr = session.beginTransaction();
			session.persist(project1);
			tr.commit();

			break;
		case 3:
			System.out.println("UPDATION");
			System.out.println("-----------------------------");

			System.out.println("enter pcode");
			int pcode2 = sc.nextInt();
			Project project2 = session.get(Project.class, pcode2);

			System.out.println("enter cost");
			int cost3 = sc.nextInt();
			System.out.println("enter pname");
			String pname3 = sc.next();

			project2.setCost(cost3);
			project2.setPname(pname3);

			Transaction tr1 = session.beginTransaction();
			session.update(project2);
			tr1.commit();

			break;
		case 4:
			System.out.println("DELETION");
			System.out.println("-----------------------------");

			System.out.println("enter pcode");
			int pcode4 = sc.nextInt();
			Transaction tr2 = session.beginTransaction();
			Project project4 = session.get(Project.class, pcode4);
			// project4.getEmployees().remove(0);

			session.delete(project4);
			tr2.commit();

			break;
		default:
			System.out.println("CHOOSE CORRECT OPTION");
		}
		sc.close();
		session.close();
	}

}
