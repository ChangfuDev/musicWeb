package com.musicweb.hbobject;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateSessionFactory {
	
	static StandardServiceRegistry serviceRegistry;
	static SessionFactory sessionFactory;

	static {
		try
		{
			serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
			
			sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();

		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public static SessionFactory GetSessionFactory()
	{
		return sessionFactory;
	}
	
	

}
