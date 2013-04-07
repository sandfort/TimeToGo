package com.example.fragmentpractice;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class EventInfo extends Activity {

	private ArrayList<Event> events = new ArrayList<Event>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		events = intent.getParcelableArrayListExtra("events");
		setContentView(R.layout.activity_event_info);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_event_info, menu);
		return true;
	}
	
	

	public void createEvent(View view){
		//Intent intent = new Intent(this, EventManager.class);
		EditText eventName = (EditText) findViewById(R.id.event_name);
		EditText eventNotes = (EditText) findViewById(R.id.event_notes);


		Event event = new Event(eventName.getText().toString(), eventNotes.getText().toString(), null, null, null);
		if(events != null) {
			events.add(event);			
		} else {
			events = new ArrayList<Event>();
			events.add(event);
		}
		
		Intent intent = new Intent(this, EventManager.class);
		intent.putExtra("events", events);
		startActivity(intent);
	}
	
}
