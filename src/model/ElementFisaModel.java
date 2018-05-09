package model;

import java.util.ArrayList;
import java.util.List;

import Repository.ElementFisaRepository;
import Repository.PostRepositoryFromFile;
import Repository.RepositoryFromXMLFile;
import Utils.Observable;
import Utils.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ElementFisaModel implements Observer<ElementFisa>{
    ObservableList<ElementFisa> model;
    ObservableList<Post> model2;
    ObservableList<Sarcina> model3;
    ElementFisaRepository repository;
    
    public ElementFisaModel(ElementFisaRepository repository){
        this.repository = repository;
        repository.addObserver(this);
        model = FXCollections.observableArrayList(repository.getAll());
        model2 = FXCollections.observableArrayList(repository.getAllPost());
        model3= FXCollections.observableArrayList(repository.getAllSarcini());
    }

    public ObservableList<ElementFisa> getModel(){
    	System.out.println(model.size());
    	return model;
    }
    
    public ObservableList<Post> getModelPost(){
    	//System.out.println(model.size());
    	return model2;
//    	List<Post> l = new ArrayList<>();
//    	for(ElementFisa el:model)
//    	{
//    		l.add(el.getPost());
//    	}
//    	model2= FXCollections.observableArrayList(l);
//    	return model2;
    }
    
    public ObservableList<Sarcina> getModelSarcina(){
    	//System.out.println(model.size());
    	return model3;
//    	List<Sarcina> l = new ArrayList<>();
//    	for(ElementFisa el:model)
//    	{
//    		l.add(el.getSarcina());
//    	}
//    	model3= FXCollections.observableArrayList(l);
//    	return model3;
    }
    
    @Override
    public void update(Observable<ElementFisa> e) {
        model.setAll(repository.getAll());
    }
}