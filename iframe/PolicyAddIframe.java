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
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


import util.Item;
import core.Dao;

import util.MyDocument;
import util.CreatecdIcon;
/**
 * 名称：图书添加窗体
 * 
 */
public class PolicyAddIframe extends JInternalFrame {
	private JTextField unid;
	private JTextField city;
	private JTextField   department;
	//private JFormattedTextField pubDate;
	private JTextField direction;
	private JTextField year;
	private JTextField title;
	private JTextField keywords;
	//private JTextField abstrac;
	private JTextArea abstrac;
	private JTextField place;
	//private JComboBox bookType;
	private JButton buttonadd;
	private JButton buttonclose;
	//DefaultComboBoxModel bookTypeModel;
	
	//Map map=new HashMap();
	public PolicyAddIframe() {
		super();
		final BorderLayout borderLayout = new BorderLayout();
		getContentPane().setLayout(borderLayout);
		setIconifiable(true);							// 设置窗体可最小化－－－必须
		setClosable(true);								// 设置窗体可关闭－－－必须
		setTitle("政策信息添加");						// 设置窗体标题－－－必须
		setBounds(20, 20, 900, 450);					// 设置窗体位置和大小－－－必须
		setMaximizable(true);
		setResizable(true);
		
		
		final JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 10, 5, 10));
		final GridLayout gridLayout = new GridLayout(5, 2);
		gridLayout.setVgap(5);
		gridLayout.setHgap(5);
		panel.setLayout(gridLayout);
		getContentPane().add(panel);

		final JLabel label_0 = new JLabel();
		label_0.setText("编号：");
		panel.add(label_0);
		
		unid = new JTextField(20);
		unid.setDocument(new MyDocument(20)); //设置标题长度最大输入值为13
		unid.setColumns(20);
//		unid.addKeyListener(new unidkeyListener());
//		unid.addFocusListener(new unidFocusListener());
		panel.add(unid);
		
		final JLabel label_4 = new JLabel();
		label_4.setText("标题：");
		panel.add(label_4);
		
		title = new JTextField(30);
		title.setDocument(new MyDocument(30)); //设置标题长度最大输入值为13
		title.setColumns(30);
//		title.addKeyListener(new citykeyListener());
//		title.addFocusListener(new cityFocusListener());
		panel.add(title);
		
		
		final JLabel label_2 = new JLabel();
		label_2.setText("城市：");
		panel.add(label_2);

		city = new JTextField("请输入城市",20);
		city.setDocument(new MyDocument(20)); //设置城市长度最大输入值为20
		
		city.setColumns(10);
//		city.addKeyListener(new citykeyListener());
//		city.addFocusListener(new cityFocusListener());
		panel.add(city);

		final JLabel label = new JLabel();
		//label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setText("部门：");
		panel.add(label);

		department = new JTextField(20);
		department.setDocument(new MyDocument(20)); //设置部门长度最大输入值为20
		department.setColumns(20);
//		department.addKeyListener(new citykeyListener());
//		department.addFocusListener(new cityFocusListener());
		panel.add(department);
		
		
		//从数据库中取出图书类别
//		List list=Dao.selectBookCategory();
//		for(int i=0;i<list.size();i++){
//			BookType booktype=(BookType)list.get(i);
//			Item item=new Item();
//			item.setId((String)booktype.getId());
//			item.setName((String)booktype.getTypeName());
//			bookTypeModel.addElement(item);
//		}
//		panel.add(bookType);

		final JLabel label_1 = new JLabel();
		label_1.setText("资助方向：");
		panel.add(label_1);
		
		direction = new JTextField(30);
		direction.setDocument(new MyDocument(30)); //设置方向长度最大输入值为13
		direction.setColumns(30);
//		direction.addKeyListener(new citykeyListener());
//		direction.addFocusListener(new cityFocusListener());
		panel.add(direction);
		
		final JLabel label_3 = new JLabel();
		label_3.setText("年份：");
		panel.add(label_3);
		
		year = new JTextField(4);
		year.setDocument(new MyDocument(4)); //设置书号文本框最大输入值为13
		year.setColumns(4);
		year.addKeyListener(new yearkeyListener());
//		year.addFocusListener(new cityFocusListener());
		panel.add(year);
		
	

		
		final JLabel label_5 = new JLabel();
		label_5.setText("关键字：");
		panel.add(label_5);
		
		keywords = new JTextField(50);
		keywords.setDocument(new MyDocument(50)); //设置关键字长度最大输入值为13
		keywords.setColumns(50);
//		keywords.addKeyListener(new citykeyListener());
//		keywords.addFocusListener(new cityFocusListener());
		panel.add(keywords);
		
		final JLabel label_6 = new JLabel();
		label_6.setText("摘要：");
		panel.add(label_6);
		
		abstrac = new JTextArea(30,20);
		abstrac.setDocument(new MyDocument(500)); //设置书号文本框最大输入值为13
		abstrac.setColumns(500);
//		abstrac.addKeyListener(new citykeyListener());
//		abstrac.addFocusListener(new cityFocusListener());
		panel.add(abstrac);
		
		
		final JLabel label_7 = new JLabel();
		label_7.setText("存储位置：");
		panel.add(label_7);
		
		place = new JTextField(50);
		place.setDocument(new MyDocument(50)); //设置位置长度最大输入值为13
		place.setColumns(50);
//		place.addKeyListener(new citykeyListener());
//		place.addFocusListener(new cityFocusListener());
		panel.add(place);


		final JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, false));
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setVgap(2);
		flowLayout.setHgap(30);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_1.setLayout(flowLayout);

		buttonadd= new JButton();
		buttonadd.addActionListener(new addBookActionListener());
		buttonadd.setText("添加");
		panel_1.add(buttonadd);

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
//	class unidFocusListener extends FocusAdapter {
//		public void focusLost(FocusEvent e){
//			if(!Dao.selectBookInfo(unid.getText().trim()).isEmpty()){
//				JOptionPane.showMessageDialog(null, "添加编号重复！");
//				return;
//			}
//		}
//	}

//	class citykeyListener extends KeyAdapter {
//		public void keyPressed(final KeyEvent e) {
//			if (e.getKeyCode() == 13){
//				buttonadd.doClick();
//			}
//		
//		}
//	}
	
//	class cityFocusListener extends FocusAdapter {
//		public void focusLost(FocusEvent e){
//			if(!Dao.selectBookInfo(department.getText().trim()).isEmpty()){
//				JOptionPane.showMessageDialog(null, "添加书号重复！");
//				return;
//			}
//		}
//	}
	class yearkeyListener extends KeyAdapter {
		public void keyPressed(final KeyEvent e) {
			if (e.getKeyCode() == 4){
				buttonadd.doClick();
			}
		
		}
	}
	
	
	class CloseActionListener implements ActionListener {			// 添加关闭按钮的事件监听器
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
	class addBookActionListener implements ActionListener {		// 添加按钮的单击事件监听器
		public void actionPerformed(final ActionEvent e) {
			// 订书业务
			if(unid.getText().length()==0){
				JOptionPane.showMessageDialog(null, "编号不可以为空");
				return;
			}

			if(city.getText().length()==0){
				JOptionPane.showMessageDialog(null, "城市不可以为空");
				return;
			}
//			if(city.getText().length()!=13){
//				JOptionPane.showMessageDialog(null, "书号文本框输入位数为13位");
//				return;
//			}
			if(department.getText().length()==0){
				JOptionPane.showMessageDialog(null, "部门不可以为空");
				return;
			}
			if(year.getText().length()==0){
				JOptionPane.showMessageDialog(null, "年度不可以为空");
				return;
			}
			if(title.getText().length()==0){
				JOptionPane.showMessageDialog(null, "标题不可以为空");
				return;
			}
			if(keywords.getText().length()==0){
				JOptionPane.showMessageDialog(null, "关键字不可以为空");
				return;
			}
			if(abstrac.getText().length()==0){
				JOptionPane.showMessageDialog(null, "摘要不可以为空");
				return;
			}
			if(place.getText().length()==0){
				JOptionPane.showMessageDialog(null, "存储不可以为空");
				return;
			}
			
			String ct=city.getText().trim();
			
			//分类
//			Object selectedItem = bookType.getSelectedItem();
//			if (selectedItem == null)
//				return;
//			Item item = (Item) selectedItem;
//			String bookTypes=item.getId();
			
			String uid=unid.getText().trim();
			int id=Integer.parseInt(uid);
			
			String dt=department.getText().trim();
//			try {
//				dt=new String(dt.getBytes(),"ISO8859_1");
//			} catch (UnsupportedEncodingException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			String dir=direction.getText().trim();
			String tt=title.getText().trim();
			String yr=year.getText().trim();
			int yer=Integer.parseInt(yr);
			String kw=keywords.getText().trim();
			String abs=abstrac.getText().trim();
			String pl=place.getText().trim();
//			String publishers=(String)publisher.getSelectedItem();
//			String pubDates=pubDate.getText().trim();
//			String prices=price.getText().trim();
			int i=Dao.Insertpolicy(id,ct,dt, yer, tt, kw, abs, pl,dir);
			
			
				
			if(i==1){
			
				JOptionPane.showMessageDialog(null, "添加成功");
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
