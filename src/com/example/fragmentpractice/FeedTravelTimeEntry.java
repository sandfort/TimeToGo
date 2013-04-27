package com.example.fragmentpractice;

import android.provider.BaseColumns;

public class FeedTravelTimeEntry implements BaseColumns {
	public static final String TABLE_NAME = "travel_times";
	public static final String ENTRY_ID = "id";
	public static final String START_ADDRESS = "startAddress";
	public static final String END_ADDRESS = "endAddress";
	public static final String TRAVEL_TIME = "travelTime";

}
