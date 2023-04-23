package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws RuntimeException {

        try (ServerSocket serverSocket = new ServerSocket(8080);) { // порт можете выбрать любой в доступном диапазоне 0-65536. Но чтобы не нарваться на уже занятый - рекомендуем использовать около 8080
            System.out.println("Сервер в работе:)");
            while (true) {
                try (Socket clientSocket = serverSocket.accept(); // ждем подключения
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    // ваш код
                    System.out.println("New connection accepted");

                    final String name = in.readLine();

                    System.out.println(String.format("%s, порт %d", name, clientSocket.getPort()));

                    out.println("Отвали:)");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}