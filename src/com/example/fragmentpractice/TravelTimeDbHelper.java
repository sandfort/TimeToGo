package com.example.fragmentpractice;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TravelTimeDbHelper extends SQLiteOpenHelper {

	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";
	private static final String SQL_CREATE_ENTRIES = "CREATE TABLE "
			+ FeedTravelTimeEntry.TABLE_NAME + "(" + FeedTravelTimeEntry.ENTRY_ID
			+ " INTEGER PRIMARY KEY," + FeedTravelTimeEntry.START_ADDRESS
			+ TEXT_TYPE + COMMA_SEP + FeedTravelTimeEntry.END_ADDRESS + TEXT_TYPE
			+ COMMA_SEP + FeedTravelTimeEntry.TRAVEL_TIME + TEXT_TYPE + ")";

	private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
			+ FeedAddressEntry.TABLE_NAME;

	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "TravelTimes.db";

	public TravelTimeDbHelper(Context context) {
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

	// add new travel time to the db
	public void addTravelTime(TravelTime travelTime) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(FeedTravelTimeEntry.START_ADDRESS, travelTime.getStartAddressString());
		values.put(FeedTravelTimeEntry.END_ADDRESS, travelTime.getEndAddressString());
		values.put(FeedTravelTimeEntry.TRAVEL_TIME, Float.toString(travelTime.getTravelTime()));

		db.insert(FeedTravelTimeEntry.TABLE_NAME, null, values);
		db.close();
	}

	public TravelTime getTravelTime(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(FeedTravelTimeEntry.TABLE_NAME, new String[] {
				FeedTravelTimeEntry.ENTRY_ID, FeedTravelTimeEntry.START_ADDRESS,
				FeedTravelTimeEntry.END_ADDRESS,
				FeedTravelTimeEntry.TRAVEL_TIME},
				FeedTravelTimeEntry.ENTRY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);

		if (cursor != null)
			cursor.moveToFirst();

		TravelTime travelTime = new TravelTime(Integer.parseInt(cursor.getString(0)),
				AddressBook.getAddress(cursor.getString(1)), AddressBook.getAddress(cursor.getString(2)), 
				Float.parseFloat(cursor.getString(3)));
		return travelTime;
	}

	public ArrayList<TravelTime> getAllTravelTime(String startAddress, String endAddress) {
		ArrayList<TravelTime> travelTimes = new ArrayList<TravelTime>();

		String selectQuery = "SELECT  * FROM " + FeedTravelTimeEntry.TABLE_NAME + " WHERE " + FeedTravelTimeEntry.START_ADDRESS + " = "
				+ "'" + startAddress + "'" + " AND " + FeedTravelTimeEntry.END_ADDRESS + " = "
						+ "'" + endAddress + "'";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				TravelTime travelTime = new TravelTime(Integer.parseInt(cursor.getString(0)),
						AddressBook.getAddress(cursor.getString(1)), AddressBook.getAddress(cursor.getString(2)), 
						Float.parseFloat(cursor.getString(3)));
				travelTimes.add(travelTime);
			} while (cursor.moveToNext());
		}

		return travelTimes;
	}

	public String getName() {
		return DATABASE_NAME;
	}

}
