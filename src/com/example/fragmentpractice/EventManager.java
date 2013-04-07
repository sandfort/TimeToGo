package com.example.fragmentpractice;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

public class EventManager extends ListActivity {

	private ArrayList<Event> events = new ArrayList<Event>();
	private static EventManager eventManager = new EventManager();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		events = intent.getParcelableArrayListExtra("events");
		setContentView(R.layout.activity_event_manager);
		if (events == null) {
			String[] eventStrings = new String[] { "No Events"};
			setListAdapter(new ArrayAdapter<String>(this, R.layout.list_layout,	eventStrings));
		} else {
			String[] eventStrings = new String[events.size()];
			for (int i = 0; i < events.size(); ++i) {
				eventStrings[i] = events.get(i).getName();
			}
			setListAdapter(new ArrayAdapter<String>(this, R.layout.list_layout,	eventStrings));
		}
//		setContentView(R.layout.activity_event_manager);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_event_manager, menu);
		return true;
	}
	
	//public void enterEventInfo(View view) {
    //	Intent intent = new Intent(this, EventInfo.class);
    //	startActivity(intent);
	//}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int choice = item.getItemId();
		if (choice == R.id.create_event_button) {
			Intent intent = new Intent(this, EventInfo.class);
			intent.putExtra("events", events);
			startActivity(intent);
		//	return true;
		} else if (choice == R.id.save_events_button){
			//save data
		//	return true;
		}
		return true;
		
	}
	
	public ArrayList<Event> getEvents() {
		return events;
	}
	
	public static EventManager getEventManager() {
		return eventManager;
	}
	
	

}
