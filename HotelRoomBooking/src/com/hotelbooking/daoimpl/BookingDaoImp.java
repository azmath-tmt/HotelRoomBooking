package com.hotelbooking.daoimpl;

import com.hotelbooking.dao.BookingDao;
import com.hotelbooking.exception.CustomException;
import com.hotelbooking.model.Booking;
import com.hotelbooking.util.DatabaseConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookingDaoImp implements BookingDao {
    Connection db =  DatabaseConnection.Connection();

    private static final Logger LOGGER = Logger.getLogger(BookingDaoImp.class.getName());

    final static String INSERT_QUERY_FOR_BOOKING_ROOM = "INSERT INTO booking(`room_type_id`, `customer_name`, `contact_info`, " +
            "`rooms_booked`, `discount_applied`, `total_price`) values(?,?,?,?,?,?)";
    final static String SELECT_QUERY_FOR_GET_BOOKROOM_DETAILS = "SELECT * from `booking` where `booking_id` = ?";
    final static String DELETE_QUERY_FOR_CANCEL_BOOK = "DELETE from `booking` where `booking_id` = ?";

    @Override
    public void bookingRoom(int roomId, String customerName, String contactInfo, int roomsBooked, BigDecimal discountValue, BigDecimal totalPrice) {
        PreparedStatement ps = null;

        try {
            ps = db.prepareStatement(INSERT_QUERY_FOR_BOOKING_ROOM);
            ps.setInt(1, roomId);
            ps.setString(2, customerName);
            ps.setString(3, contactInfo);
            ps.setInt(4, roomsBooked);
            ps.setBigDecimal(5, discountValue);
            ps.setBigDecimal(6, totalPrice);
            ps.executeUpdate();
            LOGGER.info("Room booked successfully. Customer: " + customerName + ", Rooms: " + roomsBooked + ", Total Price: " + totalPrice);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error while booking room for customer: " + customerName, e);
            throw new CustomException("Error: " + e.getMessage());
        }
    }

    @Override
    public Booking getBookingDetails(int bookingId) {
        PreparedStatement ps = null;
        ResultSet res = null;
        Booking booking = null;

        try {
            ps = db.prepareStatement(SELECT_QUERY_FOR_GET_BOOKROOM_DETAILS);
            ps.setInt(1, bookingId);

            res = ps.executeQuery();

            if (res.next()) {
                int roomId = res.getInt("room_type_id");
                String customerName = res.getString("customer_name");
                String contactInfo = res.getString("contact_info");
                int roomsBooked = res.getInt("rooms_booked");
                BigDecimal discountApplied = res.getBigDecimal("discount_applied");
                BigDecimal totalPrice = res.getBigDecimal("total_price");

                booking = new Booking(bookingId, roomId, customerName, contactInfo, roomsBooked, discountApplied, totalPrice);
                LOGGER.info("Successfully retrieved booking details for booking ID: " + bookingId);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error while retrieving booking details for booking ID: " + bookingId, e);
            throw new CustomException("Error: " + e.getMessage());
        }
        return booking;
    }

    @Override
    public void cancelBooking(int bookingId) {
        PreparedStatement ps = null;

        try {
            ps = db.prepareStatement(DELETE_QUERY_FOR_CANCEL_BOOK);
            ps.setInt(1, bookingId);
            ps.executeUpdate();
            LOGGER.info("Booking canceled successfully. Booking ID: " + bookingId);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error while canceling booking with ID: " + bookingId, e);
            throw new CustomException("Error: " + e.getMessage());
        }
    }
}
