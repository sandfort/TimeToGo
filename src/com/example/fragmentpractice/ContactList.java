package com.example.fragmentpractice;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

public class ContactList extends ListActivity {

	private ArrayList<Contact> contacts = new ArrayList<Contact>();
	private static ContactList contactList = new ContactList();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// display contact list 
		setContentView(R.layout.activity_contact_list);
		// make database connection
		ContactDbHelper db = new ContactDbHelper(this);
		// get all contacts
		contacts = db.getAllContacts();
		// display contacts or say that none exist
		if (contacts == null) {
			String[] contactStrings = new String[] { "No Contacts"};
			setListAdapter(new ArrayAdapter<String>(this, R.layout.list_layout,	contactStrings));
		} else {
			String[] contactStrings = new String[contacts.size()];
			for (int i = 0; i < contacts.size(); ++i) {
				contactStrings[i] = contacts.get(i).getName();
			}
			setListAdapter(new ArrayAdapter<String>(this, R.layout.list_layout,	contactStrings));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu
		// this menu says "Create Contact" 
		getMenuInflater().inflate(R.menu.activity_contact_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// on clicking "Create Contact"
		// control is transfered to screen to enter contact info
		int choice = item.getItemId();
		if (choice == R.id.create_contact_button) {
			Intent intent = new Intent(this, ContactInfo.class);
			intent.putExtra("Action", "Create");
			startActivity(intent);
		//	return true;
		} else if (choice == R.id.clear_contacts_database){
			ContactDbHelper db = new ContactDbHelper(this);
			this.deleteDatabase(db.getName());
			contacts = db.getAllContacts();
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		//	return true;
		}		
		
		
    	return true;
	}
	
	public ArrayList<Contact> getContacts() {
		return contacts;
	}
	
	public static ContactList getContactList() {
		return contactList;
	}
	
	public void removeContact(Contact contact) {
		contacts.remove(contact);
	}
	
	public void addContact(Contact contact) {
		contacts.add(contact);
	}
	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		return;
	}

	
}
