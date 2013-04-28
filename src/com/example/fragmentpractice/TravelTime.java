package com.example.fragmentpractice;

public class TravelTime {
	private int _id;
	private Address startAddress;
	private Address endAddress;
	private float travelTime;
	
	public TravelTime(Address startAddress, Address endAddress, float travelTime) {
		this._id = EventManager.getNextTravelTimeID();
		this.startAddress = startAddress;
		this.endAddress = endAddress;
		this.travelTime = travelTime;
	}

	public TravelTime(int _id, Address startAddress, Address endAddress, float travelTime) {
		this._id = _id;
		this.startAddress = startAddress;
		this.endAddress = endAddress;
		this.travelTime = travelTime;
	}

	public int getID() {
		return this._id;
	}
	
	public Address getStartAddress() {
		return this.startAddress;
	}
	
	public String getStartAddressString() {
		return this.startAddress.getName();
	}
	
	public Address getEndAddress() {
		return this.endAddress;
	}
	
	public String getEndAddressString() {
		return this.endAddress.getName();
	}
	
	public float getTravelTime() {
		return this.travelTime;
	}
	
	public void editStartAddress(Address startAddress) {
		this.startAddress = startAddress;
	}
	
	public void editEndAddress(Address endAddress) {
		this.endAddress = endAddress;
	}
	
	public void editTravelTime(int travelTime) {
		this.travelTime = travelTime;
	}
}
