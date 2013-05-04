package com.example.fragmentpractice;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AddressDbHelper extends SQLiteOpenHelper {

	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";
	private static final String SQL_CREATE_ENTRIES = "CREATE TABLE "
			+ FeedAddressEntry.TABLE_NAME + "(" + FeedAddressEntry.ADDRESS_ID
			+ " INTEGER PRIMARY KEY," + FeedAddressEntry.ADDRESS_NAME
			+ TEXT_TYPE + COMMA_SEP + FeedAddressEntry.ADDRESS_NOTE + TEXT_TYPE
			+ COMMA_SEP + FeedAddressEntry.ADDRESS_LOCATION + TEXT_TYPE + ")";

	private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
			+ FeedAddressEntry.TABLE_NAME;

	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "Addresses.db";

	public AddressDbHelper(Context context) {
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

	// add new address to the db
	public void addAddress(Address address) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(FeedAddressEntry.ADDRESS_NAME, address.getName());
		values.put(FeedAddressEntry.ADDRESS_NOTE, address.getNotes());
		values.put(FeedAddressEntry.ADDRESS_LOCATION, address.getLocation());

		db.insert(FeedAddressEntry.TABLE_NAME, null, values);
		db.close();
	}

	public Address getAddress(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(FeedAddressEntry.TABLE_NAME, new String[] {
				FeedAddressEntry.ADDRESS_ID, FeedAddressEntry.ADDRESS_NAME,
				FeedAddressEntry.ADDRESS_NOTE,
				FeedAddressEntry.ADDRESS_LOCATION },
				FeedAddressEntry.ADDRESS_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);

		if (cursor != null)
			cursor.moveToFirst();

		Address address = new Address(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2), cursor.getString(3));
		return address;
	}

	public ArrayList<Address> getAllAdresses() {
		ArrayList<Address> addresses = new ArrayList<Address>();

		String selectQuery = "SELECT  * FROM " + FeedAddressEntry.TABLE_NAME;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				Address address = new Address(Integer.parseInt(cursor
						.getString(0)), cursor.getString(1),
						cursor.getString(2), cursor.getString(3));
				addresses.add(address);
			} while (cursor.moveToNext());
		}

		return addresses;
	}

	public int updateAddress(Address address) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(FeedAddressEntry.ADDRESS_NAME, address.getName());
		values.put(FeedAddressEntry.ADDRESS_NOTE, address.getNotes());
		values.put(FeedAddressEntry.ADDRESS_LOCATION, address.getLocation());

		return db.update(FeedAddressEntry.TABLE_NAME, values,
				FeedAddressEntry.ADDRESS_ID + " = ?",
				new String[] { String.valueOf(address.getID()) });
	}

	public void deleteAddress(Address address) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(FeedAddressEntry.TABLE_NAME, FeedAddressEntry.ADDRESS_ID
				+ " = ?", new String[] { String.valueOf(address.getID()) });
		db.close();
	}

	public String getName() {
		return DATABASE_NAME;
	}

}
