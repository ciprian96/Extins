package Repository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import Utils.Observer;
import javafx.collections.FXCollections;
import model.Post;
import model.Sarcina;
import model.ValidatorSarcina;

//import model.Sarcina;

public class SarcinaRepositorySer extends AbstractRepository<Integer,Sarcina> {
	protected String fileName;
	
	public SarcinaRepositorySer(String file)
	{
		super();
		validator = new ValidatorSarcina();
		this.fileName=file;
		readData();
	}
	
	@Override
	public void save(Sarcina e) throws ExceptiaMea
	{
		super.save(e);
		//this.saveData();
	}
	
	@Override
	public void updateObject(Integer id,Sarcina e) throws ExceptiaMea
	{
		super.updateObject(id, e);
		//this.saveData();
	}
	
	@Override
	
	public Sarcina delete(Integer id) throws ExceptiaMea
	{
		Sarcina e=super.delete(id);
		//this.saveData();
		return e;
	}
	
	@Override
	public void saveData() {
		// TODO Auto-generated method stub
		try
		{
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName));
			//List<Sarcina> sarc = (List<Sarcina>)entities;
			objectOutputStream.writeObject(entities);
			objectOutputStream.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void readData() {
		// TODO Auto-generated method stub
		try
		{
			ObjectInputStream objectInputStream  = new ObjectInputStream(new FileInputStream(fileName));
			
			//List<Sarcina> sarcini=(List<Sarcina>) objectInputStream.readObject();
			
			entities=(List<Sarcina>) objectInputStream.readObject();
			//entities=FXCollections.observableArrayList(sarcini);
			objectInputStream.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private List<Observer<Sarcina>> observers = new ArrayList<>();
	@Override
	public void addObserver(Observer<Sarcina> o) {
		// TODO Auto-generated method stub
		System.out.println("Add observer"+o.getClass());
        observers.add(o);
	}

	@Override
	public void removeObserver(Observer<Sarcina> o) {
		// TODO Auto-generated method stub
		System.out.println("Remove observer"+o.getClass());
        observers.remove(o);
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		for (Observer o : observers){
            o.update(this);
        }
	}

}
