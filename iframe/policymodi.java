package iframe;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.NumberFormat;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import util.Item;

import core.Dao;

import model.PolicyInfo;
import util.CreatecdIcon;
import util.MyDocument;

/**
 * 名称：图书修改窗体
 *
 */
public class policymodi extends JInternalFrame {
	private JTextField unid;
	private JTextField city;
	private JTextField   department;
	//private JFormattedTextField pubDate;
	private JTextField direction;
	private JTextField year;
	private JTextField title;
	private JTextField keywords;
	//private JTextField abstrac;
	private JTextField abstrac;
	private JTextField place;
	
	private JTable table;
	
	DefaultComboBoxModel bookTypeModel;
	private Item item;
	Map map=new HashMap();
	private String[] columnNames;
//	private Map m=MapPz.getMap();

	//取数据库中图书相关信息放入表格中
	private Object[][] getFileStates(List list){
		String[] columnNames = {"编号", "城市", "部门", "年份", "标题", "关键字", "摘要",
				"方向", "存储" };
		Object[][]s=new Object[list.size()][columnNames.length];
		
		for(int i=0;i<list.size();i++){
			PolicyInfo policy = (PolicyInfo) list.get(i);
			s[i][0]=policy.getunid();
//			System.out.println("unid"+s[i][0]);
			s[i][1] = policy.getcity();
			s[i][2] = policy.getdepartment();
			s[i][3] = policy.getyear();
			s[i][4] = policy.gettitle();
			s[i][5] = policy.getkeywords();
			s[i][6] = policy.getabstract();
			s[i][7] = policy.getdirection();
			s[i][8] = policy.getplace();
		}
		return s;
	         		
	}
	public policymodi() {
		super();
		final BorderLayout borderLayout = new BorderLayout();
		getContentPane().setLayout(borderLayout);
		setIconifiable(true);
		setClosable(true);
		setTitle("政策信息修改");
		setBounds(20, 20, 900, 600);
		setMaximizable(true);
		setResizable(true);
		

		final JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, false));
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setVgap(2);
		flowLayout.setHgap(30);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_1.setLayout(flowLayout);

		final JButton button = new JButton();
		button.addActionListener(new addBookActionListener());
		button.setText("修改");
		panel_1.add(button);



		final JButton button_1 = new JButton();
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				doDefaultCloseAction();
			}
		});
		button_1.setText("关闭");
		panel_1.add(button_1);

//		final JLabel headLogo = new JLabel();
//		ImageIcon bookModiAndDelIcon=CreatecdIcon.add("bookmodify.jpg");
//		headLogo.setIcon(bookModiAndDelIcon);
//		headLogo.setOpaque(true);
//		headLogo.setBackground(Color.CYAN);
//		headLogo.setPreferredSize(new Dimension(900, 80));
//		headLogo.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, false));
//		getContentPane().add(headLogo, BorderLayout.NORTH);


		final JPanel panel_2 = new JPanel();
		final BorderLayout borderLayout_1 = new BorderLayout();
		borderLayout_1.setVgap(5);
		panel_2.setLayout(borderLayout_1);
		panel_2.setBorder(new EmptyBorder(5, 10, 5, 10));
		getContentPane().add(panel_2);

		final JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane);

		Object[][] results=getFileStates(Dao.selectPolicyInfo());
		String[] columnNames = {"编号", "城市", "部门", "年份", "标题", "关键字", "摘要",
				"方向", "存储" };
		table = new JTable(results,columnNames);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		//鼠标单击表格中的内容产生事件,将表格中的内容放入文本框中
		table.addMouseListener(new TableListener());

		
		
		
		
		scrollPane.setViewportView(table);

		final JPanel bookPanel = new JPanel();
		panel_2.add(bookPanel, BorderLayout.SOUTH);
		final GridLayout gridLayout = new GridLayout(3, 3);
		gridLayout.setVgap(5);
		gridLayout.setHgap(5);
		bookPanel.setLayout(gridLayout);
		
		final JLabel label_0 = new JLabel();
		label_0.setHorizontalAlignment(SwingConstants.CENTER);
		label_0.setText("编号：");
		bookPanel.add(label_0);

		unid = new JTextField();
		unid.setDocument(new MyDocument(20)); 
		bookPanel.add(unid);

		final JLabel label_2 = new JLabel();
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setText("城市：");
		bookPanel.add(label_2);

		city = new JTextField();
		city.setDocument(new MyDocument(20)); 
		bookPanel.add(city);
		


		final JLabel label_1 = new JLabel();
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setText("部门：");
		bookPanel.add(label_1);
		
		department = new JTextField();
		department.setDocument(new MyDocument(20)); 
		bookPanel.add(department);
		
		final JLabel label_3 = new JLabel();
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setText("年份：");
		bookPanel.add(label_3);

		year = new JTextField();
		bookPanel.add(year);

		final JLabel label_4 = new JLabel();
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setText("标题：");
		bookPanel.add(label_4);

		title = new JTextField();
		bookPanel.add(title);

		final JLabel label_5 = new JLabel();
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setText("关键字：");
		bookPanel.add(label_5);

		keywords = new JTextField();
		bookPanel.add(keywords);
		
		final JLabel label_6 = new JLabel();
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setText("摘要：");
		bookPanel.add(label_6);

		abstrac = new JTextField();
		bookPanel.add(abstrac);

		final JLabel label_7 = new JLabel();
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setText("方向：");
		bookPanel.add(label_7);
		
		direction = new JTextField();
		bookPanel.add(direction);
		
		final JLabel label_8 = new JLabel();
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setText("存储：");
		bookPanel.add(label_8);

		place = new JTextField();
		bookPanel.add(place);

		setVisible(true);
	}
	class TableListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
//			String ISBNs, typeids, bookNames,writers,translators,publishers,dates,prices;
			String unid1,city1,department1,year1,title1,keywords1,abstrac1,place1,direction1;
			int selRow = table.getSelectedRow();
			unid1 = table.getValueAt(selRow, 0).toString().trim();
			city1 = table.getValueAt(selRow, 1).toString().trim();
			department1 = table.getValueAt(selRow, 2).toString().trim();
			year1 = table.getValueAt(selRow, 3).toString().trim();
			title1 = table.getValueAt(selRow, 4).toString().trim();
			keywords1 = table.getValueAt(selRow, 5).toString().trim();
			abstrac1 = table.getValueAt(selRow, 6).toString().trim();
			direction1 = table.getValueAt(selRow, 7).toString().trim();
			place1 = table.getValueAt(selRow, 8).toString().trim();
			
			unid.setText(unid1);
			city.setText(city1);
			
//			for(int i=0;i<bookTypeModel.getSize();i++){
//				Item o=(Item)bookTypeModel.getElementAt(i);
//				if(o.getId().equals(typeids)){
//					bookTypeModel.setSelectedItem(o);
//				}
//			}
//			//bookTypeModel.setSelectedItem(item);
			
			department.setText(department1);
			year.setText(year1);
			title.setText(title1);
			keywords.setText(keywords1);
			abstrac.setText(abstrac1);
			direction.setText(direction1);
			place.setText(place1);
		}
	}
	class addBookActionListener implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			// 修改图书信息表
			
			if(unid.getText().length()==0){
				JOptionPane.showMessageDialog(null, "城市不可以为空");
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
			
			
			String unid1=unid.getText().trim();
			int unid2=Integer.parseInt(unid1);
			
			
			String city1=city.getText().trim();
			


			
			
			
			
			String department1=department.getText().trim();
			String year1=year.getText().trim();
			int year2=Integer.parseInt(year1);
			String title1=title.getText().trim();
			String keywords1=keywords.getText().trim();
			String abstrac1=abstrac.getText().trim();
			String place1=place.getText().trim();
			String direction1=direction.getText().trim();
			
//			int i=Dao.Updatebook(ISBNs, bookTypes, bookNames, writers, translators, publishers, Date.valueOf(pubDates), Double.parseDouble(prices));
			int i=Dao.Updatepolicy(unid2,city1, department1, year2, title1, keywords1, abstrac1,place1, direction1);
			System.out.println(i);
			
			if(i==1){

				JOptionPane.showMessageDialog(null, "修改成功");
				Object[][] results=getFileStates(Dao.selectPolicyInfo());
				//注释代码为使用表格模型
				DefaultTableModel model=new DefaultTableModel();
//				DefaultTableModel model = (DefaultTableModel) table.getModel();
				table.setModel(model);
				model.setDataVector(results, columnNames);
				

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
