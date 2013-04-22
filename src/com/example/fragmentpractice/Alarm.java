package com.example.fragmentpractice;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class Alarm extends Activity {

	Date date;
	Context sendingActivity;

	public Alarm(Date date, Context sendingActivity) {
		this.date = date;
		this.sendingActivity = sendingActivity;
		// Calendar cal = Calendar.getInstance();
		// //@SuppressWarnings("deprecation")
		// //int secondsDiff = date.getSeconds() - Calendar.SECOND;
		// cal.add(Calendar.SECOND, 5);
		//
		// Intent intent = new Intent(this, AlarmReceiver.class);
		// PendingIntent pendingIntent = PendingIntent.getActivity(this,
		// 12345, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		// AlarmManager am =
		// (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
		// am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
		// pendingIntent);

	}

	public void set() {
		Calendar cal = Calendar.getInstance();
		// @SuppressWarnings("deprecation")
		// int secondsDiff = this.date.getSeconds() - Calendar.SECOND;
		cal.add(Calendar.SECOND, 5);
		Intent intent = new Intent(sendingActivity, AlarmReceiver.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 12345,
				intent, PendingIntent.FLAG_CANCEL_CURRENT);
		AlarmManager am = (AlarmManager) getSystemService(Activity.ALARM_SERVICE);
		am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
	}

}
