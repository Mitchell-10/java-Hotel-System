package com.yjh.HotelSystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HotelSystem system = new HotelSystem();
        //预设管理员账号
        system.addUser(new User("admin", "admin123", "admin"));

        Scanner scanner=new Scanner(System.in);

        //进入界面
        while (true) {
            System.out.println("请输入用户名：");
            String username = scanner.nextLine();
            System.out.println("请输入密码：");
            String password = scanner.nextLine();

            User user = system.findUser(username);
            if (user != null && user.password.equals(password)) {
                if ("admin".equals(user.role)) {
                    adminMenu(system, scanner);
                } else if ("customer".equals(user.role)) {
                    customerMenu(system, (Customer) user, scanner);
                }
            } else {
                System.out.println("用户名或密码错误，请重新输入。");
            }
        }
    }

    //管理员界面
    private static void adminMenu(HotelSystem system, Scanner scanner) {
        while (true) {
            System.out.println("*****管理员界面*****");
            System.out.println("1. 添加用户");
            System.out.println("2. 删除用户");
            System.out.println("3. 修改用户信息");
            System.out.println("4. 查询用户");
            System.out.println("5. 添加房间");
            System.out.println("6. 删除房间");
            System.out.println("7. 修改房间信息");
            System.out.println("8. 查询房间");
            System.out.println("9. 退出");
            int choose = scanner.nextInt();
            scanner.nextLine();


            switch (choose) {
                case 1 -> {
                    //System.out.println("1. 添加用户");

                    System.out.println("请输入用户名:");
                    String username = scanner.nextLine();
                    System.out.println("请输入密码:");
                    String password = scanner.nextLine();
                    System.out.println("是否为会员 (true/false):");
                    boolean isMember = scanner.nextBoolean();
                    scanner.nextLine();
                    System.out.println("请输入余额:");
                    double balance = scanner.nextDouble();
                    scanner.nextLine();
                    system.addUser(new Customer(username, password, isMember, balance));
                    break;
                }
                case 2 -> {
                    //System.out.println("2. 删除用户");

                    System.out.println("请输入要删除的用户名:");
                    String deleteUsername = scanner.nextLine();
                    system.deleteUser(deleteUsername);
                    break;
                }
                case 3 -> {
                    //System.out.println("3. 修改用户信息");

                    System.out.println("请输入要修改的用户名:");
                    String updateUsername = scanner.nextLine();
                    System.out.println("请输入新密码:");
                    String newPassword = scanner.nextLine();
                    system.updateUser(updateUsername, newPassword);
                    break;
                }
                case 4 -> {
                    //System.out.println("4. 查询用户");

                    System.out.println("请输入要查询的用户名:");
                    String queryUsername = scanner.nextLine();
                    User user = system.findUser(queryUsername);
                    if (user != null) {
                        System.out.println("用户名: " + user.username + ", 角色: " + user.role);
                    } else {
                        System.out.println("用户不存在。");
                    }
                    break;
                }
                case 5 -> {
                    //System.out.println("5. 添加房间");

                    System.out.println("请输入房间号:");
                    int roomNumber = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("请输入房间类型:");
                    String roomType = scanner.nextLine();
                    System.out.println("请输入房间价格:");
                    double price = scanner.nextDouble();
                    scanner.nextLine();
                    system.addRoom(new Room(roomNumber, roomType, price));
                    break;
                }
                case 6 -> {
                    //System.out.println("6. 删除房间");

                    System.out.println("请输入要删除的房间号:");
                    int deleteRoomNumber = scanner.nextInt();
                    scanner.nextLine();
                    system.deleteRoom(deleteRoomNumber);
                    break;
                }
                case 7 -> {
                    //System.out.println("7. 修改房间信息");

                    System.out.println("请输入要修改的房间号:");
                    int updateRoomNumber = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("请输入新的房间类型:");
                    String newRoomType = scanner.nextLine();
                    System.out.println("请输入新的房间价格:");
                    double newPrice = scanner.nextDouble();
                    scanner.nextLine();
                    system.updateRoom(updateRoomNumber, newRoomType, newPrice);
                    break;
                }
                case 8 -> {
                    //System.out.println("8. 查询房间");

                    system.queryRooms();
                    break;
                }
                case 9 -> {
                    //System.out.println("9. 退出");

                    return;
                }
                default -> {
                    System.out.println("输入错误，请重新输入。");
                }
            }
            }
    }

    //顾客界面
    private static void customerMenu(HotelSystem system, Customer customer, Scanner scanner) {
        while (true) {
            System.out.println("顾客菜单:");
            System.out.println("1. 查询房间信息");
            System.out.println("2. 预订房间");
            System.out.println("3. 退出");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    system.queryRooms();
                    break;
                case 2:
                    System.out.println("请输入要预订的房间号:");
                    int roomNumber = scanner.nextInt();
                    scanner.nextLine();
                    if (system.bookRoom(customer, roomNumber)) {
                        System.out.println("预订成功，当前余额: " + customer.balance);
                    } else {
                        System.out.println("预订失败，可能是房间已被预订或余额不足。");
                    }
                    break;
                case 3:
                    return;
            }
        }
    }
}
