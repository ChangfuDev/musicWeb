package com.musicweb.Controller;

import com.musicweb.fileOperation.UploadFiles;
import com.musicweb.hbobject.SongListInfo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by WJY on 2018/4/21.
 */
@Controller
public class UpdateSongList extends MusicWebController{
    @PostMapping("/UpdateSL")
    public String UpdateSL(@RequestParam("imageFile") MultipartFile[] files,
                           SongListInfo songListInfo)
    {
        Session session=getSession();
        String hql;
        Query query;
        Transaction transaction=session.beginTransaction();
        if(!files[0].getOriginalFilename().equals("")&&songListInfo!=null)
        {
            UploadFiles uploadFiles=(UploadFiles)getFactory().getBean("UserImageFile");
            uploadFiles.uploadFileHandler(files);
            hql="update SongListInfo as SLI set SLI.pic='"+files[0].getOriginalFilename()+"' where SLI.songListId="+songListInfo.getSongListId();
            query=session.createQuery(hql);
            query.executeUpdate();

        }
        if(!songListInfo.getName().equals(""))
        {
            hql="update SongListInfo as SLI set SLI.name ='"+songListInfo.getName()+"' where SLI.songListId="+songListInfo.getSongListId();
            query=session.createQuery(hql);
            query.executeUpdate();
        }
        if(!songListInfo.getIntrodution().equals(""))
        {
            hql="update SongListInfo as SLI set SLI.introdution ='"+songListInfo.getIntrodution()+"' where SLI.songListId="+songListInfo.getSongListId();
            query=session.createQuery(hql);
            query.executeUpdate();
        }
        if(!songListInfo.getTab().equals(""))
        {
            hql="update SongListInfo as SLI set SLI.tab ='"+songListInfo.getTab()+"' where SLI.songListId="+songListInfo.getSongListId();
            query=session.createQuery(hql);
            query.executeUpdate();
        }
        transaction.commit();
        session.close();
        return "redirect:/MusicList?songListId="+songListInfo.getSongListId();
    }
}
