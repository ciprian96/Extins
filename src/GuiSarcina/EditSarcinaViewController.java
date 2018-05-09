package GuiSarcina;

import Controller.PostController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Sarcina;

public class EditSarcinaViewController {
	@FXML
	private TextField idSarcina;
	
	@FXML
	private TextField descriere;
	
	@FXML
	private Button Add;
	
	@FXML
	private Button Delete;
	@FXML
	private Button Update;
	@FXML
	private Button Cancel;
	
	private PostController ctrl;
	static Stage dialogStage;
	
	public EditSarcinaViewController(){
		
	}

	public void setService(PostController service, Stage dialogStage) {
		// TODO Auto-generated method stub
		this.ctrl = service;
        EditSarcinaViewController.dialogStage=dialogStage;
	}
	
	@FXML
    private void initialize() {
    }
	
	@FXML
	public void handleSave(){
		try
		{
			Integer id = Integer.parseInt(idSarcina.getText());
			String des = descriere.getText();
			ctrl.addSarcinaBasic(id, des);
			showMessage(Alert.AlertType.INFORMATION,"Salvare cu succes","Sarcina a fost adaugata!");
			clearFields();
			dialogStage.close();
		}
		catch(Exception e)
		{
			showErrorMessage(e.getMessage());
		}
	}
	
	@FXML
	public void handleDelete(){
		try
		{
			Integer id = Integer.parseInt(idSarcina.getText());
			ctrl.deleteSarcina(id);
			showMessage(Alert.AlertType.INFORMATION,"Stergere cu succes","Sarcina a fost stearsa!");
			clearFields();
		}
		catch(Exception e)
		{
			showErrorMessage(e.getMessage());
		}
		//dialogStage.close();
	}
	
	@FXML
	public void handleUpdate()
	{
		try
		{
			Integer id=Integer.parseInt(idSarcina.getText());
			String des = descriere.getText();
			ctrl.updateSarcina(id, des);
			showMessage(Alert.AlertType.INFORMATION,"Modificare cu succes","Sarcina a fost modificata!");
			clearFields();
		}
		catch(Exception e)
		{
			this.showErrorMessage(e.getMessage());
		}
		dialogStage.close();
	}
	
	
	public void populateFields(Sarcina s)
	{
		idSarcina.setText(s.getId().toString());
		descriere.setText(s.getDescriere());
	}
	
	 private void clearFields() {
	        idSarcina.setText("");
	        descriere.setText("");
	    }

	    @FXML
	    public void handleCancel(){
	        dialogStage.close();
	    }


	    static void showMessage(Alert.AlertType type, String header, String text){
	        Alert message=new Alert(type);
	        message.setHeaderText(header);
	        message.setContentText(text);
	        message.initOwner(dialogStage);
	        message.showAndWait();
	    }

	    static void showErrorMessage(String text){
	        Alert message=new Alert(Alert.AlertType.ERROR);
	        message.initOwner(dialogStage);
	        message.setTitle("Mesaj eroare");
	        message.setContentText(text);
	        message.showAndWait();
	    }
}
