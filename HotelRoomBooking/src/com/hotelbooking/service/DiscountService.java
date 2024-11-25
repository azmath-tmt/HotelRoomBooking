package com.hotelbooking.service;
import com.hotelbooking.daoimpl.BookingDaoImp;
import com.hotelbooking.daoimpl.DiscountDaoImpl;
import com.hotelbooking.daoimpl.RoomDaoImpl;
import com.hotelbooking.exception.CustomException;
import com.hotelbooking.model.Booking;
import com.hotelbooking.model.Discount;
import com.hotelbooking.model.Rooms;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;


public class DiscountService {
    static Scanner scan = new Scanner(System.in);
    static RoomDaoImpl rdi = new RoomDaoImpl();
    static DiscountDaoImpl ddi = new DiscountDaoImpl();
    static BookingDaoImp rbdi = new BookingDaoImp();


    public static void bookingRoom(String roomTypeName, String customerName, String contactInfo, int roomsBooked)  {

        /* With this we get everything available in Rooms by entering roomType. */
        Rooms rooms = rdi.getRoomsByType(roomTypeName);

        /* It will check the user enters the room type as per requirements or not and it also checks roomsbooked lessthan available rooms. */
        if (roomTypeName.equalsIgnoreCase("basic") || roomTypeName.equalsIgnoreCase("standard") || roomTypeName.equalsIgnoreCase("premium")
                && roomsBooked <= rooms.getAvailableRooms()) {
        } else {
            System.out.println("Invalid Room Type or Number of Rooms is greater than Available rooms . Please Try Again...");
            return;
        }


        /* If roomsBooked greater than and contact number is lessthan 10 will throw an error. */

        if (roomsBooked > rooms.getAvailableRooms() || contactInfo.length() < 10) {
            System.out.println("Contact Number should be 10 Numbers or Insufficient rooms available for Room Type : " + roomTypeName.toUpperCase() + "    " + " Rooms Available: " + rooms.getAvailableRooms());
            return;
        }

        BigDecimal discountValue = BigDecimal.ZERO;

        while (true) {
            System.out.print("Enter Discount Code or you can write skip for no discount: ");
            /* Takes input from user for discount. */
            String discountCode = scan.nextLine();

            /* To get total price without discount. */
            BigDecimal totalPriceWithoutDiscountCode = rooms.getPricePerNight().multiply(BigDecimal.valueOf(roomsBooked));

            /* If user writes skip then it will stop the application. */
            if(discountCode.equalsIgnoreCase("skip")){
                /* Room Booked. */
                rbdi.bookingRoom(rooms.getRoomsTypeId(), customerName, contactInfo, roomsBooked, discountValue, totalPriceWithoutDiscountCode);
                /* Updates Available Rooms. */
                rdi.updateAvailableRooms(rooms.getRoomsTypeId(), rooms.getAvailableRooms() - roomsBooked);
                System.out.println("Hurray!!!" + " " + customerName + " your room booked successfully... " + "Discount Applied : " + discountValue + "%" + " "
                        + "Total Price: " + totalPriceWithoutDiscountCode);
                System.out.println();
                break;
            }

            Discount discount = ddi.getDiscount(discountCode);

            /* If Discount is not null. */
            if (discount != null) {
                if (roomsBooked >= discount.getMinRoomsToQualify() || discount.isFestivalDiscount()) {
                    discountValue = discount.getDiscountValue();
                    System.out.println("Discount Applied Successfully.." + "Discount Value : " + discountValue + "%");
                }
                else {
                    System.out.println("Your are not eligible for Discount code....");
                }
            }
            else {
                System.out.println("Invalid Discount Code....");
                continue;
            }

            /* After applying discount code. */
            BigDecimal totalPrice = totalPriceWithoutDiscountCode.multiply(discountValue).divide(BigDecimal.valueOf(100)).subtract(totalPriceWithoutDiscountCode).abs();

            rbdi.bookingRoom(rooms.getRoomsTypeId(), customerName, contactInfo, roomsBooked, discountValue, totalPrice);
            rdi.updateAvailableRooms(rooms.getRoomsTypeId(), rooms.getAvailableRooms() - roomsBooked);
            System.out.println("Hurray!!!" + " " + customerName + " your room booked successfully... " + "Discount Applied : " + discountValue + "%" + " "
                    + "Total Price: " + totalPrice);

            break;
        }
    }

    public static void cancelBooking(int bookingId) {
        try {
            /* It gives everything available in booking by entering bookingId. */
            Booking booking = rbdi. getBookingDetails(bookingId);

            /* If booking is null. */
            if (booking == null) {
                System.out.println("Error: Invalid Booking ID exists in the system...");
                return;
            }
            /* It deletes the row in database if booking id available in database. */
            rbdi.cancelBooking(bookingId);
            /* Get everything about Rooms. */
            Rooms rooms = rdi.getRoomsById(booking.getRoomTypeId());

            /* If room is not null. */
            if (rooms != null) {
                int updatedAvailableRooms = rooms.getAvailableRooms() + booking.getRoomsBooked();
//                Updates Available Rooms.
                rdi.updateAvailableRooms(booking.getRoomTypeId(), updatedAvailableRooms);
                System.out.println("Room availability updated successfully after booking cancellation.");
            }
            else {
                System.out.println("Error: Room data not found for Room Type ID " + booking.getRoomTypeId());
                return;
            }
            System.out.println("Booking Cancelled Successfully....");

        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            throw new CustomException("Error: " + e.getMessage());
        }
    }

    public static void roomAvailable() {
        /* Returns everything available in Rooms table. */
        List<Rooms> rooms = rdi.roomAvailable();

        System.out.println("List of room types with details: Room type | Total Rooms | Available Rooms | Price Per Night");

        /* Checks whether the rooms are empty or not. */
        if (rooms.isEmpty()) {
            System.out.println("No rooms available at the moment...");
        }
        rooms.stream().map(val -> "Room Type: " + val.getRoomTypeName() + "     " + "Total Rooms: " + val.getTotalRooms() + "    "
                        + "Available Rooms: " + val.getAvailableRooms() + "    " + "Price Per Night: " + val.getPricePerNight())
                .forEach(System.out::println);
    }

    public static void availableDiscount() {
        /* Returns everything available in Discount table. */
        List<Discount> discount = ddi.availableDiscount();
        System.out.println("List of available discounts with details: Discount Code | Discount Value | Valid From | Valid To | Minimum Rooms Required | Festival Discount");

        /* Checks whether the discount is empty or not. */
        if (discount.isEmpty()) {
            System.out.println("No discounts available at the moment...");
        }
        discount.stream().map(val -> "Discount Code: " + val.getDiscountCode() + "   " + "Discount Value: " + val.getDiscountValue() + "   " +
                        "Valid From: " + val.getValidFrom() + "   " + "Valid To: " + val.getValidTo()
                        + "   " + "Minimum Rooms Required: " + val.getMinRoomsToQualify() + "   " + "Festival Discount: " + val.isFestivalDiscount())
                .forEach(System.out::println);

    }

}
