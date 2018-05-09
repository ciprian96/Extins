package model;


public class Post implements HasId<Integer>{
	private Integer id;
	private String denumire;
	private String tip;
	private Double salariu;
	public Post()
	{
		id=0;
		denumire="";
		tip="";
		salariu=0d;
	}
	
	public Post(Integer id,String denumire,String tip,Double salariu)
	{
		this.id=id;
		this.denumire=denumire;
		this.tip=tip;
		this.salariu=salariu;
	}
	
	public Integer getId()
	{
		return this.id;
	}
	
	public String getDenumire()
	{
		return this.denumire;
	}
	
	public String getTip()
	{
		return this.tip;
	}
	
	public Double getSalariu()
	{
		return this.salariu;
	}
	
	public void setId(Integer id)
	{
		this.id=id;
	}
	
	public void setDenumire(String denumire)
	{
		this.denumire=denumire;
	}
	
	public void setTip(String tip)
	{
		this.tip=tip;
	}
	
	public void setSalariu(double s)
	{
		this.salariu=s;
	}
	@Override
	public String toString()
	{
		return id+", "+denumire+", "+tip+", "+salariu+"\n";
	}

	public boolean equals(Post p)
	{
		if(this.getId().compareTo(p.getId())!=0)
		{
			return false;
		}
		if(this.getDenumire().compareTo(p.getDenumire())!=0)
		{
			return false;
		}
		if(this.getTip().compareTo(p.getTip())!=0)
		{
			return false;
		}
		if(this.getSalariu()!=p.getSalariu())
		{
			return false;
		}
			
		return true;
	}
}

