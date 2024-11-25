This project is about Hotel Room Booking System.

In this project there are 4 functional requirements.
1. Book a Room.
2. Cancel a Booking.
3. Check Room Availability.
4. View Available Discounts.
5. Exit the System.


1. Book a Room -> In this category there are five inputs:
1.1 The user will select which type of room he/she wants. There are only 3 rooms available i.e. basic, standard, and premium.
If user select other than this 3 rooms it will throw an error.
1.2 Next input will be Name of the user.
1.3 Next input will be contact details. In this input if user enters less than 10 numbers it will throw an error.
1.4 Next input will be how many rooms user need. If he/she selects more than available rooms then it will throw an error.
1.5 Next input will be discount code. In this there are only 2 available discounts. User need to enter any of one code to eligible for discount and
there will be certain conditions to eligible for this discount code. If user don't want any discount he/she can write skip to skip the discount.
If user enters everything correct, then will it book a room and creates a row in Database.


2. Cancel a Booking -> In this category there are only one input i.e. enter booking id. If booking id is present in
database then it will delete the row if not then it will throw an error -> "Invalid Booking ID. Please try again."

3. Check Room Availability -> In this category it will return everything like how many Room Type available, Total Rooms,
Available Rooms and Price per night.

4. View Available Discounts -> In this category it will return everything like Discount code, Discount value, Valid From,
Valid To, Minimum Rooms required, Festival Discount. 

5. Exit the System -> It will exit the application by giving a message "Thank You!, for choosing our Application...."
