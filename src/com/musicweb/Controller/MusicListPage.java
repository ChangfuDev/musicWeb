package com.musicweb.Controller;

import com.musicweb.hbobject.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WJY on 2018/4/21.
 */
@Controller
public class MusicListPage extends MusicWebController {
    @GetMapping("/MusicList")
    public String MusicList(int songListId, Model model, HttpSession httpSession)
    {

        Session session=getSession();
        SongListInfo songListInfo=session.get(SongListInfo.class,songListId);
        String hql="From SongList where songListId="+songListId;
        Query query=session.createQuery(hql);
        List<SongList> songs=query.list();

        hql="FROM Comment where songListId="+songListId+"order by commentTime desc ";
        query=session.createQuery(hql);
        List<Comment> commentList=query.list();
        List<CommentAndReply> commentAndReplyList=new ArrayList<>();
        int replyCommentId;
        //commentList.
        for(Comment comment:commentList)
        {
            CommentAndReply commentAndReply=new CommentAndReply();
            commentAndReply.setComment(comment);
            replyCommentId=comment.getReplyCommentId();
            if(replyCommentId>0)
            {
                Comment replyComment=session.get(Comment.class,replyCommentId);
                commentAndReply.setReplyComment(replyComment);

            }
            commentAndReplyList.add(commentAndReply);
        }


        model.addAttribute("songList",songListInfo);
        model.addAttribute("songs",songs);
        model.addAttribute("commentAndReplyList", commentAndReplyList);
        User user=(User) httpSession.getAttribute("user");

        if(user!=null)
        {
            if(user.getName().trim().equals(songListInfo.getCreateUserId().getName().trim()))
            model.addAttribute("Isedit",true);
            else
                model.addAttribute("Isedit",false);
        }
        else
        {
            model.addAttribute("Isedit",false);

        }


        return "MusicList";


    }

}
