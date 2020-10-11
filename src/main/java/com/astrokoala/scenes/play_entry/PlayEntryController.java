package com.astrokoala.scenes.play_entry;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import com.astrokoala.models.User;
import com.astrokoala.playbook.App;
import com.astrokoala.playbook.Greet;
//import com.astrokoala.playbook.Greet;
import com.astrokoala.playbook.State;
import com.astrokoala.services.login_service.LoginService;

import animatefx.animation.ZoomIn;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PlayEntryController implements Initializable{
	@FXML private Button close;
	@FXML private ImageView pngBigIcon;
	@FXML private Label lblFooter;
	@FXML private Pane paneRegister;
	@FXML private TextField txtRegisterEmail;
	@FXML private Button btnRegister;
	@FXML private Label lblRegister;
	@FXML private ImageView btnBack;
	
	@FXML private Pane paneSignIn;
	@FXML private TextField txtSignInEmail;
	@FXML private PasswordField txtPass;
	@FXML private Button btnSignIn;
	@FXML private Hyperlink linkForgotPass;
	@FXML private Button btnNoAcct;
	@FXML private Label lblSignIn;
	@FXML private CheckBox chkRememberMe;
	
//	@FXML private Circle redCircle;
//	@FXML private Circle yellowCircle;
//	@FXML private Circle greenCircle;
	@FXML private ImageView pic;
	@FXML private TextField userInput;
	@FXML private PasswordField passInput;
	@FXML private Button closeBtn;
	@FXML private Button loginBtn;
	@FXML private Hyperlink	forgotPass;
	@FXML private CheckBox rememberMe;

	
	@FXML
	public void close() {
		System.exit(0);
	}
	
	@FXML
	public void hide() {
		((Stage) App.getPrimaryStage().getScene().getWindow()).setIconified(true);
	}
	
//	@FXML 
//	public void fullScreen() {
////		App.getPrimaryStage().setFullScreen(true);
////		App.getPrimaryStage().setMaximized(true);
//		System.out.println("EVEN");
//		try {
//	    Parent root = FXMLLoader.load(this.getClass().getResource("PlayEntry.fxml"));
//	    Rectangle2D screenBounds = Screen.getPrimary().getBounds();
//	    System.out.println(screenBounds.getHeight());
//	    System.out.println(screenBounds.getWidth());
//	    Scene scene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());
//	    App.getPrimaryStage().setScene(scene);
//	    App.getPrimaryStage().setX(1);
//	    App.getPrimaryStage().setY(1);
//		} catch (Exception e) {
//		    System.out.println("Cannot load resource PlayEntry.fxml");
//		    System.out.println(e.getMessage());
//		}
//	}
	
//	@FXML void exitFullScreen() {
//		System.out.println("EVEN");
//		try {
//			App.getPrimaryStage().setScene(new Scene(new FXMLLoader(this.getClass().getResource("PlayEntry.fxml")).load()));
//		} catch (Exception e) {
//		    System.out.println("Cannot load resource PlayEntry.fxml");
//		    System.out.println(e.getMessage());
//		}
//	}
	
	@FXML
	public void printUser() {
		System.out.println();
	}
	
	@FXML
	public void printPass() {
		System.out.println();
	}
	
	@FXML
	private void setRememberMe() {
		System.out.println("VALUE: " + rememberMe.isSelected());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		paneSignIn.toFront();
		//Utils.makeDraggable(App.getPrimaryStage(), topBar);
//		Play play = new Play("this", "is", PlayType.RUN_LEFT, "play");
//		Play play2 = new Play("ALSO", "ALSO", PlayType.PASS_SHORT, "ALSO");
//		Playbook pb = new Playbook(1, "Plays11111", new ArrayList<Play>(), new Date(System.currentTimeMillis()));
//		pb.addPlay(play);
//		pb.addPlay(play2);
//		State.addPlaybook(pb);
	}
	
//	public void makeDraggable(MouseEvent event) {
//		System.out.println("HERE");
//		Utils.makeDraggable(App.getPrimaryStage(), topBar);
//	}

	@FXML
	public void showRegister() {
		new ZoomIn(paneRegister).play();
		paneRegister.toFront();
	}
	
	@FXML
	public void showSignIn() {
		new ZoomIn(paneSignIn).play();
		paneSignIn.toFront();
	}
	
	@FXML
	public void register() {
		System.out.println(txtRegisterEmail.getText());
		txtRegisterEmail.setText("");
	}

	@FXML
	public void login() {
		if (!txtSignInEmail.getText().equals("") && !txtPass.getText().equals("")) {
			//App.getPrimaryStage().setScene(App.getPages().get(Pages.HOME.getName()));
			// if logged in, greet them
			User user = LoginService.login(txtSignInEmail.getText(), txtPass.getText());
			if (user.getId() > 0)
				//greetUser(user.getUsername()); //TODO: user name
				new Greet().greetPeople(Arrays.asList(user.getUsername()));
			else 
				System.out.println("Login failed :(");
		} else {
			if (txtSignInEmail.getText().equals("")) { System.out.println("email can't be empty!!"); outlineRed(txtSignInEmail); }
			if (txtPass.getText().equals("")) { System.out.println("pass can't be empty!!"); outlineRed(txtPass); }
		}
	}
	
	private void greetUser(String userName) { 
//		//Runnable run = () -> {
//			State.setUserName(txtSignInEmail.getText());
//			new Greet().greetPeople(Arrays.asList(State.getUserName()));
//    //}
//    //new Thread(run);
  }    
	
	@FXML
	public void forgotPassword() {
		System.out.println("stoopid");
	}
	
	public void visitAuthor() {
		System.out.println("AstroKoala is best dev");
		State.getHostServices().showDocument("https://github.com/AstroKoala");
	}
	
	private void outlineRed(TextField node) {
		String cssLayout = "-fx-border-color: red; " + "-fx-border-width: 1; -fx-text-color: red;";
		node.setPromptText("Enter your name");
		node.setStyle(cssLayout);
	}
	
	private void outlineRed(PasswordField node) {
		String cssLayout = "-fx-border-color: red; " + "-fx-border-width: 1; -fx-text-color: red;";
		node.setPromptText("Enter your name");
		node.setStyle(cssLayout);
	}
	
	@FXML
	private void resetEmailInputStyle(Event e) {
		txtSignInEmail.setPromptText("Email");
		txtSignInEmail.setStyle("");
	}
	
	@FXML
	private void resetPassInputStyle(Event e) {
		txtPass.setPromptText("Password");
		txtPass.setStyle("");
	}
}
