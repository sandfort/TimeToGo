package com.example.fragmentpractice;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class EventInfo extends Activity {

	//private ArrayList<Event> events = new ArrayList<Event>();
	private String action = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_info);
		Intent intent = getIntent();
		action = intent.getStringExtra("Action");
		
		if (!action.equalsIgnoreCase("Create")) {

			Event event = EventManager.getEvent(action);
			String eventNotes = event.getNotes();
			String eventTime = event.getTime();
			String eventDate = event.getDate();
			String eventAddress = event.getAddressName();
			//String eventContacts= event.getContactsString();
			
			EditText eventNameEdit = (EditText) findViewById(R.id.event_name);
			EditText eventNotesEdit = (EditText) findViewById(R.id.event_notes);
			EditText eventTimeEdit = (EditText) findViewById(R.id.event_time);
			EditText eventDateEdit = (EditText) findViewById(R.id.event_date);
			EditText eventAddressEdit = (EditText) findViewById(R.id.event_address);
			//EditText eventContactsEdit = (EditText) findViewById(R.id.event_name);
			
			eventNameEdit.setText(action);
			eventNotesEdit.setText(eventNotes);
			eventTimeEdit.setText(eventTime);
			eventDateEdit.setText(eventDate);
			eventAddressEdit.setText(eventAddress);
			//eventNotesEdit.setText(eventNotes);	
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_event_info, menu);
		return true;
	}
	
	
	public void selectAddress(View view) {
		
	}
	

	public void createEvent(View view){
		//get data from text boxes
		EditText eventName = (EditText) findViewById(R.id.event_name);
		EditText eventNotes = (EditText) findViewById(R.id.event_notes);
		EditText eventTime = (EditText) findViewById(R.id.event_time);
		EditText eventDate = (EditText) findViewById(R.id.event_date);
		EditText eventAddress = (EditText) findViewById(R.id.event_address);
		
		//EditText eventContacts = (EditText) findViewById(R.id.event_name);

		//create an event object with the information
		Event event = new Event(eventName.getText().toString(), eventNotes.getText().toString(), 
				eventTime.getText().toString(), eventDate.getText().toString(), 
				AddressBook.getAddress(eventAddress.getText().toString()), null);
	
		// make a database connection and add address to it
		EventDbHelper db = new EventDbHelper(this);
		
		if(action.equalsIgnoreCase("Create")) {
			db.addEvent(event);
			//db.addEvent(new Address("Hell", null, null));
		} else {
			//needs to be fixed
			db.deleteEvent(event);
			//db.addAddress(new Address("He'll", null, null));
			//db.addAddress(address);
		}
		
		
		Calendar cal = Calendar.getInstance();
		//@SuppressWarnings("deprecation")
		//int secondsDiff = this.date.getSeconds() - Calendar.SECOND;
		cal.add(Calendar.SECOND, 5);
		Intent alarmIntent = new Intent(this, AlarmReceiver.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this,
	            12345, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT);
	        AlarmManager am =
	            (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
	        am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
	                pendingIntent);
		
		Intent intent = new Intent(this, EventManager.class);
		//intent.putExtra("events", events);
		startActivity(intent);
	}
	
}
