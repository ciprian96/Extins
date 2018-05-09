package GUI;

import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Post;

public class SecondStage extends Stage {
//Label x = new Label("Second stage");
//VBox y = new VBox();

public SecondStage(PostView p){
    //y.getChildren().add(x);
	PostCrudView postView = new PostCrudView(p);
	Parent root2=postView.getView2();
	Scene scene2 = new Scene(root2,750,500);
	this.setScene(scene2);
	this.show();
   }    
}