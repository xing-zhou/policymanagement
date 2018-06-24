package model;

public class ProvinceInfo {
	
	private int provId;
	private String provName;
	private String provPy;
	
	public int getId()
	
	{
		return this.provId;
	}
	public void setId(int Id)
	{
		this.provId=Id;
	}

	public String getName()
	{
		return this.provName;
	}
	public void setName(String name)
	{
		this.provName=name;
	}
	public String getPy()
	{
		return this.provPy;
	}
	public void setPy(String py)
	
	{
		this.provPy=py;
	}
	
}
