package com.musicweb.hbobject;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.musicweb.fileOperation.*;
import com.mpatric.mp3agic.*;
public class GetSongList {
	
	
	String path="";
	List<Song> songs=new ArrayList<>();
	
	public GetSongList(String path) {
		// TODO Auto-generated constructor stub
		this.path=path;
	}
	public GetSongList(){}
	
	public List<Song>  GetSongs(List<File> files)
	{
		//List<File> files=new FileFilter(path, null).getFilelist();
		Mp3File mp3File;
        ID3v1 id3v1tag;
        ID3v2 id3v2tag;

		for(File f:files)
		{
			Song song=new Song();
			try {
				mp3File=new Mp3File(f);
				if((id3v2tag=mp3File.getId3v2Tag())!=null)
				{

					song=new Song();
					song.setArtist(id3v2tag.getArtist());
					song.setTitle(id3v2tag.getTitle());
					song.setDuration(mp3File.getLengthInSeconds());
					song.setAlbum(id3v2tag.getAlbum());
                    byte[] albumImageData=id3v2tag.getAlbumImage();
					if(albumImageData!=null)
					{
						File file=new File("E:\\Album\\"+id3v2tag.getAlbum()+".jpeg");
						try {
							FileOutputStream iamgeout=new FileOutputStream(file);
							try {
								iamgeout.write(albumImageData);
								System.out.println("Album write successful\nfile:"+file.getAbsolutePath());
							} catch (IOException e) {
								e.printStackTrace();
							}
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
					}



				}
				else
				{
					if ((id3v1tag=mp3File.getId3v1Tag())!=null)
					{
						song=new Song();
						song.setArtist(id3v1tag.getArtist());
						song.setTitle(id3v1tag.getTitle());
						song.setDuration(mp3File.getLengthInSeconds());
						song.setAlbum(id3v1tag.getAlbum());


					}
					else
					{
						continue;
					}

				}

			} catch (IOException e) {
				e.printStackTrace();
			} catch (UnsupportedTagException e) {
				e.printStackTrace();
			} catch (InvalidDataException e) {
				e.printStackTrace();
			}

			songs.add(song);


		}
		
		return songs;
	}

}
