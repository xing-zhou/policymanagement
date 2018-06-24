package core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class contenSearch {
	
	public static String checkContent(File file,String[] keywords)
	{ 
		String content="";
		String name=null;
		try {
			content = FileUtils.readFileToString(file,"GBK");
			boolean key=true;
			 for (int i=0;i<keywords.length;i++)
		  	    {
		  	    	key=key&&content.contains(keywords[i]);
		  	    }
			 if (key)
	  	    	{
	  	    		name=file.getAbsolutePath();
	  	    	}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;
		
	}
	
	public static List<File> listFile(File file,List<File> listfiles)
	{
		if (file.isFile())
		{
			listfiles.add(file);
		}
		if (file.isDirectory())
		{
			File[] files=file.listFiles();
			for (int i=0;i<files.length;i++)
			{
				File subfile=files[i];
				listFile(subfile,listfiles);
			}
		}
		return listfiles;		
	}
	
	public static String contentQuery(String dir,String[] keywords)
	{
		File file=new File(dir);
		List<File> listfiles=new ArrayList<File>();
		listfiles=listFile(file,listfiles);
		String filename= "";
		  for (int i=0;i<listfiles.size();i++)
		  {
			  if (checkContent(listfiles.get(i),keywords)!=null)
			  {
			  filename=filename+checkContent(listfiles.get(i),keywords)+"\r\n";
			  }
		  }
		  return filename;
	}
	

	public static void main(String[] args)
	{
		String dir="D:/result";
		String[] keywords = {"µçÂ·"};
		System.out.println(contentQuery(dir,keywords));
	}
}
