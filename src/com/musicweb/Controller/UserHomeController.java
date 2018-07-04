package com.musicweb.Controller;

import com.musicweb.hbobject.CollectList;
import com.musicweb.hbobject.SongListInfo;
import com.musicweb.hbobject.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by WJY on 2018/4/17.
 */
@Controller
public class UserHomeController extends MusicWebController {
    @GetMapping("/UserHome")
    public String  UserHome(String strUserId, Model model)
    {

        if(strUserId!=null)
        {
            int userId=Integer.parseInt(strUserId);
            Session session=getSession();
            Transaction transaction =session.beginTransaction();
            User user=session.get(User.class,userId);
            String hql="from SongListInfo where createUserId='"+userId+"'";
            Query query=session.createQuery(hql);
            List<SongListInfo> songListInfoList=query.list();

            hql="from CollectList where userId='"+userId+"'";
            query=session.createQuery(hql);
            List<CollectList> collectLists=query.list();
            List<SongListInfo> collectSonglists=new ArrayList<>();
            for (CollectList collectList:collectLists)
            {
                collectSonglists.add(collectList.getSongListId());
            }

            model.addAttribute("user",user);
            model.addAttribute("songLists",songListInfoList);
            model.addAttribute("collectSonglists",collectSonglists);
            transaction.commit();
            session.close();
            return "userHome";

        }
        else
            return null;

    }
}
