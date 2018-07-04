package com.musicweb.fileOperation;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WJY on 2018/4/11.
 * 文件上传操作类
 */

public class UploadFiles {

    /*文件存放服务端的位置*/
    private String rootPath;
    /*不同类型文件放不同目录文件*/
    private String FileType;
    /*用户目录||管理员目录*/
    private String PermissionType;
    /*文件列表*/
   List<File> fileList=new ArrayList<>();

    public UploadFiles() {

    }

    /*---------get and set---------*/
    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public String getRootPath() {
        return rootPath;
    }

    public String getFileType() {
        return FileType;
    }

    public void setFileType(String fileType) {
        FileType = fileType;
    }

    public String getPermissionType() {
        return PermissionType;
    }

    public void setPermissionType(String permissionType) {
        PermissionType = permissionType;
    }

    public List<File> getFileList() {
        return fileList;
    }

  /*------------------*/


    /*文件上传处理*/
    public boolean uploadFileHandler(MultipartFile[] multipartFiles) {

        fileList.clear();
        if (multipartFiles != null) {
            File dir;
            try {

                if(PermissionType==null&&FileType==null)
                {
                   dir = new File(rootPath);
                }
                else
                {
                   dir = new File(rootPath + File.separator +PermissionType+File.separator+FileType);
                }

                if (!dir.exists())
                    dir.mkdirs();
                // 写文件到服务器
                for (MultipartFile file : multipartFiles) {
                    File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
                    file.transferTo(serverFile);
                    fileList.add(serverFile);
                    System.out.println("You svae file successful filepath:" + serverFile.getAbsolutePath());
                }

                return true;

            }
            catch (Exception e) {
                return false;
            }

        }
        else {
            System.out.println("error：The fiels is null");
            return false;
        }
    }

}
