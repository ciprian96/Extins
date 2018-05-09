package Repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Utils.Observer;
import model.ElementFisa;
import model.Post;
import model.Sarcina;
import model.ValidatorPost;

public class ElementFisaRepository extends AbstractRepository<Integer,ElementFisa> {
	
	protected String fileName;
	
	public ElementFisaRepository(String file)
	{
		super();
		this.fileName=file;
		readData();
	}
	@Override
	public void saveData() {
		// TODO Auto-generated method stub
		BufferedWriter bw = null;
		try{
			bw=new BufferedWriter(new FileWriter(fileName));
			//bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			String line;
			for(ElementFisa e : this.entities)
			{
				line="";
				line=e.getId()+"|"+e.getPost().getId()+"|"+e.getPost().getDenumire()+"|"+e.getPost().getTip()+"|"+e.getPost().getSalariu()+"|"+e.getSarcina().getId()+"|"+e.getSarcina().getDescriere()+"\n";
				bw.write(line);
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally{
			if(bw!=null)
				try{
					bw.close();
				}
			catch(IOException e)
			{
				System.out.println("BufferedWriter nu poate fi inchis");
			}
		}
	}

	@Override
	public void readData() {
		// TODO Auto-generated method stub
		BufferedReader br = null;
		try{
			String line;
			br=new BufferedReader(new FileReader(fileName));
			while((line=br.readLine())!=null)
			{
				//System.out.println(line);
				String[] fields = line.split("\\|");
				Integer id = Integer.parseInt(fields[0]);
				
				Post p =new Post(Integer.parseInt(fields[1]),fields[2],fields[3],Double.parseDouble(fields[4]));
				Sarcina s = new Sarcina(Integer.parseInt(fields[5]),fields[6]);
				//System.out.println("ajunge si salveaza");
				
				this.entities.add(new ElementFisa(id,p,s));
				
				//entities.forEach(System.out::println);
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Nu se gaseste fisierul");
		}
		catch(IOException e)
		{
			System.out.println("Nu pot citi");
		}
		System.out.println(this.entities.size());
	}

	private List<Observer<ElementFisa>> observers = new ArrayList<>();
	
    @Override
    public void addObserver(Observer<ElementFisa> e) {
        System.out.println("Add observer"+e.getClass());
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer<ElementFisa> e) {
        System.out.println("Remove observer"+e.getClass());
        observers.remove(e);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers){
            o.update(this);
        }
    }
    
	public List<Post> getAllPost() {
		// TODO Auto-generated method stub
		//return entities;
		//ObservableList<E> postDataModel = FXCollections.observableArrayList(entities);
    	List<Post> l = new ArrayList<>();
        for(ElementFisa e:entities)
        {
        	l.add(e.getPost());
        }
        return l;
	}
	
	public List<Sarcina> getAllSarcini() {
		// TODO Auto-generated method stub
		//return entities;
		//ObservableList<E> postDataModel = FXCollections.observableArrayList(entities);
    	List<Sarcina> l = new ArrayList<>();
        for(ElementFisa e:entities)
        {
        	l.add(e.getSarcina());
        }
        return l;
	}
	public void updateFisa(Integer id, Sarcina s) throws ExceptiaMea {
		// TODO Auto-generated method stub
		for(ElementFisa e : entities)
		{
			if(e.getId().compareTo(id)==0)
			{
				e.setSarcina(s);
				//e.setDescriereSarcina(s.getDescriere());
				this.notifyObservers();
				return;
			}
		}
		throw new ExceptiaMea("Nu exista Element Fisa cu id: "+id+"\n");
	}

}
