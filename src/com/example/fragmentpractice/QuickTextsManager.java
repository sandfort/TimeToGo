package com.example.fragmentpractice;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class QuickTextsManager extends ListActivity {

	protected static final Class<?> QuickTextInfo = null;
	private static ArrayList<QuickText> quickTexts = new ArrayList<QuickText>();
	private static QuickTextsManager quickTextsManager = new QuickTextsManager();
	public static int nextQuickTextID = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quick_texts_manager);
		
		QuickTextsDbHelper db = new QuickTextsDbHelper(this);
		quickTexts = db.getAllQuickTexts();
		db.close();
		
		if (quickTexts == null) {
			String[] textsStrings = new String[] { "No Texts" };
			setListAdapter(new ArrayAdapter<String>(this, R.layout.list_layout,
					textsStrings));
		} else {
			String[] textsStrings = new String[quickTexts.size()];
			for (int i = 0; i < quickTexts.size(); ++i) {
				textsStrings[i] = quickTexts.get(i).getQuickText();
			
			}
			setListAdapter(new ArrayAdapter<String>(this, R.layout.list_layout,
					textsStrings));
			
			ListView quickTextsManager = (ListView) findViewById(android.R.id.list);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
					R.layout.list_layout, textsStrings);
			quickTextsManager.setAdapter(adapter);
			quickTextsManager.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id){
					Intent intent = new Intent(QuickTextsManager.this,
							QuickTextInfo.class);
					intent.putExtra("Action", ((TextView) view).getText()
							.toString());
					startActivity(intent);
				}
			});
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_quick_texts_manager, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int choice = item.getItemId();
		
		if (choice == R.id.create_quick_text_button) {
			Intent intent = new Intent(this, QuickTextInfo.class);
			intent.putExtra("Action","Create");
			startActivity(intent);
		} else if (choice == R.id.clear_quick_texts_database) {
			QuickTextsDbHelper db = new QuickTextsDbHelper(this);
			this.deleteDatabase(db.getName());
			quickTexts = db.getAllQuickTexts();
			this.resetNextQuickTextID();
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		}
		return true;
	}

	public static ArrayList<QuickText> getQuickTexts() {
		return quickTexts;
	}

	public static QuickTextsManager getQuickTextsManager() {
		return quickTextsManager;
	}

	public void removeQuickText(QuickText quickText) {
		quickTexts.remove(quickText);
	}

	public static QuickText getQuickText(String quickTextString) {
		for (int i = 0; i < quickTexts.size(); ++i) {
			if (quickTexts.get(i).getQuickText().equalsIgnoreCase(quickTextString)) {
				return quickTexts.get(i);
			}
		}
		return null;
	}

	public void addQuickText(QuickText quickText) {
		quickTexts.add(quickText);
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		return;
	}

	public static int getNextQuickTextID() {
		return nextQuickTextID++;
	}

	public void resetNextQuickTextID() {
		nextQuickTextID = 1;
	}
}
