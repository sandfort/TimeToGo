package com.example.fragmentpractice;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.DigitalClock;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class EnRoute extends Activity {

	private Date startDate = null;
	private boolean timing = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_en_route);
		timing = false;
		Intent intent = getIntent();
		String message = intent.getStringExtra("Event Time");
		
		
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
	}
	
	public void iAmHere(View view) {
		if(timing) {
			timing = false;
			Date endDate = new Date();
			Long totalTime = endDate.getTime() - startDate.getTime();
			
			TextView eventTime = (TextView) findViewById(R.id.en_route_event_time);
			eventTime.setTextSize(50);
			eventTime.setText(totalTime.toString());
		} else {
			
		}
	}
	
}
