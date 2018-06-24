package core;

//import edu.uci.ics.crawler4j.examples.imagecrawler.attachcrawler;
//import edu.uci.ics.crawler4j.examples.imagecrawler.attachcrawler;;

import edu.uci.ics.crawler4j.examples.contentcrawler.ctcrawler;


public class contentcrawl {
	
	public int crawler(String rootFolder,int threads, int maxdepth, String storagefolder,String[] url,String[] keywords)
	{
		int i=1;
		try {
			i=ctcrawler.crawler(rootFolder, threads, maxdepth,storagefolder,url,keywords);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	public static void main(String[] args)
	{
		String rootFolder = "D://json";
	    int threads = 1;
	    int maxdepth=30;
	    String storageFolder = "D://result";
	    String[] url = new String[] { "http://etc.wuxi.gov.cn/doc/2018/04/27/1813482.shtml" };
	    String[] keywords= {"集成电路"};
	   int i=1;
	    try {
	    	i=ctcrawler.crawler(rootFolder, threads, maxdepth,storageFolder,url,keywords);
			System.out.println("end status: "+i);
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}

	
}
