package mainframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
//import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

import iframe.BookLoginIFrame;
import util.CreatecdIcon;;

/**
 * 主窗体
 * 
 */
public class PolicyManagement extends JFrame {
	private static final JDesktopPane DESKTOP_PANE = new JDesktopPane();
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager
					.getSystemLookAndFeelClassName());
			new BookLoginIFrame();//登录窗口
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public static void addIFame(JInternalFrame iframe) { // 添加子窗体的方法
		DESKTOP_PANE.add(iframe);
	}
	public PolicyManagement() {
		super();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setLocationByPlatform(true);
		setSize(1024, 800);
		setTitle("政策信息管理系统");
		JMenuBar menuBar = createMenu(); // 调用创建菜单栏的方法
		setJMenuBar(menuBar);
		JToolBar toolBar = createToolBar(); // 调用创建工具栏的方法
		getContentPane().add(toolBar, BorderLayout.NORTH);
		final JLabel label = new JLabel();
		label.setBounds(0, 0, 0, 0);
		label.setIcon(null); // 窗体背景

		DESKTOP_PANE.addComponentListener(new ComponentAdapter() {
			public void componentResized(final ComponentEvent e) {
				Dimension size = e.getComponent().getSize();
				label.setSize(e.getComponent().getSize());
				label.setText("<html><img width=" + size.width + " height="
						+ size.height + " src='"
						+ this.getClass().getResource("/taihu12.jpg")
						+ "'></html>");
			}
		});
		DESKTOP_PANE.add(label,new Integer(Integer.MIN_VALUE));
		getContentPane().add(DESKTOP_PANE);
	}
	/**
	 * 创建工具栏
	 * 
	 * @return JToolBar
	 */
	private JToolBar createToolBar() { // 创建工具栏的方法
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBorder(new BevelBorder(BevelBorder.RAISED));
		

		
		JButton policyAddButton=new JButton(MenuActions.Policy_ADD);
		//ImageIcon icon=CreatecdIcon.add("bookAdd.bmp");//创建图标方法
		ImageIcon picon=new ImageIcon(PolicyManagement.class.getResource("/bookAddtb.jpg"));//添加菜单栏图标	
		policyAddButton.setIcon(picon);
		policyAddButton.setHideActionText(true);
		toolBar.add(policyAddButton);
		//bookAddButton.setToolTipText("fjdkjfk");//图片上提示字
//		toolBar.add(bookAddButton);
		//toolBar.add(MenuActions.BOOK_MODIFY);
		
		JButton policySearchButton=new JButton(MenuActions.Policy_SEARCH);
		//ImageIcon icon=CreatecdIcon.add("bookAdd.bmp");//创建图标方法
		ImageIcon picon1=new ImageIcon(PolicyManagement.class.getResource("/bookAddtb.jpg"));//添加菜单栏图标	
		policySearchButton.setIcon(picon1);
		policySearchButton.setHideActionText(true);
		toolBar.add(policySearchButton);
		
		JButton policyModifyButton=new JButton(MenuActions.Policy_MODIFY);
		//ImageIcon icon=CreatecdIcon.add("bookAdd.bmp");//创建图标方法
		ImageIcon picon2=new ImageIcon(PolicyManagement.class.getResource("/bookAddtb.jpg"));//添加菜单栏图标	
		policyModifyButton.setIcon(picon2);
		policyModifyButton.setHideActionText(true);
		toolBar.add(policyModifyButton);
		
		JButton ExitButton=new JButton(MenuActions.EXIT);
		ImageIcon Exiticon=CreatecdIcon.add("exittb.jpg");//创建图标方法
		ExitButton.setIcon(Exiticon);
		ExitButton.setHideActionText(true);
		toolBar.add(ExitButton);
		return toolBar;
	}
	/**
	 * 创建菜单栏
	 */
	private JMenuBar createMenu() { // 创建菜单栏的方法
		JMenuBar menuBar = new JMenuBar();

//		JMenu bookOrderMenu = new JMenu(); // 初始化新书订购管理菜单
//		bookOrderMenu.setIcon(CreatecdIcon.add("xsdgcd.jpg"));
//		bookOrderMenu.add(MenuActions.NEWBOOK_ORDER);
//		bookOrderMenu.add(MenuActions.NEWBOOK_CHECK_ACCEPT);

		
		Font font=new Font("宋体",Font.PLAIN,15);
		
//		JMenu baseMenu=new JMenu();
//		baseMenu.setText("文件");
//		baseMenu.setFont(font);
//		baseMenu.add(MenuActions.EXIT);
		 
		JMenu policyMenu = new JMenu();// 初始化基础数据维护菜单
		policyMenu.setText("政策信息");
		policyMenu.setFont(font);
//		policyMenu.setIcon(CreatecdIcon.add("jcsjcd.jpg"));
		{

			

			policyMenu.add(MenuActions.Policy_ADD);
			policyMenu.add(MenuActions.Policy_MODIFY);
			policyMenu.add(MenuActions.Policy_SEARCH);
			policyMenu.add(MenuActions.attach_Crawl);
			policyMenu.add(MenuActions.Content_Query);
			policyMenu.add(MenuActions.ftp_Add);
			policyMenu.addSeparator();

		}


		JMenu sysManageMenu = new JMenu(); // 系统维护
		sysManageMenu.setText("系统维护");
		sysManageMenu.setFont(font);
//		sysManageMenu.setIcon(CreatecdIcon.add("jcwhcd.jpg"));
		
		JMenu userManageMItem = new JMenu("用户管理"); // 用户管理
		userManageMItem.add(MenuActions.USER_ADD);
		userManageMItem.add(MenuActions.USER_MODIFY);
		
		sysManageMenu.add(MenuActions.MODIFY_PASSWORD);
		sysManageMenu.add(userManageMItem);
		sysManageMenu.add(MenuActions.EXIT);

//		menuBar.add(baseMenu);  // 文件菜单
		menuBar.add(policyMenu); // 政策信息菜单

		menuBar.add(sysManageMenu); // 系统维护菜单
		return menuBar;
	}
}
