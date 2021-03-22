/*
Author: Tran Nhu Kien
Date: 7/12/2019
This class represents the main class to execute this program.
*/
package assigment;
public class ProgTicketTest {
    public static void main(String[] args) {
        Menu menu = new Menu(11);
        menu.add("Add a new ticket");
        menu.add("Display all ticket");
        menu.add("Edit a ticket");
        menu.add("Delete a ticket");
        menu.add("Sort ticket by ID");
        menu.add("Sort ticket by price");
        menu.add("Search a ticket");
        menu.add("Total money");
        menu.add("Import list from file");
        menu.add("Export list to file");
        menu.add("Quit");

        int choice;
        TicketList list = new TicketList();
        do {
            System.out.println("\nTICKET MANAGER");
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    list.addTicket();
                    break;
                case 2:
                    list.displayAllTicket();
                    break;
                case 3:
                    list.editTicket();
                    break;
                case 4:
                    list.deleteTicket();
                    break;
                case 5:
                    list.sortById();
                    break;
                case 6:
                    list.sortByPrice();
                    break;
                case 7:
                    list.searchTicket();
                    break;
                case 8:
                    list.chargeMoney();
                    break;
                case 9:
                    list.importData();
                    break;
                case 10:
                    list.exportData();
                    break;
                case 11:
                    list.quit();
                    break;
            }
        } while (choice >= 1 && choice < 11);
    }
}
