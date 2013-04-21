package com.example.fragmentpractice;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactDbHelper extends SQLiteOpenHelper {
	
	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";
	private static final String SQL_CREATE_ENTRIES =
		    "CREATE TABLE " + FeedContactEntry.TABLE_NAME + "(" +
		    		FeedContactEntry.CONTACT_ID + " INTEGER PRIMARY KEY," +
		    		FeedContactEntry.CONTACT_NAME + TEXT_TYPE + COMMA_SEP +
		    		FeedContactEntry.CONTACT_NOTE + TEXT_TYPE + COMMA_SEP +
		    		FeedContactEntry.CONTACT_PHONE_NUMBER + TEXT_TYPE + COMMA_SEP +
		    		FeedContactEntry.CONTACT_EMAIL + TEXT_TYPE + ")";
	
	private static final String SQL_DELETE_ENTRIES =
		    "DROP TABLE IF EXISTS " + FeedContactEntry.TABLE_NAME;

	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "Contacts.db";
	
	public ContactDbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_ENTRIES);
	}
	
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(SQL_DELETE_ENTRIES);
		onCreate(db);
	}
	
	public void onDownGrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onUpgrade(db, oldVersion, newVersion);
	}
	
	//add new contact to the db
	public void addContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(FeedContactEntry.CONTACT_NAME, contact.getName());
		values.put(FeedContactEntry.CONTACT_NOTE, contact.getNotes());
		values.put(FeedContactEntry.CONTACT_PHONE_NUMBER, contact.getPhoneNumber());
		values.put(FeedContactEntry.CONTACT_EMAIL, contact.getEmail());
		
		db.insert(FeedContactEntry.TABLE_NAME, null, values);
		db.close();
	}
	
	public Contact getContact(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor = db.query(FeedContactEntry.TABLE_NAME, 
				new String[] {FeedContactEntry.CONTACT_ID, FeedContactEntry.CONTACT_NAME, FeedContactEntry.CONTACT_NOTE, FeedContactEntry.CONTACT_PHONE_NUMBER, FeedContactEntry.CONTACT_EMAIL},
				FeedContactEntry.CONTACT_ID	+ "=?", new String[] { String.valueOf(id) }, null, null, null, null); 
		
		if (cursor != null)
	        cursor.moveToFirst();
		
		Contact contact = new Contact(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
		return contact;
	}
	
	public ArrayList<Contact> getAllContacts() {
		ArrayList<Contact> contacts = new ArrayList<Contact>();

	    String selectQuery = "SELECT  * FROM " + FeedContactEntry.TABLE_NAME;
	 
	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);
	 
	    if (cursor.moveToFirst()) {
	        do {
	        	Contact contact = new Contact(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
	            contacts.add(contact);
	        } while (cursor.moveToNext());
	    }
	 
	    return contacts;
	}
	
	public int updateContact(Contact contact) {
	    SQLiteDatabase db = this.getWritableDatabase();
	 
	    ContentValues values = new ContentValues();
	    values.put(FeedContactEntry.CONTACT_NAME, contact.getName());
	    values.put(FeedContactEntry.CONTACT_NOTE, contact.getNotes());
	    values.put(FeedContactEntry.CONTACT_PHONE_NUMBER, contact.getPhoneNumber());
	    values.put(FeedContactEntry.CONTACT_EMAIL, contact.getEmail());
	    
	    return db.update(FeedContactEntry.TABLE_NAME, values, FeedContactEntry.CONTACT_ID + " = ?",
	            new String[] { String.valueOf(contact.getID()) });
	}
	
	public void deleteContact(Contact contact) {
	    SQLiteDatabase db = this.getWritableDatabase();
	    db.delete(FeedContactEntry.TABLE_NAME, FeedContactEntry.CONTACT_ID + " = ?",
	            new String[] { String.valueOf(contact.getID()) });
	    db.close();
	}
	
	public String getName() {
		return DATABASE_NAME;
	}
	

}
