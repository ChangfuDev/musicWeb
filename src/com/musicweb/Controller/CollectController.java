package com.musicweb.Controller;

import com.musicweb.hbobject.CollectList;
import com.musicweb.hbobject.SongListInfo;
import com.musicweb.hbobject.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by WJY on 2018/4/26.
 */
@Controller
public class CollectController extends MusicWebController{
    @PostMapping("/CollectSL")
    public @ResponseBody String collectSL(int collectId,HttpSession httpSession)
    {
        User currentUser=(User)httpSession.getAttribute("user");
        if(currentUser!=null)
        {
            Session session=getSession();
            Transaction transaction=session.beginTransaction();
            String hql="from CollectList where userId="+currentUser.getUserId()+"and songListId="+collectId;
            Query query=session.createQuery(hql);
            if(query.list().size()==0)
            {
                User user=session.load(User.class,currentUser.getUserId());
                SongListInfo songListInfo=session.load(SongListInfo.class,collectId);
                CollectList collectList=new CollectList();
                collectList.setSongListId(songListInfo);
                collectList.setUserId(user);
                session.save(collectList);
                query=session.createSQLQuery("{CALL songListCollectCount(?)}");
                query.setInteger(0,collectId);
                query.executeUpdate();
                transaction.commit();
                session.close();
                return "收藏成功！";
            }
            else
            {
                return "你已经收藏了这个歌单！";
            }
        }
        else
        {
            return "请先登录！";
        }
    }
}
