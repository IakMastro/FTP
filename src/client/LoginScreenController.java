package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginScreenController {
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
                        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        Parent mainScreen;
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/MainScreen.fxml"));
                            mainScreen = loader.load();
                            primaryStage.setScene(new Scene(mainScreen));
                            MainScreenController controller = loader.getController();
                            controller.setClientSocket(clientSocket);
                            controller.setContent();
                        } catch (IOException e) {
                            System.err.println("For whatever reason, the file 'DownloadScreen.fxml' was not found." +
                                    " Please check the files and if it isn't there please reinstall.");
                        }
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
