package com.musicweb.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musicweb.hbobject.Song;
import com.musicweb.hbobject.SongList;
import com.musicweb.hbobject.SongListInfo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WJY on 2018/4/14.
 */
@Controller
public class PlayController extends MusicWebController{

    @GetMapping("/GetSongs")
    public String GetSongs (String StrsongListInfoId,String StrsongId, Model model)
    {

        Session session =getSession();
        Transaction transaction=session.beginTransaction();
        List<Song> songs=new ArrayList<>();
        if(StrsongListInfoId!=null&&StrsongId==null)
        {
            int songListInfoId =Integer.parseInt(StrsongListInfoId);
            String hql="from SongList where songListId='"+songListInfoId+"'";
            Query query=session.createQuery(hql);
            List<SongList> songListList=query.list();
            query=session.createSQLQuery("{CALL songListClickCount(?)}");
            query.setInteger(0,songListInfoId);
            query.executeUpdate();
            transaction.commit();
            session.close();
            session=getSession();
            for(SongList songList:songListList)
            {
                Song song=songList.getSongId();
                File file=new File(this.picPath+File.separator+song.getAlbum()+".jpeg");
                session.close();
                if(!file.exists())
                {
                    song.setAlbum("false");
                }

                songs.add(song);
            }
            model.addAttribute("songs",songs);

        }
        if(StrsongId!=null&&StrsongListInfoId==null)
        {
            int songId =Integer.parseInt(StrsongId);
            Song song=session.get(Song.class,songId);
            File file=new File(this.picPath+File.separator+song.getAlbum()+".jpeg");
            session.close();
            if(!file.exists())
            {

                song.setAlbum("false");
            }

            songs.add(song);
            model.addAttribute("songs",songs);

        }

        return "play";

    }


    @PostMapping("/SelectedPlay")
    public @ResponseBody Map SelectedPlay(@RequestBody String songs, HttpSession httpSession) throws IOException {
        ObjectMapper objectMapper =new ObjectMapper();
        Integer[] songsId=objectMapper.readValue(songs,Integer[].class);
        Session session=getSession();
        List<Song> songList=new ArrayList<>();
        for (int sid:songsId
             ) {
            Song song=session.get(Song.class,sid);
            File file=new File(this.picPath+File.separator+song.getAlbum()+".jpeg");
            if(!file.exists())
                song.setAlbum("false");
            songList.add(song);
        }
        session.close();
        httpSession.setAttribute("selectedSongs",songList);
        Map state=new HashMap();
        state.put("PostState",true);
        return state;
    }


    @GetMapping("/PlaySelected")
    public String PlaySelected(Model model,HttpSession httpSession)
    {
        List<Song> songList=(List<Song>)httpSession.getAttribute("selectedSongs");
        model.addAttribute("songs",songList);
        httpSession.removeAttribute("selectedSongs");
        return "play";
    }

    @GetMapping("/htmlGetSongs")
    public @ResponseBody List<Song> htmlGetSongs (int songId, int songListInfoId, Model model)
    {
        List<Song> songs=new ArrayList<>();
        if(songListInfoId!=0)
        {
            Session session =getSession();
            String hql="from SongList where songListId='"+songListInfoId+"'";
            Query query=session.createQuery(hql);
            List<SongList> songListList=query.list();

            for(SongList songList:songListList)
            {
                Song song=songList.getSongId();
                File file=new File(this.picPath+File.separator+song.getAlbum()+".jpeg");
                if(!file.exists())
                    song.setAlbum("false");
                songs.add(song);
            }
            model.addAttribute("songs",songs);

        }

        return songs;

    }
}
