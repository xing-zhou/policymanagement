package iframe;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import core.Dao;
import model.CityInfo;
import model.ProvinceInfo;
import util.Item;

public class ContentQueryIFrame extends JInternalFrame{
	private JComboBox province;
	
	public ContentQueryIFrame() {
		super();
		final BorderLayout borderLayout = new BorderLayout();
		getContentPane().setLayout(borderLayout);
		setIconifiable(true);							// 设置窗体可最小化－－－必须
		setMaximizable(true);
		setClosable(true);								// 设置窗体可关闭－－－必须
		setTitle("全文查询");						// 设置窗体标题－－－必须
		setBounds(20, 20, 800, 350);					// 设置窗体位置和大小－－－必须
		setResizable(true);
		final JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 10, 5, 10));
		final GridLayout gridLayout = new GridLayout(1, 2);
		gridLayout.setVgap(5);
		gridLayout.setHgap(5);
		panel.setLayout(gridLayout);
		getContentPane().add(panel);
		
		
		final JLabel label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setText("省份：");
		panel.add(label);

//		province=new JComboBox();
//		bookTypeModel= (DefaultComboBoxModel)bookType.getModel();
		 final DefaultComboBoxModel provincename = new DefaultComboBoxModel();
		//从数据库中取出图书类别
		List list=Dao.selectProvinceInfo();
		for(int i=0;i<list.size();i++){
			ProvinceInfo pi=(ProvinceInfo)list.get(i);
			provincename.addElement(pi.getName());

		}
		
		province=new JComboBox(provincename);
		province.addActionListener(new selectListener());
		
		panel.add(province);
		
		TextField tf=new TextField();
		panel.add(tf);
		
		final JLabel label1 = new JLabel();
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setText("城市：");
		panel.add(label1);
		
		setVisible(true);		

	}
	
		
	class selectListener  implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		   
		    String select="";
		    if (province.getSelectedIndex()!=-1)
		    {
		    	select=(String) province.getSelectedItem();
		    }
		    System.out.println(select);
		    System.out.println("***********");
		    int i=0;
		    i=Dao.selectprovinceid(select);
		    List listcity=Dao.selectcitywithprovinceid(i);
		     
		    
		    Iterator it=listcity.iterator();
		    while (it.hasNext())
		    {
		    	CityInfo ci=new CityInfo();
		    	ci=(CityInfo) it.next();
		     System.out.println(ci.getName()+""+ci.getPy());
		    }
		  
	}
	}

}
