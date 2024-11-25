package com.hotelbooking.daoimpl;

import com.hotelbooking.dao.DiscountDao;
import com.hotelbooking.exception.CustomException;
import com.hotelbooking.model.Discount;
import com.hotelbooking.util.DatabaseConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.*;

public class DiscountDaoImpl implements DiscountDao {
    private static final Logger LOGGER = Logger.getLogger(DiscountDaoImpl.class.getName());
    Connection db =  DatabaseConnection.Connection();

    final static String SELECT_QUERY_FOR_GET_DISCOUNT = "SELECT * from `discount` where discount_code = ?";
    final static String SELECT_QUERY_FOR_AVAILABLE_DISCOUNT = "SELECT * from `discount`";

    @Override
    public Discount getDiscount(String discountCode) {
        PreparedStatement ps = null;
        Discount discount = null;

        try {
            ps = db.prepareStatement(SELECT_QUERY_FOR_GET_DISCOUNT);
            ps.setString(1, discountCode);

            ResultSet res = ps.executeQuery();

            if (res.next()) {
                int discountId = res.getInt("discount_id");
                int roomTypeId = res.getInt("room_type_id");
                BigDecimal discountValue = res.getBigDecimal("discount_value");
                Date validFrom = res.getDate("valid_from");
                Date validTo = res.getDate("valid_to");
                int minRoomsToQualify = res.getInt("min_rooms_to_qualify");
                boolean halfRoomsThreshold = res.getBoolean("half_rooms_threshold");
                boolean festivalDiscount = res.getBoolean("festival_discount");

                discount = new Discount(discountId, roomTypeId, discountCode, discountValue, validFrom, validTo, minRoomsToQualify, halfRoomsThreshold, festivalDiscount);
                LOGGER.info("Successfully retrieved discount: " + discountCode);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error retrieving discount for code: " + discountCode, e);
            throw new CustomException("Error: " + e.getMessage());
        }
        return discount;
    }

    @Override
    public List<Discount> availableDiscount() {
        PreparedStatement ps = null;
        ResultSet res = null;
        List<Discount> discountList = new ArrayList<>();

        try {
            ps = db.prepareStatement(SELECT_QUERY_FOR_AVAILABLE_DISCOUNT);
            res = ps.executeQuery();

            while (res.next()) {
                int discountId = res.getInt("discount_id");
                int roomTypeId = res.getInt("room_type_id");
                String discountCode = res.getString("discount_code");
                BigDecimal discountValue = res.getBigDecimal("discount_value");
                Date validFrom = res.getDate("valid_from");
                Date validTo = res.getDate("valid_to");
                int minRoomsToQualify = res.getInt("min_rooms_to_qualify");
                boolean halfRoomsThreshold = res.getBoolean("half_rooms_threshold");
                boolean festivalDiscount = res.getBoolean("festival_discount");

                Discount discount = new Discount(discountId, roomTypeId, discountCode, discountValue, validFrom, validTo, minRoomsToQualify, halfRoomsThreshold, festivalDiscount);
                discountList.add(discount);
            }

            LOGGER.info("Successfully retrieved list of available discounts.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error retrieving available discounts", e);
            throw new CustomException("Error: " + e.getMessage());
        }
        return discountList;
    }
}
