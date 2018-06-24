package util;

import java.net.URL;

import javax.swing.ImageIcon;



import mainframe.PolicyManagement;

public class CreatecdIcon {
	public static ImageIcon add(String ImageName){
		URL IconUrl = PolicyManagement.class.getResource("/"+ImageName);
		ImageIcon icon=new ImageIcon(IconUrl);
		return icon;
	}
}
