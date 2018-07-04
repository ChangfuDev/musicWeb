package com.musicweb.Controller;

import com.musicweb.fileOperation.UploadFiles;
import com.musicweb.hbobject.Singer;
import com.musicweb.hbobject.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by WJY on 2018/5/5.
 */
@Controller
public class AddSingerController extends MusicWebController {
    @PostMapping("/AddSinger")
    public @ResponseBody
    Map addSinger(@RequestParam("imageFile") MultipartFile[] files,
                  Singer singer)
    {
        Map messageMap=new HashMap();
        UploadFiles uploadFiles=(UploadFiles)getFactory().getBean("singerImageFiles");
        if(files[0].getOriginalFilename().equals(""))
        {
            singer.setIamge("default.jpg");
        }
        else
        {
            uploadFiles.uploadFileHandler(files);
            singer.setIamge(files[0].getOriginalFilename());
        }
            uploadFiles.uploadFileHandler(files);
            Session session=getSession();
            Transaction transaction=session.beginTransaction();
            session.save(singer);
            transaction.commit();
            session.close();
            messageMap.put("state",true);
            messageMap.put("message","添加歌手成功！");
            return  messageMap;

    }
}
