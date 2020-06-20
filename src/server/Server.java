package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private Socket socket = null;
    private ServerSocket server = null;

    public Server(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("Waiting for a client");
        } catch (IOException e) {
            System.err.println("Couldn't find the port");
        }
    }

    public void connect() throws IOException {
        socket = server.accept();
        System.out.println("A new client just connected: " + socket);

        ClientHandler handler = new ClientHandler(socket, new DataInputStream(socket.getInputStream()),
                new DataOutputStream(socket.getOutputStream()));

        handler.start();
    }

    public Socket getSocket() {
        return socket;
    }

    public void logout() throws IOException {
        socket.close();
    }

    public static void main(String[] args) {
        Server server = new Server(7500);

        for (;;) {
            try {
                server.connect();
            } catch (IOException e) {
                try {
                    server.logout();
                    System.out.println("Server just closed");
                } catch (IOException ioException) {
                    System.err.println("Unexpected error");
                }
            }
        }
    }
}
