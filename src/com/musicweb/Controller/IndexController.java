package com.musicweb.Controller;

import com.musicweb.hbobject.Song;
import com.musicweb.hbobject.SongListInfo;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by WJY on 2018/4/16.
 */
@Controller
public class IndexController extends MusicWebController {

   @GetMapping("/index")
    public String index(Model model)
    {
        Session session=getSession();
        String[] tabs={"华语","粤语","欧美","日语"};
        Query query;
        String hql;
        List<List<SongListInfo>> lists=new ArrayList<>();
        Random random=new Random();
        int max;
        for (String tab:tabs) {
            hql="From SongListInfo where tab ='"+tab+"'";
            query=session.createQuery(hql);
            List<SongListInfo> songListInfoList= query.list();
            boolean[] isadd=new boolean[songListInfoList.size()];
            List<SongListInfo> randomSongListInfoList= new ArrayList<>();
                if(songListInfoList.size()<16)
                {
                    max=songListInfoList.size();
                }
                else
                {
                    max=16;
                }

            while (randomSongListInfoList.size()<max)
            {
                int addindex=random.nextInt(songListInfoList.size());
                if(!isadd[addindex])
                {
                    randomSongListInfoList.add(songListInfoList.get(addindex));
                    isadd[addindex]=true;
                }
            }

            lists.add(randomSongListInfoList);
        }
        String sql="SELECT * FROM wjy.song order by playCount desc limit 10";
        query=session.createSQLQuery(sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List objectSongs=query.list();
        List<Song> songsOrderClick=new ArrayList<>();
        for(Object object:objectSongs)
        {
            Map row=(Map)object;
            Song song=new Song();
            song.setTitle((String)row.get("title"));
            song.setArtist((String)row.get("artist"));
            song.setSongId((int)row.get("songId"));
            song.setPlayCount((int)row.get("playCount"));
            songsOrderClick.add(song);

        }

        List<Song> randomSongs=new ArrayList<>();
        hql="select Count(*) From Song";
        query=session.createQuery(hql);
        Object object=query.uniqueResult();
        Long songsCountlong=(Long)object;
        int songCount=songsCountlong.intValue();

        for(int i=0;i<20;i++)
        {
            int index=random.nextInt(songCount);

            Song song=session.get(Song.class,index);
            if(song!=null)
                randomSongs.add(song);
        }
        model.addAttribute("songLists",lists);
        model.addAttribute("songsOrderClick",songsOrderClick);
        model.addAttribute("randomSongs",randomSongs);
        return "index";
    }
}
