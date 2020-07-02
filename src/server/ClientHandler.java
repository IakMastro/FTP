package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClientHandler extends Thread {
    final private DataInputStream in;
    final private DataOutputStream out;
    final private Socket socket;

    public ClientHandler(Socket socket, DataInputStream in, DataOutputStream out) {
        this.socket = socket;
        this.in = in;
        this.out = out;
    }

    @Override
    public void run() {
        for (; ; ) {
            try {
                checkCredentials();
            } catch (IOException e) {
                try {
                    in.close();
                    out.close();
                } catch (IOException ioException) {
                    System.err.println("Unexpected error");
                }
                System.err.println("Lost connection from: " + socket);
                break;
            }
        }
    }

    private void checkCredentials() throws IOException {
        String username;
        String password;

        username = in.readUTF();
        password = in.readUTF();

        if (username.equals("test") && password.equals("pass1")) {
            System.out.println("Correct credentials that do exist");
            out.writeUTF("OK");

            Path dir;
            switch (System.getProperty("os.name").toLowerCase()) {
                case "linux" -> {
                    dir = Paths.get("/home/thedoctor");
                    setDirectoryInfo(dir);
                    out.writeUTF(dir.toString());
                }
                case "windows 10" -> {
                    dir = Paths.get("C:\\Users\\akism");
                    setDirectoryInfo(dir);
                    out.writeUTF(dir.toString());
                }
            }
        } else {
            System.out.println("Wrong credentials that don't exist");
            out.writeUTF("NOT OK");
            in.close();
            socket.close();
        }
    }

    private void setDirectoryInfo(Path dir) {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            StringBuilder stringBuilder = new StringBuilder();

            for (Path file : stream) {
                stringBuilder.append(file.toString().strip());
                stringBuilder.append("\n");
            }

            out.writeUTF(stringBuilder.toString().strip());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
