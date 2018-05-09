package GuiSarcina;

import Controller.PostController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ElementFisa;
import model.Post;
import model.Sarcina;

public class EntitateFisaController {
	@FXML
    private TableView<ElementFisa> fisaTable;
	//@FXML
    //private TableView<Post> fisaTablePost;
	//@FXML
    //private TableView<Sarcina> fisaTableSarcina;
    @FXML
    private TableColumn<ElementFisa, Integer> Id;
    
    @FXML
    private TableColumn<ElementFisa, Post> post;
    
    @FXML
    private TableColumn<ElementFisa, Sarcina> sarcina;
    
//    @FXML
//    private TableColumn<ElementFisa, Integer> Id2;
//    @FXML
//    private TableColumn<ElementFisa, Post> Id4;
//    @FXML
//    private TableColumn<ElementFisa, String> denumire;
//    
//    @FXML
//    private TableColumn<ElementFisa, String> tip;
//    
//    @FXML
//    private TableColumn<ElementFisa, Double> salariu;
//    @FXML
//    private TableColumn<ElementFisa, Integer> Id3;
//    @FXML
//    private TableColumn<ElementFisa, String> descriere;
    @FXML
    private TextField fieldId;
    @FXML
    private TextField fieldIdPost;
    @FXML
    private TextField fieldDenumirePost;
    @FXML
    private TextField fieldTipPost;
    @FXML
    private TextField fieldSalariuPost;
    @FXML
    private TextField fieldIdSarcina;
    @FXML
    private TextField fieldDescriereSarcina;
    @FXML
    private Button Adauga;
    @FXML
    private Button Sterge;
    @FXML
    private Button Modifica;
    
    private PostController ctrl; 
    
    ObservableList<ElementFisa> model;
    
    ObservableList<Post> modelPost;
    
    ObservableList<Sarcina> modelSarcina;
    
    @FXML
	public void setService(PostController ctrl) {
		// TODO Auto-generated method stub
		this.ctrl=ctrl;
		model=ctrl.model3.getModel();
		//modelPost=ctrl.model3.getModelPost();
		//modelSarcina=ctrl.model3.getModelSarcina();
		
		//System.out.println("fasfsafsafasfasfas");
        fisaTable.setItems(model);
        //fisaTablePost.setItems(modelPost);
        //fisaTableSarcina.setItems(modelSarcina);
	}
	
	@FXML
    private void initialize() {
		//Id.setCellValueFactory(new PropertyValueFactory<Sarcina, Integer>("id"));
        //descriere.setCellValueFactory(new PropertyValueFactory<Sarcina, String>("descriere"));
        
        Id.setCellValueFactory(new PropertyValueFactory<ElementFisa, Integer>("id"));
        
        this.post.setCellValueFactory(new PropertyValueFactory<ElementFisa, Post>("post"));
        
        this.sarcina.setCellValueFactory(new PropertyValueFactory<ElementFisa, Sarcina>("sarcina"));
        
//        Id4.setCellValueFactory(new PropertyValueFactory<ElementFisa,Post>("post"));
//        
//        //Id2.setCellValueFactory(new PropertyValueFactory<ElementFisa, Integer>("idPost"));
//        denumire.setCellValueFactory(new PropertyValueFactory<ElementFisa, String>("denumirePost"));
//        tip.setCellValueFactory(new PropertyValueFactory<ElementFisa, String>("tipPost"));
//        salariu.setCellValueFactory(new PropertyValueFactory<ElementFisa, Double>("salariuPost"));
//        Id3.setCellValueFactory(new PropertyValueFactory<ElementFisa, Integer>("idSarcina"));
//        descriere.setCellValueFactory(new PropertyValueFactory<ElementFisa, String>("descriereSarcina"));
        
//        if(model.size()==0)
//        	System.out.println("sfsafsa");
//        //fisaTable.setItems(model);
    }
	
	public void showSarcinaDetails(Sarcina value)
    {
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
	
	public void showPostDetails(Post value)
    {
        if (value==null)
        {
            fieldIdPost.setText("");
            fieldDenumirePost.setText("");
            fieldTipPost.setText("");
            fieldSalariuPost.setText("");
        }
        else
        {
        	fieldIdPost.setText(Integer.toString(value.getId()) );
        	fieldDenumirePost.setText(value.getDenumire());
        	fieldTipPost.setText(value.getTip());
        	fieldSalariuPost.setText( Double.toString(value.getSalariu()) );
        }
    }
	
	@FXML
	public void handleSave(){
		try
		{
			Integer id = Integer.parseInt(fieldId.getText());
			Integer idPost = Integer.parseInt(this.fieldIdPost.getText());
			String denumire = this.fieldDenumirePost.getText();
			String tip= this.fieldTipPost.getText();
			Double pret = Double.parseDouble(this.fieldSalariuPost.getText());
			Integer idSar = Integer.parseInt(this.fieldIdSarcina.getText());
			String des = this.fieldDescriereSarcina.getText();
			ctrl.addEntitatePost(id,idPost,denumire,tip,pret,idSar,des);
			showMessage(Alert.AlertType.INFORMATION,"Salvare cu succes","Element Fisa Adaugat!");
			clearFields();
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
			Integer id = Integer.parseInt(fieldId.getText());
			ctrl.deleteEntitatePost(id);
			showMessage(Alert.AlertType.INFORMATION,"Stergere cu succes","Element Fisa Eliminat!");
			clearFields();
		}
		catch(Exception e)
		{
			showErrorMessage(e.getMessage());
		}
	}
	
	@FXML
	public void handleUpdate(){
		try
		{
			Integer id = Integer.parseInt(fieldId.getText());
			Integer idSar = Integer.parseInt(this.fieldIdSarcina.getText());
			String des = this.fieldDescriereSarcina.getText();
			ctrl.updateEntitatePost(id,idSar,des);
			showMessage(Alert.AlertType.INFORMATION,"Modificare cu succes","Element Fisa Modificat!");
			clearFields();
		}
		catch(Exception e)
		{
			showErrorMessage(e.getMessage());
		}
	}
	
	private void clearFields() {
		this.fieldId.setText("");
		fieldIdPost.setText("");
        fieldDenumirePost.setText("");
        fieldTipPost.setText("");
        fieldSalariuPost.setText("");
        fieldIdSarcina.setText("");
    	fieldDescriereSarcina.setText("");
    }
	static void showMessage(Alert.AlertType type, String header, String text){
        Alert message=new Alert(type);
        message.setHeaderText(header);
        message.setContentText(text);
        message.showAndWait();
    }

    static void showErrorMessage(String text){
        Alert message=new Alert(Alert.AlertType.ERROR);
        message.setTitle("Mesaj eroare");
        message.setContentText(text);
        message.showAndWait();
    }
    
    @FXML
    public void tableSelected()
    {
    	ElementFisa el= fisaTable.getSelectionModel().getSelectedItem();
    	if(el!=null)
    	{showPostDetails(el.getPost());
    	showSarcinaDetails(el.getSarcina());
    	this.fieldId.setText(el.getId().toString());
    	}
    }
}
