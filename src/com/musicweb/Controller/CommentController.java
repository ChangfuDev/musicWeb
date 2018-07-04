package com.musicweb.Controller;

import com.musicweb.hbobject.Comment;
import com.musicweb.hbobject.Song;
import com.musicweb.hbobject.SongListInfo;
import com.musicweb.hbobject.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by WJY on 2018/5/2.
 */
@Controller
public class CommentController extends MusicWebController{
    @PostMapping("/Comment")
    public String
    comment(Integer songId,
            Integer songListId,
            Integer replyCommentId,
            String commentContent,
            HttpSession httpSession){
        User user =(User)httpSession.getAttribute("user");
        if(user!=null)
        {
            Session session=getSession();
            Transaction transaction=session.beginTransaction();
            Comment comment=new Comment();

            comment.setUserId(user);
            comment.setCommentTime(new Date());
            comment.setContent(commentContent);
            if(replyCommentId!=null)
            {
                comment.setReplyCommentId(replyCommentId);
            }
            if(songId!=null)
            {
                Song song=session.load(Song.class,songId);
                comment.setSongId(song);
            }
            if(songListId!=null)
            {
                SongListInfo songListInfo=session.load(SongListInfo.class,songListId);
                comment.setSongListId(songListInfo);
            }
            session.save(comment);
            transaction.commit();
            session.close();
            return "redirect:/MusicList?songListId="+songListId+"#Comment-title";
        }
        else
        {
            return "redirect:/Login";
        }

    }
}
