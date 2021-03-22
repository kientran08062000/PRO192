/*
Author: Tran Như Kien
Date: 5/12/2019
This class represents the Menu for this program
*/

package assigment;

import java.util.Scanner;

public class Menu {
    String[] hints;
    int n = 0;

    public Menu(int size) {
        if (size < 1) size = 10;
        hints = new String[size];
    }

    public void add(String aHint) {
        if (n < hints.length) {
            hints[n++] = aHint;
        }
    }

    public int getChoice() {
        int result = 0;
        System.out.println("------------------------------------------------------");
        if (n > 0) {
            for (int i = 0; i < n; i++)
                System.out.format("|%4d - %s\n", (i + 1), hints[i]);
            System.out.println("------------------------------------------------------");
            System.out.print("Please enter an operation: ");
            do {
                Scanner sc = new Scanner(System.in);
                try {
                    result = Integer.parseInt(sc.nextLine());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    result = 0;
                }
                if (result < 1 || result > n)
                    System.out.print("Wrong option, your option must be in [1.." + n + "]. Enter again: ");
            } while (result < 1 || result > n);
        }
        return result;
    }
}