package Tests;

import Controller.PostController;
import Repository.ElementFisaRepository;
import Repository.ExceptiaMea;
import Repository.PostRepositoryFromFile;
import Repository.RepositoryFromXMLFile;
import model.ElementFisaModel;
import model.Post;
import model.PostDataModel;
import model.SarcinaModel;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import static org.junit.Assert.assertEquals;

public class TestController {
    private static PostRepositoryFromFile posturi;
    private static RepositoryFromXMLFile sarcini;

    private static ElementFisaRepository elementeFisa;

    private static List<Observer> observers = new ArrayList<>();

    private static SarcinaModel model;
    private static PostDataModel model2;
    private static ElementFisaModel model3;

    private static PostController postController;
    @BeforeClass
    public static void initialize(){
        posturi = new PostRepositoryFromFile("./src/Posturi.txt");


        sarcini=new RepositoryFromXMLFile("./src/Sarcini.xml");
        elementeFisa=new ElementFisaRepository("./src/ElementeFisa.txt");

        model = new SarcinaModel(sarcini);
        model2 = new PostDataModel(posturi);
        model3 = new ElementFisaModel(elementeFisa);

        postController=new PostController(posturi,sarcini,elementeFisa);
    }

    @Test
    public void testCauta(){
        int nrPost = postController.getPosturi().size();

        try {
            postController.addPost(new Post(21,"Administrator","fulltime",3000.0));
            assertEquals(nrPost+1,postController.getPosturi().size());
        } catch (ExceptiaMea exceptiaMea) {
            exceptiaMea.printStackTrace();
        }

    }
}
