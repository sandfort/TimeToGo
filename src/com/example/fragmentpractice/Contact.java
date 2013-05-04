package com.example.fragmentpractice;

public class Contact {
	private int _id;
	private String name;
	private String notes;
	private String phoneNumber;
	private String email;

	public Contact(String name, String notes, String phoneNumber, String email) {
		this._id = ContactList.getNextContactID();
		this.name = name;
		this.notes = notes;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public Contact(int _id, String name, String notes, String phoneNumber,
			String email) {
		this._id = _id;
		this.name = name;
		this.notes = notes;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public String getNotes() {
		return notes;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
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

	public void editPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void editEmail(String email) {
		this.email = email;
	}
}
