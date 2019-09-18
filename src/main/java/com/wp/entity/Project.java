package com.wp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@NamedQueries({ @NamedQuery(name = "GET_ALL_PROJECT", query = "from Project") })
public class Project {

	@Id
	private int pcode;
	private String pname;
	private int cost;

	@ManyToMany(mappedBy = "projects")
	@Cascade({ CascadeType.SAVE_UPDATE, CascadeType.DELETE_ORPHAN })
	private List<Employee> employees = new ArrayList<Employee>();

	@Override
	public String toString() {
		return "Project [pcode=" + pcode + ", pname=" + pname + ", cost=" + cost + "]";
	}

	public Project(int pcode) {
		super();
		this.pcode = pcode;
	}

	public Project() {
		super();
	}

	public Project(int pcode, String pname, int cost) {
		super();
		this.pcode = pcode;
		this.pname = pname;
		this.cost = cost;
	}

	public int getPcode() {
		return pcode;
	}

	public void setPcode(int pcode) {
		this.pcode = pcode;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(ArrayList<Employee> employees) {
		this.employees = employees;
	}

}
