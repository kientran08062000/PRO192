/*
Author: Tran Dang Khoa
Date: 5/12/2019
This class represents the Ticker for staff object extend the Ticket object: add staff's ID and price = 0
*/

package assigment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TicketForStaff extends Ticket {
    private String staffID;

    public TicketForStaff(String code, Date parkingDate, Date pickUpDate, String name, String staffID) {
        super(code, parkingDate, pickUpDate, name);
        this.staffID = staffID;
        this.setPrice(0);
    }

    public TicketForStaff() {
        this.setPrice(0);
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    // Override input method: add staff's ID input
    @Override
    public void input() {
        super.input();
        boolean check = true;
        Scanner sc = new Scanner(System.in);
        String pattern = "^NV\\d{3}$";
        do {
            System.out.print(check ? "Enter staff's ID (NV*** - * is digit): " : "Wrong input format (NV*** - * is digit).\nEnter again: ");
            staffID = sc.nextLine();
            check = staffID.matches(pattern);
        } while (!check);
    }

    // Override output method: add staff's ID output
    @Override
    public void output() {
        super.output();
        System.out.println("Staff's ID: " + staffID);
    }

    // Override toString method: add staff's ID
    @Override
    public String toString() {
        SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return "Staff," + this.getCode() + "," + ft.format(this.getParkingDate()) + "," + ft.format(this.getPickUpDate()) + "," + this.getStaffName() + "," +
                this.staffID;
    }
}