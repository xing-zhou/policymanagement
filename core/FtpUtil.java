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
 * Java�Դ���API��FTP�Ĳ���
 * 
 * �ļ����ƣ�FtpUtil.java
 */
public final class FtpUtil {

    public FtpUtil(){}

    public final static int BUFF_SIZE = 1024;

    /**
     * ftp�ļ��ϴ�
     * @param connectionString ftpЭ�������ַ��� ftp://�û���:����@��ַ:�˿�/ ��  ftp://��ַ:�˿�/
     * @param localFile �����ļ����ļ���
     * @param isOver �Ƿ񸲸Ǵ����ļ�
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
     * ftp�ļ��ϴ�
     * @param connectionString ftpЭ�������ַ��� ftp://�û���:����@��ַ:�˿�/ ��  ftp://��ַ:�˿�/
     * @param remoteFile ftp�������ļ�����
     * @param fileStream ��д���ļ���
     * @param isOver �Ƿ񸲸Ǵ����ļ�
     * @throws RuntimeException
     */
    @SuppressWarnings("restriction")
    public final static void upload(String connectionString, String remoteFile, InputStream fileStream, boolean isOver) throws RuntimeException {
        FtpClient ftpClient = connect(connectionString);
        try {
            if(!"".equals(ftpClient.getStatus(remoteFile)) && !isOver)//�Ƿ񸲸��ļ�
                return;

            try (OutputStream os = ftpClient.putFileStream(remoteFile); InputStream is = fileStream) {
                byte[] bytes = new byte[BUFF_SIZE];// ����һ��������
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
     * �ļ��ϴ�
     * @param ftpClient ftp�ͻ��˾��
     * @param localFile �����ļ����ļ���
     * @param isOver �Ƿ񸲸Ǵ����ļ�
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
     * �ļ�д�뵽ftp������
     * @param ftpClient ftp�ͻ��˾��
     * @param localFile �����ļ�
     * @param isOver �Ƿ񸲸Ǵ����ļ�
     * @throws RuntimeException
     */
    @SuppressWarnings("restriction")
    private static void streamWrite(FtpClient ftpClient, File localFile, boolean isOver) throws RuntimeException{

    	
    
    	try{
            if(!"".equals(ftpClient.getStatus(localFile.getName())) && !isOver)//�Ƿ񸲸��ļ�
                return;
            try (OutputStream os = ftpClient.putFileStream(localFile.getName());
                    InputStream is = new FileInputStream(localFile)) {
                byte[] bytes = new byte[BUFF_SIZE];// ����һ��������
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
    * ��ftp�����ļ������� 
    *  
    * @param filename �������ϵ��ļ��� 
    * @param newfilename �������ɵ��ļ��� 
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
     * ȡ������ڵ�ǰ����Ŀ¼��ĳ��Ŀ¼�������ļ��б� 
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
     * ���ӵ�ftp������
     * @param ip ftp��������ַ
     * @param port ftp�������˿�
     * @param user ��½ftp�������û�
     * @param password ��½ftp�������û�����
     * @param remotePath ftp�������ļ�·��
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
            if (remotePath!=null && remotePath.length() != 0) { // ��Զ��ϵͳ�ϵ�Ŀ¼�л�������path��ָ����Ŀ¼
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
     * ���ӵ�ftp������
     * @param connectionString �����ַ���
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
     * �Ͽ���ftp���������� 
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
            System.out.println("�Ѵӷ������Ͽ�");  
            return true;  
        } catch (IOException e) {  
            e.printStackTrace();  
            return false;  
        }  
    }  

    public static int uploadfile(String address,String port,String user,String password,String sfile,String dir)
    {
    	//���ϴ��ļ����˸���װ
    	//�����ֱ��ʾ����ַ���˿ڣ��û��������룬Դ�ļ������أ������·��
    	int port1=Integer.parseInt(port);
    	FtpClient ftpClient=connect(address, port1, user, password, dir);
    	File up=new File(sfile);
    	 try {
    		 upload(ftpClient,up,true);
    	 }
    	 catch(Exception ex){
    		 System.out.println("�ϴ�ʧ��");          
         }
    	 return 0;
    }
    
    public static int downloadfile(String address,String port,String user,String password,String sfile,String dir,String dfile)
    {
    	
    	//�������ļ����˸���װ
    	//�����ֱ��ʾ����ַ���˿ڣ��û��������룬Դ�ļ����������������·����Ŀ���ļ������أ�
    	int port1=Integer.parseInt(port);
    	 FtpClient ftpClient=connect(address, port1, user, password, dir);
    	 try {
    		 downloadFile(ftpClient, sfile, dfile);
    	 }
    	 catch(Exception ex){
    		 System.out.println("����ʧ��");      
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