package com.example.fragmentpractice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class QuickTextInfo extends Activity {
	
	private String action = null;
	private QuickText quickText = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quick_text_info);
		Intent intent = getIntent();
		action = intent.getStringExtra("Action");
		if(!action.equalsIgnoreCase("Create")) {
			QuickText quickText = QuickTextsManager.getQuickText(action);
			EditText quickTextStringEdit = (EditText) findViewById(R.id.quick_text_string);
			quickTextStringEdit.setText(action);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_quick_text_info, menu);
		return true;
	}
	
	public void createQuickText(View view) {
		EditText quickTextString = (EditText) findViewById(R.id.quick_text_string);

		QuickTextsDbHelper db = new QuickTextsDbHelper(this);

		QuickText quickText = QuickTextsManager.getQuickText(quickTextString.getText()
				.toString());

		if(quickText != null){
			int quickTextID = quickText.getID();
			quickText = new QuickText(quickTextID, quickTextString.getText().toString());
			db.updateQuickText(quickText);
		} else {
			quickText = new QuickText(quickTextString.getText().toString());
			db.addQuickText(quickText);
		}

		db.close();
		Intent intent = new Intent(this, QuickTextsManager.class);
		startActivity(intent);
	}
	
	public void deleteQuickText(View view) {
		QuickTextsManager.getQuickTextsManager().removeQuickText(quickText);
		QuickTextsDbHelper db = new QuickTextsDbHelper(this);
		db.deleteQuickText(quickText);
		Intent intent = new Intent(this, QuickTextsManager.class);
		startActivity(intent);
	}
}
