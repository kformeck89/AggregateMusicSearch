package com.example.aggregatemusicsearch;

import java.util.Collection;

import de.umass.lastfm.Artist;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class ResultsActivity extends ListActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.results_view);
		Collection<Artist> artistCollection = (Collection<Artist>)
				getIntent().getExtras().getSerializable(LastFMWrapper.ARTIST);
		for (Artist artist : artistCollection) {
			Toast.makeText(getApplicationContext(), artist.toString(), Toast.LENGTH_SHORT).show();
		}
	}
	@Override
	public void onListItemClick(ListView parent, View view, int position, long id) {
		
	}
}
