/*
Author: Tran Dang Khoa
Date: 6/12/2019
This class represents the Ticker list:
- Fields: list (Ticket), count (int)
- Methods: addTicket, displayAllTicket, editTicket, deleteTicket, sortTicketByID, sortTickerByPrice, searchTicker, totalMoney and chargeMoney, exportData, importData, quit.
*/

package assigment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class TicketList extends Vector<Ticket> {
    private boolean isSaved = true;
    final int MAX_INT = 5000;


    public TicketList() {
        super();
    }

    int find(String aCode) {
        for (int i = 0; i < this.size(); i++) {
            if (aCode.equals(this.get(i).getCode())) return i;
        }
        return -1;
    }

    // This method allows us to add a new Ticket into the list
    public void addTicket() {
        if (this.size() == MAX_INT) System.out.println("List is full");
        else {
            Scanner sc = new Scanner(System.in);
            String newCode;
            int pos;
            boolean check = true;
            int option = 0;
            System.out.println("Choose ticket's type: ");
            System.out.println("\t1. Student\n\t2. Staff\n\t3. Others");
            System.out.print("Enter your option: ");
            do {
                try {
                    option = Integer.parseInt(sc.nextLine());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    option = 0;
                }
                if (option < 1 || option > 3)
                    System.out.print("Wrong option, your option must be in [1..3]. Enter again: ");
            } while (option < 1 || option > 3);
            do {
                System.out.print(check ? "Enter the ticket's code(FORMAT: XE***): " : "Wrong input format (XE*** - * is digit).\nEnter again: ");
                newCode = sc.nextLine();
                pos = find(newCode);
                if (pos >= 0) {
                    System.out.println("\tThis code existed!");
                } else {
                    String pattern = "^XE\\d{3}$";
                    check = newCode.matches(pattern);
                }
            } while (pos >= 0 || !check);
            switch (option) {
                case 1:
                    this.add(new TicketForStudent());
                    this.lastElement().setCode(newCode);
                    this.lastElement().input();
                    break;
                case 2:
                    this.add(new TicketForStaff());
                    this.lastElement().setCode(newCode);
                    this.lastElement().input();
                    break;
                case 3:
                    this.add(new Ticket());
                    this.lastElement().setCode(newCode);
                    this.lastElement().input();
                    break;
            }
            System.out.println("New ticket have been added");
            isSaved = false;
        }
    }

    // This method allows us to display all Ticket in the list
    public void displayAllTicket() {
        if (this.size() == 0) {
            System.out.println("Empty list.");
            return;
        }
        System.out.println("LIST OF TICKETS:");
        for (int i = 0; i < this.size(); i++) {
            this.get(i).output();
        }
    }

    // This method allows us to edit an existed Ticket
    public void editTicket() {
        Scanner sc = new Scanner(System.in);
        String code;
        boolean check = true;
        do {
            System.out.print(check ? "Enter the ticket's code(FORMAT: XE***): " : "Wrong input format (XE*** - * is digit).\nEnter again: ");
            code = sc.nextLine();
            String pattern = "^XE\\d{3}$";
            check = code.matches(pattern);
        } while (!check);
        int pos = find(code);
        if (pos < 0) System.out.println("\tThis code does not exist!");
        else {
            this.get(pos).input();
            System.out.println("Your ticket has been edited");
        }
        isSaved = false;
    }

    // This method allows us to delete a Ticket in the list
    public void deleteTicket() {
        Scanner sc = new Scanner(System.in);
        String code;
        boolean check = true;
        do {
            System.out.print(check ? "Enter the ticket's code(FORMAT: XE***): " : "Wrong input format (XE*** - * is digit).\nEnter again: ");
            code = sc.nextLine();
            String pattern = "^XE\\d{3}$";
            check = code.matches(pattern);
        } while (!check);
        int pos = find(code);
        if (pos < 0) System.out.println("\tThis code does not exist!");
        else {
            this.remove(pos);
            System.out.println("Your ticket has been deleted");
        }
        isSaved = false;
    }

    // This method is used to sort Ticket by their ID
    public void sortById() {
        Collections.sort(this);
        this.displayAllTicket();
    }

    // This method is used to sort Ticket by their price
    public void sortByPrice() {
        Collections.sort(this, Ticket.compareByPrice);
        this.displayAllTicket();
    }

    // This method is used to search a Ticket in this list
    public void searchTicket() {
        Scanner sc = new Scanner(System.in);
        String code;
        System.out.print("Enter ticket code(FORMAT: XE***): ");
        code = sc.nextLine();
        int pos = find(code);
        if (pos >= 0)
            this.get(pos).output();
        else System.out.println("This ticket is not available in this list.");
    }

    // This method calculate total money in the list
    public void chargeMoney() {
        int total = 0;
        for (int i = 0; i < this.size(); i++) {
            total += this.get(i).getPrice();
        }
        System.out.println("Total money: " + total);
    }

    // This method allows us to export data to a file
    public void exportData() {
        try {
            FileWriter myWriter = new FileWriter("data.sak");
            for (int i = 0; i < this.size(); i++) {
                myWriter.write(this.get(i).toString() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        System.out.println("Successfully wrote to the file.");
        isSaved = true;
    }

    // This method is used to import data from file
    public void importData() {
        SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        try {
            BufferedReader br = new BufferedReader(new FileReader("data.sak"));
            String line;

            while ((line = br.readLine()) != null) {
                String tmp[] = line.split(",");
                switch (tmp[0]) {
                    case "Normal":
                        this.add(new Ticket(tmp[1], ft.parse(tmp[2]), ft.parse(tmp[3]), tmp[4]));
                        break;
                    case "Student":
                        this.add(new TicketForStudent(tmp[1], ft.parse(tmp[2]), ft.parse(tmp[3]), tmp[4], Double.parseDouble(tmp[5])));
                        break;
                    case "Staff":
                        this.add(new TicketForStaff(tmp[1], ft.parse(tmp[2]), ft.parse(tmp[3]), tmp[4], tmp[5]));
                        break;
                }
            }
            System.out.println("Read data from file successful!");
            br.close();
        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
        }
        isSaved = false;
    }

    // This method is used to quit this program
    public void quit() {
        if (!isSaved) {
            Scanner sc = new Scanner(System.in);
            char option;
            do {
                System.out.print("Your list has been modified but not saved yet. Do you want to save your list? (Y/N)? ");
                option = sc.nextLine().charAt(0);
            } while (option != 'Y' && option != 'N');
            if (option == 'Y') exportData();
        }
        System.out.println("Quit!");
    }
}
