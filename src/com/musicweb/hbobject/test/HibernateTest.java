package com.musicweb.hbobject.test;

import com.musicweb.hbobject.*;
import com.mysql.cj.api.result.Row;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by WJY on 2018/4/8.
 */
public class HibernateTest {

    SessionFactory sessionFactory= HibernateSessionFactory.GetSessionFactory();
    Session session;
    Transaction transaction;


    public Session getSession()
    {
        return session=sessionFactory.openSession();
    }

    public void update()
    {
        session=sessionFactory.openSession();
        transaction=session.beginTransaction();
        /*Admin admin=new Admin();
        admin.setAdminName("wjy");
        admin.setPassword("123456");
        session.save(admin);*/
        transaction.commit();
        session.close();
    }
    public void FromOption()
    {
        session=sessionFactory.openSession();
        transaction=session.beginTransaction();
        String hql="from User where name='wjy'and password='12346'";
        Query query =session.createQuery(hql);
        List<User> songList=query.list();
        transaction.commit();
        session.close();
        //Object[] objects=new Object[2];
        for(User user:songList)
        {
            System.out.println(user.getName());
        }
    }

    public void sqlOption()
    {
        session=sessionFactory.openSession();
        transaction=session.beginTransaction();
        String Sql="select title,artist from song";
        //Query query =session.createNativeQuery(Sql);
        Query query =session.createSQLQuery(Sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List songList=query.list();
        List<Song> songs=new ArrayList<>();
        for(Object object:songList)
        {
            Map row=(Map)object;
            Song song=new Song();
            System.out.println(row.get("title"));
            song.setTitle((String)row.get("title"));
            song.setArtist((String)row.get("artist"));
            //if(row.get("duration")!=null)
            song.setDuration((int)row.get("duration"));
            songs.add(song);
        }
        transaction.commit();
        session.close();

    }

    public void selectOption()
    {
        session=sessionFactory.openSession();
        transaction=session.beginTransaction();
        String hql="select s.title,s.artist from Song as s";
        Query query =session.createQuery(hql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List songList=query.list();
        transaction.commit();
        session.close();
        //Object[] objects=new Object[2];
        for(Object object:songList)
        {
            Object[] objects=( Object[])object;
            System.out.println("Artist:"+objects[0]+"Title:"+objects[1]);
        }
    }

    public void getObject()
    {
        session=sessionFactory.openSession();
        transaction=session.beginTransaction();
        Song song=session.get(Song.class,2);
        User user=session.get(User.class,6);
        System.out.println(song.getTitle()+ user.getName());
        session.close();
    }


    public void likeTest()
    {
        session=sessionFactory.openSession();
        String seachStr="陈";
        String hql="From Song where " +
                "title like  '%" +
                seachStr+"%'or" +
                " artist like '%" +
                seachStr+"%'";
        Query query = session.createQuery(hql);
        List<Song> songList = query.list();
        for (Song song:songList
             ) {
            System.out.println(song.getArtist()+" - "+song.getTitle());
        }
    }


    /*get得到的对象删除会把外键的表中的数据也删除掉*/
    public void getAndDelete()
    {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        SongListInfo songListInfo=session.get(SongListInfo.class,8);
        session.delete(songListInfo);
        transaction.commit();
        session.close();
    }


    public void procedure()
    {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Query query=session.createSQLQuery("{CALL songClickCount(?)}");
        query.setInteger(0,5);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    public void hibernateRandom()
    {

        Random random=new Random();


        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        List<Song> songList=new ArrayList<>();
        String hql="select Count(*) From Song";
        Query query=session.createQuery(hql);
        Object object=query.uniqueResult();
        Long songsCountlong=(Long)object;
        int songCount=songsCountlong.intValue();
        for(int i=0;i<20;i++)
        {
            int index=random.nextInt(songCount);
            Song song=session.get(Song.class,index);
            if(song!=null)
            songList.add(song);
        }
        transaction.commit();
        session.close();
    }


    public void SerializableID()
    {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        /**User user=new User();
        user.setImage("009.jpg");
        user.setAge(18);
        user.setName("ceshi");
        user.setPassword("111111");*/
        SongList songList=new SongList();
        Song song=session.get(Song.class,499);
        SongListInfo songListInfo=session.get(SongListInfo.class,20);
        songList.setSongListId(songListInfo);
        songList.setSongId(song);
        SongList serializable=(SongList)session.save(songList);
        transaction.commit();
        session.close();

    }

}
