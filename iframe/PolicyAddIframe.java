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
 * ���ƣ�ͼ����Ӵ���
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
		setIconifiable(true);							// ���ô������С������������
		setClosable(true);								// ���ô���ɹرգ���������
		setTitle("������Ϣ���");						// ���ô�����⣭��������
		setBounds(20, 20, 900, 450);					// ���ô���λ�úʹ�С����������
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
		label_0.setText("��ţ�");
		panel.add(label_0);
		
		unid = new JTextField(20);
		unid.setDocument(new MyDocument(20)); //���ñ��ⳤ���������ֵΪ13
		unid.setColumns(20);
//		unid.addKeyListener(new unidkeyListener());
//		unid.addFocusListener(new unidFocusListener());
		panel.add(unid);
		
		final JLabel label_4 = new JLabel();
		label_4.setText("���⣺");
		panel.add(label_4);
		
		title = new JTextField(30);
		title.setDocument(new MyDocument(30)); //���ñ��ⳤ���������ֵΪ13
		title.setColumns(30);
//		title.addKeyListener(new citykeyListener());
//		title.addFocusListener(new cityFocusListener());
		panel.add(title);
		
		
		final JLabel label_2 = new JLabel();
		label_2.setText("���У�");
		panel.add(label_2);

		city = new JTextField("���������",20);
		city.setDocument(new MyDocument(20)); //���ó��г����������ֵΪ20
		
		city.setColumns(10);
//		city.addKeyListener(new citykeyListener());
//		city.addFocusListener(new cityFocusListener());
		panel.add(city);

		final JLabel label = new JLabel();
		//label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setText("���ţ�");
		panel.add(label);

		department = new JTextField(20);
		department.setDocument(new MyDocument(20)); //���ò��ų����������ֵΪ20
		department.setColumns(20);
//		department.addKeyListener(new citykeyListener());
//		department.addFocusListener(new cityFocusListener());
		panel.add(department);
		
		
		//�����ݿ���ȡ��ͼ�����
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
		label_1.setText("��������");
		panel.add(label_1);
		
		direction = new JTextField(30);
		direction.setDocument(new MyDocument(30)); //���÷��򳤶��������ֵΪ13
		direction.setColumns(30);
//		direction.addKeyListener(new citykeyListener());
//		direction.addFocusListener(new cityFocusListener());
		panel.add(direction);
		
		final JLabel label_3 = new JLabel();
		label_3.setText("��ݣ�");
		panel.add(label_3);
		
		year = new JTextField(4);
		year.setDocument(new MyDocument(4)); //��������ı����������ֵΪ13
		year.setColumns(4);
		year.addKeyListener(new yearkeyListener());
//		year.addFocusListener(new cityFocusListener());
		panel.add(year);
		
	

		
		final JLabel label_5 = new JLabel();
		label_5.setText("�ؼ��֣�");
		panel.add(label_5);
		
		keywords = new JTextField(50);
		keywords.setDocument(new MyDocument(50)); //���ùؼ��ֳ����������ֵΪ13
		keywords.setColumns(50);
//		keywords.addKeyListener(new citykeyListener());
//		keywords.addFocusListener(new cityFocusListener());
		panel.add(keywords);
		
		final JLabel label_6 = new JLabel();
		label_6.setText("ժҪ��");
		panel.add(label_6);
		
		abstrac = new JTextArea(30,20);
		abstrac.setDocument(new MyDocument(500)); //��������ı����������ֵΪ13
		abstrac.setColumns(500);
//		abstrac.addKeyListener(new citykeyListener());
//		abstrac.addFocusListener(new cityFocusListener());
		panel.add(abstrac);
		
		
		final JLabel label_7 = new JLabel();
		label_7.setText("�洢λ�ã�");
		panel.add(label_7);
		
		place = new JTextField(50);
		place.setDocument(new MyDocument(50)); //����λ�ó����������ֵΪ13
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
		buttonadd.setText("���");
		panel_1.add(buttonadd);

		buttonclose = new JButton();
		buttonclose.addActionListener(new CloseActionListener());
		buttonclose.setText("�ر�");
		panel_1.add(buttonclose);

//		final JLabel label_8 = new JLabel();
//		ImageIcon bookAddIcon=CreatecdIcon.add("newBookorderImg.jpg");
//		label_5.setIcon(bookAddIcon);
//		label_5.setPreferredSize(new Dimension(400, 80));
//		label_5.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, false));
//		getContentPane().add(label_5, BorderLayout.NORTH);
//		label_5.setText("���鶨��(LOGOͼƬ)");
		
		setVisible(true);											// ��ʾ����ɹرգ�����������������пؼ�֮��ִ�и����
	}
//	class unidFocusListener extends FocusAdapter {
//		public void focusLost(FocusEvent e){
//			if(!Dao.selectBookInfo(unid.getText().trim()).isEmpty()){
//				JOptionPane.showMessageDialog(null, "��ӱ���ظ���");
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
//				JOptionPane.showMessageDialog(null, "�������ظ���");
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
	
	
	class CloseActionListener implements ActionListener {			// ��ӹرհ�ť���¼�������
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
	class addBookActionListener implements ActionListener {		// ��Ӱ�ť�ĵ����¼�������
		public void actionPerformed(final ActionEvent e) {
			// ����ҵ��
			if(unid.getText().length()==0){
				JOptionPane.showMessageDialog(null, "��Ų�����Ϊ��");
				return;
			}

			if(city.getText().length()==0){
				JOptionPane.showMessageDialog(null, "���в�����Ϊ��");
				return;
			}
//			if(city.getText().length()!=13){
//				JOptionPane.showMessageDialog(null, "����ı�������λ��Ϊ13λ");
//				return;
//			}
			if(department.getText().length()==0){
				JOptionPane.showMessageDialog(null, "���Ų�����Ϊ��");
				return;
			}
			if(year.getText().length()==0){
				JOptionPane.showMessageDialog(null, "��Ȳ�����Ϊ��");
				return;
			}
			if(title.getText().length()==0){
				JOptionPane.showMessageDialog(null, "���ⲻ����Ϊ��");
				return;
			}
			if(keywords.getText().length()==0){
				JOptionPane.showMessageDialog(null, "�ؼ��ֲ�����Ϊ��");
				return;
			}
			if(abstrac.getText().length()==0){
				JOptionPane.showMessageDialog(null, "ժҪ������Ϊ��");
				return;
			}
			if(place.getText().length()==0){
				JOptionPane.showMessageDialog(null, "�洢������Ϊ��");
				return;
			}
			
			String ct=city.getText().trim();
			
			//����
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
			
				JOptionPane.showMessageDialog(null, "��ӳɹ�");
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
