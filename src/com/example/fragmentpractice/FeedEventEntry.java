package com.example.fragmentpractice;

import android.provider.BaseColumns;

	public abstract class FeedEventEntry implements BaseColumns {
	    public static final String TABLE_NAME = "events";
	    public static final String COLUMN_NAME_ENTRY_ID = "entryid";
	    public static final String COLUMN_NAME_TITLE = "title";
	    public static final String COLUMN_NAME_SUBTITLE = "subtitle";
	
	
}
