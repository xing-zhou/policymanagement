package iframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import util.Item;

import core.Dao;

import model.PolicyInfo;

import java.awt.*;

public class PolicySearchIFrame extends JInternalFrame {

	private JTextField textField_1;

	private JComboBox comboBox_1;

	private JTable table_1, table_2;

	private JComboBox choice;

	private JTextField textField_2;

	private JScrollPane scrollPane, scrollPane_1;
//	private Map m=MapPz.getMap();


	/**
	 * Launch the application
	 * 
	 * @param args
	 */

	/**
	 * Create the frame
	 */
	String policysearch[] = { "���","����", "����", "����", "�ؼ���", "ժҪ",  "����",  "���",  "�洢" };

	private Object[][] getselect(List list) {
		Object[][] s = new Object[list.size()][9];
		for (int i = 0; i < list.size(); i++) {
			PolicyInfo policy = (PolicyInfo) list.get(i);
			s[i][0]=policy.getunid();
			s[i][1] = policy.getcity();
			//String booktypename=String.valueOf(MapPz.getMap().get(book.getTypeid()));
			s[i][2] = policy.getdepartment();
			s[i][3] = policy.gettitle();
			s[i][4] = policy.getkeywords();
			s[i][5] = policy.getabstract();
			s[i][6] = policy.getdirection();
			s[i][7] = policy.getyear();
			s[i][8] = policy.getplace();

		}
		return s;

	}

	private Object[] getselectid(List list) {
		Object[] ids = new Object[list.size()];
		for (int i = 0; i < list.size(); i++) {
			PolicyInfo book = new PolicyInfo();
			ids[i] = book.gettitle();
		}
		return ids;
	}

	public PolicySearchIFrame() {
		super();
		setIconifiable(true);
		setClosable(true);
		setTitle("���߲�ѯ");
		setBounds(20, 20, 700, 600);
		setMaximizable(true);
		setResizable(true);

		
		setVisible(true);

		final JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setPreferredSize(new Dimension(0, 50));
		getContentPane().add(tabbedPane);

		final JPanel panel_1 = new JPanel();
		panel_1.setLayout(new BorderLayout());
		tabbedPane.addTab("������ѯ", null, panel_1, null);

		final JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new TitledBorder(null, "��ѡ���ѯ��Ŀ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_1_1.setPreferredSize(new Dimension(50, 50));
		panel_1.add(panel_1_1, BorderLayout.NORTH);
         choice=new JComboBox();
		String[] array={ "���","����","����","����","�ؼ���","����","���","�߼���ѯ"};
		for(int i=0;i<array.length;i++){
			choice.addItem(array[i]);
			
		}
		panel_1_1.add(choice);
		textField_1 = new JTextField();
		textField_1.setColumns(50);
		panel_1_1.add(textField_1);
        
		final JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "��ѯ�����ʾ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_1.add(panel);

		
		 scrollPane_1 = new JScrollPane();
		scrollPane_1.setPreferredSize(new Dimension(660, 400));
		
		
		
		panel.add(scrollPane_1);

		final JPanel panel_2_1 = new JPanel();
		panel_2_1.setPreferredSize(new Dimension(0, 50));
		panel_1.add(panel_2_1, BorderLayout.SOUTH);

		final JButton button = new JButton();
		button.setText("��ѯ");
		panel_2_1.add(button);

		 button.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent arg0) {
					String name=(String)choice.getSelectedItem();
					
					if(name.equals("���")){
						
						Object[][] results=getselect(Dao.selectpolicymohu("uid",textField_1.getText()));
						table_2 = new JTable(results,policysearch);
						
						scrollPane_1.setViewportView(table_2);
					}

					else if(name.equals("����")){
						
						Object[][] results=getselect(Dao.selectpolicymohu("city",textField_1.getText()));
						table_2 = new JTable(results,policysearch);
						
						scrollPane_1.setViewportView(table_2);
					}
					else if(name.equals("����")){
						
						Object[][] results=getselect(Dao.selectpolicymohu("department",textField_1.getText()));
						table_2 = new JTable(results,policysearch);
						
						scrollPane_1.setViewportView(table_2);
					}
					else if(name.equals("����")){
						
						Object[][] results=getselect(Dao.selectpolicymohu("title",textField_1.getText()));
						table_2 = new JTable(results,policysearch);
						
						scrollPane_1.setViewportView(table_2);
					}
					else if(name.equals("�ؼ���")){
						
						Object[][] results=getselect(Dao.selectpolicymohu("keywords",textField_1.getText()));
						table_2 = new JTable(results,policysearch);
						
						scrollPane_1.setViewportView(table_2);
					}
					else if(name.equals("����")){
	
						Object[][] results=getselect(Dao.selectpolicymohu("direction",textField_1.getText()));
						table_2 = new JTable(results,policysearch);
	
						scrollPane_1.setViewportView(table_2);
					}
					else if(name.equals("���")){
						
						Object[][] results=getselect(Dao.selectpolicymohu("year",textField_1.getText()));
						table_2 = new JTable(results,policysearch);
	
						scrollPane_1.setViewportView(table_2);
					}
				
	        	
				else if(name.equals("�߼���ѯ")){
					
					Object[][] results=getselect(Dao.selectpolicyadvance(textField_1.getText()));
					table_2 = new JTable(results,policysearch);

					scrollPane_1.setViewportView(table_2);
				}
			}
        	
	        });
		
		
		final JButton button_1 = new JButton();
		button_1.setText("�˳�");
		panel_2_1.add(button_1);
		button_1.addActionListener(new CloseActionListener());
		
		setVisible(true);
		
		final JPanel panel_2 = new JPanel();
		tabbedPane.addTab("��ʾ����ȫ����Ϣ", null, panel_2, null);
         
		 scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(660, 570));
		panel_2.add(scrollPane);
		
		Object[][] results=getselect(Dao.selectpolicyserch());
		String [] booksearch = {  "���","����", "����", "����", "�ؼ���", "ժҪ","����",  "���",  "�洢" };
		table_1 = new JTable(results,booksearch);
		
		scrollPane.setViewportView(table_1);
		
		}
	class CloseActionListener implements ActionListener {			// ��ӹرհ�ť���¼�������
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
}
