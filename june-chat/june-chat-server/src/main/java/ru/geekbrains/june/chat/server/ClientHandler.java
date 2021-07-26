package ru.geekbrains.june.chat.server;

import ru.geekbrains.june.chat.db.DataBase;

import javax.xml.crypto.Data;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private Server server;
    private Socket socket;
    private String username;
    private Integer userId;
    private DataInputStream in;
    private DataOutputStream out;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    // инициализировать каналы (in\out) сообщений клиента и запустить в своем потоке
    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            new Thread(() -> logic()).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // отправлять сообщения клиенту
    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // читать сообщения от клиента (командные, информационные), пока он не отключился
    // если отключился ,то удалить его из списка активных клиентов (освободив занятое имя)
    private void logic() {
        try {
            while (!consumeAuthorizeMessage(in.readUTF())) ;
            while (consumeRegularMessage(in.readUTF())) ;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Клиент " + username + " отключился");
            server.unsubscribe(this);
            closeConnection();
        }
    }

    // обработать информационное (для одного или всех  участников чата) или командное (отключиться)
    // сообщение от клиента
    private boolean consumeRegularMessage(String inputMessage) {
        if (inputMessage.startsWith("/")) {
            if (inputMessage.equals("/exit")) {
                sendMessage("/exit");
                return false;
            }
            if (inputMessage.startsWith("/w ")) {
                String[] tokens = inputMessage.split("\\s+", 3);
                server.sendPersonalMessage(this, tokens[1], tokens[2]);
            }
            return true;
        }
        server.broadcastMessage(username + ": " + inputMessage);
        return true;
    }

    // обработать авторизационное сообщение от клиента (проверить имя и подключить к чату)
    private boolean consumeAuthorizeMessage(String message) {
        if (message.startsWith("/auth ")) { // /auth bob
            String[] tokens = message.split("\\s+");
            if (tokens.length == 1) {
                sendMessage("SERVER: Вы не указали имя пользователя");
                return false;
            }
            if (tokens.length > 2) {
                sendMessage("SERVER: Имя пользователя не может состоять из нескольких слов");
                return false;
            }
            String selectedUsername = tokens[1];
            if (DataBase.connect()) {
                if (DataBase.isUserNameUsed(selectedUsername, this)) {
                    sendMessage("SERVER: Данное имя пользователя уже занято");
                    return false;
                }
                DataBase.disconnect();
            } else {
                sendMessage("SERVER: Ошибка подключения к базе данных. Попробуйте позже.");
                return false;
            }
//            username = selectedUsername;
            sendMessage("/authok");
            server.subscribe(this);
            return true;
        } else {
            sendMessage("SERVER: Вам необходимо авторизоваться");
            return false;
        }
    }

    // закрыть подключение клиента
    private void closeConnection() {
        try {
            if (in != null) {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (out != null) {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
