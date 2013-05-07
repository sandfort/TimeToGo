package com.example.fragmentpractice;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QuickTextsDbHelper extends SQLiteOpenHelper {
	
	private static final String TEXT_TYPE = " TEXT";
	private static final String SQL_CREATE_ENTRIES = "CREATE TABLE "
			+ FeedQuickTextsEntry.TABLE_NAME + "(" + FeedQuickTextsEntry.QUICKTEXT_ID
			+ " INTEGER PRIMARY KEY," + FeedQuickTextsEntry.QUICKTEXT_STRING
			+ TEXT_TYPE + ")";

	private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
			+ FeedQuickTextsEntry.TABLE_NAME;
	
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "QuickTexts.db";
	
	public QuickTextsDbHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_ENTRIES);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(SQL_DELETE_ENTRIES);
		onCreate(db);
		
	}
	
	public void onDownGrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onUpgrade(db, oldVersion, newVersion);
	}
	
	public void addQuickText(QuickText quickText) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(FeedQuickTextsEntry.QUICKTEXT_STRING, quickText.getQuickText());

		db.insert(FeedQuickTextsEntry.TABLE_NAME, null, values);
		db.close();
	}
	
	public QuickText getQuickText(int id){
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(FeedQuickTextsEntry.TABLE_NAME, new String[] {
				FeedQuickTextsEntry.QUICKTEXT_ID, 
				FeedQuickTextsEntry.QUICKTEXT_STRING},
				FeedQuickTextsEntry.QUICKTEXT_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);

		if (cursor != null)
			cursor.moveToFirst();

		QuickText quickText = new QuickText(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1));
		return quickText;
	}
	
	public ArrayList<QuickText> getAllQuickTexts(){
		ArrayList<QuickText> quickTexts = new ArrayList<QuickText>();

		String selectQuery = "SELECT  * FROM " + FeedQuickTextsEntry.TABLE_NAME;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				QuickText quickText = new QuickText(Integer.parseInt(cursor
						.getString(0)), cursor.getString(1));
				quickTexts.add(quickText);
			} while (cursor.moveToNext());
		}
		return quickTexts;
	}
	
	
	public int updateQuickText(QuickText quickText) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(FeedQuickTextsEntry.QUICKTEXT_STRING, quickText.getQuickText());

		return db.update(FeedQuickTextsEntry.TABLE_NAME, values,
				FeedQuickTextsEntry.QUICKTEXT_ID + " = ?",
				new String[] { String.valueOf(quickText.getID()) });
	}

	
	public void deleteQuickText(QuickText quickText) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(FeedQuickTextsEntry.TABLE_NAME, FeedQuickTextsEntry.QUICKTEXT_ID
				+ " = ?", new String[] { String.valueOf(quickText.getID()) });
		db.close();
	}

	public String getName() {
		return DATABASE_NAME;
	}
}
