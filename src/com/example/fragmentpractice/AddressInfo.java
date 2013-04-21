package com.example.fragmentpractice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class AddressInfo extends Activity {
 
	private String action = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// display screen to enter address info
		setContentView(R.layout.activity_address_info);
		Intent intent = getIntent();
		action = intent.getStringExtra("Action");
		if(!action.equalsIgnoreCase("Create")){
			
			Address address = AddressBook.getAddress(action);
			String addressNotes = address.getNotes();
			String addressLocation = address.getLocation();
			
			EditText addressNameEdit = (EditText) findViewById(R.id.address_name);
			EditText addressNotesEdit = (EditText) findViewById(R.id.address_notes);
			EditText addressLocationEdit = (EditText) findViewById(R.id.address_location);
			
			addressNameEdit.setText(action);
			addressNotesEdit.setText(addressNotes);
			addressLocationEdit.setText(addressLocation);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu
		// this menu needs to be edited
		getMenuInflater().inflate(R.menu.activity_address_info, menu);
		return true;
	}

	public void createAddress(View view){
		// get data from text boxes
		EditText addressName = (EditText) findViewById(R.id.address_name);
		EditText addressNotes = (EditText) findViewById(R.id.address_notes);
		EditText addressLocation = (EditText) findViewById(R.id.address_location);
		
		AddressDbHelper db = new AddressDbHelper(this);
		
		Address address = AddressBook.getAddress(addressName.getText().toString());
		
		if(address != null) {
			//update address object with the information
			int addressID = address.getID();
			address = new Address(addressID, addressName.getText().toString(), addressNotes.getText().toString(), addressLocation.getText().toString());
		} else {
			//create an address object with the information
			address = new Address(addressName.getText().toString(), addressNotes.getText().toString(), addressLocation.getText().toString());	
		}
		
		//create an address object with the information
		//Address address = new Address(addressName.getText().toString(), addressNotes.getText().toString(), addressLocation.getText().toString());
		
		// make a database connection and add address to it
		//AddressDbHelper db = new AddressDbHelper(this);
		
		
		
		if(action.equalsIgnoreCase("Create")) {
			db.addAddress(address);
			//db.addAddress(new Address("Hell", null, null));
		} else {
			//needs to be fixed
			db.updateAddress(address);
			//db.addAddress(new Address("He'll", null, null));
			//db.addAddress(address);
		}
		db.close();
		// on clicking "Create"
		// control is transfered back to the address book
		Intent intent = new Intent(this, AddressBook.class);
		intent.putExtra("EditOrSelect", "edit");
		startActivity(intent);
	}
	


}
