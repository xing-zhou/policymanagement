package model;

import java.sql.Date;

public class PolicyInfo {
	private int unid;
	private String city;
	private String department;
	private int year;
	private String title;
	private String keywords;
	private String abstrac;
	private String place;
	private String direction;

	
	public int getunid()
	{
		return unid;
	}
	public void setunid(int unid)
	{
	this.unid=unid;	
	}
	public String getcity() {
		return city;
	}
	public void setcity(String city) {
		this.city = city;
	}

	public int getyear() {
		return year;
	}
	public void setyear(int year) {
		this.year = year;
	}
	public String getdepartment() {
		return department;
	}
	public void setdepartment(String department) {
		this.department= department;
	}

	public String gettitle() {
		return title;
	}
	public void settitle(String title) {
		this.title = title;
	}
	public String getkeywords() {
		return keywords;
	}
	public void setkeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getabstract() {
		return abstrac;
	}
	public void setabstract(String abstrac) {
		this.abstrac = abstrac;
	}
	public String getplace() {
		return place;
	}
	public void setplace(String place) {
		this.place = place;
	}
	public String getdirection() {
		return direction;
	}
	public void setdirection(String diretion) {
		this.direction = diretion;
	}
}
