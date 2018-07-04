package com.musicweb.fileOperation;

import java.util.regex.*;
import java.util.List;
import java.io.*;
import java.util.ArrayList;


public class FileFilter {

	static int i=0;
	String regex=".*\\.mp3";
	String FilePath="";
	File path ;
	List<String> FileStringlist =new ArrayList<String>();
	List<File> Filelist =new ArrayList<File>();
		/*if(dir.length()==0)
			list=path.list();
		else
		{
			list =path.list(new DirFilter(dir));
		}*/

	public FileFilter(String FilePath,String regex)
	{
		this.FilePath=FilePath;
		path =new File(FilePath);
		if(regex!=null)
			this.regex=regex;
	}

	public void printlnList()
	{
		if(i!=0)i=0;
		ListStringFile(path,FileStringlist,regex);
		for(String b:FileStringlist)
			System.out.println(b);
		System.out.printf("�ܹ���%d�ļ�",FileStringlist.size());
		FileStringlist.clear();

	}


	public List<String> getFileNameList()
	{
		List<String> NameList=new ArrayList<String>();
		if(i!=0)i=0;
		ListStringFile(path,FileStringlist,regex);
		for(String b:FileStringlist)
		{
			b=b.substring(b.lastIndexOf("\\")+1,b.length());
			NameList.add(b);
		}
		return NameList;
	}
		/*Arrays.sort(list,String.CASE_INSENSITIVE_ORDER);
		for(String dirItem:list)
		{
			System.out.println(dirItem);

		}*/


	public List<String> getFileStringlist()
	{
		//if(i!=0)i=0;
		ListStringFile(path,FileStringlist,regex);
		return FileStringlist;
	}

	public List<File> getFilelist()
	{
		//if(i!=0)i=0;
		ListFile(path,Filelist,regex);
		return Filelist;
	}




	/*
	 * �г�����Ŀ¼�ķ���ɸѡ�������ļ�·��
	 */
	public void ListStringFile(File f,List<String> list,String regex)
	{

		if(f.isDirectory())
		{
			File[] a=f.listFiles();
			for(File b:a)
			{
				ListStringFile(b, list,regex);
			}
		}
		if(f.isFile())
		{
			if(regex==null)
			{
				list.add(f.getAbsolutePath());
			}
			else
			{
				if(f.getAbsolutePath().matches(regex))
					list.add(f.getAbsolutePath());
			}

		}

	}

	public void ListFile(File f,List<File> list,String regex)
	{

		if(f.isDirectory())
		{
			File[] a=f.listFiles();
			for(File b:a)
			{
				ListFile(b, list,regex);
			}
		}
		if(f.isFile())
		{
			if(regex==null)
			{
				list.add(f);
			}
			else
			{
				if(f.getAbsolutePath().matches(regex))
					list.add(f);
			}

		}

	}


}



class DirFilter implements FilenameFilter
{
	private Pattern pattern;
	public DirFilter(String regex)
	{
		pattern =Pattern.compile(regex);
	}
	public boolean accept(File dir,String name)
	{
		return pattern.matcher(name).matches();
	}
}

