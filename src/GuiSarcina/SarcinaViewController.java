package GuiSarcina;




import model.Post;
import model.Sarcina;


import java.io.IOException;

import Controller.PostController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import GuiSarcina.EditingCell;
import Utils.Observer;

public class SarcinaViewController  {
	  @FXML
	    private TableView<Sarcina> sarcinaTable;
	    @FXML
	    private TableColumn<Sarcina, Integer> Id;
	    @FXML
	    private TableColumn<Sarcina, String> descriere;
	    @FXML
	    private TextField fieldIdSarcina;
	    @FXML
	    private TextField fieldDescriereSarcina;
	    
	    @FXML
	    private Button EditTable;
	    
	    private TextField textField;
	    
	    PostController service;
	    ObservableList<Sarcina> model;
	    
	    EditSarcinaViewController controller=null;
	    
	    EntitateFisaController viewCtrlFisa=null;
	    /**
	     * The constructor.
	     * The constructor is called before the initialize() method.
	     */
	    public SarcinaViewController() {
	    	//this.service=service;
	        //this.model=FXCollections.observableArrayList(service.getSarcini());
	    }
	    
	    @FXML
	    public void setService(PostController service) {
	        this.service=service;
	        this.model=service.model.getModel();
	        
	        //this.model= FXCollections.observableArrayList(service.getSarcini());
//	        sarcinaTable.setItems(model);
	    }
	    
	    public void setServiceEntitateFisa(EntitateFisaController viewCtrlFisa){
	    	System.out.println("ajunge aici");
	    	this.viewCtrlFisa=viewCtrlFisa;
	    }
	    @FXML
	    private void initialize() {
	        Id.setCellValueFactory(new PropertyValueFactory<Sarcina, Integer>("id"));
	        descriere.setCellValueFactory(new PropertyValueFactory<Sarcina, String>("descriere"));
	        
	    }
	@FXML    
	public void handlerColumDescriere()
	{
		//System.out.println("dksadsak");
		descriere.setOnEditCommit(
				
                new EventHandler<CellEditEvent<Sarcina, String>>() {
                    @Override
                    public void handle(CellEditEvent<Sarcina, String> t) {
                        ((Sarcina) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setDescriere(t.getNewValue());
                    }
                 }
            );     
	}
    public void showPostDetails(Sarcina value)
    {
    	if(this.viewCtrlFisa!=null)
    		this.viewCtrlFisa.showSarcinaDetails(value);
        if (value==null)
        {
        	fieldIdSarcina.setText("");
        	fieldDescriereSarcina.setText("");
        }
        else
        {
        	fieldIdSarcina.setText(Integer.toString(value.getId()) );
        	fieldDescriereSarcina.setText(value.getDescriere());
        }
    }
    
    @FXML
    public void handleSarcina()
    {
        showSarcinaEditDialog();
    }
    
    @FXML
    public void handleButtonFilter1()
    {
    	try{
    	Integer l = Integer.parseInt(fieldIdSarcina.getText());
    	String s = fieldDescriereSarcina.getText();
    	
    	sarcinaTable.setItems(FXCollections.observableArrayList(service.filtrareSarcini1(l, s)));
    	}
    	catch(Exception e)
    	{
    		Alert message=new Alert(Alert.AlertType.ERROR);
	        message.setTitle("Mesaj eroare");
	        message.setContentText(e.getMessage());
	        message.showAndWait();
    	}
    }
    @FXML
    public void tableSelected()
    {
    	Sarcina s= sarcinaTable.getSelectionModel().getSelectedItem();
    	if(controller!=null)
    	{	controller.populateFields(s);}
    	showPostDetails(s);
    }
    
    @FXML
    public void loadData()
    {
    	sarcinaTable.setItems(model);
    }

    public void showSarcinaEditDialog() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SarcinaViewController.class.getResource("EditSarcinaView.fxml"));
            AnchorPane root = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Sarcina");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primaryStage);
            
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);

            controller = loader.getController();
            controller.setService(service, dialogStage);

            dialogStage.show();

     } catch (IOException e) {
          e.printStackTrace();
        }
    }
}
