package Project;

import java.util.Scanner;

public class transactiontracker {
  public static void clear() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public static void main(String[] args) throws Exception {
    int option;
    char ch;
    Scanner obj = new Scanner(System.in);
    transactionusers tuser = new transactionusers();
    transactiondisplay tdisplay = new transactiondisplay();

    do {
      clear();
      System.out.println("1. Create new user");
      System.out.println("2. Money transaction");
      System.out.println("3. View transaction table");
      System.out.println("4. View all users");
      System.out.println("5. Show transactions of a user");
      System.out.println("6. Exit");

      System.out.print("\nEnter an option: ");
      option = obj.nextInt();
      switch (option) {
        case 1:
          clear();
          tuser.createUser();
          ch = (char) System.in.read();
          break;

        case 2:
          clear();
          tuser.transact();
          ch = (char) System.in.read();
          break;

        case 3:
          clear();
          tdisplay.viewTable();
          ch = (char) System.in.read();
          break;

        case 4:
          clear();
          tdisplay.viewUsers();
          ch = (char) System.in.read();
          break;

        case 5:
          clear();
          tdisplay.viewSingleUser();
          ch = (char) System.in.read();
          break;

        case 6:
          System.out.println("\nExiting...");
          ch = (char) System.in.read();
          break;

        default:
          System.out.println("\nInvalid input");
          ch = (char) System.in.read();
          break;
      }
    } while (option != 6);
    obj.close();
  }
}
