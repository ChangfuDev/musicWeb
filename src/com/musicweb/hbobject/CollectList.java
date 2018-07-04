package com.musicweb.hbobject;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by WJY on 2018/4/7.
 */
@Entity
public class CollectList implements Serializable{

    private User userId;
    private SongListInfo songListId;

    public CollectList() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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
