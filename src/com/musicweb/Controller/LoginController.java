package com.musicweb.Controller;

import com.musicweb.hbobject.Admin;
import com.musicweb.hbobject.Song;
import com.musicweb.hbobject.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WJY on 2018/4/11.
 */
@Controller
public class LoginController extends MusicWebController{

    @GetMapping("/Login")
    public String LoginPage()
    {
        return "Login";
    }

    @GetMapping("/adminPage")
    public String adminPage()
    {
        return "adminPage";
    }

    @GetMapping("/Logout")
    public String Logout(HttpSession httpSession)
    {
        httpSession.removeAttribute("user");
        return "redirect:/index";
    }

    @PostMapping("/login")
    public @ResponseBody Map Login(HttpSession httpSession, User user) {

        Map loginMessage=new HashMap();
        Session session=getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "From User where name='" + user.getName() + "'and password='" + user.getPassword() + "'";
        Query query = session.createQuery(hql);
        List<User> userList = query.list();

        hql ="from Admin where adminName='"+user.getName()+"' and password='" + user.getPassword() + "'";
        query=session.createQuery(hql);
        List<Admin> admins=query.list();

        transaction.commit();
        session.close();
        if(userList.size()!=0)
        {
            user=userList.get(0);
            System.out.println(user.getName());
            httpSession.setAttribute("user", user);
            loginMessage.put("loginState",true);
            loginMessage.put("admin",false);
            return loginMessage;

        }
        else
        {
            if(admins.size()!=0)
            {
                Admin admin=admins.get(0);
                System.out.println(admin.getAdminName());
                httpSession.setAttribute("admin", admin);
                loginMessage.put("loginState",true);
                loginMessage.put("admin",true);
                return loginMessage;
            }
            else
            {
                System.out.println("no this user");
                loginMessage.put("loginState",false);
                loginMessage.put("message","用户名或者密码错误");
                return loginMessage;
            }

        }


    }




}
