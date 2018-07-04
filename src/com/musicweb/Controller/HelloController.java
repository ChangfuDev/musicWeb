package com.musicweb.Controller;


import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.musicweb.hbobject.Song;
import com.musicweb.hbobject.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WJY on 2018/3/28.
 */
@Controller
public class HelloController extends MusicWebController {



    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String testMessage(Model model) {
        model.addAttribute("message", "hello spring mvc");
        return "hello";
    }

    @RequestMapping(value = "/testajax1", method = RequestMethod.GET)
    public void testM() {
        //return null;
    }

    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    public @ResponseBody
    List<Song> Message() {
        Session session =getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "From Song";
        Query query = session.createQuery(hql);
        List<Song> musicList = query.list();
        transaction.commit();
        session.close();
        System.out.println("请求成功！！！！");
        return musicList;
    }

   /* @GetMapping("/get")
    @ResponseBody
    public User get()
    {
      return new User("this is data");
    }*/

    /*@GetMapping("/seachResult")
    public @ResponseBody
    List<Song> seachResult() {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "From Song";
        Query query = session.createQuery(hql);
        List<Song> songList = query.list();
        transaction.commit();
        System.out.println("请求成功！！！！");
        return songList;
    }*/

    /*@PostMapping("/signIn")
    public @ResponseBody
    String signInMessage(User user, HttpSession httpSession) {

        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        httpSession.setAttribute("user", user);
        transaction.commit();
        session.close();
        System.out.println(user.getName());
        return "注册成功 滴滴...";

    }*/


    @PostMapping("/loginjsp")
    public String Login(HttpSession httpSession, User user, Model model) {
        Transaction transaction = session.beginTransaction();
        String hql = "From User where name='" + user.getName() + "'and password='" + user.getPassword() + "'";
        Query query = session.createQuery(hql);
        List<User> userList = query.list();
        user = userList.get(0);
        System.out.println(user.getName());
        httpSession.setAttribute("user", user);
        hql = "From Song";
        query= session.createQuery(hql);
        List<Song> songList =query.list();
        model.addAttribute("songs", songList);
        transaction.commit();
        return "searchResult";

    }

    @GetMapping("/getuser")
    public @ResponseBody User getUser(HttpSession httpSession) {
        User user = new User();
        if ((user = (User) httpSession.getAttribute("user")) != null) {
            return user;
        } else {
            return null;
        }


    }


    @RequestMapping(value = "/param", method = RequestMethod.POST)
    public @ResponseBody String param(User user) {
        System.out.println("age:" + user.getAge() + "\nname:" + user.getName() + "\nsex:" + user.isSex() + "\npassword:" + user.getPassword());
        return "data successful";
    }

    /*
    * var songs=JSON.stringify([{"songId":5},{"songId":6}]);  //数组json字符串化
		$.ajax({
			type: "POST",
			url: "http://localhost:8080/musicweb/StringTest",
			data: songs,
			contentType: "application/json; charset=utf-8",//设置服务器接收的编码类型，不然是
			dataType: "json",                              //表单类型会报错
			success: function (response) {
				console.log(response);
			}
		});
    * */
    // @GetMapping("/Stirngtest")
    @PostMapping("/StringTest")
    public @ResponseBody Map StringTest(@RequestBody String songs) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        /*JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, Song.class);
        try {
            List<Song> list = objectMapper.readValue(songs, javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        Integer[] songlist=objectMapper.readValue(songs,Integer[].class);

       // Integer[] songid = objectMapper.readValue(songs,Integer[].class);

        Map map =new HashMap();
        map.put("name", "wjy");
        map.put("age","12");
        Map bool=new HashMap();
        bool.put("loginState",false);

        Map all=new HashMap();
        all.put("user",map);
        all.put("loginMessage",bool);
        return all;

    }

    @PostMapping("/Arraytest")
    public @ResponseBody int[] arrayTest(@RequestParam("intarray") int[] ints)
    {
        return ints;
    }

}
