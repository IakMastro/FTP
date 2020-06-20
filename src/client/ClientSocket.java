package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientSocket {
    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream output = null;

    public ClientSocket(String address, int port) throws IOException {
        socket = new Socket(address, port);
        input = new DataInputStream(socket.getInputStream());
        output = new DataOutputStream(socket.getOutputStream());
    }

    public String connect(String username, String password) throws IOException {
        output.writeUTF(username);
        output.writeUTF(password);

        return input.readUTF();
    }

    public void logout() throws IOException {
        input.close();
        output.close();
        socket.close();
    }
}
