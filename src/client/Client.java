package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent loginScreen = FXMLLoader.load(getClass().getResource("../fxml/LoginScreen.fxml"));
        primaryStage.setTitle("FTP Client");

        primaryStage.setScene(new Scene(loginScreen, 300, 250));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
