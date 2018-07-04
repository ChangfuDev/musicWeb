package com.musicweb.Controller;

import com.musicweb.fileOperation.UploadFiles;
import com.musicweb.hbobject.Singer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by WJY on 2018/5/5.
 */
@Controller
public class UpdateSinger extends MusicWebController{
    @PostMapping("/updateSinger")
    public @ResponseBody
    Map updateSinger(Singer singer,@RequestParam("imageFile") MultipartFile[] files){

        Map messageMap=new HashMap();
        Session session=getSession();
        Singer oldSinger=session.get(Singer.class,singer.getSingerId());
        session.close();
        session=getSession();
        Transaction transaction=session.beginTransaction();
        if (!files[0].getOriginalFilename().equals(""))
        {
            UploadFiles uploadFiles=(UploadFiles)getFactory().getBean("singerImageFiles");
            uploadFiles.uploadFileHandler(files);
            singer.setIamge(files[0].getOriginalFilename());
        }
        else {
            singer.setIamge(oldSinger.getIamge());
        }


        session.update(singer);
        transaction.commit();
        session.close();
        messageMap.put("state",true);
        messageMap.put("message","更新歌手信息成功！");
        return  messageMap;


    }


}
