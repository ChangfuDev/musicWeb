package com.musicweb.hbobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Song {

	private int songId;
	private String artist;
	private String title;
	private long duration;
	private String album;
	private int playCount;


	public Song() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getSongId() {
		return songId;
	}

	public void setSongId(int songId) {
		this.songId = songId;
	}

	@Column
	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	@Column
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column
	public long getDuration() {
		return duration;
	}


	public void setDuration(long duration) {
		this.duration = duration;
	}

	@Column
	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	@Column
	public int getPlayCount() {
		return playCount;
	}

	public void setPlayCount(int playCount) {
		this.playCount = playCount;
	}

	public String durationConver()
	{
		String minute;
		String second;
		minute=(duration/60>9?duration/60+"":"0"+duration/60);
		second=(duration%60>9?duration%60+"":"0"+duration%60);
		return minute+":"+second;

	}

}
