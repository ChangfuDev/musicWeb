package com.musicweb.fileOperation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class lycRead {

	  public String min;
	  public float second;
	  public String lyc;
	  public lycRead[] l;
	  public String lycname;
	  private File f;
	  
	  public lycRead(String lycname)
	  {
		  this.lycname=lycname;
	  }
	  lycRead()
	  {
		 
	  }
	  public  void parseRead()
	 {
		  
		  
		  f=new File("D:\\musicWeb\\Admin\\lrc\\"+lycname+".lrc");
		  System.out.println(f.getAbsolutePath());
		  if(f.exists())
		  {
			  try {
					BufferedReader r=new  BufferedReader(new InputStreamReader(new FileInputStream(f),"utf-8"));
					String line=null;
					String now="";
					String lyc[];
					String lycContent[]=new String [100];
					int lineNum=0;
					while((line=r.readLine())!=null)
					{
						if(line.indexOf('[')!=-1)
						{

							lycContent[lineNum]=line;
							now=now+line.substring(line.indexOf('['), line.lastIndexOf(']')+1);
						    lineNum++;
						}
					    
					}
					now=now.replace("[","");
					lyc=now.split("]");
					String m[]=new String[3];
					l=new lycRead[lyc.length];
					for(int i=0;i<lyc.length;i++)
					{
						//System.out.println(a);
						l[i] =new lycRead();
						l[i].min=lyc[i];
						m=lyc[i].split(":");
						l[i].second=Float.parseFloat(m[0])*60+Float.parseFloat(m[1]);
						for(String content:lycContent)
						{
							if(content!=null)
							if(content.indexOf(lyc[i])!=-1)
							{
								l[i].lyc=content.substring(content.lastIndexOf("]")+1,content.length());
							}
						}
						//System.out.println(Float.parseFloat(m[0])*60+Float.parseFloat(m[1]));

					}
						
					lycRead temp=new lycRead(); 
					for(int a=0;a<l.length-1;a++)
					{
						
						for(int b=l.length-1;b-a>0;b--)
						{
							//if(l[a]!=null&&l[b]!=null)
							if(l[a].second>l[b].second)
							{
								temp=l[a];
								l[a]=l[b];
								l[b]=temp;
								
							}
						}
					}
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		  }
		  else
		  {
			  System.out.println("文件不存在");
		  }
	    
		
		
	 }
	  public lycRead[]  getlycRead()
	  {
		  if(!f.exists())
		  {
			  l=new lycRead[1];
			  l[0] =new lycRead();
			  l[0].min="null";
			  l[0].lyc="暂无歌词";
			  System.out.println(lycname);
			  return l;
		  }
		  else
		  return l;
	  }
	  
	  public void outputlyc()
	  {
		  if(f.exists())
		  for(lycRead oneline:l)
			{
				//if(oneline!=null)
				System.out.println(oneline.second+"    "+oneline.min+"    "+oneline.lyc);
			}
		  else
		  {
			  System.out.println("�ļ�������---");
		  }
	  }
	  
	  public static boolean ispic(String singer)
	  {
		  String path="C:/Users/WJY/eclipse-workspace/newweb/WebContent/singer/"+singer+".jpg";
		  System.out.println(path);
		  File f=new File(path);
		  if(f.exists())
		  {
			  return true;
		  }
		  else
			  return false;
	  }

}
