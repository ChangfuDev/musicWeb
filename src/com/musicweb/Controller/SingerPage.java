package com.musicweb.Controller;

import com.musicweb.hbobject.Singer;
import com.musicweb.hbobject.Song;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by WJY on 2018/5/6.
 */
@Controller
public class SingerPage extends MusicWebController{
    @GetMapping("/singerPage")
    public String singerPage(int singerId, Model model)
    {
        Session session=getSession();
        Singer singer=session.get(Singer.class,singerId);
        String hql="From Song where artist='"+singer.getName()+"'";
        Query query=session.createQuery(hql);
        List<Song> songs=query.list();
        model.addAttribute("singer",singer);
        model.addAttribute("songs",songs);
        session.close();
        return "singerPage";
    }
}
