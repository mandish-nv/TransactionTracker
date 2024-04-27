package Project;

import java.util.Scanner;

public class transactiontracker {
  public static void main(String[] args) throws Exception {
    int option;
    char ch;

    Scanner obj = new Scanner(System.in);
    transactionusers tuser = new transactionusers();
    transactiondisplay tdisplay = new transactiondisplay();
    transactionDB tdb = new transactionDB();

    do {
      System.out.print("\033[H\033[2J");
      System.out.flush();

      System.out.println("1. Create new user");
      System.out.println("2. Money transaction");
      System.out.println("3. View transaction table");
      System.out.println("4. View all users");
      System.out.println("5. Exit");

      System.out.print("\nEnter an option: ");
      option = obj.nextInt();
      switch (option) {
        case 1:
          System.out.print("\033[H\033[2J");
          System.out.flush();
          tuser.createUser(tdb);
          ch = (char) System.in.read();
          break;

        case 2:
          System.out.print("\033[H\033[2J");
          System.out.flush();
          tuser.transact(tdb);
          ch = (char) System.in.read();
          break;

        case 3:
          System.out.print("\033[H\033[2J");
          System.out.flush();
          tdisplay.viewTable(tdb);
          ch = (char) System.in.read();
          break;

        case 4:
          System.out.print("\033[H\033[2J");
          System.out.flush();
          tdisplay.viewUsers(tdb);
          ch = (char) System.in.read();
          break;

        case 5:
          System.out.println("\nExiting...");
          ch = (char) System.in.read();
          break;

        default:
          System.out.println("\nInvalid input");
          ch = (char) System.in.read();
          break;
      }
    } while (option != 5);
    obj.close();
  }
}
