package com.hotelbooking.model;

import java.math.BigDecimal;

public class Rooms {

    private int roomTypeId;
    private String roomTypeName;
    private int totalRooms;
    private int availableRooms;
    private BigDecimal pricePerNight;


    public Rooms(int roomTypeId, String roomTypeName, int totalRooms, int availableRooms, BigDecimal pricePerNight) {
        this.roomTypeId = roomTypeId;
        this.roomTypeName = roomTypeName;
        this.totalRooms = totalRooms;
        this.availableRooms = availableRooms;
        this.pricePerNight = pricePerNight;
    }

    public int getRoomsTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public int getTotalRooms() {
        return totalRooms;
    }

    public void setTotalRooms(int totalRooms) {
        this.totalRooms = totalRooms;
    }

    public int getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(int availableRooms) {
        this.availableRooms = availableRooms;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }
}
