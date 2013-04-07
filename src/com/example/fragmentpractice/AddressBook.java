package com.example.fragmentpractice;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

public class AddressBook extends ListActivity {

	private ArrayList<Address> addresses = new ArrayList<Address>();
	private static AddressBook addressBook = new AddressBook();
	public static int count = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//show address book
		setContentView(R.layout.activity_address_book);
		//make new database connection
		AddressDbHelper db = new AddressDbHelper(this);
		//get all addresses from address database
		addresses = db.getAllAdresses();
		//displays addresses or says that no addresses exist
		if (addresses == null) {
			String[] addressStrings = new String[] { "No Addresses"};
			setListAdapter(new ArrayAdapter<String>(this, R.layout.list_layout,	addressStrings));
		} else {
			String[] addressStrings = new String[addresses.size()];
			for (int i = 0; i < addresses.size(); ++i) {
				addressStrings[i] = addresses.get(i).getName();
			}
			setListAdapter(new ArrayAdapter<String>(this, R.layout.list_layout,	addressStrings));
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu
		//this menu says "Add Address"
		getMenuInflater().inflate(R.menu.activity_address_book, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//on selecting "Add Address" 
		//control is transfer to activity where address info can be entered
		Intent intent = new Intent(this, AddressInfo.class);
    	startActivity(intent);
    	return true;
	}
	
	public ArrayList<Address> getAddresses() {
		return addresses;
	}
	
	public static AddressBook getAddressBook() {
		return addressBook;
	}
	
	public void removeAddress(Address address) {
		addresses.remove(address);
	}	
	
	public void addAddress(Address address) {
		addresses.add(address);
	}

}
