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
 * �˵��Ͱ�ť��Action����
 * 
 */
public class MenuActions {
	private static Map<String, JInternalFrame> frames; // �Ӵ��弯��

	public static PasswordModiAction MODIFY_PASSWORD; // �޸����봰�嶯��
	public static UserModiAction USER_MODIFY; // �޸��û����ϴ��嶯��
	public static UserAddAction USER_ADD; // �û���Ӵ��嶯��

	public static PolicySearchAction Policy_SEARCH; // ͼ���������嶯��
	public static ftpAddAction ftp_Add; // ͼ���������嶯��
	public static attachCrawlAction attach_Crawl ;//������Ϣ��ȡ����
	
	public static PolicyModiAction Policy_MODIFY; // ͼ����Ϣ�޸Ĵ��嶯��
	public static ContentQueryAction Content_Query;//������Ϣȫ�Ĳ�ѯ

	public static PolicyAddAction Policy_ADD; // ������Ϣ��Ӵ��嶯��
	public static ExitAction EXIT; // ϵͳ�˳�����

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
			putValue(Action.NAME,"���Ŀ���");
			putValue(Action.LONG_DESCRIPTION, "�޸ĵ�ǰ�û�����");
			putValue(Action.SHORT_DESCRIPTION, "��������");//�ڡ����Ŀ����ʾ����ʾ������
			//putValue(Action.SMALL_ICON,CreatecdIcon.add("bookAddtb.jpg"));
			//��ͼ��洢������������
			//setEnabled(false);//ʹ��������
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("��������")||frames.get("��������").isClosed()) {
				GengGaiMiMa iframe=new GengGaiMiMa();
				frames.put("��������", iframe);
				PolicyManagement.addIFame(frames.get("��������"));
			}
		}
	}

	private static class UserModiAction extends AbstractAction {
		UserModiAction() {
			super("�û��޸���ɾ��", null);
			putValue(Action.LONG_DESCRIPTION, "�޸ĺ�ɾ���û���Ϣ");
			putValue(Action.SHORT_DESCRIPTION, "�û��޸���ɾ��");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("�û���Ϣ�޸���ɾ��")||frames.get("�û���Ϣ�޸���ɾ��").isClosed()) {
				UserModiAndDelIFrame iframe=new UserModiAndDelIFrame();
				frames.put("�û���Ϣ�޸���ɾ��", iframe);
				PolicyManagement.addIFame(frames.get("�û���Ϣ�޸���ɾ��"));
			}
		}
	}

	private static class UserAddAction extends AbstractAction {
		UserAddAction() {
			super("�û����", null);
			putValue(Action.LONG_DESCRIPTION, "����µ��û�");
			putValue(Action.SHORT_DESCRIPTION, "�û����");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("�û���Ϣ���")||frames.get("�û���Ϣ���").isClosed()) {
				UserAddIFrame iframe=new UserAddIFrame();
				frames.put("�û���Ϣ���", iframe);
				PolicyManagement.addIFame(frames.get("�û���Ϣ���"));
			}
			
		}
	}



	
	private static class PolicySearchAction extends AbstractAction {
		PolicySearchAction() {
			super("������Ϣ��ѯ", null);
			putValue(Action.LONG_DESCRIPTION, "��������������Ϣ");
			putValue(Action.SHORT_DESCRIPTION, "������Ϣ����");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("���߲�ѯ")||frames.get("���߲�ѯ").isClosed()) {
				PolicySearchIFrame iframe=new PolicySearchIFrame();
				frames.put("���߲�ѯ", iframe);
				PolicyManagement.addIFame(frames.get("���߲�ѯ"));
			}
		}
	}
	
	private static class ContentQueryAction extends AbstractAction {
		ContentQueryAction() {
			super("����ȫ�Ĳ�ѯ", null);
			putValue(Action.LONG_DESCRIPTION, "��������������Ϣ");
			putValue(Action.SHORT_DESCRIPTION, "������Ϣ����");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("���߲�ѯ")||frames.get("���߲�ѯ").isClosed()) {
				ContentQueryIFrame iframe=new ContentQueryIFrame();
				frames.put("���߲�ѯ", iframe);
				PolicyManagement.addIFame(frames.get("���߲�ѯ"));
			}
		}
	}
	


	







	private static class PolicyModiAction extends AbstractAction {
		PolicyModiAction() {
			super("������Ϣ�޸�", null);
			putValue(Action.LONG_DESCRIPTION, "�޸ĺ�ɾ��������Ϣ");
			putValue(Action.SHORT_DESCRIPTION, "������Ϣ�޸�");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("������Ϣ�޸�")||frames.get("������Ϣ�޸�").isClosed()) {
				policymodi iframe=new policymodi();
//				PolicyModiAndDelIFrame iframe=new PolicyModiAndDelIFrame();
				frames.put("������Ϣ�޸�", iframe);
				PolicyManagement.addIFame(frames.get("������Ϣ�޸�"));
			}
		}
	}

	
	
	private static class PolicyAddAction extends AbstractAction {				// ������Ϣ��ӣ������Ѿ�ʵ�֣������
		PolicyAddAction() {

			super("������Ϣ���", null);
			//super();
			putValue(Action.LONG_DESCRIPTION, "Ϊͼ�������µ�������Ϣ");
			putValue(Action.SHORT_DESCRIPTION, "������Ϣ���");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("������Ϣ���")||frames.get("������Ϣ���").isClosed()) {
				PolicyAddIframe iframe = new PolicyAddIframe();
				frames.put("������Ϣ���", iframe);
				PolicyManagement.addIFame(frames.get("������Ϣ���"));
			}
		}
	}
	
	
	private static class ftpAddAction extends AbstractAction {				// ������Ϣ��ӣ������Ѿ�ʵ�֣������
		ftpAddAction() {

			super("ftp�ϴ�����", null);
			//super();
			putValue(Action.LONG_DESCRIPTION, "����ftp�����ļ�");
			putValue(Action.SHORT_DESCRIPTION, "ftp�ϴ�����");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("������Ϣ���")||frames.get("������Ϣ���").isClosed()) {
				ftpIFrame iframe = new ftpIFrame();
//				PolicyAddIframe iframe = new PolicyAddIframe();
				frames.put("������Ϣ���", iframe);
				PolicyManagement.addIFame(frames.get("������Ϣ���"));
			}
		}
	}
	
	private static class attachCrawlAction extends AbstractAction {				// ������Ϣ��ӣ������Ѿ�ʵ�֣������
		attachCrawlAction() {

			super("������Ϣ��ȡ", null);
			//super();
			putValue(Action.LONG_DESCRIPTION, "��ȡ������Ϣ");
			putValue(Action.SHORT_DESCRIPTION, "������Ϣ��ȡ");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("������Ϣ��ȡ")||frames.get("������Ϣ��ȡ").isClosed()) {
				crawlerIFrame iframe = new crawlerIFrame();
//				PolicyAddIframe iframe = new PolicyAddIframe();
				frames.put("������Ϣ��ȡ", iframe);
				PolicyManagement.addIFame(frames.get("������Ϣ��ȡ"));
			}
		}
	}
	
	private static class ExitAction extends AbstractAction { // �˳�ϵͳ����
		public ExitAction() {
			super("�˳�ϵͳ", null);
			putValue(Action.LONG_DESCRIPTION, "�˳�ͼ��ݹ���ϵͳ");
			putValue(Action.SHORT_DESCRIPTION, "�˳�ϵͳ");
		}
		public void actionPerformed(final ActionEvent e) {
			System.exit(0);
		}
	}

	private MenuActions() {
		super();
	}

}
