package test;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import core.Dao;
import model.CityInfo;
import model.ProvinceInfo;

public class testProvinceandCity {
	public static void main(String[] args)
	{
		List<ProvinceInfo> list=new ArrayList();
		List listcity=new ArrayList();
		list=Dao.selectProvinceInfo();
		Iterator<ProvinceInfo> it=list.iterator();
		ProvinceInfo pi=new ProvinceInfo();
		while(it.hasNext())
		{
			pi= it.next();
			System.out.println(pi.getName()+""+pi.getPy());
		}
		int id=0;
		id=Dao.selectprovinceid("ËÄ´¨");
		System.out.println(id);
		listcity=Dao.selectcitywithprovinceid(id);
		CityInfo ci=new CityInfo();
		Iterator<CityInfo> itcity=listcity.iterator();
		
		while(itcity.hasNext())
		{
			ci=itcity.next();
			System.out.println(ci.getName()+""+ci.getPy());
		}
		
	}

}
