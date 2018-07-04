package com.musicweb.hbobject;

/**
 * Created by WJY on 2018/5/2.
 */
public class CommentAndReply {
    private Comment comment;
    private Comment replyComment;

    public CommentAndReply() {
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Comment getReplyComment() {
        return replyComment;
    }

    public void setReplyComment(Comment replyComment) {
        this.replyComment = replyComment;
    }
}
