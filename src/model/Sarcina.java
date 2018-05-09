package model;

import java.io.Serializable;

public class Sarcina implements HasId<Integer>,Serializable {
	private Integer id;
	private String descriere;
	
	public Sarcina()
	{
		id=0;
		descriere="";
	}
	
	public Sarcina(Integer id,String descriere)
	{
		this.id=id;
		this.descriere=descriere;
	}
	
	public Integer getId()
	{
		return this.id;
	}
	
	public String getDescriere()
	{
		return this.descriere;
	}
	
	
	public void setId(Integer id)
	{
		this.id=id;
	}
	
	public void setDescriere(String descriere)
	{
		this.descriere=descriere;
	}
	
	public String toString()
	{
		return id+", "+descriere;
	}
	
	public boolean equals(Sarcina s)
	{
		if(this.getId()!=s.getId())
			return false;
		if(!this.getDescriere().equals(s.getDescriere()))
			return false;
		return true;
	}
}
