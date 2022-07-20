package ru.kuznetsov.seabattle.client.network;

import lombok.SneakyThrows;
import ru.kuznetsov.seabattle.client.core.Action;
import ru.kuznetsov.seabattle.client.core.ActionProvider;
import ru.kuznetsov.seabattle.client.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetworkActionProvider implements ActionProvider {
    private final Socket clientSocket;
    private final BufferedReader inputStream;
    private final PrintWriter outputStream;
    private final Pattern commandSeparator;

    public NetworkActionProvider(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        commandSeparator = Pattern.compile("<\\|\\|\\|>");
        inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        outputStream = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    @SneakyThrows
    @Override
    public Pair<Action, String> receiveAction() {
        var content = inputStream.readLine();

        if (commandSeparator.matcher(content).matches()) {
            String[] actionContent = content.split(commandSeparator.pattern());
            return new Pair<>(Action.resolve(actionContent[0]), actionContent[1]);
        } else {
            return new Pair<>(Action.INVALID_ACTION, content);
        }
    }

    @Override
    public void sendResponse(String content) {
        outputStream.println(content);
    }
}
