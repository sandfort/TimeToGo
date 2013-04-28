package com.example.fragmentpractice;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.DigitalClock;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class EnRoute extends Activity {

	private Date startDate = null;
	private boolean timing = false;
	private String eventName = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_en_route);
		timing = false;
		Intent intent = getIntent();
		String message = intent.getStringExtra("Event Time");
		eventName = intent.getStringExtra("Event Name");

		DigitalClock clock = (DigitalClock) findViewById(R.id.digitalClock1);
		clock.setTextSize(50);

		TextView eventTime = (TextView) findViewById(R.id.en_route_event_time);
		eventTime.setTextSize(50);
		eventTime.setText(message);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.en_route, menu);
		return true;
	}

	public void timeMe(View view) {
		startDate = new Date();
		timing = true;
		Toast.makeText(getBaseContext(), "Timing Started",
				Toast.LENGTH_LONG).show();
	}

	public void iAmHere(View view) {
		if (timing) {
			Toast.makeText(getBaseContext(), "Timing Finished",
					Toast.LENGTH_LONG).show();
			timing = false;
			Date endDate = new Date();
			Long totalTime = endDate.getTime() - startDate.getTime();
			Event event = EventManager.getEvent(eventName);
			
			TravelTime travelTime = new TravelTime(event.getStartAddress(), event.getEndAddress(), totalTime);
			
			//TextView eventTime = (TextView) findViewById(R.id.en_route_event_time);
			//eventTime.setTextSize(50);
			//eventTime.setText(totalTime.toString());
			TravelTimeDbHelper db = new TravelTimeDbHelper(this);
			db.addTravelTime(travelTime);
			EventDbHelper dbEvent = new EventDbHelper(this);
			dbEvent.deleteEvent(event);
		} else {
			Toast.makeText(getBaseContext(), "Timing Never Started",
					Toast.LENGTH_LONG).show();
		}
	}

	public void iWillBeLate(View view) {

	}

}
