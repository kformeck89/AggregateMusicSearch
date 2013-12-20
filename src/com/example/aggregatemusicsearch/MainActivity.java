package com.example.aggregatemusicsearch;

import java.util.Collection;
import java.util.concurrent.ExecutionException;

import de.umass.lastfm.Artist;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText searchCriteria = null;
	private LastFMWrapper lastFm = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lastFm = new LastFMWrapper();
		searchCriteria = (EditText)findViewById(R.id.txtCriteria1);
		Button btnSearch = (Button)findViewById(R.id.btnSearch);
		btnSearch.setOnClickListener(searchClickListener);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private OnClickListener searchClickListener = new OnClickListener() {
		@Override
		public void onClick(View view) {
			String searchText = searchCriteria.getText().toString();
			if (searchText != null && !searchText.isEmpty()) {
				SearchTask task = new SearchTask();
				Collection<Artist> results = null;
				try {
					results = task.execute(searchText).get();
				} catch (InterruptedException e) {
					Toast.makeText(
							getApplicationContext(), 
							e.getMessage(), 
							Toast.LENGTH_SHORT)
						    .show();
				} catch (ExecutionException e) {
					Toast.makeText(
							getApplicationContext(), 
							e.getMessage(), 
							Toast.LENGTH_SHORT)
						    .show();
				}
				ActivityDataHandler dataHandler = new ActivityDataHandler();
				Intent intent = new Intent(getApplicationContext(), ResultsActivity.class);
				dataHandler.setArtistCollection(results);
				intent.putExtra(LastFMWrapper.ARTIST, dataHandler);
				startActivity(intent);
			}
		}
	};

	private class SearchTask extends AsyncTask<String, String, Collection<Artist>> {
		Collection<Artist> results;
		@Override
		protected Collection<Artist> doInBackground(String... searchText) {
			results = lastFm.artistSearch(searchText[0]);
			return results;
		}
	}
}
