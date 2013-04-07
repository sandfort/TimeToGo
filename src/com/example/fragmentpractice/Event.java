package com.example.fragmentpractice;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class Event implements Parcelable {
//public class Event {
	private int _id;
	private String name;
	private String notes;
	private String date;
	private Address address;
	private ArrayList<Contact> contacts;
	private Alarm alarm;
	
	public Event(String name, String notes, String date, Address address, ArrayList<Contact> contacts) {
		this.name = name;
		this.notes = notes;
		this.date = date;
		this.address = address;
		this.contacts = contacts;
	}
	
	public Event(int _id, String name, String notes, String date, Address address, ArrayList<Contact> contacts) {
		this._id = _id;
		this.name = name;
		this.notes = notes;
		this.date = date;
		this.address = address;
		this.contacts = contacts;
	}
	
	
	public void calculateNow() {
		
	}
		
	public void beginTrip() {
		
	}
	
	
	public String getName() {
		return name;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public String getDate() {
		return date;
	}
	
	public Address getAddress() {
		return address;
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
	
	public void editAddess(Address address) {
		this.address = address;
	}
	
	public void addContact(Contact contact) {
		contacts.add(contact);
	}
	
	public void removeContact(Contact contact) {
		contacts.remove(contact);
	}
	
	 @Override
	    public void writeToParcel(Parcel dest, int flags) {
	        dest.writeString(name);
	        dest.writeString(notes);
	        dest.writeString(date);
	        //dest.writeParcelable(address, 0);
	        //dest.writeTypedList(contacts);
	        dest.writeValue(alarm);
	    }
	   
	    public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
	    	public Event createFromParcel(Parcel in) {
	    		return new Event(in);
	    	}

	    	public Event[] newArray(int size) {
	    		return new Event[size];
	    	}
	    };

	    public Event(Parcel in) {
	    	name = in.readString();
	    	notes = in.readString();
	    	date = in.readString();
	    	address = null;
	    	contacts = null;
	    	alarm = null;
//	    	address = in.readParcelable(Address.class);
//	    	contact
	    }


		@Override
		public int describeContents() {
			// TODO Auto-generated method stub
			return 0;
		}
	
	
	
}
