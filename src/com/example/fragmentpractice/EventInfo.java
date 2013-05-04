package com.example.fragmentpractice;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EventInfo extends Activity {

	// private ArrayList<Event> events = new ArrayList<Event>();
	private String action = null;
	private String eventName = null;
	//private int alarmRequestCode = 0;
	private Event event;

	//ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();
	//ArrayList<AlarmManager> alarmManager = new ArrayList<AlarmManager>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_info);
		Intent intent = getIntent();
		action = intent.getStringExtra("Action");
		if (action == null) {
			action = getIntent().getStringExtra("EventName");
		}
		if (!action.equalsIgnoreCase("Create")) {

			// Event event = EventManager.getEvent(action);
			event = EventManager.getEvent(action);
			eventName = event.getName();
			String eventNotes = event.getNotes();
			String eventTime = event.getTime();
			String eventDate = event.getDate();
			String eventStartAddress = event.getStartAddressName();
			String eventEndAddress = event.getEndAddressName();
			String eventContacts = event.getContactsString();

			EditText eventNameEdit = (EditText) findViewById(R.id.event_name);
			EditText eventNotesEdit = (EditText) findViewById(R.id.event_notes);
			EditText eventTimeEdit = (EditText) findViewById(R.id.event_time);
			EditText eventDateEdit = (EditText) findViewById(R.id.event_date);
			EditText eventStartAddressEdit = (EditText) findViewById(R.id.start_address);
			EditText eventAddressEdit = (EditText) findViewById(R.id.event_address);
			EditText eventContactsEdit = (EditText) findViewById(R.id.event_contacts);

			eventNameEdit.setText(action);
			eventNotesEdit.setText(eventNotes);
			eventTimeEdit.setText(eventTime);
			eventDateEdit.setText(eventDate);
			eventStartAddressEdit.setText(eventStartAddress);
			eventAddressEdit.setText(eventEndAddress);
			eventContactsEdit.setText(eventContacts);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_event_info, menu);
		return true;
	}
	
	public void selectStartAddress(View view) {
		Intent intent = new Intent(this, AddressBook.class);
		if (eventName == null) {
			// get data from text boxes
			EditText eventName = (EditText) findViewById(R.id.event_name);
			EditText eventNotes = (EditText) findViewById(R.id.event_notes);
			EditText eventTime = (EditText) findViewById(R.id.event_time);
			EditText eventDate = (EditText) findViewById(R.id.event_date);			
			EditText eventStartAddress = (EditText) findViewById(R.id.start_address);
			EditText eventAddress = (EditText) findViewById(R.id.event_address);
			EditText eventContacts = (EditText) findViewById(R.id.event_contacts);

			// Toast.makeText(getBaseContext(),
			// Boolean.toString(eventContacts.getText().toString().equalsIgnoreCase("")),
			// Toast.LENGTH_LONG).show();
			// try {
			// Thread.sleep(1000);
			// } catch (Exception e) {

			// }

			ArrayList<Contact> contacts = null;
			if (!eventContacts.getText().toString().equalsIgnoreCase("")
					&& !eventContacts.getText().toString().equalsIgnoreCase("none")) {
				contacts = new ArrayList<Contact>();
				String[] contactStrings = eventContacts.getText().toString()
						.split(";");
				for (int i = 0; i < contactStrings.length; ++i) {
					contacts.add(ContactList.getContact(contactStrings[i]));
				}
			}
			// if(eventContacts != null) {
			// contacts = new ArrayList<Contact>();
			// String[] contactStrings =
			// eventContacts.getText().toString().split(";");
			// Toast.makeText(getBaseContext(),
			// Integer.toString(contactStrings.length),
			// Toast.LENGTH_LONG).show();
			// try {
			// Thread.sleep(1000);
			// } catch (Exception e) {
			//
			// }
			//
			// for(int i = 0; i < contactStrings.length; ++i) {
			// contacts.add(ContactList.getContact(contactStrings[i]));
			// }
			// }

			// make a database connection
			//***********************************************
			EventDbHelper db = new EventDbHelper(this);
			Event event = new Event(eventName.getText().toString(), eventNotes
					.getText().toString(), eventTime.getText().toString(),
					eventDate.getText().toString(),
					AddressBook.getAddress(eventStartAddress.getText().toString()),
					AddressBook.getAddress(eventAddress.getText().toString()),
					contacts);
			Toast.makeText(getBaseContext(), Integer.toString(event.getID()),
					Toast.LENGTH_LONG).show();
			try {
				Thread.sleep(100);
			} catch (Exception e) {

			}
			db.addEvent(event);
			db.close();
			this.eventName = event.getName();
			EventManager.getEvents().add(event);

			// Intent intentMan = new Intent(this, EventManager.class);
			// intentMan.putExtra("event", event.getName());
			// startActivity(intentMan);

		}
		intent.putExtra("EditOrSelect", "select");
		intent.putExtra("StartOrEnd", "Start");
		intent.putExtra("EventName", eventName);
		startActivity(intent);
	}

	public void selectEventAddress(View view) {
		Intent intent = new Intent(this, AddressBook.class);
		if (eventName == null) {
			// get data from text boxes
			EditText eventName = (EditText) findViewById(R.id.event_name);
			EditText eventNotes = (EditText) findViewById(R.id.event_notes);
			EditText eventTime = (EditText) findViewById(R.id.event_time);
			EditText eventDate = (EditText) findViewById(R.id.event_date);			
			EditText eventStartAddress = (EditText) findViewById(R.id.start_address);
			EditText eventAddress = (EditText) findViewById(R.id.event_address);
			EditText eventContacts = (EditText) findViewById(R.id.event_contacts);

			// Toast.makeText(getBaseContext(),
			// Boolean.toString(eventContacts.getText().toString().equalsIgnoreCase("")),
			// Toast.LENGTH_LONG).show();
			// try {
			// Thread.sleep(1000);
			// } catch (Exception e) {

			// }

			ArrayList<Contact> contacts = null;
			if (!eventContacts.getText().toString().equalsIgnoreCase("")
					&& !eventContacts.getText().toString().equalsIgnoreCase("none")) {
				contacts = new ArrayList<Contact>();
				String[] contactStrings = eventContacts.getText().toString()
						.split(";");
				for (int i = 0; i < contactStrings.length; ++i) {
					contacts.add(ContactList.getContact(contactStrings[i]));
				}
			}
			// if(eventContacts != null) {
			// contacts = new ArrayList<Contact>();
			// String[] contactStrings =
			// eventContacts.getText().toString().split(";");
			// Toast.makeText(getBaseContext(),
			// Integer.toString(contactStrings.length),
			// Toast.LENGTH_LONG).show();
			// try {
			// Thread.sleep(1000);
			// } catch (Exception e) {
			//
			// }
			//
			// for(int i = 0; i < contactStrings.length; ++i) {
			// contacts.add(ContactList.getContact(contactStrings[i]));
			// }
			// }

			// make a database connection
			//***********************************************
			EventDbHelper db = new EventDbHelper(this);
			Event event = new Event(eventName.getText().toString(), eventNotes
					.getText().toString(), eventTime.getText().toString(),
					eventDate.getText().toString(),
					AddressBook.getAddress(eventStartAddress.getText().toString()),
					AddressBook.getAddress(eventAddress.getText().toString()),
					contacts);
			Toast.makeText(getBaseContext(), Integer.toString(event.getID()),
					Toast.LENGTH_LONG).show();
			try {
				Thread.sleep(100);
			} catch (Exception e) {

			}
			db.addEvent(event);
			db.close();
			this.eventName = event.getName();
			EventManager.getEvents().add(event);

			// Intent intentMan = new Intent(this, EventManager.class);
			// intentMan.putExtra("event", event.getName());
			// startActivity(intentMan);

		}
		intent.putExtra("EditOrSelect", "select");
		intent.putExtra("StartOrEnd", "End");
		intent.putExtra("EventName", eventName);
		startActivity(intent);
	}

	public void selectContacts(View view) {
		Intent intent = new Intent(this, ContactList.class);
		intent.putExtra("EditOrSelect", "select");
		intent.putExtra("EventName", eventName);
		startActivity(intent);
	}

	public void createEvent(View view) {
		// get data from text boxes
		EditText eventName = (EditText) findViewById(R.id.event_name);
		EditText eventNotes = (EditText) findViewById(R.id.event_notes);
		EditText eventTime = (EditText) findViewById(R.id.event_time);
		EditText eventDate = (EditText) findViewById(R.id.event_date);			
		EditText eventStartAddress = (EditText) findViewById(R.id.start_address);
		EditText eventAddress = (EditText) findViewById(R.id.event_address);
		EditText eventContacts = (EditText) findViewById(R.id.event_contacts);

		ArrayList<Contact> contacts = null;

		if (!eventContacts.getText().toString().equalsIgnoreCase("")
				&& !eventContacts.getText().toString().equalsIgnoreCase("none")) {
			contacts = new ArrayList<Contact>();
			String[] contactStrings = eventContacts.getText().toString()
					.split("; ");

			for (int i = 0; i < contactStrings.length; ++i) {
				contacts.add(ContactList.getContact(contactStrings[i]));
				Toast.makeText(getBaseContext(), "Contact Name (1): " + contacts.get(i).getName(),
						Toast.LENGTH_LONG).show();
				try {
					Thread.sleep(100);
				} catch (Exception e) {

				}
			}
		}

		// make a database connection
		EventDbHelper db = new EventDbHelper(this);

		// create an event object with the information
		Event event = EventManager.getEvent(eventName.getText().toString());

		// Toast.makeText(getBaseContext(), "BEFORE", Toast.LENGTH_LONG).show();

		// Toast.makeText(getBaseContext(), Boolean.toString(contacts == null),
		// Toast.LENGTH_LONG).show();

		// Toast.makeText(getBaseContext(), "CONTACTS: " +
		// event.getContactsString(), Toast.LENGTH_LONG).show();

		try {
			Thread.sleep(1000);
		} catch (Exception e) {

		}

		if (event != null) {
			// Toast.makeText(getBaseContext(), event.getContactsString(),
			// Toast.LENGTH_LONG).show();
			// try {
			// Thread.sleep(100);
			// } catch (Exception e) {
			//
			// }
			int eventID = event.getID();
			event = new Event(eventID, eventName.getText().toString(),
					eventNotes.getText().toString(),
					eventTime.getText().toString(),
					eventDate.getText().toString(),
					AddressBook.getAddress(eventStartAddress.getText().toString()),
					AddressBook.getAddress(eventAddress.getText().toString()), contacts);
			// event = new Event(eventID, eventName.getText().toString(),
			// eventNotes.getText().toString(),
			// eventTime.getText().toString(), eventDate.getText().toString(),
			// AddressBook.getAddress(eventAddress.getText().toString()), null);
		} else {

			event = new Event(eventName.getText().toString(),
					eventNotes.getText().toString(),
					eventTime.getText().toString(),
					eventDate.getText().toString(),
					AddressBook.getAddress(eventStartAddress.getText().toString()),
					AddressBook.getAddress(eventAddress.getText().toString()),
					contacts);
			// event = new Event(eventName.getText().toString(),
			// eventNotes.getText().toString(),
			// eventTime.getText().toString(), eventDate.getText().toString(),
			// AddressBook.getAddress(eventAddress.getText().toString()), null);
		}

		// add address to database
		if (action.equalsIgnoreCase("Create")) {
			db.addEvent(event);
			// Toast.makeText(getBaseContext(), "Create",
			// Toast.LENGTH_LONG).show();
			// db.addEvent(new Address("Hell", null, null));
		} else {
			// needs to be fixed
			// Toast.makeText(getBaseContext(), Integer.toString(event.getID())
			// , Toast.LENGTH_LONG).show();
			Toast.makeText(getBaseContext(), "Contact String (1): " + event.getContactsString(),
					Toast.LENGTH_LONG).show();
			try {
				Thread.sleep(100);
			} catch (Exception e) {

			}
			 //db.deleteEvent(event);
			 //db.addEvent(event);

			//int dbReturn = db.updateEvent(event);
			
			EventManager.addEvent(event);
			
			String dbReturn = db.updateEvent(event.getName());
			
			//Event eventT = db.getEvent(event.getID());
			Event eventT = EventManager.getEvent(event.getName());
			Toast.makeText(getBaseContext(), "Contact String (2): " + eventT.getContactsString() + " " + dbReturn,
					Toast.LENGTH_LONG).show();
			
			// Toast.makeText(getBaseContext(), "UpDate",
			// Toast.LENGTH_LONG).show();
			// db.addAddress(new Address("He'll", null, null));
			// db.addAddress(address);
		}

		//Toast.makeText(getBaseContext(),
		//		db.getEvent(event.getID()).getContactsString(),
		//		Toast.LENGTH_LONG).show();
		try {
			Thread.sleep(100);
		} catch (Exception e) {

		}

		db.close();

		//Calendar cal = Calendar.getInstance();
		String[] monthInfo = event.getDate().split("/");
		String[] timeInfo = event.getTime().split(":");
		int month = Integer.parseInt(monthInfo[0]);
		int day = Integer.parseInt(monthInfo[1]);
		int year = Integer.parseInt(monthInfo[2]);
		int hour = Integer.parseInt(timeInfo[0]);
		if (timeInfo[1].substring(timeInfo.length - 2) != null && timeInfo[1].substring(timeInfo.length - 2).equalsIgnoreCase("pm")) {
			hour += 12;
		}
		int minute = Integer.parseInt(timeInfo[1].substring(0,
				timeInfo[1].length() - 2));

		@SuppressWarnings("deprecation")
		// int secondsDiff = this.date.getSeconds() - Calendar.SECOND;
		int secondsDiff = new Date(year, month, day, hour, minute).getMinutes()
				- new Date().getMinutes();

		Toast.makeText(getBaseContext(), "Seconds Diff (1): " + Integer.toString(secondsDiff),
				Toast.LENGTH_LONG).show();
		try {
			Thread.sleep(1000);
		} catch (Exception e) {

		}

		Intent intent = new Intent(this, EventManager.class);
		// intent.putExtra("events", events);
		startActivity(intent);
	}

	public void startTrip(View view) {
		// get data from text boxes
		EditText eventName = (EditText) findViewById(R.id.event_name);
		EditText eventNotes = (EditText) findViewById(R.id.event_notes);
		EditText eventTime = (EditText) findViewById(R.id.event_time);
		EditText eventDate = (EditText) findViewById(R.id.event_date);		
		EditText eventStartAddress = (EditText) findViewById(R.id.start_address);
		EditText eventAddress = (EditText) findViewById(R.id.event_address);
		//EditText eventContacts = (EditText) findViewById(R.id.event_contacts);

		Event event = new Event(eventName.getText().toString(), eventNotes
				.getText().toString(), eventTime.getText().toString(),
				eventDate.getText().toString(),
				AddressBook.getAddress(eventStartAddress.getText().toString()),
				AddressBook.getAddress(eventAddress.getText().toString()), null);

		// make a database connection and add address to it
		EventDbHelper db = new EventDbHelper(this);

		if (action.equalsIgnoreCase("Create")) {
			db.addEvent(event);
			// db.addEvent(new Address("Hell", null, null));
		} else {
			// needs to be fixed
			
			//db.deleteEvent(event); ******
			
			// db.addAddress(new Address("He'll", null, null));
			// db.addAddress(address);
		}

		Intent intent = new Intent(this, EnRoute.class);
		intent.putExtra("Event Time", eventTime.getText().toString());
		intent.putExtra("Event Name", eventName.getText().toString());
		startActivity(intent);

	}

	public void calculateNow(View view) {
		EditText eventStartAddress = (EditText) findViewById(R.id.start_address);
		EditText eventAddress = (EditText) findViewById(R.id.event_address);
		EditText eventTravelTime = (EditText) findViewById(R.id.travel_time_guess);
		
		// Get a Calendar instance.
		Calendar cal = Calendar.getInstance();
		
		// Parse the fields for the date and time from the Event.
		String[] monthInfo = event.getDate().split("/");
		String[] timeInfo = event.getTime().split(":");
		int month = Integer.parseInt(monthInfo[0]);
		int day = Integer.parseInt(monthInfo[1]);
		int year = Integer.parseInt(monthInfo[2]);
		int hour = Integer.parseInt(timeInfo[0]);
		if (timeInfo[1].substring(timeInfo.length - 2) != null && timeInfo[1].substring(timeInfo.length - 2).equalsIgnoreCase("pm"))
			hour += 12;
		int minute = Integer.parseInt(timeInfo[1].substring(0, timeInfo[1].length() - 2));
	
		// Set the fields in the Calendar.
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		
		// Get the Calendar time in the form of milliseconds since Epoch.
		long tTime = cal.getTimeInMillis();
		
		// If the user has NOT explicitly entered a guess for the travel time of this event...
		if(eventTravelTime.getText().toString().equalsIgnoreCase("")) {
			
			/* USE DATABASE TO GET PREVIOUS TRAVEL TIMES */
			TravelTimeDbHelper db = new TravelTimeDbHelper(this);
			ArrayList<TravelTime> travelTimes = db.getAllTravelTime(eventStartAddress.getText().toString(), eventAddress.getText().toString());
			float totalTime = 0;
			if (travelTimes.size() != 0) {
				for (int i = 0; i < travelTimes.size(); ++i) {
					totalTime = totalTime + travelTimes.get(i).getTravelTime();
				}
				totalTime = totalTime / travelTimes.size();
			}
			Toast.makeText(getBaseContext(), Float.toString(totalTime),
					Toast.LENGTH_LONG).show();
			
			/* SET ALARM BASED ON THOSE */
			
			// Roll back the Calendar time by the travel time.
			// NOTE: assuming totalTime is in seconds, therefore converting to milliseconds.
			tTime -= (int) totalTime*1000;
			
		} else { // If the user HAS explicitly entered a travel time for this event...
			/* SET ALARM BASED ON THIS TRAVEL TIME */
			
			// NOTE: Assuming time is given in minutes.
			tTime -= 1000*60*Integer.parseInt(eventTravelTime.getText().toString());
		}
		
		// Set up a pending intent to give the AlarmManager.
		Intent alarmIntent = new Intent(this, AlarmReceiver.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, event.getID(), alarmIntent, 0);
		
		// Schedule the alarm.
		AlarmManager am = (AlarmManager) getSystemService(Activity.ALARM_SERVICE);
		am.set(AlarmManager.RTC_WAKEUP, tTime, pendingIntent);
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(this, EventManager.class);
		startActivity(intent);
		return;
	}

}
