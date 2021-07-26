package ru.geekbrains.june.chat.db;

import ru.geekbrains.june.chat.server.ClientHandler;

import java.sql.*;

public class DataBase {
    private static Connection connection;
    private static Statement stmt;
    private static final String CONNECTED = "connected";
    private static final String DISCONNECTED = "disconnected";
    private static final String WAITING = "waiting";

//    public static void main(String[] args) {
//        try {
//            connect();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            disconnect();
//        }
//    }

    public static boolean connect() {
//        stmt = connection.createStatement();
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:javadb.db");
            createTableUsers();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
//        finally {
//            disconnect();
//            return false;
//        }
    }

    public static void disconnect() {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTableUsers() {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users (\n" +
                    "        id      INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "        name    TEXT UNIQUE, \n" +
                    "        status  TEXT\n" +
                    "    );");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

//    private static void dropTable(String table) throws SQLException {
//        stmt.executeUpdate("DROP TABLE IF EXISTS " + table + ";");
//    }

    public static int insertUser(String name, String status) {
        String[] returnId = {"ID"};
        int id = 0;
        try (PreparedStatement prepInsert = connection.prepareStatement("INSERT INTO users (name, status) VALUES (?, ?)",
                returnId)) {
            prepInsert.setString(1, name);
            prepInsert.setString(2, status);
            int result = prepInsert.executeUpdate();
            if (result > 0) {
                try (ResultSet rs = prepInsert.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getInt(1);
                    }
                    rs.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static void setUserStatusConnected(int id) {
        updateUser(id, CONNECTED);
    }

    public static void setUserStatusDisconnected(int id) {
        updateUser(id, DISCONNECTED);
    }

    public static void updateUser(int id, String status) {
        try (PreparedStatement prepInsert = connection.prepareStatement("UPDATE users SET status = ? where id = ?")) {
            prepInsert.setString(1, status);
            prepInsert.setInt(2, id);
            int result = prepInsert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isUserNameUsed(String name, ClientHandler clientHandler) {
        int userId = 0;
        boolean res = false;
        String status;
        try (PreparedStatement ps = connection.prepareStatement("SELECT id, status FROM users where name = ?")) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userId = rs.getInt("id");
                status = rs.getString("status");
                if (status.equalsIgnoreCase(CONNECTED) || status.equalsIgnoreCase(WAITING)) {
                    res = true;
                }
            }
            if (!res) {
                // no data found or status is disconnected
                clientHandler.setUsername(name);
                if (userId != 0) {
                    updateUser(userId, WAITING);
                } else {
                    userId = insertUser(name, WAITING);
                }
                clientHandler.setUserId(userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
        return res;
    }

    public static int getUserId(String name) {
        int userId = 0;
        String status = "";
        try (PreparedStatement ps = connection.prepareStatement("SELECT id, status FROM users where name = ?")) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userId = rs.getInt("id");
                status = rs.getString("status");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userId;
    }

//    private static void clearTableEx() throws SQLException {
//        stmt.executeUpdate("DELETE FROM students;");
//    }
//
//    private static void deleteEx() throws SQLException {
//        stmt.executeUpdate("DELETE FROM students WHERE name = 'Bob1';");
//    }
//
//    private static void updateEx() throws SQLException {
//        stmt.executeUpdate("UPDATE students SET score = 100 WHERE name = 'Bob4';");
//    }
//
//    private static void insertEx() throws SQLException {
//        stmt.executeUpdate("INSERT INTO students (name, score) VALUES ('Bob4', 60);");
//    }


}
