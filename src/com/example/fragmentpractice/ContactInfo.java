package com.example.fragmentpractice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class ContactInfo extends Activity {

	//private ArrayList<Contact> contacts = new ArrayList<Contact>();
	private String action = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// displays contact list
		setContentView(R.layout.activity_contact_info);
		Intent intent = getIntent();
		action = intent.getStringExtra("Action");
		if(!action.equalsIgnoreCase("Create")){
			
			Contact contact = ContactList.getContact(action);
			String contactNotes = contact.getNotes();
			String contactEmail = contact.getEmail();
			String contactPhoneNumber = contact.getPhoneNumber();
			
			EditText contactNameEdit = (EditText) findViewById(R.id.contact_name);
			EditText contactNotesEdit = (EditText) findViewById(R.id.contact_notes);
			EditText contactEmailEdit = (EditText) findViewById(R.id.contact_email);
			EditText contactPhoneNumberEdit = (EditText) findViewById(R.id.contact_phone_number);
			
			
			contactNameEdit.setText(action);
			contactNotesEdit.setText(contactNotes);
			contactEmailEdit.setText(contactEmail);
			contactPhoneNumberEdit.setText(contactPhoneNumber);
		}
	
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
		
		ContactDbHelper db = new ContactDbHelper(this);
		
		Contact contact = ContactList.getContact(contactName.getText().toString());
		if(contact != null) {
			int contactID = contact.getID();
			contact = new Contact(contactID, contactName.getText().toString(), contactNotes.getText().toString(), contactPhoneNumber.getText().toString(), contactEmail.getText().toString());
		} else {
			contact = new Contact(contactName.getText().toString(), contactNotes.getText().toString(), contactPhoneNumber.getText().toString(), contactEmail.getText().toString());
		}
		
		// create contact object with information
		//Contact contact = new Contact(contactName.getText().toString(), contactNotes.getText().toString(), contactEmail.getText().toString(), contactPhoneNumber.getText().toString());
		
		// make database connection and add contact to it
		//ContactDbHelper db = new ContactDbHelper(this);
		
		
		if(action.equalsIgnoreCase("Create")) {
			db.addContact(contact);
		} else {
			db.updateContact(contact);
//			Toast.makeText(getBaseContext(), "UpDate", Toast.LENGTH_LONG).show();
			//db.addAddress(new Address("He'll", null, null));
			//db.addAddress(address);
		}
		db.close();
		

		// on clicking "Create"
		// control is transfered back to the contact list
		Intent intent = new Intent(this, ContactList.class);
		intent.putExtra("EditOrSelect", "edit");
		startActivity(intent);
	}

}
