package com.musicweb.Controller;

import com.musicweb.fileOperation.UploadFiles;
import com.musicweb.hbobject.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by WJY on 2018/4/17.
 */
@Controller
public class UserOperation extends MusicWebController {

    @PostMapping("/signIn")
    public @ResponseBody
    Map signInMessage(User user, HttpSession httpSession) {

        Session session = getSession();
        Map signMessage=new HashMap();
        String hql="from User where name='"+user.getName()+"'";
        Query query=session.createQuery(hql);
        if(query.list().size()==0)
        {
            Transaction transaction = session.beginTransaction();
            user.setImage("default.jpg");
            session.save(user);
            httpSession.setAttribute("user", user);
            transaction.commit();
            session.close();
            System.out.println(user.getName());
            signMessage.put("state",true);
            signMessage.put("message","注册成功 ...");
            return signMessage ;
        }
        else
        {
            signMessage.put("state",false);
            signMessage.put("message","用户名已存在，请重新输入！");
            return signMessage;
        }

    }
    @PostMapping("/UserAlter")
    public
    String UserAlter(User user,
                     HttpSession httpSession,
                     @RequestParam("imageFile") MultipartFile[] files) {

        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        User oldUser=(User) httpSession.getAttribute("user");
        user.setName(oldUser.getName());
       if(!files[0].getOriginalFilename().equals("")){
           UploadFiles uploadFiles=(UploadFiles)getFactory().getBean("UserImageFile");
           uploadFiles.uploadFileHandler(files);
           user.setImage(files[0].getOriginalFilename());
       }
       else {

           user.setImage(oldUser.getImage());
       }
        session.update(user);
        transaction.commit();
        session.close();
        httpSession.setAttribute("user", user);
        System.out.println(user.getName());
        return "redirect:/UserHome?strUserId="+user.getUserId();

    }

    @PostMapping("/htmlUserAlter")
    public @ResponseBody
    String htmlUserAlter(User user,
                     @RequestParam("imageFile") MultipartFile[] files) {

        Session session = getSession();
        String hql="FROM User where name='"+user.getName()+"'";
        Query query=session.createQuery(hql);
        if(query.list().size()==0){
            User oldUser=session.get(User.class,user.getUserId());
            session.close();
            if(!files[0].getOriginalFilename().equals(""))
            {
                UploadFiles uploadFiles=(UploadFiles)getFactory().getBean("UserImageFile");
                uploadFiles.uploadFileHandler(files);
                user.setImage(files[0].getOriginalFilename());
            }
            else
                user.setImage(oldUser.getImage());

            session=getSession();
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            session.close();
            System.out.println(user.getName());
            return "用户信息修改成功！";
        }
        else
        {
            return "用户已存在不能修改！";
        }


    }


    @GetMapping("/userForm")
    public String userForm(int userId, Model model)
    {
        Session session=getSession();
        User user=session.get(User.class,userId);
        model.addAttribute("user",user);
        session.close();
        return "userForm";
    }


}
