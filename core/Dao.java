package core;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.CityInfo;
import model.Operater;
import model.PolicyInfo;
import model.ProvinceInfo;
import model.Reader;
import model.user;


public class Dao {
	protected static String dbClassName = "com.mysql.jdbc.Driver";
	protected static String dbUrl = "jdbc:mysql://localhost:3306/db_library?useUnicode=true&characterEncoding=utf8";
	protected static String dbUser = "my";
	protected static String dbPwd = "root";
	protected static String second = null;
	private static Connection conn = null;
	
	private Dao() {
		try {
			if (conn == null) {
				Class.forName(dbClassName).newInstance();
				conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
			}
			else
				return;
		} catch (Exception ee) {
			ee.printStackTrace();
		}

	}
	private static ResultSet executeQuery(String sql) {
		try {
			if(conn==null)
			new Dao();
			return conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
		}
	}
	private static int executeUpdate(String sql) {
		
		try {
			if(conn==null)
				new Dao();
			return conn.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			//if(e.getMessage().equals("[Microsoft][SQLServer 2000 Driver for JDBC][SQLServer]DELETE 语句与 COLUMN REFERENCE 约束 'FK_TB_BORRO_REFERENCE_TB_BOOKI' 冲突。该冲突发生于数据库 'db_library'，表 'tb_borrow', column 'bookISBN'。"))
				
			return -1;
		} finally {
		}
	}
	
	public static void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			conn = null;
		}
	}
	/*
	 * 管理员登录方法
	 */
	public static Operater check(String name, String password) {
		int i = 0;
		Operater operater=new Operater();
		String sql = "select *  from tb_operator where name='" + name
				+ "' and password='" + password + "'and admin=1";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				String names = rs.getString(1);
				operater.setId(rs.getString("id"));
				operater.setName(rs.getString("name"));
				operater.setGrade(rs.getString("admin"));
				operater.setPassword(rs.getString("password"));
				if (names != null) {
					i = 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return operater;
		
	}

	

	/*
	 * 图书信息表相关操作
	 */
	/*
	 * 插入图书信息方法
	 */
	public static int Insertpolicy(int id,String city,String department,int year,String title,String keywords,String abstrac,String place,String direction){
		int i=0;
		try{
			String sql="insert into policyInfo(uid,city,department,year,title,keywords,abstrac,place,direction) values('"+id+"','"+city+"','"+department+"','"+year+"','"+title+"','"+keywords+"','"+abstrac+"','"+place+"',"+"'"+direction+"'"+");";
			System.out.println(sql);
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		Dao.close();
		return i;
	}
	/*
	 * 查询图书相关信息
	 * 
	 */

	
	
	
	public static List selectPolicyInfo() {
		List list=new ArrayList();
		String sql = "select *  from policyInfo ";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				PolicyInfo policyinfo=new PolicyInfo();
//				bookinfo.setISBN(rs.getString("ISBN"));
//				bookinfo.setTypeid(rs.getString("typeid"));
//				bookinfo.setBookname(rs.getString("bookname"));
//				bookinfo.setWriter(rs.getString("writer"));
//				bookinfo.setTranslator(rs.getString("translator"));
//				bookinfo.setPublisher(rs.getString("publisher"));
//				bookinfo.setDate(rs.getDate("date"));
//				bookinfo.setPrice(rs.getDouble("price"));
				policyinfo.setunid(Integer.parseInt(rs.getString("uid")));
				policyinfo.setcity(rs.getString("city"));
				policyinfo.setdepartment(rs.getString("department"));
				policyinfo.settitle(rs.getString("title"));
				policyinfo.setyear(Integer.parseInt(rs.getString("year")));
				policyinfo.setkeywords(rs.getString("keywords"));
				policyinfo.setabstract(rs.getString("abstrac"));
				policyinfo.setplace(rs.getString("place"));
				policyinfo.setdirection(rs.getString("direction"));
				list.add(policyinfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
//输出所有的省份
	public static List selectProvinceInfo()
	{
		List list=new ArrayList();
		String sql="select prov_id, prov_name,prov_py from province";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				ProvinceInfo provinceinfo=new ProvinceInfo();
				provinceinfo.setId(Integer.parseInt(rs.getString("prov_id")));
				provinceinfo.setName(rs.getString("prov_name"));
				provinceinfo.setPy(rs.getString("prov_py"));
				list.add(provinceinfo);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
//根据省份名查询省份id	
	public static int selectprovinceid(String provincename)
	{
		int id=0;
		String sql="select prov_id from province where prov_name='"+provincename+"';";
		
	
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				id=Integer.parseInt(rs.getString("prov_id"));				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		
		return id;
		
	}
//根据省份id查询所有城市	
	public static List selectcitywithprovinceid(int provinceid)
	{
		List list=new ArrayList();
		String city;
		String sql="select city_name,city_py from city where prov_id="+provinceid+"";
//		System.out.println(sql);
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				CityInfo cityinfo=new CityInfo();
				cityinfo.setName(rs.getString("city_name"));
				cityinfo.setPy(rs.getString("city_py"));
				list.add(cityinfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		
		return list;
		
	}
	
	
	/*
	 * 修改政策信息方法
	 */
	public static int Updatepolicy(int unid,String city,String department,int year,String title,String keywords,String abstrac,String place,String direction){
		int i=0;
		try{
			
			String sql="update policyInfo set city='"+city+"',department='"+department+"',year='"+year+"',title='"+title+"',keywords='"+keywords+"',abstrac='"+abstrac+"',place='"+place+"',direction='"+direction+"',uid="+unid+" where uid="+unid;
			System.out.println(sql);
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}

	/*
	 * 对读者信息表执行的相关操作
	 */
	public static int InsertReader(String name,String sex,String age,String identityCard,Date date,String maxNum,String tel,Double keepMoney,String zj,String zy,Date bztime,String ISBN){
		int i=0;
		try{
			String sql="insert into tb_reader(name,sex,age,identityCard,date,maxNum,tel,keepMoney,zj,zy,bztime,ISBN) values('"+name+"','"+sex+"','"+age+"','"+identityCard+"','"+date+"','"+maxNum+"','"+tel+"',"+keepMoney+",'"+zj+"','"+zy+"','"+bztime+"','"+ISBN+"')";
			System.out.println(sql);
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	public static List selectReader() {
		List list=new ArrayList();
		String sql = "select *  from tb_reader";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Reader reader=new Reader();
				//reader.setId(rs.getString("id"));
				reader.setName(rs.getString("name"));
				reader.setSex(rs.getString("sex"));
				reader.setAge(rs.getString("age"));
				reader.setIdentityCard(rs.getString("identityCard"));
				reader.setDate(rs.getDate("date"));
				reader.setMaxNum(rs.getString("maxNum"));
				reader.setTel(rs.getString("tel"));
				reader.setKeepMoney(rs.getDouble("keepMoney"));
				reader.setZj(rs.getInt("zj"));
				reader.setZy(rs.getString("zy"));
				reader.setISBN(rs.getString("ISBN"));
				reader.setBztime(rs.getDate("bztime"));
				list.add(reader);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	public static List selectReader(String readerISBN) {
		List list=new ArrayList();
		String sql = "select *  from tb_reader where ISBN='"+readerISBN+"'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Reader reader=new Reader();
				reader.setName(rs.getString("name"));
				reader.setSex(rs.getString("sex"));
				reader.setAge(rs.getString("age"));
				reader.setIdentityCard(rs.getString("identityCard"));
				reader.setDate(rs.getDate("date"));
				reader.setMaxNum(rs.getString("maxNum"));
				reader.setTel(rs.getString("tel"));
				reader.setKeepMoney(rs.getDouble("keepMoney"));
				reader.setZj(rs.getInt("zj"));
				reader.setZy(rs.getString("zy"));
				reader.setISBN(rs.getString("ISBN"));
				reader.setBztime(rs.getDate("bztime"));
				list.add(reader);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	public static int UpdateReader(String id,String name,String sex,String age,String identityCard,Date date,String maxNum,String tel,Double keepMoney,String zj,String zy,Date bztime,String ISBN){
		int i=0;
		try{
			String sql="update tb_reader set name='"+name+"',sex='"+sex+"',age='"+age+"',identityCard='"+identityCard+"',date='"+date+"',maxNum='"+maxNum+"',tel='"+tel+"',keepMoney="+keepMoney+",zj='"+zj+"',zy='"+zy+"',bztime='"+bztime+"'where ISBN='"+ISBN+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	public static int DelReader(String ISBN){
		int i=0;
		try{
			String sql="delete from tb_reader where ISBN='"+ISBN+"'";
			//System.out.println(sql);
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
/*
 * 对订购信息表操作
 */
	public static int InsertBookOrder(String ISBN,Date date,String number,String operator,String checkAndAccept,Double zk){
		int i=0;
		try{
			String sql="insert into tb_order(ISBN,date,number,operator,checkAndAccept,zk) values('"+ISBN+"','"+date+"','"+number+"','"+operator+"',"+checkAndAccept+",'"+zk+"')";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
		
	}
	
	
	public static int UpdateCheckBookOrder(String ISBN){
		int i=0;
		try{
			String sql="update tb_order set checkAndAccept=0 where ISBN='"+ISBN+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
		
	}
	/*
	 * 对借阅表进行操作
	 */
	public static int InsertBookBorrow(String bookISBN,String readerISBN,String operatorId,Timestamp borrowDate,Timestamp backDate){
		int i=0;
		try{
			String sql="insert into tb_borrow(bookISBN,readerISBN,operatorId,borrowDate,backDate)values('"+bookISBN+"','"+readerISBN+"','"+operatorId+"','"+borrowDate+"','"+backDate+"')";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
		
	}
	
	
	public static int UpdateBookBack(String bookISBN,String readerISBN,int id){//归还图书操作
		int i=0;
		try{
			String sql="update tb_borrow set isback=0 where bookISBN='"+bookISBN+"'and readerISBN='"+readerISBN+"' and id="+id+"";
			System.out.println(sql);
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
		
	}
	
	
	
	public static List selectpolicyserch() {
		List list=new ArrayList();
		String sql = "select *  from policyInfo";
		ResultSet s = Dao.executeQuery(sql);
		try {
			while (s.next()) {
//				PolicyInfo bookinfo=new PolicyInfo();
//				bookinfo.setISBN(s.getString("ISBN"));
//				bookinfo.setTypeid(s.getString("typeId"));
//				bookinfo.setBookname(s.getString("bookName"));
//				bookinfo.setWriter(s.getString("writer"));
//				bookinfo.setTranslator(s.getString("translator"));
//				bookinfo.setPublisher(s.getString("publisher"));
//				bookinfo.setDate(s.getDate("date"));
//				bookinfo.setPrice(s.getDouble("price"));
				
				PolicyInfo policyinfo=new PolicyInfo();
				policyinfo.setunid(Integer.parseInt(s.getString(1)));
				policyinfo.setcity(s.getString(2));
				policyinfo.setdepartment(s.getString(3));
				policyinfo.setyear(Integer.parseInt(s.getString(4)));
				policyinfo.settitle(s.getString(5));
				policyinfo.setkeywords(s.getString(6));
				policyinfo.setabstract(s.getString(7));
				policyinfo.setplace(s.getString(8));
				policyinfo.setdirection(s.getString(9));
				
				list.add(policyinfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	
	
	public static List selectpolicymohu(String condition,String content){
		List list=new ArrayList();
		String sql="select * from policyInfo where"+" "+condition+" like '%"+content+"%'";
//		System.out.print(sql+"\r");
		ResultSet s=Dao.executeQuery(sql);
		try {
			while(s.next()){
				PolicyInfo policyinfo=new PolicyInfo();
				policyinfo.setunid(Integer.parseInt(s.getString(1)));
				policyinfo.setcity(s.getString(2));
				policyinfo.setdepartment(s.getString(3));
				policyinfo.setyear(Integer.parseInt(s.getString(4)));
				policyinfo.settitle(s.getString(5));
				policyinfo.setkeywords(s.getString(6));
				policyinfo.setabstract(s.getString(7));
				policyinfo.setplace(s.getString(8));
				policyinfo.setdirection(s.getString(9));
//				bookinfo.setISBN(s.getString(1));
//				bookinfo.setTypeid(s.getString(2));
//				bookinfo.setBookname(s.getString(3));
//				bookinfo.setWriter(s.getString(4));
//				bookinfo.setTranslator(s.getString(5));
//				bookinfo.setPublisher(s.getString(6));
//				bookinfo.setDate(s.getDate(7));
//				bookinfo.setPrice(s.getDouble(8));
				list.add(policyinfo);
			}
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return list;
		
		
	}
	
	
	public static List selectpolicyadvance(String condition){
		List list=new ArrayList();
		String sql="select * from policyInfo where"+" "+condition+"";
		System.out.print(sql+"\r");
		ResultSet s=Dao.executeQuery(sql);
		try {
			while(s.next()){
				PolicyInfo policyinfo=new PolicyInfo();
				policyinfo.setunid(Integer.parseInt(s.getString(1)));
				policyinfo.setcity(s.getString(2));
				policyinfo.setdepartment(s.getString(3));
				policyinfo.setyear(Integer.parseInt(s.getString(4)));
				policyinfo.settitle(s.getString(5));
				policyinfo.setkeywords(s.getString(6));
				policyinfo.setabstract(s.getString(7));
				policyinfo.setplace(s.getString(8));
				policyinfo.setdirection(s.getString(9));
//				bookinfo.setISBN(s.getString(1));
//				bookinfo.setTypeid(s.getString(2));
//				bookinfo.setBookname(s.getString(3));
//				bookinfo.setWriter(s.getString(4));
//				bookinfo.setTranslator(s.getString(5));
//				bookinfo.setPublisher(s.getString(6));
//				bookinfo.setDate(s.getDate(7));
//				bookinfo.setPrice(s.getDouble(8));
				list.add(policyinfo);
			}
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return list;
		
		
	}
	
	
	public static int Insertoperator(String name,String sex,int age,String identityCard,Date workdate,String tel,String password){
		int i=0;
		try{
			String sql="insert into tb_operator(name,sex,age,identityCard,workdate,tel,password) values('"+name+"','"+sex+"',"+age+",'"+identityCard+"','"+workdate+"','"+tel+"','"+password+"')";
			System.out.println(sql);
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
	public static List selectuser() {
		List list=new ArrayList();
		String sql = "select id,name,sex,age,identityCard,workdate,tel,password  from tb_operator where admin=0";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				user user=new user();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setSex(rs.getString(3));
				user.setAge(rs.getInt(4));
				user.setIdentityCard(rs.getString(5));
				user.setWorkdate(rs.getDate(6));
				user.setTel(rs.getString(7));
				user.setPassword(rs.getString(8));
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	
	public static int Deluser(int id){
		int i=0;
		try{
			String sql="delete from tb_operator where id='"+id+"'";
			//System.out.println(sql);
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
	public static int Updateuser(int id,String name,String sex,int age,String identityCard,Date workdate,String tel,String password){
		int i=0;
		try{
			String sql="update tb_operator set name='"+name+"',sex='"+sex+"',age="+age+",identityCard='"+identityCard+"',workdate='"+workdate+"',tel='"+tel+"',password='"+password+"' where id='"+id+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
	public static int Updatepass(String password,String name){
		int i=0;
		try{
			String sql="update tb_operator set password='"+password+"' where name='"+name+"'";
			
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
}