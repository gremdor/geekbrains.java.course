package ru.geekbrains.june.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private List<ClientHandler> clients;


    //запуск сервера и ожидание\подключение пользователей
    public Server() {
        try {
            this.clients = new ArrayList<>();
            ServerSocket serverSocket = new ServerSocket(8189);
            System.out.println("Сервер запущен. Ожидаем подключение клиентов..");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Подключился новый клиент");
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // добавление нового пользователя чата в список участников
    public synchronized void subscribe(ClientHandler c) {
        broadcastMessage("В чат зашел пользователь " + c.getUsername());
        clients.add(c);
        broadcastClientList();
    }

    // удаление пользователя из списка участников чата
    public synchronized void unsubscribe(ClientHandler c) {
        clients.remove(c);
        broadcastMessage("Из чата вышел пользователь " + c.getUsername());
        broadcastClientList();
    }

    // отправить сообщение на всех участников чата
    public synchronized void broadcastMessage(String message) {
        for (ClientHandler c : clients) {
            c.sendMessage(message);
        }
    }

    // отправить список участников чата всем участникам чата
    public synchronized void broadcastClientList() {
        StringBuilder builder = new StringBuilder(clients.size() * 10);
        builder.append("/clients_list ");
        for (ClientHandler c : clients) {
            builder.append(c.getUsername()).append(" ");
        }
        String clientsListStr = builder.toString();
        broadcastMessage(clientsListStr);
    }

    // проверить занятость имени
    public synchronized boolean isUsernameUsed(String username) {
        for (ClientHandler c : clients) {
            if (c.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    // отправить сообщение конкретному (указанному) участнику чата, отправлять сообщения самому себе нельзя
    public synchronized void sendPersonalMessage(ClientHandler sender, String receiverUsername, String message) {
        if (sender.getUsername().equalsIgnoreCase(receiverUsername)) {
            sender.sendMessage("Нельзя отправлять личные сообщения самому себе");
            return;
        }
        for (ClientHandler c : clients) {
            if (c.getUsername().equalsIgnoreCase(receiverUsername)) {
                c.sendMessage("от " + sender.getUsername() + ": " + message);
                sender.sendMessage("пользователю " + receiverUsername + ": " + message);
                return;
            }
        }
        sender.sendMessage("Пользователь " + receiverUsername + " не в сети");
    }
}
