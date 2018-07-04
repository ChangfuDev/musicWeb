package com.musicweb.Controller;

import com.musicweb.hbobject.CollectList;
import com.musicweb.hbobject.SongListInfo;
import com.musicweb.hbobject.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WJY on 2018/4/12.
 */
@Controller
public class LoadMessage extends MusicWebController {



    @GetMapping("/loadUserSL")
    public @ResponseBody Map loadUserSL(HttpSession httpSession)
    {
        Map loadUserSLMessage=new HashMap();
        User user=(User)httpSession.getAttribute("user");
        if(user!=null)
        {
            Session session=getSession();
            String hql="From SongListInfo where createUserId= '"+user.getUserId()+"'";
            Query query=session.createQuery(hql);
            List<SongListInfo> songListInfoList=query.list();
            loadUserSLMessage.put("getInfoState",true);
            loadUserSLMessage.put("songListInfoList",songListInfoList);
            return loadUserSLMessage;

        }
        else
        {
            loadUserSLMessage.put("getInfoState",false);
            loadUserSLMessage.put("message","get songListInfo fail");
            return loadUserSLMessage;
        }
    }

    @GetMapping("/loadUserCollectSL")
    public @ResponseBody Map loadUserCollectSL(HttpSession httpSession)
    {
        Map loadUserSLMessage=new HashMap();
        User user=(User)httpSession.getAttribute("user");
        if(user!=null)
        {
            Session session=getSession();
            String hql="From CollectList where userId= '"+user.getUserId()+"'";
            Query query=session.createQuery(hql);
            List<CollectList> collectLists=query.list();
            List<SongListInfo> songListInfoList=new ArrayList<>();
            for(CollectList collectList:collectLists)
            {
                songListInfoList.add(collectList.getSongListId());
            }
            loadUserSLMessage.put("getInfoState",true);
            loadUserSLMessage.put("songListInfoList",songListInfoList);
            return loadUserSLMessage;

        }
        else
        {
            loadUserSLMessage.put("getInfoState",false);
            loadUserSLMessage.put("message","get songListInfo fail");
            return loadUserSLMessage;
        }
    }



    @GetMapping("/loadSL")
    public @ResponseBody Map loadUserSL()
    {
        Session loadUserSLsession=getSession();
        Map loadUserSLMessage=new HashMap();
        String hql="From  SongListInfo";
        Query query=loadUserSLsession.createQuery(hql);
        List<SongListInfo> songListInfoList=query.list();
        loadUserSLMessage.put("getInfoState",true);
        loadUserSLMessage.put("songListInfoList",songListInfoList);
        return loadUserSLMessage;
    }





}
