package com.example.fragmentpractice;
 
import java.util.ArrayList;
 
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
 
public class QuickTextsManager extends ListActivity{
	
	private ArrayList<String> texts = new ArrayList<String>();
	private ListView listview;
	//private ArrayAdapter<String> adapter;
	private AdapterView.OnItemClickListener clicklistener;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quick_texts_manager);
		texts.add("Sample Text");
		if (texts == null) {
			String[] textsStrings = new String[] { "No Texts"};
			setListAdapter(new ArrayAdapter<String>(this, R.layout.list_layout,	textsStrings));
		} else {
			String[] textsStrings = new String[texts.size()];
			for (int i = 0; i < texts.size(); ++i) {
				textsStrings[i] = texts.get(i);
			}
			setListAdapter(new ArrayAdapter<String>(this, R.layout.list_layout,	textsStrings));
			listview = (ListView) findViewById(android.R.id.list);
			//adapter = new ArrayAdapter<String>(this, R.layout.list_layout,	textsStrings);
			clicklistener = new AdapterView.OnItemClickListener() {
				
				public void onItemClick(AdapterView<?>parent, View view, int position, long id){
					String item = (String) parent.getItemAtPosition(position);
					int index = -1;
					for(int i = 0; i < texts.size(); i++){
						if(item.equals(texts.get(i))){
							index = i;
							break;
						}
					}
					texts.remove(index);
					onCreateDialog();
				}
			};
			listview.setOnItemClickListener(clicklistener);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_quick_texts_manager, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		onCreateDialog();
    	return true;
	}
	
	private Dialog onCreateDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setCancelable(true);
		builder.setTitle("New Quick Text");
	    final EditText input = new EditText(this);
	    builder.setView(input);
	    builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
            	Editable newText = input.getText();
            	texts.add(newText.toString());
            	Toast.makeText(getApplicationContext(), newText, Toast.LENGTH_SHORT).show();
            }
        });
	    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
            	 
            }
        });
		AlertDialog dialog = builder.create();
		dialog.show();
		return null;
	}
}