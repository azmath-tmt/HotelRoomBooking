package com.hotelbooking.model;

import java.math.BigDecimal;

public class Booking {

    private int bookingId;
    private int roomTypeId;
    private String customerName ;
    private String contactInfo ;
    private int roomsBooked ;
    private BigDecimal discountApplied ;
    private BigDecimal totalPrice ;

    public Booking(int bookingId, int roomTypeId, String customerName, String contactInfo, int roomsBooked, BigDecimal discountApplied, BigDecimal totalPrice) {
        this.bookingId = bookingId;
        this.roomTypeId = roomTypeId;
        this.customerName = customerName;
        this.contactInfo = contactInfo;
        this.roomsBooked = roomsBooked;
        this.discountApplied = discountApplied;
        this.totalPrice = totalPrice;
    }


    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public int getRoomsBooked() {
        return roomsBooked;
    }

    public void setRoomsBooked(int roomsBooked) {
        this.roomsBooked = roomsBooked;
    }

    public BigDecimal getDiscountApplied() {
        return discountApplied;
    }

    public void setDiscountApplied(BigDecimal discountApplied) {
        this.discountApplied = discountApplied;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
