
import com.hotelbooking.exception.CustomException;
import java.util.Scanner;
import static com.hotelbooking.service.DiscountService.*;


public class HotelRoomBookingConsoleApplication {

    static Scanner scan = new Scanner(System.in);

    public static void displayMenu() {
        System.out.println("-------- Welcome to Hotel Room Booking System ------");
        System.out.println("1. Book a Room.");
        System.out.println("2. Cancel a Booking.");
        System.out.println("3. Check Room Availability.");
        System.out.println("4. View Available Discounts.");
        System.out.println("5. Exit the System.");
        System.out.print("Choose the option: ");
    }

    public static void main(String[] args) {
        try {
            while (true) {
                displayMenu();
                int choice = scan.nextInt();
                scan.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter which room do u need(basic/standard/premium) : ");
                        String roomTypeName = scan.nextLine();
                        System.out.print("Enter Your Name: ");
                        String customerName = scan.nextLine();
                        System.out.print("Enter your contact details : ");
                        String contactInfo = scan.nextLine();
                        System.out.print("Enter Number of Rooms do u want to book : ");
                        int roomsBooked = scan.nextInt();
                        scan.nextLine();

                        bookingRoom(roomTypeName, customerName,contactInfo,roomsBooked);
                        break;
                    case 2:
                        System.out.print("Enter Booking Id : ");
                        int bookingId = scan.nextInt();
                        cancelBooking(bookingId);
                        break;
                    case 3:
                        roomAvailable();
                        break;
                    case 4:
                        availableDiscount();
                        break;
                    case 5:
                        System.out.println("Thank You!, for choosing our Application....");
                        return;

                    default:
                        System.out.println("Invalid Option: Please try again");
                }
            }

        }
        catch (Exception e) {
            throw new CustomException("Error: " + e.getMessage());
        }
    }
}
