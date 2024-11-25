package com.hotelbooking.model;

import java.math.BigDecimal;
import java.util.Date;

public class Discount {

    private int discountId;
    private int roomTypeId;
    private String discountCode;
    private BigDecimal discountValue;
    private Date validFrom;
    private Date validTo;
    private int minRoomsToQualify;
    private boolean halfRoomsThreshold;
    private boolean festivalDiscount;

    public Discount(int discountId, int roomTypeId, String discountCode, BigDecimal discountValue, Date validFrom, Date validTo, int minRoomsToQualify, boolean halfRoomsThreshold, boolean festivalDiscount) {
        this.discountId = discountId;
        this.roomTypeId = roomTypeId;
        this.discountCode = discountCode;
        this.discountValue = discountValue;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.minRoomsToQualify = minRoomsToQualify;
        this.halfRoomsThreshold = halfRoomsThreshold;
        this.festivalDiscount = festivalDiscount;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public BigDecimal getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(BigDecimal discountValue) {
        this.discountValue = discountValue;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public int getMinRoomsToQualify() {
        return minRoomsToQualify;
    }

    public void setMinRoomsToQualify(int minRoomsToQualify) {
        this.minRoomsToQualify = minRoomsToQualify;
    }

    public boolean isHalfRoomsThreshold() {
        return halfRoomsThreshold;
    }

    public void setHalfRoomsThreshold(boolean halfRoomsThreshold) {
        this.halfRoomsThreshold = halfRoomsThreshold;
    }

    public boolean isFestivalDiscount() {
        return festivalDiscount;
    }

    public void setFestivalDiscount(boolean festivalDiscount) {
        this.festivalDiscount = festivalDiscount;
    }
}
