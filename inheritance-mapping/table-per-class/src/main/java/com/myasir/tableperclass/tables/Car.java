package com.myasir.tableperclass.tables;

import javax.persistence.Entity;

@Entity
public class Car extends Vehicle{
	private int seatingCapacity;
	private String model;
	
	public int getSeatingCapacity() {
		return seatingCapacity;
	}
	public void setSeatingCapacity(int seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
}
