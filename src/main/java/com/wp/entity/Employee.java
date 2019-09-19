package com.wp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@NamedQueries({ @NamedQuery(name = "GET_ALL_EMPLOYEE", query = "from Employee") })
public class Employee {

	@Id
	private int eno;
	private String ename;
	private int esal;
	@OneToOne(fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	private Laptop laptop;

	@OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, orphanRemoval = true)
	@Cascade({ CascadeType.ALL})
	private List<Vehicle> vehicles = new ArrayList<Vehicle>();


	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "emp_prj",
	joinColumns = { @JoinColumn(name = "fk_emp") },
	inverseJoinColumns = { @JoinColumn(name = "fk_prj") })
//	@Cascade(CascadeType.ALL) //no require for cascading in many to many as it autimatically work
	private List<Project> projects = new ArrayList<Project>();
	
	
	public Employee(int eno, String ename, int esal) {
		super();
		this.eno = eno;
		this.ename = ename;
		this.esal = esal;
	}

	public Employee(int eno) {
		super();
		this.eno = eno;
	}

	

	public Employee(int eno, String ename, int esal, Laptop laptop) {
		super();
		this.eno = eno;
		this.ename = ename;
		this.esal = esal;
		this.laptop = laptop;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(ArrayList<Project> projects) {
		this.projects = projects;
	}

	public Employee() {
		super();
	}

	public Laptop getLaptop() {
		return laptop;
	}

	public void setLaptop(Laptop laptop) {
		this.laptop = laptop;
	}

	public int getEno() {
		return eno;
	}

	public void setEno(int eno) {
		this.eno = eno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public int getEsal() {
		return esal;
	}

	public void setEsal(int esal) {
		this.esal = esal;
	}

	@Override
	public String toString() {
		return "Employee [eno=" + eno + ", ename=" + ename + ", esal=" + esal + ", laptop=" + laptop + ", vehicles="
				+ vehicles + ", projects=" + projects + "]";
	}

}
