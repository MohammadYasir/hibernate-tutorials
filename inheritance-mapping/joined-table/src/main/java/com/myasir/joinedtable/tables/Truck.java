package com.myasir.joinedtable.tables;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("truck")
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
