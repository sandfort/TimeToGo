package com.example.fragmentpractice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		return true;

	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	public void showEventManager(View view) {
		Intent intent = new Intent(this, EventManager.class);
		startActivity(intent);
	}

	public void showUserPrefs(View view) {
		Intent intent = new Intent(this, UserPrefs.class);
		startActivity(intent);
	}

	public void showAddressBook(View view) {
		Intent intent = new Intent(this, AddressBook.class);
		intent.putExtra("EditOrSelect", "edit");
		startActivity(intent);
	}

	public void showContactList(View view) {
		Intent intent = new Intent(this, ContactList.class);
		intent.putExtra("EditOrSelect", "edit");
		startActivity(intent);
	}

	@Override
	public void onBackPressed() {

		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		startActivity(intent);
		return;
	}

}
