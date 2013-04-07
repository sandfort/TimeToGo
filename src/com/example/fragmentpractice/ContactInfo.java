package com.example.fragmentpractice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class ContactInfo extends Activity {

	//private ArrayList<Contact> contacts = new ArrayList<Contact>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// displays contact list
		setContentView(R.layout.activity_contact_info);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu
		getMenuInflater().inflate(R.menu.activity_contact_info, menu);
		return true;
	}
	
	public void createContact(View view){
		// get contact info from text boxes
		EditText contactName = (EditText) findViewById(R.id.contact_name);
		EditText contactNotes = (EditText) findViewById(R.id.contact_notes);
		EditText contactEmail = (EditText) findViewById(R.id.contact_email);
		EditText contactPhoneNumber = (EditText) findViewById(R.id.contact_phone_number);
		
		// create contact object with information
		Contact contact = new Contact(contactName.getText().toString(), contactNotes.getText().toString(), contactEmail.getText().toString(), contactPhoneNumber.getText().toString());
		
		// make database connection and add contact to it
		ContactDbHelper db = new ContactDbHelper(this);
		db.addContact(contact);

		// on clicking "Create"
		// control is transfered back to the contact list
		Intent intent = new Intent(this, ContactList.class);
		startActivity(intent);
	}

}
