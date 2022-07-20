package ru.kuznetsov.seabattle.client.console;

import ru.kuznetsov.seabattle.client.core.Player;

import java.util.Scanner;

public class ConsolePlayer implements Player {
    private final Scanner scanner;

    public ConsolePlayer() {
        scanner = new Scanner(System.in);
    }

    @Override
    public String name() {
        System.out.print("Enter your name: ");
        return scanner.nextLine();
    }

    @Override
    public int selectPoint() {
        int attempt = 0;

        while (true) {
            if (attempt++ > 0) {
                System.out.println("Input points must be digit in rage (1, 9]!");
            }

            String input = scanner.next();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException ignore) {
            }
        }
    }

    @Override
    public void displayContent(String content) {
        System.out.println(content);
    }
}
