package com.wp.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Employee {

	@Id
	private int eno;
	private String ename;
	private int esal;
	@OneToOne(fetch=FetchType.LAZY)
	private Laptop laptop;
	
	@OneToOne(fetch=FetchType.LAZY)
	private Vehicle vehicle;
	
	public Employee(int eno, String ename, int esal, Laptop laptop, Vehicle vehicle) {
		super();
		this.eno = eno;
		this.ename = ename;
		this.esal = esal;
		this.laptop = laptop;
		this.vehicle = vehicle;
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
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
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
	@Override
	public String toString() {
		return "Employee [eno=" + eno + ", ename=" + ename + ", esal=" + esal + ", laptop=" + laptop + ", vehicle="
				+ vehicle + "]";
	}
	public int getEsal() {
		return esal;
	}
	public void setEsal(int esal) {
		this.esal = esal;
	}
	
	
}
