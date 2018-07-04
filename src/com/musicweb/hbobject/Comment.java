package com.musicweb.hbobject;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by WJY on 2018/4/7.
 */
@Entity
public class Comment {

    private int   commentId;

    private User  userId;

    private String content;

    private Date commentTime;

    private int replyCommentId;

    private Song songId;

    private SongListInfo songListId;

    private int likeCount;

    private Comment replyComment;



    public Comment() {
    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Column(length = 300)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column
    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    @Column
    public int getReplyCommentId() {
        return replyCommentId;
    }

    public void setReplyCommentId(int replyCommentId) {
        this.replyCommentId = replyCommentId;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "songId")
    public Song getSongId() {
        return songId;
    }

    public void setSongId(Song songId) {
        this.songId = songId;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "songListId")
    public SongListInfo getSongListId() {
        return songListId;
    }

    public void setSongListId(SongListInfo songListId) {
        this.songListId = songListId;
    }

    @Column
    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }




    public void setReplyComment(Comment replyComment) {
        this.replyComment = replyComment;
    }

    public String formatTime()
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        String formatTime= df.format(commentTime);
        return formatTime;
    }

}
