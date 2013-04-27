package com.example.fragmentpractice;

import java.util.ArrayList;

//public class Event implements Parcelable {
public class Event {
	private int _id;
	private String name;
	private String notes;
	private String time;
	private String date;
	private Address startAddress;
	private Address endAddress;
	private ArrayList<Contact> contacts;
	private Alarm alarm;

	public Event(String name, String notes, String time, String date,
			Address startAddress, Address endAddress, ArrayList<Contact> contacts) {
		this._id = EventManager.getNextEventID();
		this.name = name;
		this.notes = notes;
		this.time = time;
		this.date = date;
		this.startAddress = startAddress;
		this.endAddress = endAddress;
		this.contacts = contacts;
	}

	public Event(int _id, String name, String notes, String time, String date,
			Address startAddress, Address endAddress, ArrayList<Contact> contacts) {
		this._id = _id;
		this.name = name;
		this.notes = notes;
		this.time = time;
		this.date = date;
		this.startAddress = startAddress;
		this.endAddress = endAddress;
		this.contacts = contacts;
	}

	public String getName() {
		return name;
	}

	public String getNotes() {
		return notes;
	}

	public String getTime() {
		return time;
	}

	public String getDate() {
		return date;
	}

	public Address getStartAddress() {
		return startAddress;
	}
	
	public Address getEndAddress() {
		return endAddress;
	}

	public String getStartAddressName() {
		if (startAddress != null)
			return startAddress.getName();
		return "None";
	}
	
	public String getEndAddressName() {
		if (endAddress != null)
			return endAddress.getName();
		return "None";
	}


	public String getContactsString() {
		if (contacts != null) {
			String output = "";
			for (int i = 0; i < contacts.size(); ++i) {
				if (i == contacts.size() - 1) {
					output = output + contacts.get(i).getName();
				} else {
					output = output + contacts.get(i).getName().trim() + "; ";
				}
			}
			return output;
		}
		return "None";
	}

	public int getID() {
		return _id;
	}

	public ArrayList<Contact> getContacts() {
		return contacts;
	}

	public Alarm getAlarm() {
		return alarm;
	}

	public void editName(String name) {
		this.name = name;
	}

	public void editNotes(String notes) {
		this.notes = notes;
	}

	public void editDate(String date) {
		this.date = date;
	}

	public void editStartAddess(Address startAddress) {
		this.startAddress = startAddress;
	}
	
	public void editEndAddess(Address endAddress) {
		this.endAddress = endAddress;
	}

	public void addContact(Contact contact) {
		if (contacts == null) {
			contacts = new ArrayList<Contact>();
		}
		contacts.add(contact);
	}

	public void removeContact(Contact contact) {
		contacts.remove(contact);
	}

}
