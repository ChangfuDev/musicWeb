package com.musicweb.Controller;

import com.musicweb.fileOperation.Slrc;
import com.musicweb.fileOperation.lycRead;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WJY on 2018/4/14.
 */
@Controller
public class Producelrc extends MusicWebController {
    @GetMapping("/producelrc")
    public String  producelrc(String song, int songId,Model model)
    {
        Session session=getSession();
        Transaction transaction=session.beginTransaction();
        Query query=session.createSQLQuery("{CALL songClickCount(?)}");
        query.setInteger(0,songId);
        query.executeUpdate();
        transaction.commit();
        session.close();
        System.out.println("song:"+song);
        lycRead l =new lycRead(song);
        l.parseRead();
        lycRead[] lyc=l.getlycRead();
        List<Slrc> sLrcList=new ArrayList<>();

        for (lycRead lrc:lyc
             ) {
           // System.out.println(lrc.min+lrc.lyc);
            Slrc slrc =new Slrc();
            slrc.setLyc(lrc.lyc);
            slrc.setMin(lrc.min);
            sLrcList.add(slrc);
        }
        model.addAttribute("lyc",sLrcList);
        return "producelrc";
    }
}
