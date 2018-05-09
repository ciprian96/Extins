package Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.HasId;
import model.Post;
import model.Validator;

public abstract class AbstractRepository<ID,E extends HasId<ID>> implements IRepository<ID,E> {
	protected List<E> entities = new ArrayList<E>();
	//ObservableList<E> entities = FXCollections.observableArrayList();
	//ObservableList<Post> studentDataModel = FXCollections.observableArrayList();
	public Validator<E> validator;
	
	@Override
	public void save(E e) throws ExceptiaMea {
		// TODO Auto-generated method stub
		if(this.existObject(e.getId())==true)
			throw new ExceptiaMea("Mai exista un obiect cu id: "+e.getId());
		this.entities.add(e);
		//entities.forEach(System.out::println);
		notifyObservers();
	}

	@Override
	
	public E delete(ID id) throws ExceptiaMea {
		// TODO Auto-generated method stub
		//Sterge inregistrarea care are id-ul dat
		//daca nu gaseste inregistrarea arunca exceptie
		E entity = this.findObjectId(id);
		if(entity==null)
			throw new ExceptiaMea("Nu exista inregistrat obiect cu id: "+id);
		entities.remove(entity);
		notifyObservers();
		return entity;
	}

	@Override
	public void updateObject(ID id, E e) throws ExceptiaMea {
		// TODO Auto-generated method stub
		//Schimba entititatea cu id-ul dat
		//daca nu este inregistrata arunca exceptie
		if(this.existObject(id)==false)
			throw new ExceptiaMea("Nu exista obiect cu id: "+id);
		this.delete(id);
		notifyObservers();
		this.save(e);
	}

	@Override
	public List<E> getAll() {
		// TODO Auto-generated method stub
		//return entities;
		//ObservableList<E> postDataModel = FXCollections.observableArrayList(entities);
        return entities;
	}
	
	
	@Override
	public boolean existObject(ID id) {
		// TODO Auto-generated method stub
		for(E entity:entities)
		{
			if(entity.getId().equals(id))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public E findObjectId(ID id) {
		// TODO Auto-generated method stub
		//cauta un Obiect dupa id
		//returneaza Obiectul daca exista sau null altfel
		for(E entity:entities)
		{
			if(entity.getId().equals(id))
			{
				return entity;
			}
		}
		return null;
	}
	
	public void addColection(ObservableList<E> ent)
	{
		entities.clear();
		entities.addAll(ent);
	}
	
	public int size()
	{
		return entities.size();
	}
}
