package com.example.aggregatemusicsearch;

import java.util.Collection;

import android.os.Parcel;
import android.os.Parcelable;

import de.umass.lastfm.Artist;

public class ActivityDataHandler implements Parcelable {
	private Collection<Artist> artistCollection = null;
	
	public ActivityDataHandler() { }
	
	public Collection<Artist> getArtistCollection() {
		return artistCollection;
	}
	public void setArtistCollection(Collection<Artist> artistCollection) {
		this.artistCollection = artistCollection;
	}

	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {	
		dest.writeValue(this.artistCollection);
	}
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		public ActivityDataHandler createFromParcel(Parcel in) {
			return new ActivityDataHandler();
		}
		public ActivityDataHandler[] newArray(int size) {
			return new ActivityDataHandler[size];
		}
	};
}
