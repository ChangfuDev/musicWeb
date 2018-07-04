package com.musicweb.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musicweb.hbobject.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by WJY on 2018/4/18.
 */
@Controller
public class DeleteController extends MusicWebController{
    @GetMapping("/DeleteSL")
    public  String DeleteSL(int songListId, HttpSession httpSession)
    {
        String hql;
        Query query;
        if(songListId!=0)
        {
            Session session=getSession();
            Transaction transaction =session.beginTransaction();
            hql="delete from SongListInfo where songListId="+songListId+"";
            query=session.createQuery(hql);
            query.executeUpdate();
            transaction.commit();
            session.close();
            User user=(User)httpSession.getAttribute("user");
            return "redirect:/UserHome?strUserId="+user.getUserId()+"#CreateList-show";
        }
        else
        {
            System.out.println("songListId is null");
            return "songListId is null";
        }

    }



    @GetMapping("/DLCollection")
    public String dLCollection(int songListId, HttpSession httpSession)
    {
        String hql;
        Query query;
        if(songListId!=0)
        {
            Session session=getSession();
            Transaction transaction =session.beginTransaction();
            hql="delete from CollectList where songListId="+songListId+"";
            query=session.createQuery(hql);
            query.executeUpdate();
            transaction.commit();
            session.close();
            User user=(User)httpSession.getAttribute("user");
            return "redirect:/UserHome?strUserId="+user.getUserId()+"#Conllection-show";
        }
        else
        {
            System.out.println("songListId is null");
            return "songListId is null";
        }

    }


    @PostMapping("/htmlDeleteSL")
    public @ResponseBody String htmlDeleteSL(int songListId)
    {
        String hql;
        Query query;
        if(songListId!=0)
        {
            Session session=getSession();
            Transaction transaction =session.beginTransaction();
            /*String hql="from SongList where songListId="+songListId+"";
            Query query=session.createQuery(hql);
            List<SongList> songList=query.list();
            if(songList.size()!=0)
            {
                for (SongList s:songList
                        ) {
                    session.delete(s);
                }
            }

            transaction.commit();*/

           // SongListInfo songListInfo=session.get(SongListInfo.class,songListId);
            hql="delete from SongListInfo where songListId="+songListId+"";
            query=session.createQuery(hql);
            query.executeUpdate();
            //session.delete(songListInfo);
            transaction.commit();
            session.close();
            return "删除成功!";
        }
        else
        {
            System.out.println("songListId is null");
            return "songListId is null";
        }

    }

    @GetMapping("/DeleteSLSongs")
    public String DeleteSLSongs(int songListId,int songId)
    {
        Session session=getSession();
        Transaction transaction=session.beginTransaction();
        String hql="Delete from SongList where songListId="+songListId+" and songId="+songId;
        Query query=session.createQuery(hql);
        query.executeUpdate();
        transaction.commit();
        session.close();
        return "redirect:/MusicList?songListId="+songListId+"#songs-show";
    }


    @PostMapping("/DeleteSongs")
    public @ResponseBody  Map deleteSongs(@RequestBody String dlSongs) throws IOException {
        Map stateMap=new HashMap();
        ObjectMapper objectMapper=new ObjectMapper();
        Integer[] dlSongsId =objectMapper.readValue(dlSongs,Integer[].class);
        Session session=getSession();
        Transaction transaction=session.beginTransaction();
        for (Integer songId:dlSongsId)
        {
            Song song=session.get(Song.class,songId);
            session.delete(song);
            File file=new File("E:\\CloudMusic\\"+song.getArtist().trim()+" - "+song.getTitle().trim()+".mp3");
            //System.out.println(file.getAbsolutePath());
            if(file.exists())
                file.delete();
            else
                System.out.println(file.getAbsolutePath()+"文件不存在！");
        }

        transaction.commit();
        session.close();
        stateMap.put("state",true);
        stateMap.put("Message","删除成功!");
        return stateMap;

    }

    @PostMapping("/DeleteUser")
    public @ResponseBody Map deleteUser(int userId)
    {
        Map messageMap=new HashMap();
        Session session=getSession();
        Transaction transaction=session.beginTransaction();
        User user=session.get(User.class,userId);
        session.delete(user);
        transaction.commit();
        session.close();
        messageMap.put("state",true);
        messageMap.put("message","删除成功！");
        return messageMap;
    }

    @PostMapping("/DeleteSinger")
    public @ResponseBody Map deleteSinger(int singerId)
    {
        Map messageMap=new HashMap();
        Session session=getSession();
        Transaction transaction=session.beginTransaction();
        Singer singer=session.get(Singer.class,singerId);
        session.delete(singer);
        transaction.commit();
        session.close();
        messageMap.put("state",true);
        messageMap.put("message","删除成功！");
        return messageMap;
    }
}
