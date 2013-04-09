package com.example.fragmentpractice;

import android.provider.BaseColumns;

public abstract class FeedEventEntry implements BaseColumns {
	public static final String TABLE_NAME = "events";
	public static final String EVENT_ID = "id";
	public static final String EVENT_NAME = "name";
	public static final String EVENT_NOTE = "notes";
	public static final String EVENT_TIME = "time";
	public static final String EVENT_DATE = "date";
	public static final String EVENT_ADDRESS = "address";
	public static final String EVENT_CONTACTS = "contacts";

}
