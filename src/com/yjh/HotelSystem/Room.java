package com.yjh.HotelSystem;

public class Room {
    int roomNumber;
    String roomType;
    double price;
    boolean isOccupied;

    public Room(int roomNumber, String roomType, double price) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.isOccupied = false;
    }
}
