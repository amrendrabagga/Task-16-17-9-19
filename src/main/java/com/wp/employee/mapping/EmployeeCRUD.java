package com.wp.employee.mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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

public class EmployeeCRUD {

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
			List<Employee> list = session.getNamedQuery("GET_ALL_EMPLOYEE").list();
			for (Employee employee : list)
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
			System.out.println("enter laptop details---------------");
			System.out.println("enter laptop_code");
			int lcode1 = sc.nextInt();
			System.out.println("enter laptop brand");
			String lbrand1 = sc.next();
			System.out.println("enter laptop price");
			int lprice1 = sc.nextInt();
			System.out.println("enter vehicle details---------------");
			System.out.println("enter regno");
			int regno1 = sc.nextInt();
			System.out.println("enter vehicle name");
			String vname1 = sc.next();
			System.out.println("enter vehicle model");
			String vmodel1 = sc.next();
			System.out.println("enter vehicle price");
			int vprice1 = sc.nextInt();
			System.out.println("choose project code---------------");
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
			Root<Project> root1 = cq.from(Project.class);
			cq.multiselect(root1.<Integer>get("pcode"), root1.<String>get("pname"));
			List<Object[]> list1 = session.createQuery(cq).list();

			System.out.println("	pcode	pname");
			System.out.println("-------------------------------");
			for (Object obj[] : list1) {

				for (Object prj : obj) {
					System.out.print("\t" + prj);
				}
				System.out.println();
			}
			int pcode1 = sc.nextInt();
			Project project = session.get(Project.class, pcode1);

			Transaction tr = session.beginTransaction();
			Employee employee1 = new Employee();
			employee1.setEno(eno1);
			employee1.setEname(ename1);
			employee1.setEsal(esal1);
			employee1.setLaptop(new Laptop(lcode1, lbrand1, lprice1));
			employee1.getVehicles().add(new Vehicle(regno1, vname1, vmodel1, vprice1, new Employee(eno1)));
			employee1.getProjects().add(project);

			session.save(employee1);
			tr.commit();
			break;
		case 3:
			System.out.println("UPDATION");
			System.out.println("-----------------------------");

			System.out.println("enter eno");
			int eno2 = sc.nextInt();
			System.out.println("enter ename");
			// fetching pcode which are not present in current employee
			Employee employee2 = session.get(Employee.class, eno2);

			String ename2 = sc.next();
			System.out.println("enter esal");
			int esal2 = sc.nextInt();

			System.out.println("1.Add Project 2.Remove Project 3.No change");
			int choice2 = sc.nextInt();
			System.out.println("choose project code ---------------");
			CriteriaBuilder cb3 = session.getCriteriaBuilder();
			CriteriaQuery<Integer> cq3 = cb3.createQuery(Integer.class);
			Root<Project> root3 = cq3.from(Project.class);
			cq3.select(root3.<Integer>get("pcode"));
			List<Integer> list3 = session.createQuery(cq3).list();

			List<Integer> existingProject = employee2.getProjects().stream().map(x -> x.getPcode())
					.collect(Collectors.toList());

			employee2.setEno(eno2);
			employee2.setEname(ename2);
			employee2.setEsal(esal2);

			int pcode2 = 0;
			switch (choice2) {
			case 1:
				System.out.println("pcode");
				System.out.println("------------------");
				list3.stream().filter(x -> !existingProject.contains(x)).forEach(x -> System.out.println(x));
				pcode2 = sc.nextInt();
				Project prj = session.get(Project.class, pcode2);
				employee2.getProjects().add(prj);// this will update employee_project
				break;
			case 2:
				list3.stream().filter(x -> existingProject.contains(x)).forEach(x -> System.out.println(x));
				pcode2 = sc.nextInt();
				Project prj1 = session.get(Project.class, pcode2);
				employee2.getProjects().remove(prj1);
				break;
			case 3:
				break;

			}
			Transaction tr1 = session.beginTransaction();
			session.saveOrUpdate(employee2);
			tr1.commit();

			break;
		case 4:
			System.out.println("DELETION");
			System.out.println("-----------------------------");

			System.out.println("enter eno");
			int eno3 = sc.nextInt();
			Employee employee3 = session.get(Employee.class, eno3);

			Transaction tr2 = session.beginTransaction();
			session.delete(employee3);
			tr2.commit();

			break;
		default:
			System.out.println("CHOOSE CORRECT OPTION");
		}
		sc.close();
		session.close();
	}

}
