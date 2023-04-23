package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args) throws RuntimeException {
        try (Socket socket = new Socket("127.0.0.1", 8080);
            PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            writer.println("Привет от Клиента!");

            System.out.println(reader.readLine());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
