package com.example.fragmentpractice;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class UserPrefs extends Activity {

	private static int alarmVal = 0;
	private String qtTitle = "Manage Quick Texts";
	private String aTitle = "Set Time Before Leave";
	private ArrayList<String> prefOptions = new ArrayList<String>();
	private AdapterView.OnItemClickListener clickListener;
	private ListView listview;
	private ArrayAdapter<String> adapter;
	final CharSequence[] items = { "0 mins", "5 mins", "10 mins", "15 mins" };

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_prefs);
		prefOptions.add(qtTitle);
		prefOptions.add(aTitle);
		listview = (ListView) findViewById(android.R.id.list);
		adapter = new ArrayAdapter<String>(this, R.layout.list_layout,
				prefOptions);
		listview.setAdapter(adapter);
		clickListener = new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String item = (String) parent.getItemAtPosition(position);
				if (item.equals(qtTitle)) {
					startQuickTextsManager();
				} else if (item.equals(aTitle)) {
					onCreateDialog();
				}
			}
		};
		listview.setOnItemClickListener(clickListener);
	}

	private Dialog onCreateDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setCancelable(true);
		builder.setTitle("Alarm Val");
		builder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// The 'which' argument contains the index position
				// of the selected item
				if (which == 0) {
					alarmVal = 0;
				} else if (which == 1) {
					alarmVal = 5;
				} else if (which == 2) {
					alarmVal = 10;
				} else if (which == 3) {
					alarmVal = 15;
				}
				Toast.makeText(getApplicationContext(),
						Integer.toString(alarmVal) + " mins",
						Toast.LENGTH_SHORT).show();
			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
		return null;
	}

	private void startQuickTextsManager() {
		Intent intent = new Intent(this, QuickTextsManager.class);
		startActivity(intent);
	}

	public static int getAlarmVal() {
		return alarmVal;
	}
}