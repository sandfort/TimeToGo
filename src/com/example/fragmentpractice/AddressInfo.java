package com.example.fragmentpractice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class AddressInfo extends Activity {
 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// display screen to enter address info
		setContentView(R.layout.activity_address_info);
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
				
		//create an address object with the information
		Address address = new Address(addressName.getText().toString(), addressNotes.getText().toString(), addressLocation.getText().toString());
		
		// make a database connection and add address to it
		AddressDbHelper db = new AddressDbHelper(this);
		db.addAddress(address);
		
		// on clicking "Create"
		// control is transfered back to the address book
		Intent intent = new Intent(this, AddressBook.class);
		startActivity(intent);
	}
	
}
