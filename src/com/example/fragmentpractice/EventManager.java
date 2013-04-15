package com.example.fragmentpractice;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class EventManager extends ListActivity {

	private static ArrayList<Event> events = new ArrayList<Event>();
	private static EventManager eventManager = new EventManager();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Intent intent = getIntent();
		//events = intent.getParcelableArrayListExtra("events");
		
		//show event manager
		setContentView(R.layout.activity_event_manager);
		//make new database connection
		EventDbHelper db = new EventDbHelper(this);
		//get all addresses from address database
		events = db.getAllEvents();
		//displays addresses or says that no addresses exist
		if (events == null) {
			String[] eventStrings = new String[] { "No Events"};
			setListAdapter(new ArrayAdapter<String>(this, R.layout.list_layout,	eventStrings));
		} else {
			String[] eventStrings = new String[events.size()];
			for (int i = 0; i < events.size(); ++i) {
				eventStrings[i] = events.get(i).getName();
			}
			setListAdapter(new ArrayAdapter<String>(this, R.layout.list_layout,	eventStrings));
			
			ListView eventManager = (ListView) findViewById(android.R.id.list);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_layout,	eventStrings);
			eventManager.setAdapter(adapter);
			eventManager.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					//String item = ((TextView)view).getText().toString();
		               
	                //Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG).show();
	                
	        		Intent intent = new Intent(EventManager.this, EventInfo.class);
	            	intent.putExtra("Action", ((TextView)view).getText().toString());
	        		startActivity(intent);
	            	//return true;
				}
			});
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
			intent.putExtra("Action", "Create");
			startActivity(intent);
		//	return true;
		} else if (choice == R.id.clear_events_database){
			EventDbHelper db = new EventDbHelper(this);
			this.deleteDatabase(db.getName());
			events = db.getAllEvents();
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
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
	
	public static Event getEvent(String eventName) {
		for(int i = 0; i < events.size(); ++i) {
			if(events.get(i).getName().equalsIgnoreCase(eventName)) {
				return events.get(i);
			}
		}
		return null;
	}
	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		return;
	}

	

}
