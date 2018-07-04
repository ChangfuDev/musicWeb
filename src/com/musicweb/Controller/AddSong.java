package com.musicweb.Controller;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.musicweb.hbobject.Song;
import com.musicweb.hbobject.SongList;
import com.musicweb.hbobject.SongListInfo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by WJY on 2018/4/11.
 */
@Controller
public class AddSong extends MusicWebController {

    @PostMapping("/addSong")
    public @ResponseBody
    String addSong(@RequestBody String songs, HttpServletRequest httpServletRequest) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,List<Integer>> songlist=objectMapper.readValue(songs,Map.class);
        List<Integer> songListId=songlist.get("songListId");
        List<Integer> songsId = songlist.get("songsId");
        Session session=getSession();
        Transaction transaction=session.beginTransaction();
        String hql;
        Query query;
        boolean hasSave=false;
       for (int songId:songsId
             ) {
            hql="from SongList where songId="+songId+"and songListId="+songListId.get(0);
            query=session.createQuery(hql);
            if(query.list().size()==0)
            {
                Song song=session.load(Song.class,songId);
                SongListInfo songListInfo=session.load(SongListInfo.class,songListId.get(0));
                SongList songList=new SongList();
                songList.setSongId(song);
                songList.setSongListId(songListInfo);
                session.save(songList);
                hasSave=true;
            }
        }

        transaction.commit();
        session.close();
        if(hasSave)
        return "添加歌曲成功！";
        else
            return "歌曲已存在！";


    }
}
