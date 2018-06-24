package iframe;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import util.MyDocument;

import util.Item;
import core.Dao;

import sun.net.ftp.FtpClient;
import core.FtpUtil;
/**
 * 名称：图书添加窗体
 * 
 */
public class ftpIFrame extends JInternalFrame {
	private JTextField address;
	private JTextField port;
	private JTextField   user;
	//private JFormattedTextField pubDate;
	private JPasswordField password;
	private JTextField sourcefile;
	private JTextField dir;
	private JTextField destinationfile;
	
	private JTextField selectfile;
	//private JComboBox bookType;
	private JButton buttonadd;
	private JButton browse;
	private JButton buttonclose;
	//DefaultComboBoxModel bookTypeModel;
	
	//Map map=new HashMap();
	public ftpIFrame() {
		super();
		final BorderLayout borderLayout = new BorderLayout();
		getContentPane().setLayout(borderLayout);
		setIconifiable(true);							// 设置窗体可最小化－－－必须
		setMaximizable(true);
		setClosable(true);								// 设置窗体可关闭－－－必须
		setTitle("ftp上传下载");						// 设置窗体标题－－－必须
		setBounds(20, 20, 800, 350);					// 设置窗体位置和大小－－－必须
		setResizable(true);
		final JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 10, 5, 10));
		final GridLayout gridLayout = new GridLayout(4, 2);
		gridLayout.setVgap(5);
		gridLayout.setHgap(5);
		panel.setLayout(gridLayout);
		getContentPane().add(panel);

		final JLabel label_0 = new JLabel();
		label_0.setText("地址：");
		panel.add(label_0);
		
		address = new JTextField(20);
		address.setDocument(new MyDocument(20)); //设置标题长度最大输入值为13
		address.setColumns(20);
//		address.addKeyListener(new userkeyListener());
//		address.addFocusListener(new userFocusListener());
		panel.add(address);
		
		
		
		
		final JLabel label_2 = new JLabel();
		label_2.setText("端口：");
		panel.add(label_2);

		port = new JTextField("请输入端口",20);
		port.setDocument(new MyDocument(20)); //设置城市长度最大输入值为20
		
		port.setColumns(10);
//		port.addKeyListener(new portkeyListener());
//		port.addFocusListener(new portFocusListener());
		panel.add(port);

		final JLabel label = new JLabel();
		//label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setText("用户名：");
		panel.add(label);

		user = new JTextField(20);
		user.setDocument(new MyDocument(20)); //设置部门长度最大输入值为20
		user.setColumns(20);
//		user.addKeyListener(new userkeyListener());
//		user.addFocusListener(new userFocusListener());
		panel.add(user);
		
		

		final JLabel label_1 = new JLabel();
		label_1.setText("密码：");
		panel.add(label_1);
		
		password = new JPasswordField(30);
		password.setDocument(new MyDocument(30)); //设置方向长度最大输入值为13
		password.setColumns(30);
		password.setEchoChar('*');
		
//		password.addKeyListener(new userkeyListener());
//		password.addFocusListener(new userFocusListener());
		panel.add(password);
		
		
		
		final JLabel label_3 = new JLabel();
		label_3.setText("源文件：");
		panel.add(label_3);
		
		sourcefile = new JTextField(50);
		sourcefile.setDocument(new MyDocument(50)); //设置书号文本框最大输入值为13
		sourcefile.setColumns(50);
//		sourcefile.addKeyListener(new userkeyListener());
//		sourcefile.addFocusListener(new userFocusListener());
		panel.add(sourcefile);
		
		final JLabel label_5 = new JLabel();
		label_5.setText("相对路径：");
		panel.add(label_5);
		
		dir = new JTextField(50);
		dir.setDocument(new MyDocument(50)); //设置书号文本框最大输入值为13
		dir.setColumns(50);
//		dir.addKeyListener(new userkeyListener());
//		dir.addFocusListener(new userFocusListener());
		panel.add(dir);

		final JLabel label_4 = new JLabel();
		label_4.setText("目的文件：");
		panel.add(label_4);
		
		destinationfile = new JTextField(30);
		destinationfile.setDocument(new MyDocument(30)); //设置标题长度最大输入值为13
		destinationfile.setColumns(30);
//		destinationfile.addKeyListener(new userkeyListener());
//		destinationfile.addFocusListener(new userFocusListener());
		panel.add(destinationfile);
	
		
		
//		final JLabel label_7=new JLabel();
//		label_7.setText("打开文件:");
//		panel.add(label_7);
//		
////		selectfile=new JTextField(70);
////		selectfile.setColumns(70);
////		panel.add(selectfile);
//		browse=new JButton("浏览");
//		browse.addActionListener( new browseListener());
//		
//		panel.add(browse);
		


		final JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, false));
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setVgap(2);
		flowLayout.setHgap(30);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_1.setLayout(flowLayout);

		buttonadd= new JButton();
		buttonadd.addActionListener(new uploadActionListener());
		buttonadd.setText("上传");
		panel_1.add(buttonadd);

		buttonclose = new JButton();
		buttonclose.addActionListener(new downloadActionListener());
		buttonclose.setText("下载");
		panel_1.add(buttonclose);
		
		buttonclose = new JButton();
		buttonclose.addActionListener(new CloseActionListener());
		buttonclose.setText("关闭");
		panel_1.add(buttonclose);

//		final JLabel label_8 = new JLabel();
//		ImageIcon bookAddIcon=CreatecdIcon.add("newBookorderImg.jpg");
//		label_5.setIcon(bookAddIcon);
//		label_5.setPreferredSize(new Dimension(400, 80));
//		label_5.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, false));
//		getContentPane().add(label_5, BorderLayout.NORTH);
//		label_5.setText("新书定购(LOGO图片)");
		
		setVisible(true);											// 显示窗体可关闭－－－必须在添加所有控件之后执行该语句
	}

	
//	class userFocusListener extends FocusAdapter {
//		public void focusLost(FocusEvent e){
//			if(!Dao.selectBookInfo(user.getText().trim()).isEmpty()){
//				JOptionPane.showMessageDialog(null, "添加书号重复！");
//				return;
//			}
//		}
//	}
	
	
	class browseListener  implements ActionListener {
		
		
		public void actionPerformed(ActionEvent e) {
		   
		      JFileChooser fc = new JFileChooser();
		      fc.showDialog(new JLabel(), "选择");  
		      fc.setDialogTitle("请选择待文件...");
		      File file=fc.getSelectedFile();
		      String filepath=file.getAbsolutePath();
		      String filename=file.getName();
		    
		     
		     System.out.println(filepath+filename);
		  
	}
	}
	
	class userkeyListener extends KeyAdapter {
		public void keyPressed(final KeyEvent e) {
			if (e.getKeyCode() == 13){
				buttonadd.doClick();
			}
		
		}
	}
	
//	class portFocusListener extends FocusAdapter {
//		public void focusLost(FocusEvent e){
//			if(!Dao.selectBookInfo(port.getText().trim()).isEmpty()){
//				JOptionPane.showMessageDialog(null, "添加书号重复！");
//				return;
//			}
//		}
//	}
	class portkeyListener extends KeyAdapter {
		public void keyPressed(final KeyEvent e) {
			if (e.getKeyCode() == 13){
				buttonadd.doClick();
			}
		
		}
	}
	class CloseActionListener implements ActionListener {			// 添加关闭按钮的事件监听器
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
	class downloadActionListener implements ActionListener {		// 添加按钮的单击事件监听器
		public void actionPerformed(final ActionEvent e) {
			// 订书业务
			if(address.getText().length()==0){
				JOptionPane.showMessageDialog(null, "地址不可以为空");
				return;
			}

			if(port.getText().length()==0){
				JOptionPane.showMessageDialog(null, "端口不可以为空");
				return;
			}
//			if(port.getText().length()!=13){
//				JOptionPane.showMessageDialog(null, "书号文本框输入位数为13位");
//				return;
//			}
			if(user.getText().length()==0){
				JOptionPane.showMessageDialog(null, "用户名不可以为空");
				return;
			}
			if(password.getText().length()==0){
				JOptionPane.showMessageDialog(null, "用户名不可以为空");
				return;
			}
			if(sourcefile.getText().length()==0){
				JOptionPane.showMessageDialog(null, "源文件不可以为空");
				return;
			}
			
			if(dir.getText().length()==0){
				JOptionPane.showMessageDialog(null, "源文件不可以为空");
				return;
			}
			if(destinationfile.getText().length()==0){
				JOptionPane.showMessageDialog(null, "目的文件不可以为空");
				return;
			}
			
			
			String ct=port.getText().trim();
			

			
			String add=address.getText().trim();
//			int id=Integer.parseInt(uid);
			
			String ur1=user.getText().trim();

			String pw1=password.getText().trim();
			String tt=destinationfile.getText().trim();
			String yr=sourcefile.getText().trim();
			
			String dir2=dir.getText().trim();
			FtpUtil ftp=new FtpUtil();
			
//			int i=ftp.uploadfile(add,ct,dt,dir,yr,tt);
			int i=ftp.downloadfile(add,ct,ur1,pw1,yr,dir2,tt);
//			FtpClient ftpClient=connect("127.0.0.1", 21, "ftp", "ftp", "/policy");

//			int i=Dao.Insertpolicy(id,ct,dt, yer, tt, kw, abs, pl,dir);
			
			
				
			if(i==0){
			
				JOptionPane.showMessageDialog(null, "下载成功");
//				doDefaultCloseAction();
			}
		}
	}
	
	class uploadActionListener implements ActionListener {		// 添加按钮的单击事件监听器
		public void actionPerformed(final ActionEvent e) {
			// 订书业务
			if(address.getText().length()==0){
				JOptionPane.showMessageDialog(null, "地址不可以为空");
				return;
			}

			if(port.getText().length()==0){
				JOptionPane.showMessageDialog(null, "端口不可以为空");
				return;
			}
//			if(port.getText().length()!=13){
//				JOptionPane.showMessageDialog(null, "书号文本框输入位数为13位");
//				return;
//			}
			if(user.getText().length()==0){
				JOptionPane.showMessageDialog(null, "用户名不可以为空");
				return;
			}
			if(password.getText().length()==0){
				JOptionPane.showMessageDialog(null, "密码不可以为空");
				return;
			}
			if(sourcefile.getText().length()==0){
				JOptionPane.showMessageDialog(null, "源文件不可以为空");
				return;
			}
			if(dir.getText().length()==0){
				JOptionPane.showMessageDialog(null, "相当路径不可以为空");
				return;
			}
			
			
			String ct=port.getText().trim();
			

			
			String add=address.getText().trim();
//			int id=Integer.parseInt(uid);
			
			String dt=user.getText().trim();

			String ur1=user.getText().trim();

			String pw1=password.getText().trim();
//			String tt=destinationfile.getText().trim();
			String yr=sourcefile.getText().trim();
			
			String dir2=dir.getText().trim();
			FtpUtil ftp=new FtpUtil();
			
			int i=ftp.uploadfile(add,ct,ur1,pw1,yr,dir2);
			
//			FtpClient ftpClient=connect("127.0.0.1", 21, "ftp", "ftp", "/policy");

//			int i=Dao.Insertpolicy(id,ct,dt, yer, tt, kw, abs, pl,dir);
			
			
				
			if(i==0){
			
				JOptionPane.showMessageDialog(null, "上传成功");
//				doDefaultCloseAction();
			}
		}
	}
	class NumberListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			String numStr="0123456789."+(char)8;
			if(numStr.indexOf(e.getKeyChar())<0){
				e.consume();
			}
		}
	}

}
