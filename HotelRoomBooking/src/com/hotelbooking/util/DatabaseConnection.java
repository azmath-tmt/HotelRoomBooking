package com.hotelbooking.util;

import com.hotelbooking.exception.CustomException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;

import static com.hotelbooking.daoimpl.RoomDaoImpl.LOGGER;


public class DatabaseConnection {
    public static Connection Connection() {

        String url = "jdbc:mysql://localhost:3306/hotelroom";
        String username = "root";
        String password = "root";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            /* It will create connection in myssql database. */
            return DriverManager.getConnection(url, username, password);
        }
        catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Database connection failed: " + e.getMessage(), e);
            throw new CustomException("Error establishing database connection: " + e.getMessage());
        }
    }
}
