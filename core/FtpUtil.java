package core;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;


/**
 * Java自带的API对FTP的操作
 * 
 * 文件名称：FtpUtil.java
 */
public final class FtpUtil {

    public FtpUtil(){}

    public final static int BUFF_SIZE = 1024;

    /**
     * ftp文件上传
     * @param connectionString ftp协议链接字符串 ftp://用户名:密码@地址:端口/ 或  ftp://地址:端口/
     * @param localFile 本地文件或文件夹
     * @param isOver 是否覆盖存在文件
     * @throws RuntimeException 
     */
    @SuppressWarnings("restriction")
    public final static void upload(String connectionString, File localFile, boolean isOver) throws RuntimeException
              {
        if(!localFile.exists()){
            throw new RuntimeException("local file is exists!");
        }
        FtpClient ftpClient = connect(connectionString);
        try {
            upload(ftpClient, localFile, isOver);
        }finally {
            try{
                ftpClient.close();
            }catch(Exception ex){
                throw new RuntimeException(ex);
            }
        }
    }


    /**
     * ftp文件上传
     * @param connectionString ftp协议链接字符串 ftp://用户名:密码@地址:端口/ 或  ftp://地址:端口/
     * @param remoteFile ftp服务器文件名称
     * @param fileStream 待写入文件流
     * @param isOver 是否覆盖存在文件
     * @throws RuntimeException
     */
    @SuppressWarnings("restriction")
    public final static void upload(String connectionString, String remoteFile, InputStream fileStream, boolean isOver) throws RuntimeException {
        FtpClient ftpClient = connect(connectionString);
        try {
            if(!"".equals(ftpClient.getStatus(remoteFile)) && !isOver)//是否覆盖文件
                return;

            try (OutputStream os = ftpClient.putFileStream(remoteFile); InputStream is = fileStream) {
                byte[] bytes = new byte[BUFF_SIZE];// 创建一个缓冲区
                int c;
                while ((c = is.read(bytes)) > 0) {
                    os.write(bytes, 0, c);
                }
            }
        } catch(Exception ex){
            throw new RuntimeException("input stream write fail!", ex);
        }finally {
            try{
                ftpClient.close();
            }catch(Exception ex){
                throw new RuntimeException(ex);
            }
        }
    }

    /**
     * 文件上传
     * @param ftpClient ftp客户端句柄
     * @param localFile 本地文件或文件夹
     * @param isOver 是否覆盖存在文件
     * @throws RuntimeException
     */
    @SuppressWarnings("restriction")
    private static void upload(FtpClient ftpClient, File localFile, boolean isOver) throws RuntimeException {
        if(localFile.isDirectory()){
            try{
                try{
                    ftpClient.changeDirectory(localFile.getName());
                }catch(Exception ex){
                    ftpClient.makeDirectory(localFile.getName());
                    ftpClient.changeDirectory(localFile.getName());
                }
                String[] files = localFile.list();
                File tempFile;
                for (int i = 0; i < files.length; i++) {        
                    tempFile = new File(localFile.getPath()+"\\"+files[i] );        
                    if(tempFile.isDirectory()){        
                        upload(ftpClient, tempFile, isOver);        
                        ftpClient.changeToParentDirectory();        
                    }else{                      
                        tempFile = new File(localFile.getPath()+"\\"+files[i]);        
                        streamWrite(ftpClient, tempFile, isOver);
                    }                   
                }        
            }catch(Exception ex){
                throw new RuntimeException(ex);
            }
        }else{  
            streamWrite(ftpClient, localFile, isOver);
        }        
    } 

    /**
     * 文件写入到ftp服务器
     * @param ftpClient ftp客户端句柄
     * @param localFile 本地文件
     * @param isOver 是否覆盖存在文件
     * @throws RuntimeException
     */
    @SuppressWarnings("restriction")
    private static void streamWrite(FtpClient ftpClient, File localFile, boolean isOver) throws RuntimeException{

    	
    
    	try{
            if(!"".equals(ftpClient.getStatus(localFile.getName())) && !isOver)//是否覆盖文件
                return;
            try (OutputStream os = ftpClient.putFileStream(localFile.getName());
                    InputStream is = new FileInputStream(localFile)) {
                byte[] bytes = new byte[BUFF_SIZE];// 创建一个缓冲区
                int c;
                while ((c = is.read(bytes)) > 0) {
                    os.write(bytes, 0, c);
                }
            }
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
    /** 
    * 从ftp下载文件到本地 
    *  
    * @param filename 服务器上的文件名 
    * @param newfilename 本地生成的文件名 
    * @return 
    * @throws Exception 
    */  
    @SuppressWarnings("restriction")
    public static long downloadFile(FtpClient ftpClient, String filename, String newfilename) {
        long result = 0;
        InputStream is = null;
        FileOutputStream os = null;
        try {
            is = ftpClient.getFileStream(filename);
            java.io.File outfile = new java.io.File(newfilename);
            os = new FileOutputStream(outfile);
            byte[] bytes = new byte[is.available()];
            int c;
            while ((c = is.read(bytes)) != -1) {
                os.write(bytes, 0, c);
                result = result + c;
            }
        } catch (IOException | FtpProtocolException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
     /** 
     * 取得相对于当前连接目录的某个目录下所有文件列表 
     *  
     * @param path 
     * @return 
     * @throws FtpProtocolException 
     */  
    @SuppressWarnings("restriction")
    public static List<Object> getFileList(FtpClient ftpClient,String path) throws FtpProtocolException{  
        List<Object> list = new ArrayList<Object>();  
        DataInputStream dis;  
        try {  
            dis = new DataInputStream(ftpClient.nameList( path));  
            String filename = "";  
            while((filename = dis.readLine()) != null){  
                list.add(filename);  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  

        return list;  
    }  

    /**
     * 连接到ftp服务器
     * @param ip ftp服务器地址
     * @param port ftp服务器端口
     * @param user 登陆ftp服务器用户
     * @param password 登陆ftp服务器用户密码
     * @param remotePath ftp服务器文件路径
     * @return FtpClient
     * @throws RuntimeException
     */
    @SuppressWarnings("restriction")
    public static FtpClient connect(String ip, int port, String user, String password, String remotePath) throws RuntimeException {
        FtpClient ftpClient = FtpClient.create();
        SocketAddress addr = new InetSocketAddress(ip, port);
        try{
            ftpClient.connect(addr);
            ftpClient.login(user, password.toCharArray());
            ftpClient.setBinaryType();
            if (remotePath!=null && remotePath.length() != 0) { // 把远程系统上的目录切换到参数path所指定的目录
                String[] paths=remotePath.split("/");
                for(String path : paths){
                    if(path.length() == 0)
                        continue;
                    try{
                        ftpClient.changeDirectory(path);
                    }catch(FtpProtocolException ex){
                        ftpClient.makeDirectory(path);
                        ftpClient.changeDirectory(path);
                    }
                }
            }
            return ftpClient;
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
    /**
     * 连接到ftp服务器
     * @param connectionString 链接字符串
     * @return
     */
    @SuppressWarnings("restriction")
    public static FtpClient connect(String connectionString) throws RuntimeException{
        Pattern pattern = Pattern.compile("^ftp://(?:\\S+?:\\S+?@)?\\S+?(?:\\d+?)?");
        if(pattern.matcher(connectionString).find()){
            String autho = "anonymous", uri = connectionString.substring("ftp://".length()).replace("\\", "/"),
                   user = "anonymous",pwd = "", ip = "", remotePath = "";
            int sp = uri.indexOf("/"), port = 21;
            if(sp >= 0){
                remotePath = uri.substring(sp);
                uri = uri.substring(0, sp);
            }
            sp = uri.lastIndexOf("@");
            if(sp >= 0){
                autho = uri.substring(0, sp);
                uri = ip = uri.substring(sp + 1);
            }
            sp = autho.lastIndexOf(":");
            if(sp >= 0){
                user = autho.substring(0, sp);
                pwd  = autho.substring(sp + 1);
            }
            sp = uri.lastIndexOf(":");
            if(sp >= 0){
                ip = uri.substring(0, sp);
                port  = Integer.parseInt(uri.substring(sp + 1));
            }
            return connect(ip, port, user, pwd, remotePath);
        }
        throw new RuntimeException("connectionString is invalid!");
    }
    /** 
     * 断开与ftp服务器连接 
     *  
     * @throws IOException 
     */ 
    @SuppressWarnings("restriction")
    public static boolean closeServer(FtpClient ftpClient) {  
        try {  
            if (ftpClient != null) {  
                //ftpClient.closeServer();  
                ftpClient.close();
            }  
            System.out.println("已从服务器断开");  
            return true;  
        } catch (IOException e) {  
            e.printStackTrace();  
            return false;  
        }  
    }  

    public static int uploadfile(String address,String port,String user,String password,String sfile,String dir)
    {
    	//对上传文件做了个封装
    	//参数分别表示：地址，端口，用户名，密码，源文件（本地），相对路径
    	int port1=Integer.parseInt(port);
    	FtpClient ftpClient=connect(address, port1, user, password, dir);
    	File up=new File(sfile);
    	 try {
    		 upload(ftpClient,up,true);
    	 }
    	 catch(Exception ex){
    		 System.out.println("上传失败");          
         }
    	 return 0;
    }
    
    public static int downloadfile(String address,String port,String user,String password,String sfile,String dir,String dfile)
    {
    	
    	//对下载文件做了个封装
    	//参数分别表示：地址，端口，用户名，密码，源文件（服务器），相对路径，目的文件（本地）
    	int port1=Integer.parseInt(port);
    	 FtpClient ftpClient=connect(address, port1, user, password, dir);
    	 try {
    		 downloadFile(ftpClient, sfile, dfile);
    	 }
    	 catch(Exception ex){
    		 System.out.println("下载失败");      
         } 
    	 return 0;
    }
    
    @SuppressWarnings("restriction")
    public static void main(String[] args) throws FtpProtocolException {

        FtpClient ftpClient=connect("127.0.0.1", 21, "ftp1", "ftp1", "/policy");
        
//        System.out.println("getFileList: "+getFileList(ftpClient, "/policy").get(0));
//        System.out.println("===="+ftpClient.isPassiveModeEnabled());
//      File up=new File("D://b.txt");
//      if (up.exists())
//      {
//      	System.out.println(" file");
//      }
//        
//        upload(ftpClient, up, true);
//        long ll=uploadfile("127.0.0.1", "21", "ftp", "ftp", "D:\\b.txt", "/p");
//        long ll=downloadfile("127.0.0.1", "21", "ftp", "ftp", "43.docx", "/policy", "D:\\d.docx");
//        long ll=downloadFile(ftpClient, "33.docx", "D:\\43.docx");
//        System.out.println("long : "+ll);
//        File up=new File("D://json//a.json");
//        if (!up.exists())
//        {
//        	System.out.println("no such file");
//        }
//        
//        upload("ftp://ftp:ftp@192.168.1.4:21/",up,true);
        closeServer(ftpClient);
    }
}