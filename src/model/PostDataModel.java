package model;

import Repository.PostRepositoryFromFile;
import Utils.Observable;
import Utils.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PostDataModel implements Observer<Post>{
	    ObservableList<Post> model;
	    PostRepositoryFromFile repository;

	    public PostDataModel(PostRepositoryFromFile repository){
	        this.repository = repository;
	        repository.addObserver(this);
	        model = FXCollections.observableArrayList(repository.getAll());
	    }

	    public ObservableList<Post> getModel(){
	    	System.out.println(model.size());
	        return model;
	    }

	    @Override
	    public void update(Observable<Post> e) {
	        model.setAll(repository.getAll());
	    }
}
