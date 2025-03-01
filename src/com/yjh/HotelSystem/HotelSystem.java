package com.yjh.HotelSystem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HotelSystem {

    //储存集合
    List<User> users = new ArrayList<>();
    List<Room> rooms = new ArrayList<>();
    private static final String USERS_FILE = "users.txt";
    private static final String ROOMS_FILE = "rooms.txt";

    public HotelSystem() {
        loadUsers();
        loadRooms();
    }


    //加载储存数据
    private void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String username = parts[0];
                String password = parts[1];
                String role = parts[2];
                if ("customer".equals(role)) {
                    boolean isMember = Boolean.parseBoolean(parts[3]);
                    double balance = Double.parseDouble(parts[4]);
                    users.add(new Customer(username, password, isMember, balance));
                } else {
                    users.add(new User(username, password, role));
                }
            }
        } catch (IOException e) {
            // 文件不存在或读取错误，不做处理
        }
    }

    //保存用户数据
    private void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE))) {
            for (User user : users) {
                if (user instanceof Customer) {
                    Customer customer = (Customer) user;
                    writer.write(customer.username + "," + customer.password + "," + customer.role + "," + customer.isMember + "," + customer.balance);
                } else {
                    writer.write(user.username + "," + user.password + "," + user.role);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //加载房间数据
    private void loadRooms() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ROOMS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

            }
        } catch (IOException e) {
            // 文件不存在或读取错误，不做处理
        }
    }

    //保存房间数据
    private void saveRooms() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ROOMS_FILE))) {
            for (Room room : rooms) {
                writer.write(room.roomNumber + "," + room.roomType + "," + room.price + "," + room.isOccupied);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //添加用户
    public void addUser(User user) {
        users.add(user);
        saveUsers();
    }

    //删除用户
    public void deleteUser(String username) {
        users.removeIf(user -> user.username.equals(username));
        saveUsers();
    }

    //修改用户信息
    public void updateUser(String username, String password) {
        for (User user : users) {
            if (user.username.equals(username)) {
                user.password = password;
                saveUsers();
                break;
            }
        }
    }

    //查询用户信息
    public User findUser(String username) {
        for (User user : users) {
            if (user.username.equals(username)) {
                return user;
            }
        }
        return null;
    }

    //添加房间
    public void addRoom(Room room) {
        rooms.add(room);
        saveRooms();
    }

    //删除房间
    public void deleteRoom(int roomNumber) {
        rooms.removeIf(room -> room.roomNumber == roomNumber);
        saveRooms();
    }

    //修改房间信息
    public void updateRoom(int roomNumber, String roomType, double price) {
        for (Room room : rooms) {
            if (room.roomNumber == roomNumber) {
                room.roomType = roomType;
                room.price = price;
                saveRooms();
                break;
            }
        }
    }

    //查询房间数量
    public Room findRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.roomNumber == roomNumber) {
                return room;
            }
        }
        return null;
    }

    //顾客订房
    public boolean bookRoom(Customer customer, int roomNumber) {
        Room room = findRoom(roomNumber);
        if (room != null && !room.isOccupied && customer.balance >= room.price) {
            customer.balance -= room.price;
            room.isOccupied = true;
            saveUsers();
            saveRooms();
            return true;
        }
        return false;
    }

    //查询房间信息
    public void queryRooms() {
        for (Room room : rooms) {
            System.out.println("房间号: " + room.roomNumber + ", 类型: " + room.roomType + ", 价格: " + room.price + ", 状态: " + (room.isOccupied ? "已预订" : "空闲"));
        }
    }
}
