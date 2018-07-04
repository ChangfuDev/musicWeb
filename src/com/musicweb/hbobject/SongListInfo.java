package com.musicweb.hbobject;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by WJY on 2018/4/7.
 */
@Entity
public class SongListInfo {

    private int songListId;
    private String Name;
    private Date createTime;
    private User createUserId;
    private String introdution;
    private int playCount;
    private int collectCount;
    private String pic;
    private String tab;

    public SongListInfo() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getSongListId() {
        return songListId;
    }

    public void setSongListId(int songListId) {
        this.songListId = songListId;
    }

    @Column
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Column
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="createUserId")
    public User getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(User createUserId) {
        this.createUserId = createUserId;
    }

    @Column

    public String getIntrodution() {
        return introdution;
    }

    public void setIntrodution(String introdution) {
        this.introdution = introdution;
    }

    @Column
    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    @Column
    public int getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(int collectCount) {
        this.collectCount = collectCount;
    }

    @Column
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Column
    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public String formatTime()
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
        String formatTime= df.format(createTime);
        return formatTime;
    }
}
