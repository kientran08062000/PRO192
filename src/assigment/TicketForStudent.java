/*
Author: Tran Nhu Kien
Date: 5/12/2019
This class represents the Ticker for student object extend the Ticket object: add addition discount field
*/

package assigment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TicketForStudent extends Ticket {
    private double discount = 10;

    public TicketForStudent(String code, Date parkingDate, Date pickingUpDate, String name) {
        super(code, parkingDate, pickingUpDate, name);
    }

    public TicketForStudent(String code, Date parkingDate, Date pickingUpDate, String name, double discount) {
        super(code, parkingDate, pickingUpDate, name);
        this.setPrice((int) (this.getPrice() * (100.0 - this.discount) / 100));
        this.discount = discount;
    }

    public TicketForStudent() {
        this.setPrice((int) (this.getPrice() * (100.0 - this.discount) / 100));
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public void input() {
        super.input();
    }

    @Override
    public void output() {
        super.output();
        System.out.println("Discount: " + discount + "%");
    }

    @Override
    public String toString() {
        SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return "Student," + this.getCode() + "," + ft.format(this.getParkingDate()) + "," + ft.format(this.getPickUpDate()) + "," + this.getStaffName() +
                "," + this.discount;
    }
}
