package model;


import java.util.ArrayList;
import java.util.List;

import Repository.RepositoryFromXMLFile;
import Repository.SarcinaRepositorySer;
import Utils.Observable;
import Utils.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SarcinaModel implements Observer<Sarcina> {
	ObservableList<Sarcina> model;
	RepositoryFromXMLFile repository;

    public SarcinaModel(RepositoryFromXMLFile sarcini){
        this.repository = sarcini;
        sarcini.addObserver(this);
        
        /*
         Sarcina: 1   Implementare
Sarcina: 2   Cautare
Sarcina: 3   Dirijare
Sarcina: 4   Dezvoltare
Sarcina: 5   Asistenta
Sarcina: 6   Asigurare management firma
Sarcina: 8   Identificare oportunitati de afaceri
Sarcina: 9   Atragerea de noi clienti
Sarcina: 7   Asigura buna gestionare a patrimoniului societatii
Sarcina: 10   Sortare si inregistrare corespondenta
Sarcina: 11   Preluare si directionare apeluri telefonice
         */
        //List<Sarcina> l=new ArrayList<Sarcina>();
        model = FXCollections.observableArrayList(sarcini.getAll());
        //model = FXCollections.observableArrayList(stude);
    }

    public ObservableList<Sarcina> getModel(){
    	System.out.println(model.size());
        return model;
    }

    @Override
    public void update(Observable<Sarcina> e) {
        model.setAll(repository.getAll());
    }
}
