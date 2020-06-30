package client;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Controller {
    public ClientSocket clientSocket;
    public PasswordField passwordField;
    public TextField usernameField;

    public void loginButton(ActionEvent actionEvent) {
        try {
            clientSocket = new ClientSocket("localhost", 7500);
            String answer = clientSocket.connect(usernameField.getText(), passwordField.getText());

            if (answer.equals("OK")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Connected!");
                alert.setContentText("Connected to the server");
                alert.showAndWait().ifPresent(rs -> {
                    if (rs == ButtonType.OK) {
                        //TODO: Change the scene
                    }
                });
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Wrong credentials");
                alert.setHeaderText("Wrong credentials were given");
                alert.setContentText("Either the user doesn't exist or the username/password is wrong.\n Try again");
                alert.showAndWait();
                clientSocket.logout();
            }

        } catch (IOException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("There is no connection to the internet");
            alert.setHeaderText("Server is either down or there is no internet in this machine");
            alert.setContentText("Wrong IP address or Port was given.\nOr Server is not open.\n" +
                    "No internet in this machine.\n Try again");
            alert.showAndWait();
        }
    }
}
