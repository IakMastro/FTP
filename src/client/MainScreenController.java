package client;

import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.io.IOException;

public class MainScreenController {
    public Text directoryName;
    public ListView<String> filesList;
    ClientSocket clientSocket;
    
    public void setClientSocket(ClientSocket clientSocket) { this.clientSocket = clientSocket; }
    
    public void setContent() {
        try {
            String directoryContent = clientSocket.getDirContent();
            String[] directoryContentList = directoryContent.split("\n");
            directoryName.setText(clientSocket.getDirName());
            
            for (String file : directoryContentList) 
                filesList.getItems().add(file);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void downloadButton(ActionEvent actionEvent) {
        //TODO: Find a way to download with a DataSocket class
    }

    public void refreshButton(ActionEvent actionEvent) {
        //TODO: Refresh Button functionality
    }
}
