package com.example.fragmentpractice;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;

@SuppressLint("NewApi")
public class ETACalc extends Activity {

	private MapView vMap;
	private GoogleMap map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eta_calc);
		
		vMap = (MapView) findViewById(R.id.map);
		map = vMap.getMap();

		/* map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		map.setMapType(GoogleMap.MAP_TYPE_NORMAL); */
		vMap.onCreate(savedInstanceState);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		vMap.onResume();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		vMap.onPause();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		vMap.onDestroy();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle bundle) {
		super.onSaveInstanceState(bundle);
		vMap.onSaveInstanceState(bundle);
	}
	
	@Override
	public void onLowMemory() {
		super.onLowMemory();
		vMap.onLowMemory();
	}

	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.etacalc, menu);
		return true;
	}
	*/

}
