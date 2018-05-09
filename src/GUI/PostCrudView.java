package GUI;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import Controller.PostController;
import Repository.ExceptiaMea;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import model.Post;

public class PostCrudView{
	BorderPane borderPane;
	
	ListView<Post> postList = new ListView<>();
	
	TextField fieldIdValue;
	TextField fieldDenumireValue;
	TextField fieldTipValue;
	TextField fieldSalariuValue; 
	
	TextField labelFirstNameValue;
    TextField labelLastNameValue;
    TextField labelEmailValue;
	
	Button buttonAdd=new Button("Add");
    Button buttonUpdate=new Button("Update");
    Button buttonDelete=new Button("Delete");
    Button buttonCauta = new Button("Cauta");
    
    Button buttonFiltrareTipPret=new Button("FiltrareTipPret");
    Button buttonFiltrarePret=new Button("FiltrarePret");
    
    PostView postView;
    
    PostControllerView ctrl;
    
    public PostCrudView(PostView postView)
    {
    	ctrl=new PostControllerView(this,postView.ctrl);
    	this.postView=postView;
        initBorderPane2();
        initButtons2();
    }
    
    
    private void initBorderPane2() {
        borderPane=new BorderPane();
        borderPane.setTop(initTop2());
        borderPane.setCenter(initCenter2());
        borderPane.setLeft(initLeft2());

    }
    private Label createLabel2(String s, int fontSize, Color c){
        Label l=new Label(s);
        l.setFont(new Font(15));
        l.setTextFill(c);
        return l;
    }
    
    private Node initTop2() {
        AnchorPane anchorPane=new AnchorPane();

        Label l=new Label("Post CRUD System");
        l.setFont(new Font(21));
        l.setStyle("-fx-font-weight: bold");
        
        AnchorPane.setTopAnchor(l,20d);
        AnchorPane.setRightAnchor(l,110d);
        anchorPane.getChildren().add(l);
               
        Image img = new Image("winter2.jpg");
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(180);
        imgView.setFitWidth(200);
        imgView.setPreserveRatio(true);
			
        AnchorPane.setLeftAnchor(imgView,20d);
        AnchorPane.setRightAnchor(imgView,10d);
        anchorPane.getChildren().add(imgView);
			
        return anchorPane;
    }
    
    public BorderPane getView2() {
        return borderPane;
    }
    
    private Node initCenter2() {
        AnchorPane anchorPane=new AnchorPane();   
        
        GridPane gridPostDetails=new GridPane();
        AnchorPane.setLeftAnchor(gridPostDetails,20d);
        AnchorPane.setTopAnchor(gridPostDetails,20d);
        ColumnConstraints c=new ColumnConstraints();
        c.setPrefWidth(100);
        gridPostDetails.getColumnConstraints().add(c);
        RowConstraints r=new RowConstraints();
        r.setPrefHeight(15);
        r.setMinHeight(30);
        gridPostDetails.getRowConstraints().add(r);
        
        Label labelId=createLabel2("ID:",12,Color.BLACK);
        labelId.setStyle("-fx-font-weight: bold");
        Label labelDenumire=createLabel2("Denumire:",12,Color.BLACK);
        labelDenumire.setStyle("-fx-font-weight: bold");
        Label labelTip=createLabel2("Tip:",12,Color.BLACK);
        labelTip.setStyle("-fx-font-weight: bold");
        Label labelSalariu=createLabel2("Salariu:",12,Color.BLACK);
        labelSalariu.setStyle("-fx-font-weight: bold");
        
        fieldIdValue=new TextField();
        fieldDenumireValue=new TextField();
        fieldTipValue=new TextField();
        fieldSalariuValue= new TextField();
        
        
        gridPostDetails.add(labelId,0,0);
        gridPostDetails.add(labelDenumire,0,1);
        gridPostDetails.add(labelTip,0,2);
        gridPostDetails.add(labelSalariu,0,3);
        
        gridPostDetails.add(fieldIdValue,1,0);
        gridPostDetails.add(fieldDenumireValue,1,1);
        gridPostDetails.add(fieldTipValue,1,2);
        gridPostDetails.add(fieldSalariuValue,1,3);

        anchorPane.getChildren().add(gridPostDetails);
        //init HBox Buuton

        HBox hb=new HBox(5, buttonAdd,buttonUpdate, buttonDelete, buttonCauta,buttonFiltrareTipPret,buttonFiltrarePret);
        //hb.getChildren().add(labelLastNameValue);
        AnchorPane.setBottomAnchor(hb,100d);
        AnchorPane.setLeftAnchor(hb,20d);
        //hb.setPadding(new Insets(30));
        anchorPane.getChildren().add(hb);
        
        return anchorPane;

    }
    
    private Node initLeft2() {
        AnchorPane anchorPane=new AnchorPane();
        //anchor the TableView into the ap
        AnchorPane.setLeftAnchor(postList,25d);
        AnchorPane.setTopAnchor(postList,25d);

        postList.setMinHeight(50d);
        postList.setPrefHeight(300d);
        postList.setItems(postView.model);
        
        fieldDenumireValue.setOnKeyPressed(new EventHandler<KeyEvent>(){

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				//postView.ctrl.cautaLista(fieldDenumireValue.getText());
				postList.setItems(postView.ctrl.cautaLista(fieldDenumireValue.getText()));
			}
        	
        });
        
        anchorPane.getChildren().add(postList);
        return anchorPane;
    }
    
    public void initButtons2(){
    	buttonAdd.setOnAction(ctrl.addButtonHandler());
    	
    	buttonDelete.setOnAction(ctrl.deleteButtonHandler());
    	
    	buttonUpdate.setOnAction(ctrl.updateButtonHandler());
    	
    	buttonCauta.setOnAction(ctrl.searchButtonHandler());
    	
    	this.buttonFiltrareTipPret.setOnAction(ctrl.filter1());
    	
    	this.buttonFiltrarePret.setOnAction(ctrl.filter2());
    }
}

