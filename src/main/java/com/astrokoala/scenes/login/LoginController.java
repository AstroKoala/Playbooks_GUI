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
import com.pepperonas.fxiconics.FxIconicsLabel;
import com.pepperonas.fxiconics.MaterialColor;
import com.pepperonas.fxiconics.awf.FxFontAwesome;

import animatefx.animation.FadeInDown;
import animatefx.animation.FadeOutUp;
import animatefx.animation.ZoomIn;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
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
	@FXML private Label lblNotification;
	@FXML private Group grpAlert;
	@FXML private HBox hbxAlert;
	@FXML private Pane activePane = null;
	@FXML private ImageView pic;
	@FXML private TextField userInput;
	@FXML private PasswordField passInput;
	@FXML private Button closeBtn;
	@FXML private Button loginBtn;
	@FXML private Hyperlink	forgotPass;
	@FXML private CheckBox rememberMe;
	private FxIconicsLabel alertIcon;
	
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
		alertIcon = (FxIconicsLabel) new FxIconicsLabel.Builder(FxFontAwesome.Icons.faw_check_circle_o)
				.size(48)
        .color(MaterialColor.GREEN_500)
        .build();
	}

	@FXML
	public void showRegister() {
		new ZoomIn(paneRegister).play();
		paneRegister.toFront();
		activePane = paneRegister;
		App.setActivePane(paneRegister);
	}
	
	@FXML
	public void showSignIn() {
		new ZoomIn(paneSignIn).play();
		paneSignIn.toFront();
		activePane = paneSignIn;
		App.setActivePane(paneSignIn);
	}
	
	@FXML
	public void register() {
		// if all inputs are not empty
		if (!txtRegisterUsername.getText().equals("") && !txtRegisterEmail.getText().equals("") && !txtRegisterEmailVerify.getText().equals("") && !txtRegisterPass.getText().equals("")) {
			// if not valid email
			if (isValidEmail(txtRegisterEmail.getText())) {
				// if email match
				if (txtRegisterEmail.getText().equalsIgnoreCase(txtRegisterEmailVerify.getText())) {	
					// if all else good, try actual register 
					if (RegisterService.register(txtRegisterEmail.getText(), txtRegisterPass.getText(), txtRegisterUsername.getText())) { 
						resetInputFields(new Control[] {txtRegisterUsername, txtRegisterEmail, txtRegisterEmailVerify, txtRegisterPass});
						showSuccessNotification("Account created successfully! Please sign in to continue.");
						showSignIn();
					}	else {
						//internal error
					}
				}
				else {
					lblInvalid.setText("Emails don't match");
					lblInvalidVerify.setText("Emails don't match");
					outlineRed(new Control[] {txtRegisterEmail, txtRegisterEmailVerify});
				}
			}
			else {
				lblInvalid.setText("Invalid email");
				txtRegisterEmailVerify.setText("");
				outlineRed(new Control[] {txtRegisterEmail, txtRegisterEmailVerify});
			}
		} 
		else {
			outlineEmptyFields();
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
			else {
				//showNotification("Login failed :(");
			}			
		} else {
			outlineEmptyFields();
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
	
	private void outlineRed(Control c) {
		String cssLayout = "-fx-border-color: red; " + "-fx-border-width: 1; -fx-text-color: red;";
		c.setStyle(cssLayout);
	}
	
	private void outlineRed(Control[] nodes) {
		for (Control c : nodes)
			outlineRed(c);
	}
	
	private void outlineEmptyFields() {
		String cssLayout = "-fx-border-color: red; " + "-fx-border-width: 1; -fx-text-color: red;";
		for (Node c : activePane.getChildren()) {
			try {
				if (((TextInputControl) c).getText().equals("")) {
					c.setStyle(cssLayout);
				}
			} catch (ClassCastException e) {}
		}
	}
	
	private void resetInputFields(Control[] nodes) {
		for (Control c : nodes)
			((TextInputControl) c).setText("");
	}
	
	@FXML
	private void resetControlInputStyle(KeyEvent e) {
		((Control) e.getSource()).setStyle("");
		if (((Node) e.getTarget()).getId().equals("txtRegisterEmail")) {
			lblInvalid.setText("");
			if (!txtRegisterEmailVerify.getText().equals("")) {
				lblInvalidVerify.setText("");
				txtRegisterEmailVerify.setStyle("");
			}
		}
		if (((Node) e.getTarget()).getId().equals("txtRegisterEmailVerify")) {
			lblInvalidVerify.setText("");
			if (!txtRegisterEmail.getText().equals("")) {
				lblInvalid.setText("");
				txtRegisterEmail.setStyle("");
			}
		}
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
	
	private void showSuccessNotification(String msg) {
		lblNotification.setText(msg);
		lblNotification.setStyle("-fx-padding: 5");
		alertIcon = (FxIconicsLabel) new FxIconicsLabel.Builder(FxFontAwesome.Icons.faw_check_circle_o)
				.size(48)
        .color(MaterialColor.GREEN_500)
        .build();
		hbxAlert.setAlignment(Pos.CENTER);
		hbxAlert.setStyle("-fx-padding: 10;" + "-fx-border-style: solid;"
        + "-fx-border-width: 1;" + "-fx-border-radius: 20;"
				+ "-fx-border-color: green;" + "-fx-background-color: #90ee90;" //90ee90 light green
        + "-fx-background-radius: 20;" + "-fx-min-height: 50;");
		hbxAlert.getChildren().add(0, alertIcon);
		new FadeInDown(hbxAlert).play();
		hbxAlert.setVisible(true);
		hbxAlert.toFront();
		setTimeout(() -> hideAlert(), 4000);
	}
	
//	private void showSuccessNotification(String msg) {
//		lblNotification.setText(msg);
//		lblNotification.setStyle("-fx-padding: 5");
//		alertIcon = (FxIconicsLabel) new FxIconicsLabel.Builder(FxFontAwesome.Icons.faw_check_circle_o)
//				.size(48)
//        .color(MaterialColor.GREEN_500)
//        .build();
//		hbxAlert.setAlignment(Pos.CENTER);
//		hbxAlert.setStyle("-fx-padding: 10;" + "-fx-border-style: solid;"
//        + "-fx-border-width: 1;" + "-fx-border-radius: 20;"
//				+ "-fx-border-color: green;" + "-fx-background-color: #90ee90;" //90ee90 light green
//        + "-fx-background-radius: 20;" + "-fx-min-height: 50;");
//		hbxAlert.getChildren().add(0, alertIcon);
//		new FadeInDown(hbxAlert).play();
//		hbxAlert.setVisible(true);
//		hbxAlert.toFront();
//		setTimeout(() -> hideAlert(), 4000);
//	}
	
	
	private void hideAlert() {
		new FadeOutUp(hbxAlert).play();
		alertIcon = null;
	}
	
	public static void setTimeout(Runnable runnable, int delay){
    new Thread(() -> {
        try {
            Thread.sleep(delay);
            Platform.runLater(runnable);
        }
        catch (Exception e){
            System.err.println(e);
        }
    }).start();
	}
	
}
