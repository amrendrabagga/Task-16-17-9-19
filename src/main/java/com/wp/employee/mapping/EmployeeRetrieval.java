package com.wp.employee.mapping;

import java.util.List;

import org.hibernate.Criteria;

import com.wp.entity.Employee;
import com.wp.util.Util;

public class EmployeeRetrieval {

	public static void main(String args[]) {
		Criteria criteria = Util.getSF(Employee.class).openSession().createCriteria(Employee.class);
		List<Employee> list = criteria.list();
		//lazy fetch
		for(Employee emp : list) {
			System.out.println(emp.getEno());
			System.out.println(emp.getEname());
			System.out.println(emp.getEsal());
		}
			
		

		
	}
}
