package com.wp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@NamedQueries({ @NamedQuery(name = "GET_ALL_VEHICLE", query = "from Vehicle") })
public class Vehicle {

	@Id
	private int regno;

	private String brand;
	private String model;
	private int price;
	@ManyToOne
	@Cascade(CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "eno")
	private Employee employee;

	public Vehicle(int regno, String brand, String model, int price, Employee employee) {
		super();
		this.regno = regno;
		this.brand = brand;
		this.model = model;
		this.price = price;
		this.employee = employee;
	}

	public Vehicle() {
		super();
	}

	public Vehicle(int regno) {
		super();
		this.regno = regno;
	}

	public int getRegno() {
		return regno;
	}

	public void setRegno(int regno) {
		this.regno = regno;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Vehicle [regno=" + regno + ", brand=" + brand + ", model=" + model + ", price=" + price + "]";
	}

	public Vehicle(int regno, String brand, String model, int price) {
		super();
		this.regno = regno;
		this.brand = brand;
		this.model = model;
		this.price = price;
	}

}
