package com.example.fragmentpractice;

import android.provider.BaseColumns;

public abstract class FeedEventEntry implements BaseColumns {
	public static final String TABLE_NAME = "events";
	public static final String EVENT_ID = "id";
	public static final String EVENT_NAME = "name";
	public static final String EVENT_NOTE = "notes";
	public static final String EVENT_TIME = "time";
	public static final String EVENT_DATE = "date";
	public static final String EVENT_START_ADDRESS = "startAddress";
	public static final String EVENT_END_ADDRESS = "endAddress";
	public static final String EVENT_CONTACTS = "contacts";

}
