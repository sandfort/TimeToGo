package com.example.fragmentpractice;


public class Contact {
//public class Contact implements Parcelable{
	private int _id;
	private String name;
	private String notes;
	private String phoneNumber;
	private String email;
	
	public Contact(String name, String notes, String phoneNumber, String email) {
		this.name = name;
		this.notes = notes;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	
	public Contact(int _id, String name, String notes, String phoneNumber, String email) {
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
	
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(name);
//        dest.writeString(notes);
//        dest.writeString(phoneNumber);
//        dest.writeString(email);
//    }
//   
//    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {
//    	public Contact createFromParcel(Parcel in) {
//    		return new Contact(in);
//    	}
//
//    	public Contact[] newArray(int size) {
//    		return new Contact[size];
//    	}
//    };
//
//    public Contact(Parcel in) {
//    	name = in.readString();
//    	notes = in.readString();
//    	phoneNumber = in.readString();
//    	email = in.readString();
//    }
//
//
//	@Override
//	public int describeContents() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
		
}
