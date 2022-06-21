package ru.kuznetsov.seabattle.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.stream.Collectors;

public class GameClient {
    private final String host;
    private final int port;
    private BufferedReader inputStream;
    private PrintWriter outputStream;

    public GameClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws IOException {
        try (Socket clientSocket = new Socket(host, port)) {
            inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            outputStream = new PrintWriter(clientSocket.getOutputStream(), true);
            String content;
            while (!clientSocket.isClosed() || !clientSocket.isConnected()) {
                content = inputStream.readLine();
                if (content != null) {
                    if ("Bye".equals(content)) {
                        clientSocket.close();
                        break;
                    }

                    System.out.println(content);
                }
            }
            System.out.println("Client close connection!");
        }
    }
}
