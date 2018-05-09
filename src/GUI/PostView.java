package GUI;



import javax.swing.JTextField;

import javax.swing.border.Border;

import Controller.PostController;
import GuiSarcina.EntitateFisaController;
//import TablePager.Person;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import model.Post;
import model.Sarcina;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Callback;


import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class PostView {
    BorderPane borderPane;
    
    TableView<Post> postTable=new TableView<>();
    TableColumn<Post,Integer> id=new TableColumn<>("Id");
    TableColumn<Post,String> denumire=new TableColumn<>("Denumire");
    TableColumn<Post,String> tip=new TableColumn<>("Tip");
    TableColumn<Post,Double> salariu=new TableColumn<>("Salariu");
    
    Label labelIdValue;
    Label labelDenumireValue;
    Label labelTipValue;
    Label labelSalariuValue;

    PostController ctrl;
    ObservableList<Post> model;
    
    EntitateFisaController viewCtrlFisa=null;
    
    //PostControllerView ctr;
	
    public PostView(ObservableList<Post> list,PostController ps)
    {
        //this.model=model;
        ctrl=ps;
        
        this.model=list;
        initBorderPane();
    }

    public BorderPane getView() {
        return borderPane;
    }

    private void initBorderPane() {
        borderPane=new BorderPane();
        borderPane.setTop(initTop());
        borderPane.setCenter(initCenter());
        borderPane.setLeft(initLeft());

    }
    
    public void setServiceViewFisa(EntitateFisaController viewCtrlFisa)
    {
    	this.viewCtrlFisa=viewCtrlFisa;
    }
    private Node initTop() {
        AnchorPane anchorPane=new AnchorPane();

        Label l=new Label("Post System");
        l.setFont(new Font(22));
        l.setStyle("-fx-font-weight: bold");
        AnchorPane.setTopAnchor(l,20d);
        AnchorPane.setRightAnchor(l,150d);
        anchorPane.getChildren().add(l);

        Image img = new Image("winter.jpg");
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(150);
        imgView.setFitWidth(200);
        imgView.setPreserveRatio(true);
			
        AnchorPane.setLeftAnchor(imgView,20d);
        AnchorPane.setRightAnchor(imgView,10d);
        anchorPane.getChildren().add(imgView);
		
        return anchorPane;
    }

    private Label createLabel(String s, int fontSize, Color c){
        Label l=new Label(s);
        l.setFont(new Font(15));
        l.setTextFill(c);
        return l;
    }

    private Node initCenter() {
        AnchorPane anchorPane=new AnchorPane();

        //init GridPane Post details
        
        
        GridPane gridPostDetails=new GridPane();
        AnchorPane.setLeftAnchor(gridPostDetails,25d);
        AnchorPane.setTopAnchor(gridPostDetails,25d);
        ColumnConstraints c=new ColumnConstraints();
        c.setPrefWidth(100);
        gridPostDetails.getColumnConstraints().add(c);
        RowConstraints r=new RowConstraints();
        r.setPrefHeight(15);
        r.setMinHeight(30);
        gridPostDetails.getRowConstraints().add(r);
        
        
        Label labelId=createLabel("ID:",12,Color.BLACK);
        labelId.setStyle("-fx-font-weight: bold");
        Label labelDenumire=createLabel("Denumire:",12,Color.BLACK);
        labelDenumire.setStyle("-fx-font-weight: bold");
        Label labelTip=createLabel("Tip:",12,Color.BLACK);
        labelTip.setStyle("-fx-font-weight: bold");
        Label labelSalariu=createLabel("Salariu:",12,Color.BLACK);
        labelSalariu.setStyle("-fx-font-weight: bold");
        
        labelIdValue=createLabel("Aprogramatoarei",12,Color.CRIMSON);
        labelDenumireValue=createLabel("Lucica",12,Color.CRIMSON);
        labelTipValue=createLabel("lucicaApr@yahoo.com",12,Color.CRIMSON);
        labelSalariuValue=createLabel("lucicaApr@yahoo.com",12,Color.CRIMSON);
        
        gridPostDetails.add(labelId,0,0);
        gridPostDetails.add(labelDenumire,0,1);
        gridPostDetails.add(labelTip,0,2);
        gridPostDetails.add(labelSalariu,0,3);
        gridPostDetails.add(labelIdValue,1,0);
        gridPostDetails.add(labelDenumireValue,1,1);
        gridPostDetails.add(labelTipValue,1,2);
        gridPostDetails.add(labelSalariuValue,1,3);

        anchorPane.getChildren().add(gridPostDetails);
        
        return anchorPane;

    }

    private Node initLeft() {
        AnchorPane anchorPane=new AnchorPane();
        //anchor the TableView into the ap
       
        //AnchorPane.setLeftAnchor(postTable,25d);
        //AnchorPane.setTopAnchor(postTable,25d);

        //postTable.setMinHeight(50d);
        //postTable.setPrefHeight(300d);
        //initTableView();
        int numOfPages = 1;
        if (model.size() % rowsPerPage() == 0) {
          numOfPages = model.size() / rowsPerPage();
        } else if (model.size() > rowsPerPage()) {
          numOfPages = model.size() / rowsPerPage() + 1;
        }
        Pagination pagination = new Pagination((numOfPages), 0);
        pagination.setPageFactory(this::createPage);
        
        
//        pagination = new Pagination((model.size() / rowsPerPage() + 1), 0);
//        //   pagination = new Pagination(20 , 0);
//        pagination.setStyle("-fx-border-color:red;");
//        pagination.setPageFactory(new Callback<Integer, Node>() {
//            @Override
//            public Node call(Integer pageIndex) {
//                if (pageIndex > model.size() / rowsPerPage() + 1) {
//                    return null;
//                } else {
//                    return createPage(pageIndex);
//                }
//            }
//        });
        
        
        AnchorPane.setTopAnchor(pagination, 10.0);
        AnchorPane.setRightAnchor(pagination, 10.0);
        AnchorPane.setBottomAnchor(pagination, 10.0);
        AnchorPane.setLeftAnchor(pagination, 10.0);
        
        anchorPane.getChildren().add(pagination);
        return anchorPane;
    }
    	

    public void initTableView()
    {       
        id.setCellValueFactory(new PropertyValueFactory<Post, Integer>("Id"));
        
        salariu.setCellValueFactory(new PropertyValueFactory<Post, Double>("Salariu"));
       
        
        postTable.setEditable(true);
        
        tip =  new TableColumn<>("tip");
        tip.setMinWidth(100);
        tip.setCellValueFactory(
            new PropertyValueFactory<>("tip"));
        
        
        tip.setCellFactory(TextFieldTableCell.<Post>forTableColumn());
        
        tip.setOnEditCommit
        (
            (CellEditEvent<Post, String> t) -> {
            	if(t.getNewValue().compareToIgnoreCase("fulltime")==1 || t.getNewValue().compareToIgnoreCase("partime")==1)
            	{	
                ((Post) t.getTableView().getItems().get
                		(
                			t.getTablePosition().getRow())
                        ).setTip(t.getNewValue());
            	}
            	else
            	{
            		showMessage(Alert.AlertType.INFORMATION,"Tipul invalid","Tipul trebuie sa fie fulltime sau partime!");
            	}
            //ctrl.getPosturi().forEach(System.out::println);
        });
        
        denumire =  new TableColumn<>("denumire");
        denumire.setMinWidth(100);
        denumire.setCellValueFactory(
            new PropertyValueFactory<>("denumire"));
        
        denumire.setCellFactory(TextFieldTableCell.<Post>forTableColumn());
        denumire.setOnEditCommit
        (
            (CellEditEvent<Post, String> t) -> {
                ((Post) t.getTableView().getItems().get
                		(
                			t.getTablePosition().getRow())
                        ).setDenumire(t.getNewValue());
            
            //ctrl.getPosturi().forEach(System.out::println);
        });
                   
         postTable.setItems(model);
//		
         postTable.getColumns().addAll(id, denumire, tip,salariu);
        // Auto resize columns
        postTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // clear Post
        showPostDetails(null);
        // Listen for selection changes and show the Post details when changed.
        postTable.getSelectionModel().selectedItemProperty().addListener(
                (observable,oldvalue,newValue)->showPostDetails(newValue) );
        
        
        postTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Post>() {
            @Override
            public void changed(ObservableValue<? extends Post> observable,
                                Post oldValue, Post newValue) {
            	//System.out.println("element selectat");
                showPostDetails(newValue);
            }
        });
    }
    
    private Pagination pagination;

    public int itemsPerPage() {
        return 1;
    }

    public int rowsPerPage() {
        return 6;
    }

    @SuppressWarnings("unchecked")
	public VBox createPage(int pageIndex) {
        int lastIndex = 0;
        int displace = model.size() % rowsPerPage();
        if (displace > 0) {
            lastIndex = model.size() / rowsPerPage();
        } else {
            lastIndex = model.size() / rowsPerPage() - 1;

        }

        VBox box = new VBox(5);
        int page = pageIndex * itemsPerPage();

        for (int i = page; i < page + itemsPerPage(); i++) {
            TableView<Post> table = new TableView<Post>();
            
            id.setCellValueFactory(new PropertyValueFactory<Post, Integer>("Id"));
            //stabilirea valorilor asociate unei celule
            
            denumire.setCellValueFactory(new PropertyValueFactory<Post, String>("denumire")); //
            
            // return an ReadOnlyObjectWrapper  if Post class don't have a firstName Property attribute
            tip.setCellValueFactory(new PropertyValueFactory<Post, String>("tip"));
            
            salariu.setCellValueFactory(new PropertyValueFactory<Post, Double>("Salariu"));

            id.setMinWidth(20);
            
            denumire.setMinWidth(160);

            tip.setMinWidth(160);
            
            salariu.setMinWidth(50);
            table.getColumns().addAll(id, denumire, tip, salariu);
            
            if (lastIndex == pageIndex) {
                table.setItems(FXCollections.observableArrayList(model.subList(pageIndex * rowsPerPage(), pageIndex * rowsPerPage() + displace)));
            } else {
                table.setItems(FXCollections.observableArrayList(model.subList(pageIndex * rowsPerPage(), pageIndex * rowsPerPage() + rowsPerPage())));
            }
            
            showPostDetails(null);
            // Listen for selection changes and show the Post details when changed.
            table.getSelectionModel().selectedItemProperty().addListener(
                    (observable,oldvalue,newValue)->showPostDetails(newValue) );

            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            
            box.getChildren().add(table);
        }
        return box;
    }
    
    static void showMessage(Alert.AlertType type, String header, String text){
        Alert message=new Alert(type);
        message.setHeaderText(header);
        message.setContentText(text);
        //message.initOwner(dialogStage);
        message.showAndWait();
    }

	public void showPostDetails(Post value)
    {
		if(this.viewCtrlFisa!=null)
		{
			this.viewCtrlFisa.showPostDetails(value);
		}
        if (value==null)
        {
            labelIdValue.setText("");
            labelDenumireValue.setText("");
            labelTipValue.setText("");
            labelSalariuValue.setText("");
        }
        else
        {
        	labelIdValue.setText(Integer.toString(value.getId()) );
            labelDenumireValue.setText(value.getDenumire());
            labelTipValue.setText(value.getTip());
            labelSalariuValue.setText( Double.toString(value.getSalariu()) );
        }
    }  
}
