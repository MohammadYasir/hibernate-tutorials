package com.myasir.tableperclass.tables;

import javax.persistence.Entity;

@Entity
public class Truck extends Vehicle {

	private int maxPayload;
	private boolean isContainerized;
	
	public int getMaxPayload() {
		return maxPayload;
	}
	public void setMaxPayload(int maxPayload) {
		this.maxPayload = maxPayload;
	}
	public boolean isContainerized() {
		return isContainerized;
	}
	public void setContainerized(boolean isContainerized) {
		this.isContainerized = isContainerized;
	}
}
