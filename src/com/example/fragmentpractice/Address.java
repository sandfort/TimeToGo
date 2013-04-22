package com.example.fragmentpractice;

//public class Address implements Parcelable {
public class Address {
	private int _id;
	private String name;
	private String notes;
	private String location;

	public Address(String name, String notes, String location) {
		this._id = AddressBook.nextAddressID;
		this.name = name;
		this.notes = notes;
		this.location = location;
	}

	public Address(int _id, String name, String notes, String location) {
		this._id = _id;
		this.name = name;
		this.notes = notes;
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public String getNotes() {
		return notes;
	}

	public String getLocation() {
		return location;
	}

	public int getID() {
		return _id;
	}

	public void editName(String name) {
		this.name = name;
	}

	public void editNotes(String notes) {
		this.notes = notes;
	}

	public void editLocation(String location) {
		this.location = location;
	}

}
