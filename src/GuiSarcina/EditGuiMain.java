package GuiSarcina;

import java.io.IOException;

import Controller.PostController;
import GUI.PostView;
import GUI.SecondStage;
import Repository.PostRepositoryFromFile;
import Repository.SarcinaRepositorySer;
import Utils.Observer;
import application.Main;
import javafx.beans.InvalidationListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Sarcina;
import model.Post;
import model.SarcinaModel;

public class EditGuiMain {
	@FXML
	protected Button Post;
	@FXML
	protected Button Sarcina;
	
	Stage primaryStage1;
	Stage primaryStage2;
	
	BorderPane rootLayout;
	
	ObservableList<Sarcina> modelSarcina;
	
	ObservableList<Post> modelPost;
	
	EntitateFisaController viewCtrlFisa=null;
	
	SarcinaViewController SarcinaviewCtrl=null;
	
	PostView postView =null;
	
	@FXML
    public void handleSarcina()
    {
		try{
        showSarcinaEditDialog();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
    }
	
	@FXML
	protected PostController ctrl;
	
	//PostRepositoryFromFile repoPost;
	//SarcinaRepositorySer repoSarcina;
	
	@FXML
    private void initialize() {
		
	}
	
	@FXML
    public void setService(PostController service)
    {
		this.ctrl=service;
		this.modelPost=ctrl.model2.getModel();
		this.modelSarcina=ctrl.model.getModel();
    }
	
	@FXML
    public void showSarcinaEditDialog() throws IOException {
            // Load the fxml file and create a new stage for the popup dialog.
        	
			//this.Sarcina.setDisable(true);
			
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SarcinaViewController.class.getResource("SarcinaView.fxml"));
            BorderPane root = (BorderPane) loader.load();

            root.setStyle("-fx-background-image: url('snow.jpg')");
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Sarcina");
            //dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primaryStage);
            
            Scene scene = new Scene(root, 800, 500);
            dialogStage.setScene(scene);
            
            SarcinaviewCtrl=loader.getController();
            
            SarcinaviewCtrl.setService(ctrl);
            
            if(viewCtrlFisa!=null)
            {
            	System.out.println("functioneaza bine");
            	SarcinaviewCtrl.setServiceEntitateFisa(viewCtrlFisa);
            }
            
            dialogStage.show();
            
            //Sarcina.disabledProperty();
            //Sarcina.disableProperty();
    }
	
    @FXML
    public void showEntitateFisa() throws IOException {
        // Load the fxml file and create a new stage for the popup dialog.
    	
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EntitateFisaController.class.getResource("ElementFisaView.fxml"));
        BorderPane root = (BorderPane) loader.load();
        
        root.setStyle("-fx-background-image: url('sno.jpg')");
        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Entitate Fisa");
        Scene scene = new Scene(root, 800, 500);
        dialogStage.setScene(scene);
        
        viewCtrlFisa=loader.getController();
        viewCtrlFisa.setService(ctrl);
        
        if(SarcinaviewCtrl!=null)
        {
        	SarcinaviewCtrl.setServiceEntitateFisa(viewCtrlFisa);
        }
        
        if(postView!=null)
        {
        	postView.setServiceViewFisa(viewCtrlFisa);
        }
        dialogStage.show(); 
    }
    
    @FXML
    public void handlePost()
    {
		try{
        showPostEditDialog();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
    }
    
	private void showPostEditDialog() {
		// TODO Auto-generated method stub
		postView = new PostView(modelPost, ctrl);
		if(viewCtrlFisa!=null)
			postView.setServiceViewFisa(viewCtrlFisa);
		
		Parent root=postView.getView();
        Scene scene = new Scene(root, 750, 500);
        Stage stage = new Stage();
        stage.setTitle("Welcome!!");
        stage.setScene(scene);
        stage.show();
        
        new SecondStage(postView);
	}
}
