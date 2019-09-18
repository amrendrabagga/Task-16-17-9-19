package com.wp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Cascade;

@Entity
@NamedQueries({ @NamedQuery(name = "GET_ALL_LAPTOP", query = "from Laptop") })
public class Laptop {

	@Id
	private int code;
	private String brand;
	private int price;
	@OneToOne(mappedBy = "laptop")
	@Cascade(CascadeType.DELETE_ORPHAN)
	private Employee employee;

	public Laptop(int code, String brand, int price) {
		super();
		this.code = code;
		this.brand = brand;
		this.price = price;
	}

	public Laptop() {
		super();
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Laptop(int code) {
		super();
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Laptop [code=" + code + ", brand=" + brand + ", price=" + price + "]";
	}

}
