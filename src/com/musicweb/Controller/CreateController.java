package com.musicweb.Controller;

import com.musicweb.fileOperation.UploadFiles;
import com.musicweb.hbobject.SongListInfo;
import com.musicweb.hbobject.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.time.LocalTime;
import java.util.Date;

/**
 * Created by WJY on 2018/4/11.
 */
@Controller
public class CreateController extends MusicWebController {

    /*创建新歌单*/
    @RequestMapping(value = "/CreateSongList", method = RequestMethod.POST)
    @ResponseBody
    public String CreateSongList(@RequestParam("imageFile") MultipartFile[] files,
                                 SongListInfo songListInfo,
                                 HttpSession httpSession) {

        UploadFiles uploadFiles=(UploadFiles)getFactory().getBean("UserImageFile");

        if(uploadFiles.uploadFileHandler(files))
        {

            Session session=getSession();
            Transaction transaction=session.beginTransaction();

            /*songListInfo初始化*/
            songListInfo.setPic(files[0].getOriginalFilename());
            User loginUser=(User)httpSession.getAttribute("user");
            songListInfo.setCreateUserId(loginUser);
            songListInfo.setCreateTime(new Date());


            if(session.save(songListInfo)!=null)
            {
                transaction.commit();
                session.close();
              return  "Create songList successful";
            }
            else
            {
                session.close();
                return "Create songList fail info invalid";
            }
        }
        else
        {
            return "Create songList fail You uploadFiles fail";
        }
    }






}
