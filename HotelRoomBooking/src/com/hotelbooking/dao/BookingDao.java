package com.hotelbooking.dao;

import com.hotelbooking.model.Booking;

import java.math.BigDecimal;


public interface BookingDao {

    void bookingRoom(int roomId, String customerName, String contactInfo, int roomsBooked, BigDecimal discountValue, BigDecimal totalPrice );

    Booking getBookingDetails(int bookingId);

    void cancelBooking(int bookingId);



}
