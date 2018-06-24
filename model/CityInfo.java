package model;

public class CityInfo {
	
	private int provId;
	private String cityName;
	private String cityPy;
	
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
		return this.cityName;
	}
	public void setName(String name)
	{
		this.cityName=name;
	}
	public String getPy()
	{
		return this.cityPy;
	}
	public void setPy(String py)
	{
		this.cityPy=py;
	}
	
}
