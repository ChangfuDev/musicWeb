package com.musicweb.hbobject;

import com.musicweb.Controller.MusicWebController;
import com.musicweb.hbobject.test.HibernateTest;
import com.sun.org.apache.bcel.internal.util.ClassPath;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;




import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Text {



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String path="E:\\CloudMusic";
		//GetSongList getSongList=new GetSongList(path);
		//List<Song> songs=getSongList.GetSongs();


		/*SessionFactory sessionFactory=HibernateSessionFactory.GetSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		//for (Song song:songs)
		//User user=session.get(User.class,16);
		/*User newUser=new User();
		newUser.setName("newuser");
		newUser.setImage("default.jpg");
		newUser.setPassword("111111");
		//user=newUser;
		session.save(newUser);*/
		/*Singer singer=session.get(Singer.class,6);
		System.out.println(singer.getIntrodution());
		String format=singer.getIntrodution().replace("\r\n","<br>");
		System.out.println(format);*/
		/*Song song=session.get(Song.class,21);
		String r=song.getArtist();*/
		/*String hql="FROM  SongListInfo ";
		Query query=session.createQuery(hql);
		List<SongListInfo> songListInfoList=query.list();
		for (SongListInfo songListInfo:songListInfoList)
		{
			Random random=new Random();
			int playrand=random.nextInt(2000);
			int collectrand=random.nextInt(1000);
			songListInfo.setPlayCount(playrand);
			songListInfo.setCollectCount(collectrand);
		}
		transaction.commit();
		session.close();

		//HibernateTest hibernateTest=new HibernateTest();
		//hibernateTest.likeTest();
		//hibernateTest.selectOption();
		//hibernateTest.FromOption();
		//hibernateTest.sqlOption();
		//hibernateTest.procedure();
		//hibernateTest.hibernateRandom();
		//hibernateTest.hibernateRandom();
		//hibernateTest.getObject();
		//hibernateTest.update();
		//hibernateTest.SerializableID();


		//MusicWebController musicWebController=new MusicWebController();
		//musicWebController.BeanDefinitionReaderTest();


		/*Date date =new Date();
		System.out.println(date.toString());
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		String mydate=DateFormat.getDateInstance().format(date);
		System.out.println(mydate);
		System.out.println(df.format(date));*/
		/*String prefixPath="D:\\垃圾文件夹\\素材\\musicwebImage\\Iamge";
		String fileType=".jpg";
		File rootFile=new File(prefixPath);
		int i=28;
		for(File f:rootFile.listFiles())
		{
			i++;
			f.renameTo(new File(prefixPath+i+fileType));
			System.out.println(f.getAbsolutePath());
		}*/

		/*File file=new File("D:\\垃圾文件夹\\素材\\musicwebImage\\300 (1).jpg");
		file.renameTo(new File(prefixPath+"1"+fileType));*/
		//System.out.println(new Date());

		/*String r="我是中文";
		String c="I am English";
		System.out.println(r);*/

		//Integer i=100;

		//this.getClass().getClassLoader();
		System.out.println(ClassLoader.getSystemClassLoader().getResource("beans.xml"));
		String[]  rs=ClassPath.getClassPath().split(";");
		for (String r:rs)
        System.out.println(r);






	}

}
