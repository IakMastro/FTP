package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

public class Client extends Application {
    LoginLayout loginLayout;
    ClientSocket clientSocket;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("FTP Client");

        loginLayout = new LoginLayout();
        loginLayout.getLoginButton().setOnAction(e -> loginButtonFunction());
        loginLayout.getRegisterButton().setOnAction(e -> registerButtonFunction());

        Scene loginScreen = new Scene(loginLayout);

        primaryStage.setScene(loginScreen);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    void loginButtonFunction() {
        try {
            clientSocket = new ClientSocket("localhost", 7500);
            String answer = clientSocket.connect(loginLayout.getUsername(), loginLayout.getPassword());

            if (answer.equals("OK")) {
                System.out.println("I arrived back");
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

    void registerButtonFunction() {

    }
}
