package com.musicweb.Controller;

import com.musicweb.hbobject.Singer;
import com.musicweb.hbobject.Song;
import com.musicweb.hbobject.SongListInfo;
import com.musicweb.hbobject.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WJY on 2018/4/16.
 */
@Controller
public class SeachResultContrller extends  MusicWebController {


    @GetMapping("/searchResult")
    public
    String searchResult(String searchStr, Model model) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        String hql;
        if(searchStr.equals("*"))
            hql = "From Song";
        else
            hql="From Song where " +
                    "title like  '%" +
                    searchStr+"%'or" +
                    " artist like '%" +
                    searchStr+"%'";
        Query query = session.createQuery(hql);
        List<Song> songList = query.list();
        model.addAttribute("songs",songList);
        transaction.commit();
        System.out.println("请求成功！！！！");
        return "searchResult";
    }


    @GetMapping("/htmlsearchResult")
    public @ResponseBody
    List<Song> htmlSearchResult(String searchStr) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        String hql;
        if(searchStr.equals("*"))
            hql = "From Song";
        else
            hql="From Song where " +
                    "title like  '%" +
                    searchStr+"%'or" +
                    " artist like '%" +
                    searchStr+"%'";
        Query query = session.createQuery(hql);
        List<Song> songList = query.list();
        transaction.commit();
        System.out.println("请求成功！！！！");
        return songList;
    }

    @GetMapping("/SearchLoadSL")
    public String SearchLoadSL(Model model)
    {
        Session loadUserSLsession=getSession();
        String hql="From  SongListInfo";
        Query query=loadUserSLsession.createQuery(hql);
        List<SongListInfo> songListInfoList=query.list();
        model.addAttribute("songListInfos",songListInfoList);
        return "songListSearch";
    }


    @GetMapping("/htmlSearchLoadSL")
    public @ResponseBody List<SongListInfo> htmlSearchLoadSL(String searchString, String tab)
    {
        Session session=getSession();
        String hql;
        if(tab.equals("全部"))
        {
            hql="From  SongListInfo where name like '%"+searchString+"%'";
        }
        else
        {
            hql="From  SongListInfo where name like '%"+searchString+"%'"+
                    "and tab='"+tab+"'";
        }
        Query query=session.createQuery(hql);
        List<SongListInfo> songListInfoList=query.list();
        session.close();
        return songListInfoList;

    }

    @PostMapping("/searchUser")
    public @ResponseBody List<User> searchUser(String searchStr)
    {

            Session session=getSession();
            String hql;
            if(searchStr.equals(""))
                hql="FROM  User ";
            else
            hql="FROM User where name like '%"+searchStr+"%'";
            Query query=session.createQuery(hql);
            List<User> userList=query.list();
            return userList;
    }

    @GetMapping("/searchSinger")
    public @ResponseBody
    List<Singer> searchSinger(String searchStr)
    {
        Session session=getSession();
        String hql;
        if(searchStr.equals(""))
            hql="FROM  Singer ";
        else
            hql="FROM Singer where name like '%"+searchStr+"%'";
        Query query=session.createQuery(hql);
        List<Singer> singerList=query.list();
        return singerList;
    }

    @GetMapping("/singerSearch")
    public String singerSearch(Model model)
    {
        Session session=getSession();
        String hql;
        hql="FROM  Singer ";
        Query query=session.createQuery(hql);
        List<Singer> singerList=query.list();
        model.addAttribute(singerList);
        return "singerSearch";
    }

}
