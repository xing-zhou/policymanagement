package iframe;
import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;



import core.attachcrawl;
import core.contentcrawl;


import util.MyDocument;

/**
 * 名称：图书添加窗体
 * 
 */
public class crawlerIFrame extends JInternalFrame {
	private JTextField rootfolder;
	private JTextField threads;
	private JTextField   maxdepth;
	//private JFormattedTextField pubDate;
	private JTextField storagefolder;
	private JTextField webdomain;
	private JTextField keywords;
//	private JTextField dir;
//	private JTextField destinationfile;

	//private JComboBox bookType;
	private JButton buttonadd;
	private JButton buttonclose;
	//DefaultComboBoxModel bookTypeModel;
	
	//Map map=new HashMap();
	public crawlerIFrame() {
		super();
		final BorderLayout borderLayout = new BorderLayout();
		getContentPane().setLayout(borderLayout);
		setIconifiable(true);							// 设置窗体可最小化－－－必须
		setClosable(true);								// 设置窗体可关闭－－－必须
		setTitle("网络爬虫");						// 设置窗体标题－－－必须
		setBounds(20, 20, 800, 350);					// 设置窗体位置和大小－－－必须
		setMaximizable(true);
		setResizable(true);
		
		
		final JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 10, 5, 10));
		final GridLayout gridLayout = new GridLayout(3, 2);
		gridLayout.setVgap(5);
		gridLayout.setHgap(5);
		panel.setLayout(gridLayout);
		getContentPane().add(panel);

		final JLabel label_0 = new JLabel();
		label_0.setText("临时文件夹：");
		panel.add(label_0);
		
		rootfolder = new JTextField(20);
		rootfolder.setDocument(new MyDocument(20)); //设置标题长度最大输入值为13
		rootfolder.setColumns(20);
//		rootfolder.addKeyListener(new userkeyListener());
//		rootfolder.addFocusListener(new userFocusListener());
		panel.add(rootfolder);
		
		
		
		
		final JLabel label_2 = new JLabel();
		label_2.setText("线程数：");
		panel.add(label_2);

		threads = new JTextField("请输入线程数",20);
		threads.setDocument(new MyDocument(20)); //设置城市长度最大输入值为20
		
		threads.setColumns(10);
//		threads.addKeyListener(new portkeyListener());
//		threads.addFocusListener(new portFocusListener());
		panel.add(threads);

		final JLabel label = new JLabel();
		//label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setText("网页深度：");
		panel.add(label);

		maxdepth = new JTextField(20);
		maxdepth.setDocument(new MyDocument(20)); //设置部门长度最大输入值为20
		maxdepth.setColumns(20);
//		maxdepth.addKeyListener(new userkeyListener());
//		maxdepth.addFocusListener(new userFocusListener());
		panel.add(maxdepth);
		
		

		final JLabel label_1 = new JLabel();
		label_1.setText("结果文件夹：");
		panel.add(label_1);
		
		storagefolder = new JTextField(30);
		storagefolder.setDocument(new MyDocument(30)); //设置方向长度最大输入值为13
		storagefolder.setColumns(30);
//		storagefolder.addKeyListener(new userkeyListener());
//		storagefolder.addFocusListener(new userFocusListener());
		panel.add(storagefolder);
		
		final JLabel label_3 = new JLabel();
		label_3.setText("网址：");
		panel.add(label_3);
		
		webdomain = new JTextField(500);
		webdomain.setDocument(new MyDocument(500)); //设置书号文本框最大输入值为13
		webdomain.setColumns(500);
		webdomain.addKeyListener(new keyListener());
//		webdomain.addFocusListener(new userFocusListener());
		panel.add(webdomain);
		
		final JLabel label_7 = new JLabel();
		label_7.setText("关键词：");
		panel.add(label_7);
		
		keywords = new JTextField(50);
		keywords.setDocument(new MyDocument(50)); //设置书号文本框最大输入值为13
		keywords.setColumns(50);
		keywords.addKeyListener(new keyListener());
//		webdomain.addFocusListener(new userFocusListener());
		panel.add(keywords);
		
		
		
		
		
		


		final JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, false));
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setVgap(2);
		flowLayout.setHgap(30);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_1.setLayout(flowLayout);

	

		buttonclose = new JButton();
		buttonclose.addActionListener(new crawlActionListener());
		buttonclose.setText("附件爬取");
		panel_1.add(buttonclose);
		
		buttonclose = new JButton();
		buttonclose.addActionListener(new contentcrawlActionListener());
		buttonclose.setText("内容爬取");
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
//			if(!Dao.selectBookInfo(maxdepth.getText().trim()).isEmpty()){
//				JOptionPane.showMessageDialog(null, "添加书号重复！");
//				return;
//			}
//		}
//	}
//	class userkeyListener extends KeyAdapter {
//		public void keyPressed(final KeyEvent e) {
//			if (e.getKeyCode() == 13){
//				buttonadd.doClick();
//			}
//		
//		}
//	}
//	
//	class portFocusListener extends FocusAdapter {
//		public void focusLost(FocusEvent e){
//			if(!Dao.selectBookInfo(threads.getText().trim()).isEmpty()){
//				JOptionPane.showMessageDialog(null, "添加书号重复！");
//				return;
//			}
//		}
//	}
	class keyListener extends KeyAdapter {
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
	class crawlActionListener implements ActionListener {		// 添加按钮的单击事件监听器
		public void actionPerformed(final ActionEvent e) {
			// 订书业务
			if(rootfolder.getText().length()==0){
				JOptionPane.showMessageDialog(null, "临时文件夹不可以为空");
				return;
			}

			if(threads.getText().length()==0){
				JOptionPane.showMessageDialog(null, "线程数不可以为空");
				return;
			}
//			if(threads.getText().length()!=13){
//				JOptionPane.showMessageDialog(null, "书号文本框输入位数为13位");
//				return;
//			}
			if(maxdepth.getText().length()==0){
				JOptionPane.showMessageDialog(null, "最大深度不可以为空");
				return;
			}
			if(storagefolder.getText().length()==0){
				JOptionPane.showMessageDialog(null, "存储文件夹不可以为空");
				return;
			}
			if(webdomain.getText().length()==0){
				JOptionPane.showMessageDialog(null, "网址不可以为空");
				return;
			}
			
			
			
			
			String ct=threads.getText().trim();
			int thread=Integer.parseInt(ct);

			
			String rfolder=rootfolder.getText().trim();
//			int id=Integer.parseInt(uid);
			
			String ur1=maxdepth.getText().trim();
			int depth=Integer.parseInt(ur1);

			String sfolder=storagefolder.getText().trim();
//			String tt=destinationfile.getText().trim();
			String domain1=webdomain.getText().trim();
			
//			String[] domain= null;
//			domain[0]=domain1;
			String[] domain= {"a"};
			domain[0]=domain1;

			
			attachcrawl ac=new attachcrawl();
			

			
			int i=ac.crawler(rfolder, thread, depth, sfolder, domain);
				
			if(i==0){
			
				JOptionPane.showMessageDialog(null, "爬取成功");
//				doDefaultCloseAction();
			}
		}
	}
	
	class contentcrawlActionListener implements ActionListener {		// 添加按钮的单击事件监听器
		public void actionPerformed(final ActionEvent e) {
			// 订书业务
			if(rootfolder.getText().length()==0){
				JOptionPane.showMessageDialog(null, "临时文件夹不可以为空");
				return;
			}

			if(threads.getText().length()==0){
				JOptionPane.showMessageDialog(null, "线程数不可以为空");
				return;
			}
//			if(threads.getText().length()!=13){
//				JOptionPane.showMessageDialog(null, "书号文本框输入位数为13位");
//				return;
//			}
			if(maxdepth.getText().length()==0){
				JOptionPane.showMessageDialog(null, "最大深度不可以为空");
				return;
			}
			if(storagefolder.getText().length()==0){
				JOptionPane.showMessageDialog(null, "存储文件夹不可以为空");
				return;
			}
			if(webdomain.getText().length()==0){
				JOptionPane.showMessageDialog(null, "网址不可以为空");
				return;
			}
			if(keywords.getText().length()==0){
				JOptionPane.showMessageDialog(null, "关键字不可以为空");
				return;
			}
			
			
			
			String ct=threads.getText().trim();
			int thread=Integer.parseInt(ct);

			
			String rfolder=rootfolder.getText().trim();
//			int id=Integer.parseInt(uid);
			
			String ur1=maxdepth.getText().trim();
			int depth=Integer.parseInt(ur1);

			String sfolder=storagefolder.getText().trim();
//			String tt=destinationfile.getText().trim();
			String domain1=webdomain.getText().trim();
			String[] domain= {"a"};
			domain[0]=domain1;
			
//			System.out.println(thread+""+depth);
//			
//			System.out.println(domain[0]);
			String keywords1=keywords.getText().trim();
		
//			System.out.println(keywords1);
			
		String[] keywords2=keywords1.split(",");
		
//		System.out.println(keywords2[0]+" "+keywords2[1]);
		
		String[] kw= new String[keywords2.length];
		
		for (int i=0;i<keywords2.length;i++)
		{
			kw[i]=keywords2[i].trim();
		}
//		String kw="{"+keywords2[0];
//		
//		for (int i=1;i<keywords2.length;i++)
//		
//		{
//			kw=kw+keywords2[i];
//		}
//			
//		kw=kw+"}";
//	
//		String[] kw1 = null;
//		kw1[0]=kw;
//			String[] domain= null;
//			domain[0]=domain1;
//			String[] domain= {"a"};
//			domain[0]=domain1;

			contentcrawl cc=new contentcrawl();
			
			
//			    String[] keywords= {"项目","指南","申报"};
			    
//			    System.out.println(keywords[0]+" "+keywords2[0]+" "+kw[0]);
//			    System.out.println("as a test: "+ (keywords[0].equals(keywords2[0]))+" "+(kw[0].equals(keywords2[0])));
			
			int i=1;
//			i=cc.crawler(rfolder, thread, depth, sfolder, domain,keywords2);
			
			i=cc.crawler(rfolder, thread, depth, sfolder, domain, kw);
				
			if(i==0){
			
				JOptionPane.showMessageDialog(null, "爬取成功");
//				doDefaultCloseAction();
			}
		}
	}
	
//	public static void main(String[] args)
//	{
//	
//	 String rootFolder = "D://json";
//	    int threads = 1;
//	    int maxdepth=1;
//	    String storageFolder = "D://result";
//	    String[] url = new String[] { "http://etc.wuxi.gov.cn" };
//	    String[] keywords= {"项目","指南","申报"};
//	   int i=1;
//	    try {
//	    	i=ctcrawler.crawler(rootFolder, threads, maxdepth,storageFolder,url,keywords);
//			System.out.println("end status: "+i);
//	    } catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//}
	class NumberListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			String numStr="0123456789."+(char)8;
			if(numStr.indexOf(e.getKeyChar())<0){
				e.consume();
			}
		}
	}

}
