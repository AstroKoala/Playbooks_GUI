package com.astrokoala.scenes.login;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.astrokoala.enums.Pages;
import com.astrokoala.models.User;
import com.astrokoala.playbook.App;
import com.astrokoala.playbook.Greet;
//import com.astrokoala.playbook.Greet;
import com.astrokoala.playbook.State;
import com.astrokoala.services.login_service.LoginService;
import com.astrokoala.services.register_service.RegisterService;

import animatefx.animation.ZoomIn;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	@FXML private Button close;
	@FXML private ImageView pngBigIcon;
	@FXML private Label lblFooter;
	@FXML private Pane paneRegister;
	@FXML private TextField txtRegisterUsername;
	@FXML private TextField txtRegisterEmail;
	@FXML private TextField txtRegisterEmailVerify;
	@FXML private PasswordField txtRegisterPass;
	@FXML private Button btnRegister;
	@FXML private Label lblRegister;
	@FXML private Label lblInvalid;
	@FXML private Label lblInvalidVerify;
	
	@FXML private ImageView btnBack;
	
	@FXML private Pane paneSignIn;
	@FXML private TextField txtSignInEmail;
	@FXML private PasswordField txtSignInPass;
	@FXML private Button btnSignIn;
	@FXML private Hyperlink linkForgotPass;
	@FXML private Button btnNoAcct;
	@FXML private Label lblSignIn;
	@FXML private CheckBox chkRememberMe;
	
//	@FXML private Circle redCircle;
//	@FXML private Circle yellowCircle;
//	@FXML private Circle greenCircle;
	@FXML private Pane activePane = null;
	@FXML private ImageView pic;
	@FXML private TextField userInput;
	@FXML private PasswordField passInput;
	@FXML private Button closeBtn;
	@FXML private Button loginBtn;
	@FXML private Hyperlink	forgotPass;
	@FXML private CheckBox rememberMe;
	
	//TODO: LOGIN FUNCTION
	private boolean isLoggedIn = false;
	
	@FXML
	public void close() {
		System.exit(0);
	}
	
	@FXML
	public void hide() {
		((Stage) App.getPrimaryStage().getScene().getWindow()).setIconified(true);
	}
	
	@FXML
	private void setRememberMe() {
		System.out.println("VALUE: " + rememberMe.isSelected());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		showSignIn();
	}

	@FXML
	public void showRegister() {
		new ZoomIn(paneRegister).play();
		paneRegister.toFront();
		activePane = paneRegister;
	}
	
	@FXML
	public void showSignIn() {
		new ZoomIn(paneSignIn).play();
		paneSignIn.toFront();
		activePane = paneSignIn;
	}
	
	@FXML
	public void register() {
		// if all inputs are not empty
		if (!txtRegisterUsername.getText().equals("") && !txtRegisterEmail.getText().equals("") && !txtRegisterEmailVerify.getText().equals("") && !txtRegisterPass.getText().equals("")) {
			// if not valid email
			if (isValidEmail(txtRegisterEmail.getText())) {
				// if email match
				if (txtRegisterEmail.getText().equalsIgnoreCase(txtRegisterEmailVerify.getText())) {	
					System.out.println("Register");
					if (RegisterService.register(txtRegisterEmail.getText(), txtRegisterPass.getText(), txtRegisterUsername.getText())) { 
						txtRegisterUsername.setText("");
						txtRegisterEmail.setText("");
						txtRegisterEmailVerify.setText("");
						txtRegisterPass.setText("");
						showSignIn();
					}
				}
				else {
					System.out.println("EMAILS DON'T MATCH");
					outlineRed(txtRegisterEmail);
					lblInvalid.setText("Emails don't match");
					outlineRed(txtRegisterEmailVerify);
					lblInvalidVerify.setText("Emails don't match");
				}
			}
			else {
				System.out.println("EMAIL IS NOT VALID");
				outlineRed(txtRegisterEmail);
				lblInvalid.setText("Invalid email");
				txtRegisterEmailVerify.setText("");
				outlineRed(txtRegisterEmailVerify);
			}
		} 
		else {
			if (txtRegisterUsername.getText().equals("")) { System.out.println("username can't be empty!!"); outlineRed(txtRegisterUsername); }
			if (txtRegisterEmail.getText().equals("")) { System.out.println("email can't be empty!!"); outlineRed(txtRegisterEmail); }
			if (txtRegisterEmailVerify.getText().equals("")) { System.out.println("verify can't be empty!!"); outlineRed(txtRegisterEmailVerify); }
			if (txtRegisterPass.getText().equals("")) { System.out.println("pass can't be empty!!"); outlineRed(txtRegisterPass); }
		}
	}

	@FXML
	public void login() {
		if (!txtSignInEmail.getText().equals("") && !txtSignInPass.getText().equals("")) {
			// if logged in, greet them, then go to landing page
			User user = LoginService.login(txtSignInEmail.getText(), txtSignInPass.getText());
			if (user.getId() > 0 && !isLoggedIn) {
				isLoggedIn = true;
				txtSignInEmail.setText("");
				txtSignInPass.setText("");
				greetUser(user);
				App.getPrimaryStage().setScene(App.getPages().get(Pages.LANDING.getName()));
			}
			else 
				System.out.println("Login failed :(");
		} else {
			if (txtSignInEmail.getText().equals("")) { System.out.println("email can't be empty!!"); outlineRed(txtSignInEmail); }
			if (txtSignInPass.getText().equals("")) { System.out.println("pass can't be empty!!"); outlineRed(txtSignInPass); }
		}
	}
	
	private void greetUser(User user) { 
			State.setUser(user);
			new Greet().greetPeople(Arrays.asList(State.getUser().getUsername()));
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
	private void resetControlInputStyle(Event e) {
		((Control) e.getSource()).setStyle("");
	}
	
	@FXML
	private void handleKeyPress(KeyEvent key) {
    if (key.getCode().equals(KeyCode.ENTER)) {
    	if ((activePane.getId().equals(paneSignIn.getId()))) {
    		login();
    		btnSignIn.requestFocus();
    	}
    	else if (activePane.getId().equals(paneRegister.getId())) {
    		register();
    		btnSignIn.requestFocus();
    	}
    }
	}
	
	
	private boolean isValidEmail(String email) {
		Pattern pattern = Pattern.compile("^(.+)@(.+)$");
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
}
