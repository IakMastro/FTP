package client;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LoginLayout extends VBox {
    private Label usernameLabel, passwordLabel;

    private TextField usernameTF;
    private PasswordField passwordTF;

    private Button loginButton, registerButton;

    public LoginLayout() {
        usernameLabel = new Label("Username");
        passwordLabel = new Label("Password");

        usernameTF = new TextField();
        passwordTF = new PasswordField();

        loginButton = new Button("Login");
        registerButton = new Button("Register");

        HBox usernameLayout = new HBox();
        HBox passwordLayout = new HBox();
        HBox buttonLayout = new HBox();

        usernameLayout.getChildren().addAll(usernameLabel, usernameTF);
        passwordLayout.getChildren().addAll(passwordLabel, passwordTF);
        buttonLayout.getChildren().addAll(loginButton, registerButton);

        this.getChildren().addAll(usernameLayout, passwordLayout, buttonLayout);
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public Button getRegisterButton() {
        return registerButton;
    }

    public String getUsername() {
        return usernameTF.getText().strip();
    }

    public String getPassword() {
        return passwordTF.getText().strip();
    }
}
