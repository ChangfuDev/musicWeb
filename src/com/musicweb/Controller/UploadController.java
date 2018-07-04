package com.musicweb.Controller;

import com.musicweb.fileOperation.FileFilter;
import com.musicweb.fileOperation.UploadFiles;
import com.musicweb.hbobject.GetSongList;
import com.musicweb.hbobject.Song;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WJY on 2018/4/24.
 */
@Controller
public class UploadController extends  MusicWebController {

    public  MultipartFile[] mp3FileFilter(MultipartFile[] multipartFile)
    {
        Session session=getSession();
        String title,artist;
        String filename;
        String hql;
        Query query;
        List<MultipartFile> multipartFileList=new ArrayList<>();
        MultipartFile[] multipartFiles=null;
        for (MultipartFile file:multipartFile)
        {
           filename= file.getOriginalFilename().replaceAll(".mp3","");

           artist=filename.split(" - ")[0];
           title=filename.split(" - ")[1];
           hql="FROM Song where title='"+title+"'and artist='"+artist+"'";
           query=session.createQuery(hql);
           if(query.list().size()==0)
           {
               multipartFileList.add(file);
           }



        }
        if(multipartFileList.size()!=0)
        {
           multipartFiles=multipartFileList.toArray(new MultipartFile[multipartFileList.size()]);

        }

        return multipartFiles;

    }
    @PostMapping("/UploadSongs")
    public @ResponseBody
    Map UploadSongsOperation(@RequestParam("songsFiles") MultipartFile[] multipartFile)
    {
        Map stateMap=new HashMap();
        multipartFile=mp3FileFilter(multipartFile);
        if(multipartFile!=null)
        {
            UploadFiles uploadFiles=(UploadFiles)getFactory().getBean("mp3Files");
            if(uploadFiles.uploadFileHandler(multipartFile))
            {
                GetSongList getSongList=new GetSongList();
                List<Song> songList=getSongList.GetSongs(uploadFiles.getFileList());
                Session session=getSession();
                Transaction transaction=session.beginTransaction();
                for(Song s:songList)
                {
                    session.save(s);
                }
                transaction.commit();
                session.close();
                stateMap.put("state",true);
                stateMap.put("Message","上传成功！");
                return stateMap;
            }
            else
            {
                stateMap.put("state",false);
                stateMap.put("Message","文件上传发生错误！");
                return stateMap;
            }


        }
        else
        {
            stateMap.put("state",false);
            stateMap.put("Message","歌曲已经存在！");
            return stateMap;
        }

    }

    @PostMapping("/UploadLrcs")
    public @ResponseBody Map uploadLrcs(@RequestParam("lrcFiles") MultipartFile[] multipartFile)
    {
        Map stateMap=new HashMap();
        UploadFiles uploadFiles=(UploadFiles)getFactory().getBean("lrcFiles");
        if(uploadFiles.uploadFileHandler(multipartFile))
        {
            stateMap.put("state",true);
            stateMap.put("Message","上传成功！");
            return stateMap;
        }
        else
        {
            stateMap.put("state",false);
            stateMap.put("Message","文件上传发生错误！");
            return stateMap;
        }
    }
}
