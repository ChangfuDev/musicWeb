package com.musicweb.hbobject;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by WJY on 2018/4/6.
 */
@Entity
public class SongList implements Serializable {

    private Song songId;
    private SongListInfo songListId;

    public SongList() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "songId")
    public Song getSongId() {
        return songId;
    }

    public void setSongId(Song songId) {
        this.songId = songId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "songListId")
    public SongListInfo getSongListId() {
        return songListId;
    }

    public void setSongListId(SongListInfo songListId) {
        this.songListId = songListId;
    }
}
