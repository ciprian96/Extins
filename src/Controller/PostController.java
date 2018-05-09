package Controller;



import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Observer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import Repository.ElementFisaRepository;
import Repository.ExceptiaMea;
import Repository.PostRepositoryFromFile;
import Repository.RepositoryFromXMLFile;

import javafx.collections.ObservableList;
import model.ElementFisa;
import model.ElementFisaModel;
import model.Post;
import model.PostDataModel;
import model.Sarcina;
import model.SarcinaModel;


public class PostController implements Utils.Observable<Object>{

    protected PostRepositoryFromFile posturi;
    protected RepositoryFromXMLFile sarcini;
    
    protected ElementFisaRepository elementeFisa;
    
    private List<Observer> observers = new ArrayList<>();
    
    public SarcinaModel model;
	public PostDataModel model2;
	public ElementFisaModel model3;
	
	public PostController(PostRepositoryFromFile repo,RepositoryFromXMLFile repo2,ElementFisaRepository elementeFisa){
        posturi = repo;
        sarcini=repo2;
        this.elementeFisa=elementeFisa;
        
        model = new SarcinaModel(sarcini);
        model2 = new PostDataModel(posturi);
        model3 = new ElementFisaModel(elementeFisa);
    }
    
    
    public <E> List<E> filterGeneric(List<E> lista, Predicate<E> p,Comparator<E> comp) 
	{
		return lista.stream().filter(p).sorted(comp).collect(Collectors.toList());

	}
    
    public <E> List<Sarcina> filtrareSarcini1(int l,String s)
    {
		List<Sarcina> sarcini = new ArrayList<>();
		this.getSarcini().forEach(sarcini::add);
			
		Predicate<Sarcina> pr = (p)->p.getDescriere().contains(s) && p.getDescriere().length()>=l;
		Comparator<Sarcina> cmp =(s1,s2)->s1.getDescriere().compareToIgnoreCase(s2.getDescriere());
		return this.filterGeneric(sarcini, pr, cmp);
    }
    
    public List<Post> filtrarePost1(String tip,double pret)
    {
		Predicate<Post> rFilter2 = (Post p)->p.getTip().compareToIgnoreCase(tip)==0 && p.getSalariu()<pret;
		List<Post> posts =this.getPosturi();
		Comparator<Post> cmpNume =(p1,p2)->p1.getDenumire().compareToIgnoreCase(p2.getDenumire());
		return filterGeneric(posts,rFilter2,cmpNume);
    }
    
    public List<Post> filtrarePost2(double pret)
    {
		Predicate<Post> rFilter = (Post p)->p.getSalariu()>=pret;
		
		List<Post> posts = this.getPosturi();
		Comparator<Post> cmpNumeSalariu = (p1,p2)->{
			int val =p1.getTip().compareToIgnoreCase(p2.getTip());
			if(val!=0)
				return val;
			return (int) (p1.getSalariu()-p2.getSalariu());
		};
		return filterGeneric(posts,rFilter,cmpNumeSalariu);
    }
    
    public void addPost(Post p) throws ExceptiaMea
	{
		posturi.validator.validate(p);
		posturi.save(p);
	}
    
    public void addPostBasic(Integer id,String denumire,String tip, Double sal) throws ExceptiaMea
	{
    	this.addPost(new Post(id,denumire,tip,sal));
	}
    
    public Post removePost(Integer id) throws ExceptiaMea
	{
		Post p= posturi.delete(id);
		return p;
	}
    
    public void updatePost(Integer id,Post upd) throws ExceptiaMea
	{
		posturi.validator.validate(upd);
		posturi.updateObject(id, upd);
	}
    
    public List<Post> getPosturi()
	{
		return this.posturi.getAll();
	}
    
    public List<Sarcina> getSarcini()
    {
    	return this.sarcini.getAll();
    }


	public ObservableList<Post> cautaLista(String text) {
		// TODO Auto-generated method stub
		return posturi.getPosturi(text);
	}
	
	public Post cautaPost(Integer id)
	{
		return posturi.findObjectId(id);
	}

	public void updatePostBasic(Integer id, String den, String tip, Double sal) throws ExceptiaMea {
		// TODO Auto-generated method stub
		this.updatePost(id, new Post(id,den,tip,sal));
	}
	
	public void addSarcina(Sarcina s) throws ExceptiaMea
	{
		sarcini.validator.validate(s);
		sarcini.save(s);
	}
	
	
	public void addSarcinaBasic(Integer id,String des) throws ExceptiaMea
	{
		this.addSarcina(new Sarcina(id,des));
	}
	
	public Sarcina deleteSarcina(Integer id) throws ExceptiaMea
	{
		Sarcina s= this.sarcini.delete(id);
		return s;
	}
	
	public void updateSarcina(Integer id,String descriere) throws ExceptiaMea
	{
		Sarcina newSarcina= new Sarcina(id,descriere);
		sarcini.validator.validate(newSarcina);
		sarcini.updateObject(id, newSarcina);
	}

	public void rescrieFisiere() {
		

		this.posturi.saveData();
		
		this.sarcini.saveData();
		this.elementeFisa.saveData();
	}

	@Override
	public void addObserver(Utils.Observer<Object> o) {
		// TODO Auto-generated method stub
		System.out.println("Add observer" + o.getClass());
        observers.add((Observer) o);
	}

	@Override
	public void removeObserver(Utils.Observer<Object> o) {
		// TODO Auto-generated method stub
		System.out.println("Remove observer" + o.getClass());
        observers.remove(o);
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		for (Observer o : observers) {
            o.update(null, this);
        }
	}


	public void addEntitatePost(Integer id, Integer idPost, String denumire, String tip, Double pret, Integer idSar,String des) throws ExceptiaMea {
		// TODO Auto-generated method stub
		Post p = new Post(idPost,denumire,tip,pret);
		String eroare=null;
		int ok=1;
		if(!posturi.existaObject(p))
		{
			eroare="Nu exista post: "+p.toString()+"\n";
			//System.out.println("nu exista post");
			throw new ExceptiaMea(eroare);
		}
		else
		{
			Sarcina s = new Sarcina(idSar,des);
			if(!sarcini.existaObject(s))
			{	
				eroare="Nu exista sarcina: "+s.toString()+"\n";
				//System.out.println("nu exista sarcina");
				throw new ExceptiaMea(eroare);
			}
			else
			{
				//System.out.println("inca nu a fost adaugat");
				elementeFisa.save(new ElementFisa(id,new Post(idPost,denumire,tip,pret),new Sarcina(idSar,des)));
				//System.out.println("a fost adugat");
			}
		}
		
	}


	public void deleteEntitatePost(Integer id) throws ExceptiaMea {
		// TODO Auto-generated method stub
		elementeFisa.delete(id);
	}


	public void updateEntitatePost(Integer id, Integer idSar, String des) throws ExceptiaMea {
		// TODO Auto-generated method stub
		String eroare=null;
		Sarcina s = new Sarcina(idSar,des);
		if(!sarcini.existaObject(s))
		{	
			eroare="Nu exista sarcina: "+s.toString()+"\n";
			//System.out.println("nu exista sarcina");
			throw new ExceptiaMea(eroare);
		}
		else
		{
			elementeFisa.updateFisa(id,s);
		}
	}
}

