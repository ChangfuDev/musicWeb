package com.musicweb.Controller;

import com.musicweb.fileOperation.UploadFiles;
import com.musicweb.hbobject.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

/**
 * Created by WJY on 2018/4/11.
 */
public class MusicWebController {

    final static SessionFactory  sessionFactory = HibernateSessionFactory.GetSessionFactory();

    final static BeanFactory factory=new ClassPathXmlApplicationContext("beans.xml");

    Session session;
    final static String picPath="E:\\Album";

    public MusicWebController() {

    }

    public void BeanDefinitionReaderTest()
    {
        UploadFiles uploadFiles=(UploadFiles)factory.getBean("UploadFiles");
    }

    public Session getSession() {
        session = sessionFactory.openSession();
        return session;
    }

    public static BeanFactory getFactory() {
        return factory;
    }
}
