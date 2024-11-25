package com.hotelbooking.daoimpl;

import com.hotelbooking.dao.RoomDao;
import com.hotelbooking.exception.CustomException;
import com.hotelbooking.model.Rooms;
import com.hotelbooking.util.DatabaseConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

public class RoomDaoImpl implements RoomDao {
    public static final Logger LOGGER = Logger.getLogger(RoomDaoImpl.class.getName());
     Connection db =  DatabaseConnection.Connection();

    final static String SELECT_QUERY_FOR_GET_ROOMS_BY_TYPE = "SELECT * from `rooms` where `room_type_name` = ?";
    final static String SELECT_QUERY_FOR_GET_ROOMS_BY_ID = "SELECT * from `rooms` where `room_type_id` = ?";
    final static String UPDATE_QUERY_FOR_AVAILABLE_ROOMS = "UPDATE `rooms` SET `available_rooms` = ? where `room_type_id` = ?";
    final static String SELECT_QUERY_FOR_ROOM_AVAILABLE = "Select * from `rooms`";

    @Override
    public Rooms getRoomsByType(String roomTypeName) {
        PreparedStatement ps = null;
        ResultSet res = null;
        Rooms rooms = null;

        try {
           ps = db.prepareStatement(SELECT_QUERY_FOR_GET_ROOMS_BY_TYPE);
            ps.setString(1, roomTypeName);
            res = ps.executeQuery();

            if (res.next()) {
                int roomTypeId = res.getInt("room_type_id");
                int totalRooms = res.getInt("total_rooms");
                int availableRooms = res.getInt("available_rooms");
                BigDecimal pricePerNight = res.getBigDecimal("price_per_night");

                rooms = new Rooms(roomTypeId, roomTypeName, totalRooms, availableRooms, pricePerNight);
                LOGGER.info("Successfully retrieved room by type: " + roomTypeName);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error retrieving room by type: " + roomTypeName, e);
            throw new CustomException("Error : " + e.getMessage());
        }
        return rooms;
    }

    @Override
    public Rooms getRoomsById(int roomId) {
        PreparedStatement ps = null;
        ResultSet res = null;
        Rooms rooms = null;

        try {
            ps = db.prepareStatement(SELECT_QUERY_FOR_GET_ROOMS_BY_ID);
            ps.setInt(1, roomId);
            res = ps.executeQuery();

            if (res.next()) {
                String roomTypeName = res.getString("room_type_name");
                int totalRooms = res.getInt("total_rooms");
                int availableRooms = res.getInt("available_rooms");
                BigDecimal pricePerNight = res.getBigDecimal("price_per_night");

                rooms = new Rooms(roomId, roomTypeName, totalRooms, availableRooms, pricePerNight);
                LOGGER.info("Successfully retrieved room by ID: " + roomId);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error retrieving room by ID: " + roomId, e);
            throw new CustomException("Error : " + e.getMessage());
        }
        return rooms;
    }

    @Override
    public void updateAvailableRooms(int roomsTypeId, int availableRooms) {
        PreparedStatement ps = null;

        try {
            ps = db.prepareStatement(UPDATE_QUERY_FOR_AVAILABLE_ROOMS);
            ps.setInt(1, availableRooms);
            ps.setInt(2, roomsTypeId);
            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                LOGGER.info("Successfully updated available rooms for type ID: " + roomsTypeId);
            } else {
                LOGGER.warning("No rows updated for room type ID: " + roomsTypeId);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error updating available rooms for type ID: " + roomsTypeId, e);
            throw new CustomException("Error : " + e.getMessage());
        }
    }

    @Override
    public List<Rooms> roomAvailable() {
        PreparedStatement ps = null;
        Rooms rooms = null;
        List<Rooms> roomsList = new ArrayList<>();

        try {
            ps = db.prepareStatement(SELECT_QUERY_FOR_ROOM_AVAILABLE);
            ResultSet res = ps.executeQuery();

            while (res.next()) {
                int roomTypeId = res.getInt("room_type_id");
                String roomTypeName = res.getString("room_type_name");
                int totalRooms = res.getInt("total_rooms");
                int availableRooms = res.getInt("available_rooms");
                BigDecimal pricePerNight = res.getBigDecimal("price_per_night");

                rooms = new Rooms(roomTypeId, roomTypeName, totalRooms, availableRooms, pricePerNight);
                roomsList.add(rooms);
            }

            LOGGER.info("Successfully retrieved list of available rooms.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error retrieving available rooms", e);
            throw new CustomException("Error : " + e.getMessage());
        }
        return roomsList;
    }
}
