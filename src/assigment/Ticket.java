/*
Author: Tran Dang Khoa
Date: 4/12/2019
This class represents the Ticker object:
- Fields: code (String), parkingDate (Date), pickUpDate (Date), staffName (String), price (int)
- Methods: inputDate, input, output
*/

package assigment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

public class Ticket implements Comparable {
    private String code;
    private Date parkingDate, pickUpDate;
    private String staffName;
    private int price = 5000;

    public Ticket(String code, Date parkingDate, Date pickUpDate, String staffName) {
        this.code = code;
        this.parkingDate = parkingDate;
        this.pickUpDate = pickUpDate;
        this.staffName = staffName;
    }

    public Ticket() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getParkingDate() {
        return parkingDate;
    }

    public void setParkingDate(Date parkingDate) {
        this.parkingDate = parkingDate;
    }

    public Date getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(Date pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    // This method is used to input and validate a new Date
    public Date inputDate() {
        String newDate;
        Date date = null;
        boolean flag = true;
        String pattern = "^(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[0-2])-\\d\\d\\d\\d (00|[0-9]|1[0-9]|2[0-3]|0[0-9]):([0-9]|[0-5][0-9])$";

        SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        ft.setLenient(false);

        do {
            Scanner sc = new Scanner(System.in);
            newDate = sc.nextLine();
            flag = newDate.matches(pattern);
            if (flag) {
                try {
                    date = ft.parse(newDate);
                } catch (ParseException e) {
                    System.out.println(e.getMessage());
                    System.out.print("Enter again: ");
                    flag = false;
                }
            } else {
                flag = false;
                System.out.print("Wrong input format. Enter again: ");
            }
        } while (!flag);
        return date;
    }

    // This method allows us to add new Ticket with information entered from keyboard
    public void input() {
        Scanner sc = new Scanner(System.in);
        String pattern = "^[a-z, A-Z]+";
        do {
            System.out.print("Enter staff's name: ");
            staffName = sc.nextLine();
        } while (!staffName.matches(pattern));
        System.out.println("Date input format: dd-mm-yyyy HH:mm");
        do {
            System.out.print("Enter parking date (must be sooner than now): ");
            parkingDate = inputDate();

        } while (parkingDate.compareTo(new Date()) > 0);
        do {
            System.out.print("Enter pick up date (must be sooner than now and later than parking day): ");
            pickUpDate = inputDate();
        } while (pickUpDate.compareTo(new Date()) > 0 || pickUpDate.compareTo(parkingDate) < 0);
    }

    // This method is used to display all Ticket info
    public void output() {
        SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        System.out.println("------<TICKET INFO>------");
        System.out.println("Ticket code: " + code);
        System.out.println("Staff's name: " + staffName);
        System.out.println("Parking date: " + ft.format(parkingDate));
        System.out.println("Pick up date: " + ft.format(pickUpDate));
        System.out.println("Price: " + price);
    }

    // Override toString method
    @Override
    public String toString() {
        SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return "Normal," + code + "," + ft.format(parkingDate) + "," + ft.format(pickUpDate) + "," + staffName;
    }

    // Override compareTo method (default compare by code)
    @Override
    public int compareTo(Object o) {
        return -this.code.compareTo(((Ticket) o).getCode());
    }

    // Used for comparing by price
    public static Comparator compareByPrice = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            Ticket ticket1 = (Ticket) o1;
            Ticket ticket2 = (Ticket) o2;

            int d = ticket1.getPrice() - ticket2.getPrice();
            if (d > 0) return -1;
            if (d == 0) return ticket1.code.compareTo(ticket2.code);
            return 1;
        }
    };
}
