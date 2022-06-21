package ru.kuznetsov.seabattle.client;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            new GameClient("127.0.0.1", 6666).run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}