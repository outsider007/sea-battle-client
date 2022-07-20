package ru.kuznetsov.seabattle.client;

import ru.kuznetsov.seabattle.client.console.ConsolePlayer;
import ru.kuznetsov.seabattle.client.core.Game;
import ru.kuznetsov.seabattle.client.network.NetworkActionProvider;

import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try {
            new Game(new NetworkActionProvider(new Socket("127.0.0.1", 6666)), new ConsolePlayer()).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}