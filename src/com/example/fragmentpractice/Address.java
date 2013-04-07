package com.example.fragmentpractice;

//public class Address implements Parcelable {
public class Address {
	private int _id;
	private String name;
	private String notes;
	private String location;
	
	public Address(String name, String notes, String location) {
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
	
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(name);
//        dest.writeString(notes);
//        dest.writeString(location);
//    }
//   
//    public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>() {
//    	public Address createFromParcel(Parcel in) {
//    		return new Address(in);
//    	}
//
//    	public Address[] newArray(int size) {
//    		return new Address[size];
//    	}
//    };
//
//    public Address(Parcel in) {
//    	name = in.readString();
//    	notes = in.readString();
//    	location = in.readString();
//    }
//
//    
//    
//
//	@Override
//	public int describeContents() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
	
}
