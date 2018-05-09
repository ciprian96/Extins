package GUI;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Controller.PostController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Post;

public class PostControllerView {
	private PostCrudView postCrudView;
	private PostController controller;

	/*public PostControllerView(PostCrudView postCrudView2, PostController ctrl) {
		// TODO Auto-generated constructor stub
	}*/

	public PostControllerView(PostCrudView postView, PostController ctr)
	{
		this.postCrudView=postView;
		this.controller=ctr;
	}
	
	public EventHandler<ActionEvent> addButtonHandler(){
		
		return new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {	
					String idString =postCrudView.fieldIdValue.getText();
					
					Integer id = Integer.parseInt(idString);
					
					String den = postCrudView.fieldDenumireValue.getText();
					String tip = postCrudView.fieldTipValue.getText();
					String salString = postCrudView.fieldSalariuValue.getText();
					
					Double sal = Double.parseDouble(salString);
					
					controller.addPostBasic(id, den, tip, sal);
						//ctrl.addPost(p);
						//postView.adaugaPost(p);
						
						//model.add(p);
						
					} catch (Exception e) {
						Component frame = null;
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(frame,
							    e.getMessage(),
							    "Error",
							    JOptionPane.ERROR_MESSAGE);
					}
			}
			
		};
	}

	public EventHandler<ActionEvent> deleteButtonHandler() {
		// TODO Auto-generated method stub
		return new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				try{
					String idString =postCrudView.fieldIdValue.getText();
					Integer id = Integer.parseInt(idString);
					//postView.
					controller.removePost(id);
					//postView.stergePost(id);
				}
				catch(Exception e)
				{
					Component frame = null;
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(frame,
						    e.getMessage(),
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
				}
			
		};
	}

	public EventHandler<ActionEvent> updateButtonHandler() {
		// TODO Auto-generated method stub
		return new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					String idString =postCrudView.fieldIdValue.getText();
					Integer id = Integer.parseInt(idString);
					String den = postCrudView.fieldDenumireValue.getText();
					String tip = postCrudView.fieldTipValue.getText();
					String salString = postCrudView.fieldSalariuValue.getText();
					Double sal = Double.parseDouble(salString);
					
					Post p =new Post(id,den,tip,sal);
					//postView.ctrl.updatePost(id, p);
					controller.updatePostBasic(id,den,tip,sal);
					postCrudView.postView.showPostDetails(p);
					//postView.UpdatePost(id,p);
						
					} catch (Exception e) {
						Component frame = null;
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(frame,
							    e.getMessage(),
							    "Error",
							    JOptionPane.ERROR_MESSAGE);
					}
				}
		};
	}

	public EventHandler<ActionEvent> searchButtonHandler() {
		// TODO Auto-generated method stub
		return new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try{
				String idString =postCrudView.fieldIdValue.getText();
				Integer id = Integer.parseInt(idString);
				
				
				List<Post> post = new ArrayList<>();
				post.add(controller.cautaPost(id));
				
				ObservableList<Post> posts = FXCollections.observableArrayList(post);
				postCrudView.postList.setItems(posts);
				}
				catch(Exception e)
				{
					Component frame = null;
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(frame,
						    e.getMessage(),
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
    	};
	}

	public EventHandler<ActionEvent> filter1() {
		// TODO Auto-generated method stub
		return new EventHandler<ActionEvent>(){
			
		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub
			try{
			Double pret  = Double.parseDouble(postCrudView.fieldSalariuValue.getText());
			String tip = postCrudView.fieldTipValue.getText();
			
			postCrudView.postList.setItems(FXCollections.observableArrayList(controller.filtrarePost1(tip, pret)));
			}
			catch(Exception e)
			{
				Component frame = null;
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(frame,
					    e.getMessage(),
					    "Error",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
		};
	}
	
	public EventHandler<ActionEvent> filter2(){
		return new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try
				{
					Double pret  = Double.parseDouble(postCrudView.fieldSalariuValue.getText());
					postCrudView.postList.setItems(FXCollections.observableArrayList(controller.filtrarePost2(pret)));
				}
				catch(Exception e)
				{
					Component frame = null;
					JOptionPane.showMessageDialog(frame,
						    e.getMessage(),
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
			
	};
	}
	
}