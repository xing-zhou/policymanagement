package core;


import edu.uci.ics.crawler4j.examples.imagecrawler.attachcrawler;

public class attachcrawl {

	public int crawler(String rootFolder,int threads, int maxdepth,String storagefolder, String[] webdomain)
	{
		//对原crawler4j的调用
		//参数分别表示：临时文件夹，线程数，网页深度，存储文件夹，url
		int i=1;
		try {
			i=attachcrawler.crawler(rootFolder,threads,maxdepth,storagefolder,webdomain);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	public static void main(String[] args)
	{
	
		String rootFolder = "D://json";
	    int numberOfCrawlers = 1;
	    int maxdepth=5;
	    String storageFolder = "D://result";
	    String[] crawlDomains = new String[] { "http://etc.wuxi.gov.cn/doc/2018/04/27/1813482.shtml" };
	    int i=1;
	    try {
			i=attachcrawler.crawler(rootFolder,numberOfCrawlers,maxdepth,storageFolder,crawlDomains);
			System.out.println("end status: "+i);
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	}
