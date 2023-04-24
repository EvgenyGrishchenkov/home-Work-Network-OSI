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
            char lastLetter;
            char firstLetter;
            String city = null;
            String newCity;
            while (true) {
                try (Socket clientSocket = serverSocket.accept(); // ждем подключения
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    if (city == null) {
                        out.println("введите первый город");
                        city = in.readLine();
                        out.println("ОК");
                    } else {
                        out.println("предыдущий город: " + city + ". Введите следующий город");
                        newCity = in.readLine();
                        lastLetter = city.charAt(city.length() - 1);
                        firstLetter = newCity.charAt(0);

                        if (lastLetter == firstLetter) {
                            out.println("ОК");
                            city = newCity;
                        } else {
                            out.println("NOT OK");
                        }
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}