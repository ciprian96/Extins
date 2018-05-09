package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

import Controller.PostController;
import GuiSarcina.EditGuiMain;
import Repository.ElementFisaRepository;
import Repository.PostRepositoryFromFile;
import Repository.RepositoryFromXMLFile;
import model.*;

public class Main extends Application {

	BorderPane rootLayout;
	Stage primaryStage;
	PostController controller;
	RepositoryFromXMLFile repo;
	PostRepositoryFromFile repo2;
	SarcinaModel model;
	PostDataModel model2;
    
    @Override
    public void start(Stage primaryStage) throws IOException
    {
    	repo = new RepositoryFromXMLFile("./src/Sarcini.xml");
    	
    	repo2 = new PostRepositoryFromFile("./src/Posturi.txt");
    	
    	ElementFisaRepository repo3 = new ElementFisaRepository("./src/ElementeFisa.txt");
        controller = new PostController(repo2,repo,repo3);  
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/GuiSarcina/GuiMain.fxml"));
        AnchorPane anchorPane = loader.load();
        
      Image img = new Image("sno.jpg");
      ImageView imgView = new ImageView(img);
      imgView.setFitHeight(200);
      imgView.setFitWidth(280);
      imgView.setPreserveRatio(true);
		
      AnchorPane.setBottomAnchor(imgView, 40d);
      AnchorPane.setLeftAnchor(imgView,100d);
      anchorPane.getChildren().add(imgView);
        
      anchorPane.setStyle("-fx-background-image: url('sno.jpg')");
        
        EditGuiMain editMain=loader.getController();
        editMain.setService(controller);
        
        Scene scene = new Scene(anchorPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Welcome");
        primaryStage.show();
        
        primaryStage.setOnCloseRequest(event -> {
            controller.rescrieFisiere();
        });
    }
	public static void main(String[] args) {
        launch(args);
    }
}