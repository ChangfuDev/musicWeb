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

/**
 * Created by WJY on 2018/5/6.
 */
@Controller
public class ListController extends MusicWebController {
    @GetMapping("/ListPage")
    public String listPage(Model model)
    {
        Session session=getSession();
        Query query;
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

        String hql="FROM SongListInfo order by playCount desc";
        query=session.createQuery(hql);
        List<SongListInfo> songListInfoPlayList=query.list();
        hql="FROM SongListInfo order by collectCount desc";
        query=session.createQuery(hql);
        List<SongListInfo> songListInfoCollectList=query.list();

        model.addAttribute("songs",songsOrderClick);
        model.addAttribute("songListInfoCollectList",songListInfoCollectList);
        model.addAttribute("songListInfoPlayList",songListInfoPlayList);
        return "ListPage";
    }
}
