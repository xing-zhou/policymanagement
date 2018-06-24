package mainframe;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JInternalFrame;

import iframe.ContentQueryIFrame;
import iframe.GengGaiMiMa;
import iframe.PolicyAddIframe;

import iframe.PolicySearchIFrame;

import iframe.UserAddIFrame;
import iframe.UserModiAndDelIFrame;
import iframe.crawlerIFrame;
import iframe.ftpIFrame;

import iframe.policymodi;

import util.*;
/**
 * 菜单和按钮的Action对象
 * 
 */
public class MenuActions {
	private static Map<String, JInternalFrame> frames; // 子窗体集合

	public static PasswordModiAction MODIFY_PASSWORD; // 修改密码窗体动作
	public static UserModiAction USER_MODIFY; // 修改用户资料窗体动作
	public static UserAddAction USER_ADD; // 用户添加窗体动作

	public static PolicySearchAction Policy_SEARCH; // 图书搜索窗体动作
	public static ftpAddAction ftp_Add; // 图书搜索窗体动作
	public static attachCrawlAction attach_Crawl ;//政策信息爬取动作
	
	public static PolicyModiAction Policy_MODIFY; // 图书信息修改窗体动作
	public static ContentQueryAction Content_Query;//政策信息全文查询

	public static PolicyAddAction Policy_ADD; // 政策信息添加窗体动作
	public static ExitAction EXIT; // 系统退出动作

	static {
		frames = new HashMap<String, JInternalFrame>();

		MODIFY_PASSWORD = new PasswordModiAction();
		USER_MODIFY = new UserModiAction();
		USER_ADD = new UserAddAction();
		
		Policy_SEARCH = new PolicySearchAction();
		ftp_Add=new ftpAddAction();
		attach_Crawl=new attachCrawlAction();
		
		Policy_MODIFY = new PolicyModiAction();
		
		Policy_ADD = new PolicyAddAction();
		Content_Query=new ContentQueryAction();
		EXIT = new ExitAction();
	}

	private static class PasswordModiAction extends AbstractAction {
		PasswordModiAction() {
			putValue(Action.NAME,"更改口令");
			putValue(Action.LONG_DESCRIPTION, "修改当前用户密码");
			putValue(Action.SHORT_DESCRIPTION, "更换口令");//在“更改口令”提示中显示的文字
			//putValue(Action.SMALL_ICON,CreatecdIcon.add("bookAddtb.jpg"));
			//将图标存储到动作对象中
			//setEnabled(false);//使动作禁用
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("更改密码")||frames.get("更改密码").isClosed()) {
				GengGaiMiMa iframe=new GengGaiMiMa();
				frames.put("更改密码", iframe);
				PolicyManagement.addIFame(frames.get("更改密码"));
			}
		}
	}

	private static class UserModiAction extends AbstractAction {
		UserModiAction() {
			super("用户修改与删除", null);
			putValue(Action.LONG_DESCRIPTION, "修改和删除用户信息");
			putValue(Action.SHORT_DESCRIPTION, "用户修改与删除");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("用户信息修改与删除")||frames.get("用户信息修改与删除").isClosed()) {
				UserModiAndDelIFrame iframe=new UserModiAndDelIFrame();
				frames.put("用户信息修改与删除", iframe);
				PolicyManagement.addIFame(frames.get("用户信息修改与删除"));
			}
		}
	}

	private static class UserAddAction extends AbstractAction {
		UserAddAction() {
			super("用户添加", null);
			putValue(Action.LONG_DESCRIPTION, "添加新的用户");
			putValue(Action.SHORT_DESCRIPTION, "用户添加");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("用户信息添加")||frames.get("用户信息添加").isClosed()) {
				UserAddIFrame iframe=new UserAddIFrame();
				frames.put("用户信息添加", iframe);
				PolicyManagement.addIFame(frames.get("用户信息添加"));
			}
			
		}
	}



	
	private static class PolicySearchAction extends AbstractAction {
		PolicySearchAction() {
			super("政策信息查询", null);
			putValue(Action.LONG_DESCRIPTION, "搜索入库的政策信息");
			putValue(Action.SHORT_DESCRIPTION, "政策信息搜索");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("政策查询")||frames.get("政策查询").isClosed()) {
				PolicySearchIFrame iframe=new PolicySearchIFrame();
				frames.put("政策查询", iframe);
				PolicyManagement.addIFame(frames.get("政策查询"));
			}
		}
	}
	
	private static class ContentQueryAction extends AbstractAction {
		ContentQueryAction() {
			super("政策全文查询", null);
			putValue(Action.LONG_DESCRIPTION, "搜索入库的政策信息");
			putValue(Action.SHORT_DESCRIPTION, "政策信息搜索");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("政策查询")||frames.get("政策查询").isClosed()) {
				ContentQueryIFrame iframe=new ContentQueryIFrame();
				frames.put("政策查询", iframe);
				PolicyManagement.addIFame(frames.get("政策查询"));
			}
		}
	}
	


	







	private static class PolicyModiAction extends AbstractAction {
		PolicyModiAction() {
			super("政策信息修改", null);
			putValue(Action.LONG_DESCRIPTION, "修改和删除政策信息");
			putValue(Action.SHORT_DESCRIPTION, "政策信息修改");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("政策信息修改")||frames.get("政策信息修改").isClosed()) {
				policymodi iframe=new policymodi();
//				PolicyModiAndDelIFrame iframe=new PolicyModiAndDelIFrame();
				frames.put("政策信息修改", iframe);
				PolicyManagement.addIFame(frames.get("政策信息修改"));
			}
		}
	}

	
	
	private static class PolicyAddAction extends AbstractAction {				// 政策信息添加－－－已经实现，请参照
		PolicyAddAction() {

			super("政策信息添加", null);
			//super();
			putValue(Action.LONG_DESCRIPTION, "为图书馆添加新的政策信息");
			putValue(Action.SHORT_DESCRIPTION, "政策信息添加");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("政策信息添加")||frames.get("政策信息添加").isClosed()) {
				PolicyAddIframe iframe = new PolicyAddIframe();
				frames.put("政策信息添加", iframe);
				PolicyManagement.addIFame(frames.get("政策信息添加"));
			}
		}
	}
	
	
	private static class ftpAddAction extends AbstractAction {				// 政策信息添加－－－已经实现，请参照
		ftpAddAction() {

			super("ftp上传下载", null);
			//super();
			putValue(Action.LONG_DESCRIPTION, "利用ftp管理文件");
			putValue(Action.SHORT_DESCRIPTION, "ftp上传下载");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("政策信息添加")||frames.get("政策信息添加").isClosed()) {
				ftpIFrame iframe = new ftpIFrame();
//				PolicyAddIframe iframe = new PolicyAddIframe();
				frames.put("政策信息添加", iframe);
				PolicyManagement.addIFame(frames.get("政策信息添加"));
			}
		}
	}
	
	private static class attachCrawlAction extends AbstractAction {				// 政策信息添加－－－已经实现，请参照
		attachCrawlAction() {

			super("政策信息爬取", null);
			//super();
			putValue(Action.LONG_DESCRIPTION, "爬取政策信息");
			putValue(Action.SHORT_DESCRIPTION, "政策信息爬取");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("政策信息爬取")||frames.get("政策信息爬取").isClosed()) {
				crawlerIFrame iframe = new crawlerIFrame();
//				PolicyAddIframe iframe = new PolicyAddIframe();
				frames.put("政策信息爬取", iframe);
				PolicyManagement.addIFame(frames.get("政策信息爬取"));
			}
		}
	}
	
	private static class ExitAction extends AbstractAction { // 退出系统动作
		public ExitAction() {
			super("退出系统", null);
			putValue(Action.LONG_DESCRIPTION, "退出图书馆管理系统");
			putValue(Action.SHORT_DESCRIPTION, "退出系统");
		}
		public void actionPerformed(final ActionEvent e) {
			System.exit(0);
		}
	}

	private MenuActions() {
		super();
	}

}
